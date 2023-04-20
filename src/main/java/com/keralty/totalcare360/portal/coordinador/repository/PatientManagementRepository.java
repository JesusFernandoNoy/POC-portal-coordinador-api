package com.keralty.totalcare360.portal.coordinador.repository;

import com.keralty.totalcare360.portal.coordinador.models.entities.PatientManagement;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PatientManagementRepository implements PanacheRepository<PatientManagement> {

    public PatientManagement findByPatientId(Long patientId) {
        return find("patient_id = ?1", patientId).firstResult();
    }
}
