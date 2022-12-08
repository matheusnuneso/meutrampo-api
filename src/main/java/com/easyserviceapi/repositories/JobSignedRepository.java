package com.easyserviceapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easyserviceapi.models.JobSignedModel;

@Repository
public interface JobSignedRepository extends JpaRepository<JobSignedModel,Long> {

    boolean existByidClient(Long idClient);

    boolean existsByIdPerson(Long idPerson);

    boolean existByidJob(Long idJob);

    List<JobSignedModel> findByIdPerson(Long idPerson);


}
