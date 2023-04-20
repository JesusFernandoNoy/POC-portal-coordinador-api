package com.keralty.totalcare360.portal.coordinador.controllers;

import com.keralty.totalcare360.portal.coordinador.models.entities.Activity;
import com.keralty.totalcare360.portal.coordinador.models.entities.Patient;
import com.keralty.totalcare360.portal.coordinador.services.IActivityService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
    @Path("/ActivitiesByCategory/{programName}/{activityCategory:\\d+}")
    public List<Activity> getActivitiesByCategory(@PathParam("programName") String programName, @PathParam("activityCategory") int activityCategory) {
        List<Activity> activityList = activityService.findByActivityCategory(programName,activityCategory);
        return activityList;
    }
}
