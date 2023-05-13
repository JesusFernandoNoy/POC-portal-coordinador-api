package com.keralty.totalcare360.portal.coordinador.services;

import com.keralty.totalcare360.portal.coordinador.models.entities.PatientTotalCare;

import java.util.List;
import java.util.Optional;

public interface IPatientService {

    List<PatientTotalCare> findAll();

    Optional<PatientTotalCare> findByPatientId(Long patientId);

    PatientTotalCare savePatient(PatientTotalCare patient);

    Optional<PatientTotalCare> findByDocumentNumber(String documentTypeCode, String documentNumber);

}
