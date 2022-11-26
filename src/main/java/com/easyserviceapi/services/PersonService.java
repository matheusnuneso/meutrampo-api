package com.easyserviceapi.services;

import java.util.List;
import java.util.Optional;


import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.easyserviceapi.dto.PersonDto;
import com.easyserviceapi.models.PersonModel;
import com.easyserviceapi.repositories.PersonRepository;

@Service
public class PersonService {
    
    final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Transactional
    public Object save(PersonModel personModel){
        return personRepository.save(personModel);
    }

    public List<PersonModel> findAll(){
        return personRepository.findAll();
    }

    public boolean existsByUserName(String userName) {
        return personRepository.existsByUserName(userName);
    }

    public Optional<PersonModel> findById(Long id){
        return personRepository.findById(id);
    }

    @Transactional
    public void delete(PersonModel personModel){
        personRepository.delete(personModel);
    }

    public PersonModel update(Long id, PersonDto personDto){
        PersonModel personModel = new PersonModel();
        BeanUtils.copyProperties(personDto, personModel);
        personModel.setId(id);

        return personRepository.save(personModel);
    }
}
