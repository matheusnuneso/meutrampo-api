package com.easyserviceapi.services;

import java.util.List;
import java.util.Optional;


import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

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

    public boolean existsByFullName(String fullName){
        return personRepository.existsByFullName(fullName);
    }

    public Optional<PersonModel> findById(Long id){
        return personRepository.findById(id);
    }

    public void delete(PersonModel personModel){
        personRepository.delete(personModel);
    }
}
