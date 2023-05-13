package com.keralty.totalcare360.portal.coordinador.fhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Bundle;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.Iterator;

public class FhirPatientBind {

    public void fhirExample() {

        // Create a context
        FhirContext ctx = FhirContext.forR4();

        // Create a client
        //IGenericClient client = ctx.newRestfulGenericClient("https://hapi.fhir.org/baseR4");
        IGenericClient client = ctx.newRestfulGenericClient("http://34.86.91.1:8090/data-access/fhir/");

        // Read a patient with the given ID
        //Patient patient = client.read().resource(Patient.class).withId("example").execute();
        Patient patient = client.read().resource(Patient.class).withId("9952326427").execute();
        System.out.println("El nombre del paciente es = "+ patient.getName().get(0).getText());

        // Print the output
        //String string = ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(patient);
        //System.out.println(string);
    }

    public void readJson(){

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("C:\\ITAKA\\Reactjs-project\\POC-Backend\\portal-coordinador-api\\src\\main\\resources\\Bundle_FHIR.json"));
            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            JSONObject jsonObject = (JSONObject) obj;
            //System.out.println(jsonObject.toJSONString());

            // Create a context
            FhirContext ctx = FhirContext.forR4();

            IParser parserFhir = ctx.newJsonParser();

            Bundle bundle = parserFhir.parseResource(Bundle.class, jsonObject.toJSONString());
            System.out.println(bundle.getEntry().size());
            for (int i = 0; i < bundle.getEntry().size(); i++){
                System.out.println(bundle.getEntry().get(i).getResource().getResourceType().name());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
