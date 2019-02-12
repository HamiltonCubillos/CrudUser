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
    
    /**
     * Method that returns all existing users in database.
     * If there are no users return null.
     * @return all existing users in database or null if there are no users
     */
    @GetMapping("/users")
    public List<User> retrieveAllStudents() {
        return userRepository.findAll();
    }
}
