package com.keralty.totalcare360.portal.coordinador.services;

import org.hl7.fhir.r4.model.Bundle;

public interface IProgramService {

    String saveCase(Bundle bundle);

}
