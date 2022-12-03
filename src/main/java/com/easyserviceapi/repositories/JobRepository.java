package com.easyserviceapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easyserviceapi.models.JobModel;

@Repository
public interface JobRepository extends JpaRepository<JobModel,Long> {

    boolean existsByTitle(String title);

    boolean existsByIdPerson(Long idPerson);

    List<JobModel> findByIdPerson(Long idPerson);


}
