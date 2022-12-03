package com.easyserviceapi.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid; 

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easyserviceapi.dto.JobDto;
import com.easyserviceapi.models.JobModel;
import com.easyserviceapi.services.JobService;
import com.easyserviceapi.services.PersonService;

import lombok.var;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/job")
public class JobController {
    



    final JobService jobService;
    final PersonService personService;

    public JobController(JobService jobService, PersonService personService){
        this.jobService = jobService;
        this.personService= personService;
    }
    
    @PostMapping
    public ResponseEntity<Object> saveJob(@RequestBody @Valid JobDto jobDto){        
        
        var jobModel = new JobModel();
        BeanUtils.copyProperties(jobDto, jobModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(jobService.save(jobModel));
    }

    @GetMapping
    public ResponseEntity<List<JobModel>> getAllJob(){
        return ResponseEntity.status(HttpStatus.OK).body(jobService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneJob(@PathVariable(value = "id") Long id){
        Optional<JobModel> jobModelOptional = jobService.findById(id);
        if(!jobModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trabalho não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body((jobModelOptional.get()));

    }

    @GetMapping("/person-jobs/{idPerson}")
    public ResponseEntity<Object> getPersonJobs (@PathVariable(value = "idPerson") Long idPerson){
        if(jobService.existsByIdPerson(idPerson)){
            return ResponseEntity.status(HttpStatus.OK).body(jobService.findByIdPerson(idPerson));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteJob(@PathVariable(value = "id") Long id){
        Optional<JobModel> jobModelOptional = jobService.findById(id);
        if(!jobModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trabalho não encontrado");
        }
        jobService.delete(jobModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Delete concluído");
    }

    @PutMapping("/{id}")    
    public ResponseEntity<Object> updateJob (@PathVariable(value = "id") Long id, @RequestBody @Valid JobDto jobDto){
        Optional<JobModel> jobModelOptional = jobService.findById(id);
        if(!jobModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trabalho não encontrado");
        }
        
        if(jobService.existsByTitle(jobDto.getTitle())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito");
        }
        return ResponseEntity.status(HttpStatus.OK).body(jobService.update(id, jobDto));
    }
}