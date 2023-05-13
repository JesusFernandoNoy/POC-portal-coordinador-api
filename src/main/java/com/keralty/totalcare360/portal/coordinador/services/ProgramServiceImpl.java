package com.keralty.totalcare360.portal.coordinador.services;

import com.keralty.totalcare360.portal.coordinador.exception.CarePlanCaseNotFoundException;
import com.keralty.totalcare360.portal.coordinador.models.entities.*;
import org.hl7.fhir.r4.model.*;


import javax.inject.Inject;
import javax.inject.Singleton;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class ProgramServiceImpl implements IProgramService{


    @Inject
    IPatientService patientService;

    @Inject
    IPatientManagementService patientManagementService;

    @Inject
    ICarePlanCaseService carePlanCaseService;

    @Inject
    IActivityCaseChildService activityCaseChildService;


    @Override
    public String saveCase(Bundle bundle) {

        System.out.println(bundle.getEntry().size());
        CarePlan carePlan = null;
        List <Appointment> listAppointment = new ArrayList<>();
        Patient patient = null;
        Observation observation = null;
        Coverage coverage = null;
        List <Location> listLocation = new ArrayList<>();

        for (int i = 0; i < bundle.getEntry().size(); i++){
            if(bundle.getEntry().get(i).getResource().getResourceType().name().equals("CarePlan")){
                carePlan =  (CarePlan) bundle.getEntry().get(i).getResource();
            } else if (bundle.getEntry().get(i).getResource().getResourceType().name().equals("Appointment")) {
                listAppointment.add((Appointment) bundle.getEntry().get(i).getResource());
            } else if (bundle.getEntry().get(i).getResource().getResourceType().name().equals("Patient")) {
                patient = (Patient) bundle.getEntry().get(i).getResource();
            } else if (bundle.getEntry().get(i).getResource().getResourceType().name().equals("Observation")) {
                observation = (Observation) bundle.getEntry().get(i).getResource();
            } else if (bundle.getEntry().get(i).getResource().getResourceType().name().equals("Coverage")) {
                coverage = (Coverage) bundle.getEntry().get(i).getResource();
            } else if (bundle.getEntry().get(i).getResource().getResourceType().name().equals("Location")) {
                listLocation.add((Location) bundle.getEntry().get(i).getResource());
            }
        }
        System.out.println("finalización del cargue de datos en los objetos HAPI");

        CarePlanCase carePlanCase = new CarePlanCase();
        ActivityCaseChild activityCaseChild = null;
        PatientTotalCare paciente = new PatientTotalCare();
        PatientManagement registroCaso = new PatientManagement();

        String idFhirCarePlan = "";
        String carePlanName = "";

        if(carePlan != null){
            System.out.println("INFORMACIÓN DEL CAREPLAN");
            System.out.println("tipo de carePlan = "+ carePlan.getIntent());
            System.out.println("ID = "+ carePlan.getId());
            System.out.println("IdPlanDefinition = "+ carePlan.getBasedOn().get(0).getReference());
            System.out.println("Descripción = "+ carePlan.getDescription());
            System.out.println("paciente ID= "+ carePlan.getSubject().getReference());
            System.out.println("paciente Name= "+ carePlan.getSubject().getDisplay());
            System.out.println("Note = "+ carePlan.getNote().get(0).getText());
            System.out.println("Status = "+ carePlan.getStatus());


            carePlanName = carePlan.getDescription();

            String[] arrOfPatientId = carePlan.getSubject().getReference().split("/", 2);
            String patientIdFhir = arrOfPatientId[1];

            String[] arrOfStr = carePlan.getNote().get(0).getText().split(";", 2);
            String[] riesgoArrOfStr = arrOfStr[0].split("=");
            String riesgo = riesgoArrOfStr[1].toString().replace("'","");

            String[] rankArrOfStr = arrOfStr[1].split("=");
            String rank =  rankArrOfStr[1].toString().replace("'","");

            //risk_factor='Riesgo IV a'
            registroCaso.setPriority(riesgo);
            registroCaso.setRute(carePlan.getDescription());

            System.out.println("INFORMACIÓN DE ACTIVIDADES = "+ carePlan.getActivity().size());

            List<CarePlanActivity> listActivities = new ArrayList<>();
            for (int i = 0; i < carePlan.getActivity().size(); i++ ){
                int contador = i+1;
                System.out.println("Actividad " + contador +" - "+ carePlan.getActivity().get(i).getReference().getReference());

                String[] arrOfActivityId = carePlan.getActivity().get(i).getReference().getReference().split("/", 2);
                String activityCode = arrOfActivityId[1];

                CarePlanActivity carePanActivity = new CarePlanActivity();
                carePanActivity.setActivityFhirId(activityCode);
                listActivities.add(carePanActivity);
            }

            /*
            Inicialización de los atributos de carePlanCase
             */
            carePlanCase.setCarePlanType(carePlan.getIntent().toString());
            /*
            Just get the carePlan FHIR id
             */
            String[] arrOfCarePlanFhirId = carePlan.getId().split("/",2);
            String carePlanFhirId = arrOfCarePlanFhirId[1];
            carePlanCase.setCarePlanFhirId(carePlanFhirId);
            idFhirCarePlan = carePlanFhirId;

            /*
            Just get the plan definition code
             */
            String[] arrOfPlanDef = carePlan.getBasedOn().get(0).getReference().split("/",2);
            String planDefCode = arrOfPlanDef[1];
            carePlanCase.setPlanDefinitionId(planDefCode);

            carePlanCase.setProgramName(carePlan.getDescription());
            carePlanCase.setPatientRisk(riesgo);
            carePlanCase.setPatientRank(Long.parseLong(rank));
            carePlanCase.setCarePlanStatus(carePlan.getStatus().toString());
            carePlanCase.setPatientId(patientIdFhir);
            carePlanCase.setPatientName(carePlan.getSubject().getDisplay());
            carePlanCase.setAssignedActivities(carePlan.getActivity().size());
            carePlanCase.setListCodeActivities(listActivities);

            carePlanCaseService.saveCarePlanCase(carePlanCase);

            System.out.println("La información del CarePlanCase se ha guardado exitosamente ");
        }
        System.out.println(" ");
        System.out.println(" ");
        int cont = 1;
        if(listAppointment.size() > 0 ){
            registroCaso.setPendingActivity(listAppointment.size());
            for (Appointment appointment:listAppointment) {
                System.out.println("Información de la Actividad "+cont);
                System.out.println("ID = " +appointment.getId());
                System.out.println("participant actor display name = "+appointment.getParticipant().get(0).getActor().getDisplay());
                System.out.println("participant actor reference = "+ appointment.getParticipant().get(0).getActor().getReference());
                System.out.println("Speciality code = "+appointment.getSpecialty().get(0).getCoding().get(0).getCode());
                System.out.println("Speciality name = "+appointment.getSpecialty().get(0).getCoding().get(0).getDisplay());
                System.out.println("Appointment Status = "+appointment.getStatus());
                cont = cont + 1;
                System.out.println(" ");

                String[] arrOfPatientIdReference = appointment.getParticipant().get(0).getActor().getReference().split("/", 2);
                String patientIdFhirReference = arrOfPatientIdReference[1];

                /*
                Inicialización atributos de activityCaseChild
                 */
                activityCaseChild = new ActivityCaseChild();

                /*
                Just get the activity fhir id
                 */
                String[] arrOfActivityFhirId = appointment.getId().split("/");
                String activityFhirId = arrOfActivityFhirId[1];
                activityCaseChild.setActivityFhirId(activityFhirId);

                activityCaseChild.setCarePlanFhirId(idFhirCarePlan);
                activityCaseChild.setCarePlanName(carePlanName);
                activityCaseChild.setPatientFhirId(patientIdFhirReference);
                activityCaseChild.setSpecialityCode(appointment.getSpecialty().get(0).getCoding().get(0).getCode());
                activityCaseChild.setSpecialityName(appointment.getSpecialty().get(0).getCoding().get(0).getDisplay());
                activityCaseChild.setActivityStatus(appointment.getStatus().toString());

                activityCaseChild.setCarePlanCaseId(carePlanCase.getId());

                activityCaseChildService.saveActivityCaseChild(activityCaseChild);
            }
        }

        if(patient != null){
            System.out.println("Información del Paciente");

            /*
            Just get the patient fhir id
             */
            String[] arrOfpatientFhirId = patient.getId().split("/");
            String patientFhirId = arrOfpatientFhirId[1];
            System.out.println("ID = "+patientFhirId);
            paciente.setFhirIdPatient(patientFhirId);

            System.out.println("Genero = " +patient.getGender());
            paciente.setGender(patient.getGender().toString().toLowerCase());

            System.out.println("Fecha de nacimiento = "+patient.getBirthDate());
            java.sql.Date sqlDate = new java.sql.Date(patient.getBirthDate().getTime());
            paciente.setBirthDay(sqlDate);

            System.out.println("NOMBRE = "+patient.getName().get(0).getGiven());
            System.out.println("APELLIDO = "+patient.getName().get(0).getFamily());
            System.out.println("Nombre Completo = "+patient.getName().get(0).getText());
            String patientName = "";
            if (patient.getName().get(0).getGiven().size() > 0 ){
               for (int i = 0; i < patient.getName().get(0).getGiven().size(); i++){
                   patientName = patientName + patient.getName().get(0).getGiven().get(i).toString();
                   if (i == 0){
                       patientName = patientName + " ";
                   }

               }
            }
            paciente.setName(patientName);
            paciente.setLastName(patient.getName().get(0).getFamily());


            System.out.println("IDENTIFICACION");
            System.out.println("Codigo identificacion = "+patient.getIdentifier().get(0).getType().getCoding().get(0).getCode());
            System.out.println("nombre identificacion = "+patient.getIdentifier().get(0).getType().getCoding().get(0).getDisplay());
            System.out.println("Numero de identificacion = "+patient.getIdentifier().get(0).getValue());

            String typeIdentificationStr = patient.getIdentifier().get(0).getType().getCoding().get(0).getCode();

            paciente.setDocumentTypeDescription(patient.getIdentifier().get(0).getType().getCoding().get(0).getDisplay());
            paciente.setDocumentType(typeIdentificationStr);
            paciente.setDocumentNumber(patient.getIdentifier().get(0).getValue());

            System.out.println("DIRECCION");
            System.out.println("Pais = "+patient.getAddress().get(0).getCountry());
            System.out.println("Departamento = "+patient.getAddress().get(0).getState());
            System.out.println("Ciudad = "+patient.getAddress().get(0).getCity());
            System.out.println("info direccion = "+patient.getAddress().get(0).getLine());
            System.out.println("Codigo postal = "+patient.getAddress().get(0).getPostalCode());

            paciente.setCity(patient.getAddress().get(0).getCity());

            System.out.println("Lenguaje de comunicacion = "+patient.getCommunication().get(0).getLanguage().getText());

            System.out.println("Información marital del paciente");
            System.out.println("Codigo = "+patient.getMaritalStatus().getCoding().get(0).getCode());
            System.out.println("descripcion = "+patient.getMaritalStatus().getCoding().get(0).getDisplay());
            System.out.println("Texto = "+patient.getMaritalStatus().getText());
            paciente.setMaritalStatus(patient.getMaritalStatus().getText());

            System.out.println("Información de contacto ");
            System.out.println("El paciente cuenta con " +  patient.getTelecom().size() + " metodos de contacto");

            PatientContact patientContact = null;
            List<PatientContact> listPatientContact = new ArrayList<>();
            for (ContactPoint contactPoint: patient.getTelecom()) {
                System.out.println("consecutivo = "+contactPoint.getRank());
                System.out.println("sistema = "+contactPoint.getSystem());
                System.out.println("uso = "+contactPoint.getUse());
                System.out.println("valor = "+contactPoint.getValue());
                System.out.println(" ");

                if(contactPoint.getSystem().toString().equals("PHONE") && contactPoint.getUse().toString().equals("MOBILE")){
                    paciente.setPhone(contactPoint.getValue());
                }

                if(contactPoint.getSystem().toString().equals("EMAIL")){
                    paciente.setEmail(contactPoint.getValue());
                }

                patientContact = new PatientContact();

                if(contactPoint.getSystem() != null){
                    patientContact.setSystem(contactPoint.getSystem().toString());
                }else{
                    patientContact.setSystem("");
                }

                if(contactPoint.getUse() != null){
                    patientContact.setUse(contactPoint.getUse().toString());
                }else {
                    patientContact.setUse("");
                }

                if(contactPoint.getValue() != null){
                    patientContact.setValue(contactPoint.getValue());
                }else {
                    patientContact.setValue("");
                }
                listPatientContact.add(patientContact);
            }
            paciente.setListPatientContact(listPatientContact);
        }

        patientService.savePatient(paciente);
        System.out.println("La información del paciente se ha guardado exitosamente ");

        String documentTypePatient = paciente.getDocumentType();
        String documentNumberPatient = paciente.getDocumentNumber();

        Optional<PatientTotalCare> findPaciente = patientService.findByDocumentNumber(documentTypePatient,documentNumberPatient);
        if(findPaciente.isPresent()){
            registroCaso.setPatient_id(findPaciente.get().getId());
        }

        String identificationCritera ="";
        if(observation != null && !observation.getCode().getText().equals("none")){
            System.out.println("Información de la Observacion ");
            System.out.println("ID =  "+observation.getId());
            System.out.println("categoria =  "+observation.getCategory().get(0).getCoding().get(0).getCode());
            System.out.println("codigo =  "+observation.getCode().getCoding().get(0).getCode());
            System.out.println("nombre observacion =  "+observation.getCode().getCoding().get(0).getDisplay());
            System.out.println("fecha=  "+observation.getIssued());
            System.out.println("Resultado =  "+observation.getValueQuantity().getValue());
            System.out.println(" ");

            String mdy="";
            try {
                SimpleDateFormat mdyFormat = new SimpleDateFormat("dd/MM/yyyy");
                // Format the date to Strings
                mdy = mdyFormat.format(observation.getIssued());

            } catch (Exception exp) {
                exp.printStackTrace();
            }

            identificationCritera = "{category = "+observation.getCategory().get(0).getCoding().get(0).getCode();
            identificationCritera = identificationCritera +", code = "+observation.getCode().getCoding().get(0).getCode();
            identificationCritera = identificationCritera +", name = "+ observation.getCode().getCoding().get(0).getDisplay();
            identificationCritera = identificationCritera +", result = "+observation.getValueQuantity().getValue();
            identificationCritera = identificationCritera +", dateIssued = "+mdy;
            identificationCritera = identificationCritera +"}";


            carePlanCase.setIdentificationCriteria(identificationCritera);

            int hemoglobinaResult = Integer.parseInt(""+observation.getValueQuantity().getValue());
            registroCaso.setHemoglobina(hemoglobinaResult);

            if(observation.getCode().getCoding().get(0).getDisplay().equals("Hemoglobina Glicosilada")){
                registroCaso.setTestDiagnostic("SÍ");
            }
        }else{
            identificationCritera = "{No Information}";
            carePlanCase.setIdentificationCriteria(identificationCritera);
            registroCaso.setTestDiagnostic("NO");
            System.out.println("El paciente no tiene información de Hemoglobina Glicosilada");
            System.out.println(" ");
        }

        if(coverage != null){
            System.out.println("Informacion covertura ");
            System.out.println("ID "+coverage.getId());
            System.out.println("Network "+coverage.getNetwork());
            System.out.println(" ");
            carePlanCase.setCoverageNetwork(coverage.getNetwork());
        }

        String LocationProgramSite ="";
        String LocationServiceProviderCode ="";
        String LocationServiceProviderName ="";

        if (listLocation.size() > 0 ){
            System.out.println("Informacion de localización  ");
            for (Location location :listLocation) {
                System.out.println("ID "+location.getId());
                System.out.println("descripción "+location.getDescription());
                System.out.println("Nombre "+location.getName());
                System.out.println(" ");

                if(location.getDescription() != null && location.getDescription().toString().equals("Sede Programa")){
                    LocationProgramSite = location.getName();
                    carePlanCase.setLocationProgramSite(LocationProgramSite);
                }

                if(location.getIdentifier() != null && location.getIdentifier().size()>0){
                    for(int i = 0; i < location.getIdentifier().size(); i++)
                    if(location.getIdentifier().get(i).getType().getCoding().get(0).getCode().equals("SUCURSAL")){
                        System.out.println("Sucursal =  "+location.getIdentifier().get(i).getValue());
                        LocationServiceProviderCode = location.getIdentifier().get(i).getValue();
                        LocationServiceProviderName = location.getName();

                        carePlanCase.setLocationServiceProviderCode(LocationServiceProviderCode);
                        carePlanCase.setLocationServiceProviderName(LocationServiceProviderName);
                    }
                }
            }
        }

        try {
            carePlanCaseService.updateCarePlanCase(carePlanCase.getId(),carePlanCase);
        } catch (CarePlanCaseNotFoundException e) {
            //throw new RuntimeException(e);
            System.out.println("Ocurrio un error no se pudo actualizar el registro de carePlanCase con el criterio de identificación");
        }

        patientManagementService.savePatientManagement(registroCaso);
        System.out.println("La información del caso se ha guardado exitosamente ");

        return "200";
    }
}
