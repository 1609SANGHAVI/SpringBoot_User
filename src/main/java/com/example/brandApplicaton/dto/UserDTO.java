package com.example.brandApplicaton.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private String firstName;
    private String lastName;
    private String uniqueId;
//    private LocalDateTime createAt;

}
