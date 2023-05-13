package com.keralty.totalcare360.portal.coordinador.controllers;

import com.keralty.totalcare360.portal.coordinador.models.entities.PatientTotalCare;
import com.keralty.totalcare360.portal.coordinador.services.IPatientService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/patient")
@Produces(MediaType.APPLICATION_JSON)
public class PatientController {

    @Inject
    IPatientService patientService;

    @GET
    public List<PatientTotalCare> allPatients() {
        return patientService.findAll();
    }

    @GET
    @Path("/id/{patientId}")
    public PatientTotalCare getPatientById(@PathParam("patientId") Long patientId) {
        Optional<PatientTotalCare> objPatient = patientService.findByPatientId(patientId);

        if (objPatient.isPresent()){
            PatientTotalCare patientTotalCare = objPatient.get();
            return patientTotalCare;
        }else {
            throw new WebApplicationException("Patient with ID " + patientId + " does not exist.", 404);
        }
    }
}
