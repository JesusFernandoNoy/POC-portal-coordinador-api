package com.keralty.totalcare360.portal.coordinador.services;

import com.keralty.totalcare360.portal.coordinador.models.entities.PatientTotalCare;
import com.keralty.totalcare360.portal.coordinador.models.entities.PatientManagement;
import com.keralty.totalcare360.portal.coordinador.repository.PatientManagementRepository;


import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import java.util.List;
import java.util.Optional;

@Singleton
public class PatientManagementServiceImpl implements IPatientManagementService{

    @Inject
    IPatientService patientService;

    @Inject
    PatientManagementRepository patientManagementRepository;

    public PatientManagement getPatientInformation(PatientTotalCare patientTotalCare, PatientManagement pm){
        pm.setName(patientTotalCare.getFullNameLastName());
        pm.setIdType(patientTotalCare.getDocumentTypeName());
        pm.setNumId(patientTotalCare.getDocumentNumber().toString());
        pm.setAge(patientTotalCare.getAge());
        pm.setOlder(patientTotalCare.getOlder());
        return pm;
    }

    @Override
    public List<PatientManagement> findAll() {
        List<PatientManagement> listPatientManagement = (List<PatientManagement>) patientManagementRepository.listAll();

        for (PatientManagement pm: listPatientManagement){
            Long patientId = pm.getPatient_id();
            Optional<PatientTotalCare> patientObjet = patientService.findByPatientId(patientId);
            if(patientObjet.isPresent()){
                PatientTotalCare patientTotalCare = patientObjet.get();
                pm = getPatientInformation(patientTotalCare,pm);
            }
        }
        return listPatientManagement;
    }

    @Override
    public List<PatientManagement> findAllOrderByHemoglobina() {
        List<PatientManagement> listPatientManagement = (List<PatientManagement>) patientManagementRepository.findAllOrderByHemoglobina();

        for (PatientManagement pm: listPatientManagement){
            Long patientId = pm.getPatient_id();
            Optional<PatientTotalCare> patientObjet = patientService.findByPatientId(patientId);
            if(patientObjet.isPresent()){
                PatientTotalCare patientTotalCare = patientObjet.get();
                pm = getPatientInformation(patientTotalCare,pm);
            }
        }
        return listPatientManagement;
    }

    @Override
    public Optional<PatientManagement> findByPatientId(Long patientId) {

        Optional<PatientManagement> o = Optional.ofNullable(patientManagementRepository.findByPatientId(patientId));

        if(o.isPresent()) {
            PatientManagement pm = o.get();
            Long pId= pm.getPatient_id();
            Optional<PatientTotalCare> patientObj = patientService.findByPatientId(pId);
            if(patientObj.isPresent()){
                PatientTotalCare patientTotalCare = patientObj.get();
                pm = getPatientInformation(patientTotalCare,pm);
            }else{
                throw new WebApplicationException("Patient with ID " + patientId + " does not exist.", 404);
            }
            return  Optional.of(pm);
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public PatientManagement savePatientManagement(PatientManagement patientManagement) {
        patientManagementRepository.persistAndFlush(patientManagement);
        return patientManagement;
    }
}
