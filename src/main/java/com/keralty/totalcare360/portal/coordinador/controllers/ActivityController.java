package com.keralty.totalcare360.portal.coordinador.controllers;

import com.keralty.totalcare360.portal.coordinador.models.entities.Activity;
import com.keralty.totalcare360.portal.coordinador.services.IActivityService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/activity")
@Produces(MediaType.APPLICATION_JSON)
public class ActivityController {

    @Inject
    IActivityService activityService;

    @GET
    public List<Activity> allActivities() {
        return activityService.findAll();
    }

    @GET
    @Path("/ActivitiesByPatient/{patientID}")
    public List<Activity> getActivitiesByCategory(@PathParam("patientID") Long patientID) {
        List<Activity> activityList = activityService.findByPatientId(patientID);

        if (activityList.size() == 0){
            throw new WebApplicationException("Patient with ID " + patientID + " does not exist.", 404);
        }

        return activityList;
    }
}
