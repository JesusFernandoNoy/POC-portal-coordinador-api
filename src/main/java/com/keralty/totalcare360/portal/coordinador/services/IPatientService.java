package com.keralty.totalcare360.portal.coordinador.services;

import com.keralty.totalcare360.portal.coordinador.models.entities.Patient;

import java.util.List;
import java.util.Optional;

public interface IPatientService {

    List<Patient> findAll();

    Optional<Patient> findByPatientId(Long patientId);
}
