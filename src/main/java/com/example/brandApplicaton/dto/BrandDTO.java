package com.example.brandApplicaton.dto;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandDTO {

    private int id;
    private String name;
//    private String uniqueId;
//    private LocalDateTime createAt;

}
