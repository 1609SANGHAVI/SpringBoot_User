package com.example.brandApplicaton.repository;

import com.example.brandApplicaton.entity.Brand;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer> {
    List<Brand> findByIdIn(List<Integer> brandIds);

    Brand findByUniqueId(String uniqueId);

    boolean existsByUniqueId(String uniqueId);
}