package com.crud.user.controller;

import static com.crud.user.utils.Constants.USERNOTEXIST;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.crud.user.exceptions.UserNotFoundException;
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
    
    @GetMapping("/users/{id}")
    public User retrieveUser(final @PathVariable Integer id) {
        final Optional<User> student = userRepository.findById(id);
        if (!student.isPresent()) {
            try {
                throw new UserNotFoundException(USERNOTEXIST);
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            }
        }
        return student.get();
    }
    
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }
    
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(final @RequestBody User user) {
        final User savedUser = userRepository.save(user);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();

    }
    
    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateUser(final @RequestBody User user, final @PathVariable Integer id) {
        final Optional<User> userBd = userRepository.findById(id);
        if (!userBd.isPresent())
            return ResponseEntity.notFound().build();
        user.setId(id);
        userRepository.save(user);
        return ResponseEntity.noContent().build();
    }
}
