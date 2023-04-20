package com.keralty.totalcare360.portal.coordinador.services;

import com.keralty.totalcare360.portal.coordinador.models.entities.Activity;
import com.keralty.totalcare360.portal.coordinador.repository.ActivityRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class ActivityServiceImp implements IActivityService{

    @Inject
    ActivityRepository activityRepository;

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
    public List<Activity> findByActivityCategory(String programName, int activityCategory) {
        List<Activity> listActivities = activityRepository.findByActivityCategory(programName,activityCategory);

        return listActivities;
    }
}
