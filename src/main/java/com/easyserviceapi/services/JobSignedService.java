package com.easyserviceapi.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.easyserviceapi.dto.JobSignedDto;
import com.easyserviceapi.dto.JobSignedPersonDto;
import com.easyserviceapi.models.JobSignedModel;
import com.easyserviceapi.repositories.JobRepository;
import com.easyserviceapi.repositories.JobSignedRepository;
import com.easyserviceapi.repositories.PersonRepository;

@Service
public class JobSignedService {

    final JobSignedRepository jobSignedRepository;
    final JobRepository jobRepository;
    final PersonRepository personRepository;

    public JobSignedService(JobSignedRepository JobSignedRepository, JobRepository jobRepository, PersonRepository personRepository){
        this.jobSignedRepository = JobSignedRepository;
        this.jobRepository = jobRepository;
        this.personRepository = personRepository;
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

    public List<JobSignedPersonDto> findByIdPerson(Long idPerson){
        List<JobSignedModel> listJobSigned = jobSignedRepository.findByIdPerson(idPerson);
        List<JobSignedPersonDto> listJobSignedPerson = new ArrayList<>();

        for (JobSignedModel jobSigned : listJobSigned) {
            listJobSignedPerson.add(
                new JobSignedPersonDto(
                    jobSigned.getId(),
                    this.jobRepository.findById(jobSigned.getIdJob()).get().getTitle(),
                    jobSigned.getFinalPrice(),
                    this.personRepository.findById(jobSigned.getIdClient()).get().getFullName(),
                    jobSigned.getJobDate(),
                    jobSigned.getContractDate()
                )
            );
        }

        return listJobSignedPerson;
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

    public boolean existsByIdPerson(Long idPerson){
        return jobSignedRepository.existsByIdPerson(idPerson);
    }
}
