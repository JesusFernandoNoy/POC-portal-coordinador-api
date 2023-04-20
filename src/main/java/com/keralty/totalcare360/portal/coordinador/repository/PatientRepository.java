package com.keralty.totalcare360.portal.coordinador.repository;

import com.keralty.totalcare360.portal.coordinador.models.entities.Patient;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PatientRepository implements PanacheRepository<Patient> {

    public Patient findByDocumentNumber(Long documentNumber) {
        return find("documentNumber = ?1", documentNumber).firstResult();
    }
}
