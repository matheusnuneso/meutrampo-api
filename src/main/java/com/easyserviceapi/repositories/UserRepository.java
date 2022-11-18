package com.easyserviceapi.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easyserviceapi.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    boolean existsByUserName(String userName);

}
