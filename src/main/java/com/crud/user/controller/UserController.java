package com.crud.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.user.model.User;
import com.crud.user.repository.UserRepository;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/users")
    public List<User> retrieveAllStudents() {
        return userRepository.findAll();
    }
}
