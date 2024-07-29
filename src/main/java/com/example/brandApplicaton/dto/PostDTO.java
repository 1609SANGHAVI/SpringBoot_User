package com.example.brandApplicaton.dto;


import com.example.brandApplicaton.entity.Brand;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import lombok.*;


import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDTO {
//    private int id;
    private  String content;
    @Column(unique = true)
    @JsonIgnore
    private String uniqueId;
    private LocalDateTime createAt;
    private List<Integer> brandIds;
//    private Integer brandId;

//    private Long userId;
//      private List<Brand> brandIds;
//      private UserDTO signupDTO;
//      private List<BrandDTO> brands;
}
