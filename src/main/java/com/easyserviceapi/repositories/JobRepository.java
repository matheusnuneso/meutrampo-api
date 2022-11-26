package com.easyserviceapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easyserviceapi.models.JobModel;

@Repository
public interface JobRepository extends JpaRepository<JobModel,Long> {

    boolean existsByTitle(String title);

}
