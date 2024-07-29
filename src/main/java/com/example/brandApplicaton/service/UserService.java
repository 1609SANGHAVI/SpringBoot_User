package com.example.brandApplicaton.service;

import com.example.brandApplicaton.converter.UserConverter;
import com.example.brandApplicaton.dto.UserDTO;
import com.example.brandApplicaton.entity.User;
import com.example.brandApplicaton.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private UserRepository userRepository;

    public User saveUser(UserDTO userDTO) {
        User user= userConverter.convertUserDTOToEntity(userDTO);

        // Check for existing user and throw exception if found
        if (userRepository.existsByUniqueId(user.getUniqueId())) {
            throw new RuntimeException("User with uniqueId: " + user.getUniqueId() + " already exists!");
        }

        return userRepository.save(user);
    }
    public boolean checkUserExists(String uniqueId) {
        return userRepository.existsByUniqueId(uniqueId);
    }

    public User findByUniqueId(String uniqueId) {
        return userRepository.findByUniqueId(uniqueId)
                .orElseThrow(() -> new RuntimeException("User not found with uniqueId: " + uniqueId));
    }

    public Optional<User> findUserByUniqueId(String uniqueId) {
        return userRepository.findByUniqueId(uniqueId);
    }

}




