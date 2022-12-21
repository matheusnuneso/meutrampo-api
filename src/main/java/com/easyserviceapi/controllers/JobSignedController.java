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

import com.easyserviceapi.dto.JobSignedDto;
import com.easyserviceapi.models.JobSignedModel;
import com.easyserviceapi.models.PersonModel;
import com.easyserviceapi.services.JobSignedService;
import com.easyserviceapi.services.PersonService;

import lombok.var;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "api/jobSigned")
public class JobSignedController {

    final JobSignedService jobSignedService;

    public JobSignedController(JobSignedService jobSignedService) {
        this.jobSignedService = jobSignedService;
    }

    @PostMapping
    public ResponseEntity<Object> saveJobSigned(@RequestBody @Valid JobSignedDto jobSignedDto) {

        var jobSignedModel = new JobSignedModel();
        if (jobSignedService.existsJobSigned(jobSignedDto)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Já existe um serviço para este usuário realizar na data");
        }

        BeanUtils.copyProperties(jobSignedDto, jobSignedModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(jobSignedService.save(jobSignedModel));
    }

    @GetMapping
    public ResponseEntity<List<JobSignedModel>> getAllJobSigned() {
        return ResponseEntity.status(HttpStatus.OK).body(jobSignedService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneJobSigned(@PathVariable(value = "id") Long id) {
        Optional<JobSignedModel> jobSignedModelOptional = jobSignedService.findById(id);
        if (!jobSignedModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trabalho não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body((jobSignedModelOptional.get()));

    }

    @GetMapping("/jobsigned/{id}")
    public ResponseEntity<Object> getAllJobSignedId(@PathVariable(value = "id") Long id){
        if (jobSignedService.existsByIdPerson(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(jobSignedService.findByIdPerson(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteJobSigned(@PathVariable(value = "id") Long id) {
        Optional<JobSignedModel> jobSignedModelOptional = jobSignedService.findById(id);
        if (!jobSignedModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trabalho não encontrado");
        }
        jobSignedService.delete(jobSignedModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Delete concluído");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateJobSigned(@PathVariable(value = "id") Long id,
            @RequestBody @Valid JobSignedDto jobSignedDto) {
        Optional<JobSignedModel> jobSignedModelOptional = jobSignedService.findById(id);
        if (!jobSignedModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trabalho não encontrado");
        }

        if (
            jobSignedModelOptional.get().getIdPerson() == jobSignedDto.getIdPerson() &&  //VERIFICAR COM FRONT SE É NECESSARIO
            jobSignedModelOptional.get().getJobDate().compareTo(jobSignedDto.getJobDate()) == 0)
         {
            return ResponseEntity.status(HttpStatus.OK).body(jobSignedService.update(id, jobSignedDto));
        }

        if (jobSignedService.existsJobSigned(jobSignedDto)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Já existe um serviço para este usuário realizar na data");
        }

        return ResponseEntity.status(HttpStatus.OK).body(jobSignedService.update(id, jobSignedDto));
    }
}