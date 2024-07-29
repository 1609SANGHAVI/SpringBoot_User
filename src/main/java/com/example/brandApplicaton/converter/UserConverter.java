package com.example.brandApplicaton.converter;

import com.example.brandApplicaton.dto.UserDTO;
import com.example.brandApplicaton.entity.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserConverter {
    public User convertUserDTOToEntity(UserDTO userDTO){
        return User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .uniqueId(String.valueOf(UUID.randomUUID()))
                .build();


    }

    public UserDTO convertEntityTODTO(User user)
    {
        return  UserDTO.builder()

                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .uniqueId(String.valueOf(UUID.randomUUID()))
                .build();
    }
}
