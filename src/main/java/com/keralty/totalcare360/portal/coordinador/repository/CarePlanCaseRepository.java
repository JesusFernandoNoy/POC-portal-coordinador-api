package com.keralty.totalcare360.portal.coordinador.repository;

import com.keralty.totalcare360.portal.coordinador.models.entities.CarePlanCase;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CarePlanCaseRepository implements PanacheRepository<CarePlanCase> {


}
