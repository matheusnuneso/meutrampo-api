package com.easyserviceapi.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easyserviceapi.dto.UserDto;
import com.easyserviceapi.models.UserModel;
import com.easyserviceapi.services.UserService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/user")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDto userDto) {

        if (userService.existsByUserName(userDto.getUserName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: UserName is already in use");
        }

        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));

    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUser() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneParkingSopt(@PathVariable(value = "id") Long id) {
        Optional<UserModel> userModelOptional = userService.findById(id);
        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") Long id) {
        Optional<UserModel> userModelOptional = userService.findById(id);
        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        userService.delete(userModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") Long id, @RequestBody @Valid UserDto userDto) {
        Optional<UserModel> userModelOptional = userService.findById(id);
        
        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        if (userService.existsByUserName(userDto.getUserName()) && !(userModelOptional.get().getUserName().equals(userDto.getUserName()))) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: UserName is already in use");
        }

        var userModel = userModelOptional.get();
        userModel.setUserName(userDto.getUserName());
        userModel.setPassword(userDto.getPassword());

        return ResponseEntity.status(HttpStatus.OK).body(userService.save(userModel));
    }

}
