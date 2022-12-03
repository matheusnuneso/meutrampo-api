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

import com.easyserviceapi.dto.PersonDto;
import com.easyserviceapi.models.PersonModel;
import com.easyserviceapi.services.PersonService;

import lombok.var;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/person")
public class PersonController {

    final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<Object> savePerson(@RequestBody @Valid PersonDto personDto) {

        if (personService.existsByUserName(personDto.getUserName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: UserName já está em uso");
        }

        var personModel = new PersonModel();
        BeanUtils.copyProperties(personDto, personModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(personService.save(personModel));

    }

    @GetMapping
    public ResponseEntity<List<PersonModel>> getAllPerson() {
        return ResponseEntity.status(HttpStatus.OK).body(personService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePerson(@PathVariable(value = "id") Long id) {
        Optional<PersonModel> personModelOptional = personService.findById(id);
        if (!personModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT_FOUND: Pessoa não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(personModelOptional.get());
    }

    @GetMapping("/{userName}" + "/{password}")
    public ResponseEntity<Object> authPerson(@PathVariable(value = "userName") String userName,
            @PathVariable(value = "password") String password) {

        if (personService.existsByUserName(userName)) {
            Optional<PersonModel> personModelOptional = personService.findByUserName(userName);
            if (personModelOptional.get().getPassword().equals(password)) {
                return ResponseEntity.status(HttpStatus.OK).body(personModelOptional.get());
            } else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT_FOUND: senha incorreta");

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND: UserName não encontrado ");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePerson(@PathVariable(value = "id") Long id) {
        Optional<PersonModel> personModelOptional = personService.findById(id);
        if (!personModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT_FOUND: Usuário não encontrado");
        }
        personService.delete(personModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("OK: Delete concluído");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePerson(@PathVariable(value = "id") Long id,
            @RequestBody @Valid PersonDto personDto) {
        Optional<PersonModel> personModelOptional = personService.findById(id);
        if (!personModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT_FOUND: Usuário não encontrado");
        }

        if (!personModelOptional.get().getUserName().equals(personDto.getUserName())) {
            if (personService.existsByUserName(personDto.getUserName())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: UserName ja está em uso");
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(personService.update(id, personDto));

    }
}
