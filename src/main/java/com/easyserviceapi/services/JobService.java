package com.easyserviceapi.services;

import java.util.List;
import java.util.Optional;


import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.easyserviceapi.dto.JobDto;
import com.easyserviceapi.models.JobModel;
import com.easyserviceapi.repositories.JobRepository;

@Service
public class JobService {

    final JobRepository jobRepository;

    public JobService(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }

    @Transactional
    public Object save(JobModel jobModel){
        return jobRepository.save(jobModel);
    }

    public List<JobModel> findAll(){
        return jobRepository.findAll();
    }
    
    public boolean existsByTitle(String title){
        return jobRepository.existsByTitle(title);
    }

    public Optional<JobModel> findById(Long id){
        return jobRepository.findById(id);
    }

    public boolean existsByIdPerson(Long idPerson){
        return jobRepository.existsByIdPerson(idPerson);
    }

    public List<JobModel> findByIdPerson(Long idPerson){
        return jobRepository.findByIdPerson(idPerson);
    }
 

    @Transactional
    public void delete(JobModel jobModel){
        jobRepository.delete(jobModel);
    }

    public JobModel update(Long id, JobDto jobDto){
        JobModel jobModel = new JobModel();
        BeanUtils.copyProperties(jobDto, jobModel);
        jobModel.setId(id);
        
        return jobRepository.save(jobModel);
    }

}
