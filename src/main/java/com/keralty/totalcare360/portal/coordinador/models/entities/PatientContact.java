package com.keralty.totalcare360.portal.coordinador.models.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "patient_contact")
public class PatientContact {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String system;

    private String use;

    private String value;

    public PatientContact(Long id, String system, String use, String value) {
        this.id = id;
        this.system = system;
        this.use = use;
        this.value = value;
    }

    public PatientContact(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientContact patientContact = (PatientContact) o;
        return id.equals(patientContact.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

