package com.keralty.totalcare360.portal.coordinador.services;

import com.keralty.totalcare360.portal.coordinador.models.Age;
import com.keralty.totalcare360.portal.coordinador.models.entities.PatientTotalCare;
import com.keralty.totalcare360.portal.coordinador.repository.PatientRepository;
import com.keralty.totalcare360.portal.coordinador.utility.AgeCalculator;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
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

    public PatientTotalCare getPatientInfoUtility(PatientTotalCare patientTotalCare){
        Age edad = AgeCalculator.calculateAge(patientTotalCare.getBirthDay());
        patientTotalCare.setAge(edad.toString());

        if (edad.getYears() >= 18){
            patientTotalCare.setOlder(true);
        }else{
            patientTotalCare.setOlder(false);
        }

        patientTotalCare.setFullNameLastName(patientTotalCare.getName() + " " + patientTotalCare.getLastName());

        if (patientTotalCare.getGender().toLowerCase().equals("male")){
            patientTotalCare.setGender("Masculino");
        } else if (patientTotalCare.getGender().toLowerCase().equals("female")) {
            patientTotalCare.setGender("Femenino");
        }

        if(patientTotalCare.getDocumentType().equals("CC") ){
            patientTotalCare.setDocumentTypeName("Cédula de ciudadanía");
        } else if (patientTotalCare.getDocumentType().equals("TI")) {
            patientTotalCare.setDocumentTypeName("Tarjeta de Identidad");
        } else if (patientTotalCare.getDocumentType().equals("RC")) {
            patientTotalCare.setDocumentTypeName("Registro civil");
        }

        //Day of week and month in Spanish
        Calendar cal = Calendar.getInstance();
        cal.setTime(patientTotalCare.getBirthDay());
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        LocalDate localDate=LocalDate.of(year,month,day);
        Locale spanishLocale=new Locale("es", "ES");
        String dateInSpanish=localDate.format(DateTimeFormatter.ofPattern("dd MMMM, yyyy",spanishLocale));

        patientTotalCare.setBirthDayFormatter(dateInSpanish);


        return patientTotalCare;
    }


    @Override
    public List<PatientTotalCare> findAll() {
        List<PatientTotalCare> listPatientTotalCares = patientRepository.listAll();

        for (PatientTotalCare p: listPatientTotalCares){
            p = getPatientInfoUtility(p);
        }
        return listPatientTotalCares;
    }

    @Override
    public Optional<PatientTotalCare> findByPatientId(Long patientId) {
        Optional<PatientTotalCare> o = Optional.ofNullable(patientRepository.findById(patientId));

        if(o.isPresent()) {
            PatientTotalCare p = o.get();
            p = getPatientInfoUtility(p);
            return  Optional.of(p);
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public PatientTotalCare savePatient(PatientTotalCare patient) {
        patientRepository.persistAndFlush(patient);
        return patient;
    }

    @Override
    public Optional<PatientTotalCare> findByDocumentNumber(String documentTypeCode, String documentnumber) {
        Optional<PatientTotalCare> o = Optional.ofNullable(patientRepository.findByDocumentNumber(documentTypeCode,documentnumber));

        if(o.isPresent()) {
            PatientTotalCare p = o.get();
            p = getPatientInfoUtility(p);
            return  Optional.of(p);
        }
        return Optional.empty();
    }
}
