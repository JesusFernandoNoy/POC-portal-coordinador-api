package com.keralty.totalcare360.portal.coordinador.models.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "care_plan_cases")
public class CarePlanCase {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String carePlanType; //PLAN
    private String carePlanFhirId; // b57d157f-5dfc-459a-8f2c-f2c6f1ee4167
    private String planDefinitionId; //PlanDefinition/idPL
    private String programName; //UPI Diabetes
    private String patientRisk; //Riesgo IV c
    private Long patientRank; //255150140
    private String carePlanStatus; //DRAFT
    private String patientId; //Patient/dtd012373
    private String patientName; // Renato Aparicio Torre
    private int assignedActivities; // 5
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="carePlanCase_id")
    private List<CarePlanActivity> listCodeActivities; // Appointment/2f4dfbf9-304f-446d-8a76-a5a94921b77d ..

    private String identificationCriteria;

    private String coverageNetwork;

    private String LocationProgramSite;

    private String LocationServiceProviderCode;

    private String LocationServiceProviderName;

    /*@Lob
    @Column(columnDefinition = "text")
    private String bundle;*/

    public CarePlanCase(Long id, String carePlanType, String carePlanFhirId, String planDefinitionId, String programName, String patientRisk, Long patientRank, String carePlanStatus, String patientId, String patientName, int assignedActivities) {
        this.id = id;
        this.carePlanType = carePlanType;
        this.carePlanFhirId = carePlanFhirId;
        this.planDefinitionId = planDefinitionId;
        this.programName = programName;
        this.patientRisk = patientRisk;
        this.patientRank = patientRank;
        this.carePlanStatus = carePlanStatus;
        this.patientId = patientId;
        this.patientName = patientName;
        this.assignedActivities = assignedActivities;
        this.listCodeActivities = new ArrayList<>();
    }

    public CarePlanCase(){
        this.listCodeActivities = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarePlanType() {
        return carePlanType;
    }

    public void setCarePlanType(String carePlanType) {
        this.carePlanType = carePlanType;
    }

    public String getCarePlanFhirId() {
        return carePlanFhirId;
    }

    public void setCarePlanFhirId(String carePlanFhirId) {
        this.carePlanFhirId = carePlanFhirId;
    }

    public String getPlanDefinitionId() {
        return planDefinitionId;
    }

    public void setPlanDefinitionId(String planDefinitionId) {
        this.planDefinitionId = planDefinitionId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getPatientRisk() {
        return patientRisk;
    }

    public void setPatientRisk(String patientRisk) {
        this.patientRisk = patientRisk;
    }

    public Long getPatientRank() {
        return patientRank;
    }

    public void setPatientRank(Long patientRank) {
        this.patientRank = patientRank;
    }

    public String getCarePlanStatus() {
        return carePlanStatus;
    }

    public void setCarePlanStatus(String carePlanStatus) {
        this.carePlanStatus = carePlanStatus;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getAssignedActivities() {
        return assignedActivities;
    }

    public void setAssignedActivities(int assignedActivities) {
        this.assignedActivities = assignedActivities;
    }

    public List<CarePlanActivity> getListCodeActivities() {
        return listCodeActivities;
    }

    public void setListCodeActivities(List<CarePlanActivity> listCodeActivities) {
        this.listCodeActivities = listCodeActivities;
    }

    public String getIdentificationCriteria() {
        return identificationCriteria;
    }

    public void setIdentificationCriteria(String identificationCriteria) {
        this.identificationCriteria = identificationCriteria;
    }

    public String getCoverageNetwork() {
        return coverageNetwork;
    }

    public void setCoverageNetwork(String coverageNetwork) {
        this.coverageNetwork = coverageNetwork;
    }

    public String getLocationProgramSite() {
        return LocationProgramSite;
    }

    public void setLocationProgramSite(String locationProgramSite) {
        LocationProgramSite = locationProgramSite;
    }

    public String getLocationServiceProviderCode() {
        return LocationServiceProviderCode;
    }

    public void setLocationServiceProviderCode(String locationServiceProviderCode) {
        LocationServiceProviderCode = locationServiceProviderCode;
    }

    public String getLocationServiceProviderName() {
        return LocationServiceProviderName;
    }

    public void setLocationServiceProviderName(String locationServiceProviderName) {
        LocationServiceProviderName = locationServiceProviderName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarePlanCase carePlan = (CarePlanCase) o;
        return id.equals(carePlan.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carePlanFhirId);
    }


}
