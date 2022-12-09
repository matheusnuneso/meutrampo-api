package com.easyserviceapi.repositories;

import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easyserviceapi.models.JobSignedModel;

@Repository
public interface JobSignedRepository extends JpaRepository<JobSignedModel,Long> {

    boolean existsByidPersonAndJobDate(Long idPerson, Date jobDate);
    
}
