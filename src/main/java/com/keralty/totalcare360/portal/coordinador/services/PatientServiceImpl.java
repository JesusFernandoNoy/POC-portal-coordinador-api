package com.keralty.totalcare360.portal.coordinador.services;

import com.keralty.totalcare360.portal.coordinador.models.Age;
import com.keralty.totalcare360.portal.coordinador.models.entities.Patient;
import com.keralty.totalcare360.portal.coordinador.repository.PatientRepository;
import com.keralty.totalcare360.portal.coordinador.utility.AgeCalculator;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.time.LocalDate;

@Singleton
public class PatientServiceImpl implements IPatientService{

    @Inject
    PatientRepository patientRepository;

    public Patient getPatientInfoUtility(Patient patient){
        Age edad = AgeCalculator.calculateAge(patient.getBirthDay());
        patient.setAge(edad.toString());

        patient.setFullNameLastName(patient.getName() + " " + patient.getLastName());

        if (patient.getGender().equals("Male")){
            patient.setGender("Masculino");
        } else if (patient.getGender().equals("Female")) {
            patient.setGender("Femenino");
        }

        if(patient.getDocumentType() == 1){
            patient.setDocumentTypeName("Cédula de ciudadanía");

        } else if (patient.getDocumentType() == 2) {
            patient.setDocumentTypeName("Tarjeta de Identidad");
        }

        //Day of week and month in Spanish
        Calendar cal = Calendar.getInstance();
        cal.setTime(patient.getBirthDay());
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        LocalDate localDate=LocalDate.of(year,month,day);
        Locale spanishLocale=new Locale("es", "ES");
        String dateInSpanish=localDate.format(DateTimeFormatter.ofPattern("dd MMMM, yyyy",spanishLocale));

        patient.setBirthDayFormatter(dateInSpanish);

        return patient;
    }


    @Override
    public List<Patient> findAll() {
        List<Patient> listPatients = patientRepository.listAll();

        for (Patient p: listPatients){
            p = getPatientInfoUtility(p);
        }
        return listPatients;
    }

    @Override
    public Optional<Patient> findByPatientId(Long patientId) {
        Optional<Patient> o = Optional.ofNullable(patientRepository.findById(patientId));

        if(o.isPresent()) {
            Patient p = o.get();
            p = getPatientInfoUtility(p);
            return  Optional.of(p);
        }
        return Optional.empty();
    }
}
