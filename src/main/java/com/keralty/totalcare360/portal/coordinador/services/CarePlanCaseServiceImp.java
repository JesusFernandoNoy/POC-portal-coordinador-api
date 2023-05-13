package com.keralty.totalcare360.portal.coordinador.services;

import com.keralty.totalcare360.portal.coordinador.exception.CarePlanCaseNotFoundException;
import com.keralty.totalcare360.portal.coordinador.models.entities.CarePlanCase;
import com.keralty.totalcare360.portal.coordinador.repository.CarePlanCaseRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

@Singleton
public class CarePlanCaseServiceImp implements ICarePlanCaseService{

    @Inject
    CarePlanCaseRepository carePlanCaseRepository;

    @Transactional
    @Override
    public CarePlanCase saveCarePlanCase(CarePlanCase carePlanCase) {
        carePlanCaseRepository.persistAndFlush(carePlanCase);
        return carePlanCase;
    }

    @Transactional
    @Override
    public CarePlanCase updateCarePlanCase(Long id, CarePlanCase carePlanCase) throws CarePlanCaseNotFoundException{
        CarePlanCase existingCarePlanCase = getCarePlanCaseById(id);
        existingCarePlanCase.setIdentificationCriteria(carePlanCase.getIdentificationCriteria());
        existingCarePlanCase.setCoverageNetwork(carePlanCase.getCoverageNetwork());
        existingCarePlanCase.setLocationProgramSite(carePlanCase.getLocationProgramSite());
        existingCarePlanCase.setLocationServiceProviderCode(carePlanCase.getLocationServiceProviderCode());
        existingCarePlanCase.setLocationServiceProviderName(carePlanCase.getLocationServiceProviderName());
        carePlanCaseRepository.persist(existingCarePlanCase);
        return existingCarePlanCase;
    }

    private CarePlanCase getCarePlanCaseById(Long id) throws CarePlanCaseNotFoundException {
        return carePlanCaseRepository.findByIdOptional(id).orElseThrow(() -> new CarePlanCaseNotFoundException("The  CarePlanCase doesn't exist"));
    }
}
