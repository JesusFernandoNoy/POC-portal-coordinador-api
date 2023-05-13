package com.keralty.totalcare360.portal.coordinador.models.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "patients")
public class PatientTotalCare {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String fhirIdPatient;
    private String documentTypeDescription;
    private String documentType;
    private String documentNumber;
    private String name;
    private String lastName;
    private Date birthDay;
    private String gender;
    private String email;
    private String phone;
    private String city;
    private String maritalStatus;
    @Transient
    private String age;

    @Transient
    private String documentTypeName;

    @Transient
    private String fullNameLastName;

    @Transient
    private String birthDayFormatter;

    @Transient
    private Boolean isOlder;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="PatientTotalCare_id")
    private List<PatientContact> listPatientContact;

    public PatientTotalCare(){
        listPatientContact = new ArrayList<>();
    }

    public PatientTotalCare(Long id, String idFhirPatient, String documentTypeDescription, String documentType, String documentNumber, String name, String lastName, Date birthDay, String gender, String email, String phone, String city, String maritalStatus) {
        this.id = id;
        this.fhirIdPatient = idFhirPatient;
        this.documentTypeDescription = documentTypeDescription;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.name = name;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.maritalStatus = maritalStatus;
        listPatientContact = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFhirIdPatient() {
        return fhirIdPatient;
    }

    public void setFhirIdPatient(String fhirIdPatient) {
        this.fhirIdPatient = fhirIdPatient;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getDocumentTypeDescription() {
        return documentTypeDescription;
    }

    public void setDocumentTypeDescription(String documentTypeDescription) {
        this.documentTypeDescription = documentTypeDescription;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDocumentTypeName() {
        return documentTypeName;
    }

    public void setDocumentTypeName(String documentTypeName) {
        this.documentTypeName = documentTypeName;
    }

    public String getFullNameLastName() {
        return fullNameLastName;
    }

    public void setFullNameLastName(String fullNameLastName) {
        this.fullNameLastName = fullNameLastName;
    }

    public String getBirthDayFormatter() {
        return birthDayFormatter;
    }

    public void setBirthDayFormatter(String birthDayFormatter) {
        this.birthDayFormatter = birthDayFormatter;
    }

    public Boolean getOlder() {
        return isOlder;
    }

    public void setOlder(Boolean older) {
        isOlder = older;
    }

    public List<PatientContact> getListPatientContact() {
        return listPatientContact;
    }

    public void setListPatientContact(List<PatientContact> listPatientContact) {
        this.listPatientContact = listPatientContact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientTotalCare patientTotalCare = (PatientTotalCare) o;
        return id.equals(patientTotalCare.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, documentNumber,name);
    }

}
