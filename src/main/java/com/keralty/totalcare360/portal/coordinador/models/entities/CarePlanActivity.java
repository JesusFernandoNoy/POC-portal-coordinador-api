package com.keralty.totalcare360.portal.coordinador.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "care_plan_activities")
public class CarePlanActivity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String activityFhirId;

    public CarePlanActivity(Long id, String activityFhirId) {
        this.id = id;
        this.activityFhirId = activityFhirId;
    }
    public CarePlanActivity(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityFhirId() {
        return activityFhirId;
    }

    public void setActivityFhirId(String activityFhirId) {
        this.activityFhirId = activityFhirId;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(!(obj instanceof CarePlanActivity)){
            return false;
        }
        CarePlanActivity o = (CarePlanActivity) obj;
        return this.activityFhirId != null && this.activityFhirId.equals(o.activityFhirId);
    }


}
