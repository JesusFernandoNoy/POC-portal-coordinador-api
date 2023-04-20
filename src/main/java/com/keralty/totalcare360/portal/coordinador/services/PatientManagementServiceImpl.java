package com.keralty.totalcare360.portal.coordinador.services;

import com.keralty.totalcare360.portal.coordinador.models.entities.Patient;
import com.keralty.totalcare360.portal.coordinador.models.entities.PatientManagement;
import com.keralty.totalcare360.portal.coordinador.repository.PatientManagementRepository;


import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.WebApplicationException;
import java.util.List;
import java.util.Optional;

@Singleton
public class PatientManagementServiceImpl implements IPatientManagementService{

    @Inject
    IPatientService patientService;

    @Inject
    PatientManagementRepository patientManagementRepository;

    public PatientManagement getPatientInformation(Patient patient, PatientManagement pm){
        pm.setName(patient.getFullNameLastName());
        pm.setIdType(patient.getDocumentTypeName());
        pm.setNumId(patient.getDocumentNumber().toString());
        pm.setAge(patient.getAge());
        return pm;
    }

    @Override
    public List<PatientManagement> findAll() {
        List<PatientManagement> listPatientManagement = (List<PatientManagement>) patientManagementRepository.listAll();

        for (PatientManagement pm: listPatientManagement){
            Long patientId = pm.getPatient_id();
            Optional<Patient> patientObjet = patientService.findByPatientId(patientId);
            if(patientObjet.isPresent()){
                Patient patient = patientObjet.get();
                pm = getPatientInformation(patient,pm);
            }else{
                throw new WebApplicationException("Patient with ID " + patientId + " does not exist.", 404);
            }
        }
        return listPatientManagement;
    }

    @Override
    public Optional<PatientManagement> findByPatientId(Long patientId) {

        Optional<PatientManagement> o = Optional.ofNullable(patientManagementRepository.findByPatientId(patientId));

        if(o.isPresent()) {
            PatientManagement pm = o.get();
            Long pId= pm.getPatient_id();
            Optional<Patient> patientObj = patientService.findByPatientId(pId);
            if(patientObj.isPresent()){
                Patient patient = patientObj.get();
                pm = getPatientInformation(patient,pm);
            }else{
                throw new WebApplicationException("Patient with ID " + patientId + " does not exist.", 404);
            }
            return  Optional.of(pm);
        }
        return Optional.empty();
    }
}
