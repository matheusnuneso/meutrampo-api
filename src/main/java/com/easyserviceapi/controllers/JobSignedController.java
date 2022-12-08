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
import com.easyserviceapi.services.JobSignedService;
import com.easyserviceapi.services.PersonService;

import lombok.var;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "api/JobSigned")
public class JobSignedController {

    final JobSignedService JobSignedService;
    final PersonService personService;

    public JobSignedController(JobSignedService JobSignedService, PersonService personService) {
        this.JobSignedService = JobSignedService;
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<Object> saveJobSigned(@RequestBody @Valid JobSignedDto JobSignedDto) {

        var JobSignedModel = new JobSignedModel();
        BeanUtils.copyProperties(JobSignedDto, JobSignedModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(JobSignedService.save(JobSignedModel));
    }

    @GetMapping
    public ResponseEntity<List<JobSignedModel>> getAllJobSigned() {
        return ResponseEntity.status(HttpStatus.OK).body(JobSignedService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneJobSigned(@PathVariable(value = "id") Long id) {
        Optional<JobSignedModel> JobSignedModelOptional = JobSignedService.findById(id);
        if (!JobSignedModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trabalho não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body((JobSignedModelOptional.get()));

    }

    @GetMapping("/person-JobSigneds/{idPerson}")
    public ResponseEntity<Object> getPersonJobSigneds(@PathVariable(value = "idPerson") Long idPerson) {
        if (JobSignedService.existsByIdPerson(idPerson)) {
            return ResponseEntity.status(HttpStatus.OK).body(JobSignedService.findByIdPerson(idPerson));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteJobSigned(@PathVariable(value = "id") Long id) {
        Optional<JobSignedModel> JobSignedModelOptional = JobSignedService.findById(id);
        if (!JobSignedModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trabalho não encontrado");
        }
        JobSignedService.delete(JobSignedModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Delete concluído");
    }

    /* 
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateJobSigned(@PathVariable(value = "id") Long id, @RequestBody @Valid JobSignedDto JobSignedDto) {
        Optional<JobSignedModel> JobSignedModelOptional = JobSignedService.findById(id);
        if (!JobSignedModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trabalho não encontrado");
        }

        if (JobSignedService.existsByTitle(JobSignedDto.getTitle())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito");
        }
        return ResponseEntity.status(HttpStatus.OK).body(JobSignedService.update(id, JobSignedDto));
    }*/
}