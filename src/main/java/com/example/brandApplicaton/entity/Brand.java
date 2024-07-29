package com.example.brandApplicaton.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

import java.util.List;



@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name ="brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true) // Add unique constraint
    private String uniqueId;

    @CurrentTimestamp
    private LocalDateTime createAt;

    @ManyToOne
    private User user;
    @JsonIgnore
    @OneToMany(mappedBy = "brand")
    private List<Post> posts;
}
