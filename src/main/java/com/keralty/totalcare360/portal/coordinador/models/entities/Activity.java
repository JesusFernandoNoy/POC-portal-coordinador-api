package com.keralty.totalcare360.portal.coordinador.models.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Activity {

    @Id
    @GeneratedValue
    private Long id;

    private String programName;

    /*
    This property will identify for the UPI Diabetes Program if it is 1 Senior Activities
    if it is 2 activities for minors
     */
    private int activityCategory;

    private String activityName;

    private String activityCode;

    private String frequency;

    private String dateAppointmentStr;

    private Date dateAppointment;

    private String programRange;

    private String activityState;

    public Activity(){

    }

    public Activity(Long id, String programName, int activityCategory, String activityName, String activityCode, String frequency, String dateAppointmentStr, Date dateAppointment, String programRange, String activityState) {
        this.id = id;
        this.programName = programName;
        this.activityCategory = activityCategory;
        this.activityName = activityName;
        this.activityCode = activityCode;
        this.frequency = frequency;
        this.dateAppointmentStr = dateAppointmentStr;
        this.dateAppointment = dateAppointment;
        this.programRange = programRange;
        this.activityState = activityState;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDateAppointmentStr() {
        return dateAppointmentStr;
    }

    public void setDateAppointmentStr(String dateAppointmentStr) {
        this.dateAppointmentStr = dateAppointmentStr;
    }

    public Date getDateAppointment() {
        return dateAppointment;
    }

    public void setDateAppointment(Date dateAppointment) {
        this.dateAppointment = dateAppointment;
    }

    public String getProgramRange() {
        return programRange;
    }

    public void setProgramRange(String programRange) {
        this.programRange = programRange;
    }

    public String getActivityState() {
        return activityState;
    }

    public void setActivityState(String activityState) {
        this.activityState = activityState;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public int getActivityCategory() {
        return activityCategory;
    }

    public void setActivityCategory(int activityCategory) {
        this.activityCategory = activityCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return id.equals(activity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, activityName);
    }
}
