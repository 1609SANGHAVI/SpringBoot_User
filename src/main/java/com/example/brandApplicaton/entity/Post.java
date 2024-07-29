package com.example.brandApplicaton.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name ="post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;
    @CurrentTimestamp

    private LocalDateTime createAt;

    private String uniqueId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "brand_id")
    private Brand brand;

//    @Column(name = "brand_id")
//    private Integer brandId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "post_brand",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "brand_id"))
    @JsonIgnore
    private List<Brand> brands=new ArrayList<>();
}



