package com.keralty.totalcare360.portal.coordinador.models.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "activity_case_children")
public class ActivityCaseChild {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private Long carePlanCaseId;
    private String activityFhirId;
    private String carePlanFhirId;
    private String carePlanName;
    private String patientFhirId;
    private String specialityCode;
    private String specialityName;
    private String activityStatus;

    public ActivityCaseChild(Long id, Long carePlanCaseId, String activityFhirId, String carePlanFhirId, String carePlanName, String patientFhirId, String specialityCode, String specialityName, String activityStatus) {
        this.id = id;
        this.carePlanCaseId = carePlanCaseId;
        this.activityFhirId = activityFhirId;
        this.carePlanFhirId = carePlanFhirId;
        this.carePlanName = carePlanName;
        this.patientFhirId = patientFhirId;
        this.specialityCode = specialityCode;
        this.specialityName = specialityName;
        this.activityStatus = activityStatus;
    }

    public ActivityCaseChild(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarePlanCaseId() {
        return carePlanCaseId;
    }

    public void setCarePlanCaseId(Long carePlanCaseId) {
        this.carePlanCaseId = carePlanCaseId;
    }

    public String getActivityFhirId() {
        return activityFhirId;
    }

    public void setActivityFhirId(String activityFhirId) {
        this.activityFhirId = activityFhirId;
    }

    public String getCarePlanFhirId() {
        return carePlanFhirId;
    }

    public void setCarePlanFhirId(String carePlanFhirId) {
        this.carePlanFhirId = carePlanFhirId;
    }

    public String getCarePlanName() {
        return carePlanName;
    }

    public void setCarePlanName(String carePlanName) {
        this.carePlanName = carePlanName;
    }

    public String getPatientFhirId() {
        return patientFhirId;
    }

    public void setPatientFhirId(String patientFhirId) {
        this.patientFhirId = patientFhirId;
    }

    public String getSpecialityCode() {
        return specialityCode;
    }

    public void setSpecialityCode(String specialityCode) {
        this.specialityCode = specialityCode;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityCaseChild activityCaseChild = (ActivityCaseChild) o;
        return id.equals(activityCaseChild.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carePlanCaseId);
    }

}
