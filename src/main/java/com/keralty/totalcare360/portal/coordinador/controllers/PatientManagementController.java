package com.keralty.totalcare360.portal.coordinador.controllers;

import com.keralty.totalcare360.portal.coordinador.models.entities.PatientManagement;
import com.keralty.totalcare360.portal.coordinador.services.IPatientManagementService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/Management")
@Produces(MediaType.APPLICATION_JSON)
public class PatientManagementController {

    @Inject
    IPatientManagementService patientManagement;

    @GET
    public List<PatientManagement> allPatientsManagement() {

        System.out.println("ingresa a allPatientsManagement");

        return patientManagement.findAll();
    }

    @GET
    @Path("/{patientId}")
    public PatientManagement getPatientManagementByPatientId(@PathParam("patientId") Long patientId) {
        Optional<PatientManagement> objPM = patientManagement.findByPatientId(patientId);

        if (objPM.isPresent()){
            PatientManagement pm = objPM.get();
            return pm;
        }else {
            throw new WebApplicationException("Patient with ID " + patientId + " does not exist.", 404);
        }
    }

}
