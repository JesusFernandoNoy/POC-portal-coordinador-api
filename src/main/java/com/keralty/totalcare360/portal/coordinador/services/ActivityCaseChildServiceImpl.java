package com.keralty.totalcare360.portal.coordinador.services;

import com.keralty.totalcare360.portal.coordinador.models.entities.ActivityCaseChild;
import com.keralty.totalcare360.portal.coordinador.repository.ActivityCaseChildRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

@Singleton
public class ActivityCaseChildServiceImpl implements IActivityCaseChildService{

    @Inject
    ActivityCaseChildRepository activityCaseChildRepository;

    @Transactional
    @Override
    public ActivityCaseChild saveActivityCaseChild(ActivityCaseChild activityCaseChild) {
        activityCaseChildRepository.persistAndFlush(activityCaseChild);
        return activityCaseChild;
    }
}
