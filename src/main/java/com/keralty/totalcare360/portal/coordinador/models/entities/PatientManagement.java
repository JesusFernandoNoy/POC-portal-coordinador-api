package com.keralty.totalcare360.portal.coordinador.models.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "patients_management")
public class PatientManagement {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private Long patient_id;
    private int hemoglobina;
    private String priority;
    @Transient
    private String name;
    @Transient
    private String idType;
    @Transient
    private String numId;
    @Transient
    private String age;
    private String rute;
    private String testDiagnostic;
    private int pendingActivity;

    @Transient
    private boolean isOlder;

    public PatientManagement(){

    }

    public PatientManagement(Long id, Long patient_id, int hemoglobina, String priority, String rute, String testDiagnostic, int pendingActivity) {
        this.id = id;
        this.patient_id = patient_id;
        this.hemoglobina = hemoglobina;
        this.priority = priority;
        this.rute = rute;
        this.testDiagnostic = testDiagnostic;
        this.pendingActivity = pendingActivity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Long patient_id) {
        this.patient_id = patient_id;
    }

    public int getHemoglobina() {
        return hemoglobina;
    }

    public void setHemoglobina(int hemoglobina) {
        this.hemoglobina = hemoglobina;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getNumId() {
        return numId;
    }

    public void setNumId(String numId) {
        this.numId = numId;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRute() {
        return rute;
    }

    public void setRute(String rute) {
        this.rute = rute;
    }

    public String getTestDiagnostic() {
        return testDiagnostic;
    }

    public void setTestDiagnostic(String testDiagnostic) {
        this.testDiagnostic = testDiagnostic;
    }

    public int getPendingActivity() {
        return pendingActivity;
    }

    public void setPendingActivity(int pendingActivity) {
        this.pendingActivity = pendingActivity;
    }

    public boolean isOlder() {
        return isOlder;
    }

    public void setOlder(boolean older) {
        isOlder = older;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientManagement patientManagement = (PatientManagement) o;
        return id.equals(patientManagement.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patient_id);
    }
}
