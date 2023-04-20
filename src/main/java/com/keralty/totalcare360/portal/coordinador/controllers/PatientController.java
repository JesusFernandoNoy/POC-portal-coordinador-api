package com.keralty.totalcare360.portal.coordinador.controllers;

import com.keralty.totalcare360.portal.coordinador.models.entities.Patient;
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
    public List<Patient> allPatients() {
        return patientService.findAll();
    }

    @GET
    @Path("/id/{patientId}")
    public Patient getPatientById(@PathParam("patientId") Long patientId) {
        Optional<Patient> objPatient = patientService.findByPatientId(patientId);

        if (objPatient.isPresent()){
            Patient patient = objPatient.get();
            return patient;
        }else {
            throw new WebApplicationException("Patient with ID " + patientId + " does not exist.", 404);
        }
    }
}
