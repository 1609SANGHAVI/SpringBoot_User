package com.example.brandApplicaton.repository;

import com.example.brandApplicaton.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByUniqueId(String uniqueId);
    Optional<User> findByUniqueId(String uniqueId);

}
