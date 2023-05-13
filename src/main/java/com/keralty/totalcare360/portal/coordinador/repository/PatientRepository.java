package com.keralty.totalcare360.portal.coordinador.repository;

import com.keralty.totalcare360.portal.coordinador.models.entities.PatientTotalCare;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PatientRepository implements PanacheRepository<PatientTotalCare> {

    public PatientTotalCare findByDocumentNumber(String documentTypeCode, String documentNumber) {
        return find("documentType = ?1 and documentNumber = ?2", documentTypeCode,documentNumber).firstResult();
    }
}
