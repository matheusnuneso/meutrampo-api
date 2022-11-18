package com.easyserviceapi.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.easyserviceapi.models.UserModel;
import com.easyserviceapi.repositories.UserRepository;

@Service
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

@Transactional
public Object save(UserModel userModel) {
    return userRepository.save(userModel);
    }

public List<UserModel> findAll() {
    return userRepository.findAll();
    }

public boolean existsByUserName(String userName) {
    return userRepository.existsByUserName(userName);
}

public Optional<UserModel> findById(UUID id) {
    return userRepository.findById(id);
}

public void delete(UserModel userModel) {
    userRepository.delete(userModel);
}
    
}
