package com.example.brandApplicaton.controller;



import com.example.brandApplicaton.dto.UserDTO;
import com.example.brandApplicaton.entity.User;
import com.example.brandApplicaton.repository.UserRepository;
import com.example.brandApplicaton.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


@PostMapping("/user")
public ResponseEntity<Object> saveUser(@RequestBody UserDTO userDTO) {
    try {
        User savedUser = userService.saveUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "User successfully signed up", "user", savedUser));
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(Map.of("message", "Signup failed: " + e.getMessage()));
    }
}

    public boolean existsByUniqueId(String uniqueId) {
        return userRepository.existsByUniqueId(uniqueId);
    }


}


