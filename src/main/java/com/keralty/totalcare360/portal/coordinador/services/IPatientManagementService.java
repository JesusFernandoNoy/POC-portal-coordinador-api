package com.keralty.totalcare360.portal.coordinador.services;

import com.keralty.totalcare360.portal.coordinador.models.entities.PatientManagement;

import java.util.List;
import java.util.Optional;

public interface IPatientManagementService {

    List<PatientManagement> findAll();
    Optional<PatientManagement> findByPatientId(Long patientId);
}
