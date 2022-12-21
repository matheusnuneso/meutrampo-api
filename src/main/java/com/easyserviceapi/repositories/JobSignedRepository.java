package com.easyserviceapi.repositories;

import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easyserviceapi.models.JobSignedModel;

import java.util.List;

@Repository
public interface JobSignedRepository extends JpaRepository<JobSignedModel,Long> {

    boolean existsByidPersonAndJobDate(Long idPerson, Date jobDate);

    boolean existsByIdPerson(Long idPerson);

    List<JobSignedModel> findByIdPerson(Long idPerson);
    
}
