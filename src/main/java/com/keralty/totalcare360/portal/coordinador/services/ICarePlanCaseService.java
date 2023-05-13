package com.keralty.totalcare360.portal.coordinador.services;

import com.keralty.totalcare360.portal.coordinador.exception.CarePlanCaseNotFoundException;
import com.keralty.totalcare360.portal.coordinador.models.entities.CarePlanCase;

public interface ICarePlanCaseService {

    CarePlanCase saveCarePlanCase(CarePlanCase carePlanCase);

    CarePlanCase updateCarePlanCase(Long id, CarePlanCase carePlanCase) throws CarePlanCaseNotFoundException;
}
