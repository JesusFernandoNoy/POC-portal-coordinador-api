package com.keralty.totalcare360.portal.coordinador.services;



import com.keralty.totalcare360.portal.coordinador.models.entities.Activity;

import java.util.List;
import java.util.Optional;

public interface IActivityService {

    List<Activity> findAll();

    Optional<Activity> findById(Long activityId);

    List<Activity> findByActivityCategory(String programName, int activityCategory);

}
