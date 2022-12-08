package com.easyserviceapi.services;

import java.util.List;
import java.util.Optional;


import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.easyserviceapi.dto.JobSignedDto;
import com.easyserviceapi.models.JobSignedModel;
import com.easyserviceapi.repositories.JobSignedRepository;

@Service
public class JobSignedService {

    final JobSignedRepository JobSignedRepository;

    public JobSignedService(JobSignedRepository JobSignedRepository){
        this.JobSignedRepository = JobSignedRepository;
    }

    @Transactional
    public Object save(JobSignedModel JobSignedModel){
        return JobSignedRepository.save(JobSignedModel);
    }

    public List<JobSignedModel> findAll(){
        return JobSignedRepository.findAll();
    }
    
    public boolean existByidClient(Long idClient){
        return JobSignedRepository.existByidClient(idClient);
    }

    public boolean existsByIdPerson(Long idPerson){
        return JobSignedRepository.existByidClient(idPerson);
    }

    public Optional<JobSignedModel> findById(Long id){
        return JobSignedRepository.findById(id);
    }


    public List<JobSignedModel> findByIdPerson(Long idPerson){
        return JobSignedRepository.findByIdPerson(idPerson);
    }
 

    @Transactional
    public void delete(JobSignedModel JobSignedModel){
        JobSignedRepository.delete(JobSignedModel);
    }

    public JobSignedModel update(Long id, JobSignedDto JobSignedDto){
        JobSignedModel JobSignedModel = new JobSignedModel();
        BeanUtils.copyProperties(JobSignedDto, JobSignedModel);
        JobSignedModel.setId(id);
        
        return JobSignedRepository.save(JobSignedModel);
    }

}
