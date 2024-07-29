package com.example.brandApplicaton.service;

import com.example.brandApplicaton.converter.PostConverter;
import com.example.brandApplicaton.dto.PostDTO;
import com.example.brandApplicaton.entity.Brand;
import com.example.brandApplicaton.entity.Post;
import com.example.brandApplicaton.entity.User;
import com.example.brandApplicaton.repository.BrandRepository;
import com.example.brandApplicaton.repository.PostRepository;
import com.example.brandApplicaton.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;



@Service
public class PostService {
    @Autowired
    private PostConverter postConverter;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private UserRepository userRepository;


    public List<Post> createPosts(String uniqueId, List<PostDTO> postDTOs) {
        if (!userService.checkUserExists(uniqueId)) {
            throw new RuntimeException("User not found with uniqueId: " + uniqueId);
        }

        if (postDTOs == null || postDTOs.isEmpty()) {
            throw new RuntimeException("At least one post must be provided");
        }
        User user = userService.findByUniqueId(uniqueId);
        // Convert PostDTOs to Post entities and set the uniqueId for each post
        List<Post> posts = postDTOs.stream()
                .map(dto -> {
                    Post post = postConverter.convertPostDTOToEntity(dto);
//                    User user = userService.findByUniqueId(uniqueId);
                    post.setUser(user);
                    post.setUniqueId(uniqueId);
//            post.setUser(userService.findByUniqueId(uniqueId)); // Associate the user
                    List<Brand> brands = brandRepository.findByIdIn(dto.getBrandIds());

                    post.setBrands(brands); // Associate the brands
                    return post;
                })
                .collect(Collectors.toList());

        // Save posts to the database
        return postRepository.saveAll(posts);
    }


    public Page<PostDTO> getUserPosts(Long userId, LocalDateTime latestDate, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> posts = postRepository.findByUserIdAndCreateAtAfter(userId, latestDate, pageable);
        return posts.map(post -> new PostConverter().convertEntityToDTO(post)); // Use converter here
    }


    public Page<Post> getPostsByBrand(Long brandId, LocalDateTime latestDate, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createAt"));
        return postRepository.findByBrandIdAndCreateAtAfter(brandId, latestDate, pageable);

    }

    public Map<String, Long> getPostCountByBrandAndDay(int brandId) {
        List<Post> posts = postRepository.findByBrandsId(brandId);

        Map<String, Long> postCountByDay = posts.stream()
                .collect(Collectors.groupingBy(post -> post.getCreateAt().toLocalDate().toString(),
                        Collectors.counting()));

        return postCountByDay;
    }
}











