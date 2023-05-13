package com.keralty.totalcare360.portal.coordinador.controllers;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import com.keralty.totalcare360.portal.coordinador.fhir.FhirPatientBind;
import com.keralty.totalcare360.portal.coordinador.services.IPatientManagementService;
import com.keralty.totalcare360.portal.coordinador.services.IProgramService;
import org.hl7.fhir.r4.model.Bundle;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.FileReader;

@Path("/population")
public class ProgramController {

    @Inject
    IProgramService programService;


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {

        //FhirPatientBind fhir = new FhirPatientBind();
        //fhir.fhirExample();
        //fhir.readJson();

        return "Hello from RESTEasy Reactive";
    }

    @POST
    public String createCase(String bundleStr) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(bundleStr);
            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            JSONObject jsonObject = (JSONObject) obj;
            // Create a context
            FhirContext ctx = FhirContext.forR4();

            IParser parserFhir = ctx.newJsonParser();

            Bundle bundle = parserFhir.parseResource(Bundle.class, jsonObject.toJSONString());

            programService.saveCase(bundle);


        }  catch (Exception e) {
            e.printStackTrace();
        }


        return bundleStr;
    }



}