package com.easyserviceapi.services;

import java.util.Date;
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

    final JobSignedRepository jobSignedRepository;

    public JobSignedService(JobSignedRepository JobSignedRepository){
        this.jobSignedRepository = JobSignedRepository;
    }

    @Transactional
    public Object save(JobSignedModel JobSignedModel){
        return jobSignedRepository.save(JobSignedModel);
    }

    public List<JobSignedModel> findAll(){
        return jobSignedRepository.findAll();
    }    

    public Optional<JobSignedModel> findById(Long id){
        return jobSignedRepository.findById(id);
    }  

    @Transactional
    public void delete(JobSignedModel JobSignedModel){
        jobSignedRepository.delete(JobSignedModel);
    }

    public JobSignedModel update(Long id, JobSignedDto jobSignedDto){
        JobSignedModel jobSignedModel = new JobSignedModel();
        BeanUtils.copyProperties(jobSignedDto, jobSignedModel);
        jobSignedModel.setId(id);
        
        return jobSignedRepository.save(jobSignedModel);
    }

    public boolean existsJobSigned(JobSignedDto jobSignedDto){
        Long idPerson = jobSignedDto.getIdPerson();
        Date jobDate = jobSignedDto.getJobDate();

        return jobSignedRepository.existsByidPersonAndJobDate(idPerson, jobDate);
    }
}
