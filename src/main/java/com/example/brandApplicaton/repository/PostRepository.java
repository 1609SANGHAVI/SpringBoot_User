package com.example.brandApplicaton.repository;



import com.example.brandApplicaton.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    Optional<Post> findByUniqueId(String uniqueId);



    @Query("SELECT p FROM Post p WHERE p.user.id = :userId AND p.createAt > :latestDate")
    Page<Post> findByUserIdAndCreateAtAfter(@Param("userId") Long userId,
                                            @Param("latestDate") LocalDateTime latestDate,
                                            Pageable pageable);




    @Query("SELECT p FROM Post p JOIN FETCH p.brands b WHERE b.id = :brandId AND p.createAt > :latestDate")
    Page<Post> findByBrandIdAndCreateAtAfter(
            @Param("brandId") Long brandId,
            @Param("latestDate") LocalDateTime latestDate,
            Pageable pageable);

    List<Post> findByBrandsId(int brandId);
}