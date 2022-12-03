package com.easyserviceapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easyserviceapi.models.PersonModel;

@Repository
public interface PersonRepository extends JpaRepository<PersonModel, Long> {

    boolean existsByUserName(String userName);

    Optional<PersonModel> findByUserName(String userName);
    
}
