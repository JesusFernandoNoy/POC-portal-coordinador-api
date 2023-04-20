package com.keralty.totalcare360.portal.coordinador.services;

import com.keralty.totalcare360.portal.coordinador.models.entities.Activity;
import com.keralty.totalcare360.portal.coordinador.models.entities.Patient;
import com.keralty.totalcare360.portal.coordinador.repository.ActivityRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class ActivityServiceImp implements IActivityService{

    @Inject
    ActivityRepository activityRepository;

    @Inject
    IPatientService patientService;

    @Override
    public List<Activity> findAll() {
        List<Activity> listActivities = activityRepository.listAll();

        return listActivities;
    }

    @Override
    public Optional<Activity> findById(Long activityId) {
        Optional<Activity> o = Optional.ofNullable(activityRepository.findById(activityId));

        if(o.isPresent()) {
            Activity act = o.get();
            return  Optional.of(act);
        }
        return Optional.empty();
    }

    @Override
    public List<Activity> findByPatientId(Long patientId) {

        Optional<Patient> patientObj = patientService.findByPatientId(patientId);

        List<Activity> listActivities = new ArrayList<>();

        if (patientObj.isPresent()){

            Patient patient = patientObj.get();
            int category = 0;
            String programName = "UPI_Diabetes";

            if(patient.getOlder()){
                category = 1;
            }else if(!patient.getOlder()){
                category = 2;
            }
            listActivities = activityRepository.findByActivityCategory(programName,category);

        }

        return listActivities;
    }
}
