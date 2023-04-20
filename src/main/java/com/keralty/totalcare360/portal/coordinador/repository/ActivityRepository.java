package com.keralty.totalcare360.portal.coordinador.repository;

import com.keralty.totalcare360.portal.coordinador.models.entities.Activity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ActivityRepository implements PanacheRepository<Activity> {

    public List<Activity> findByActivityCategory(String programName, int activityCategory) {

        return list("programName = ?1 and activityCategory = ?2", programName, activityCategory);
    }
}
