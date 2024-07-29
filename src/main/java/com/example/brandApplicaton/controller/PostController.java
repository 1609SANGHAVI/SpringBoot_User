package com.example.brandApplicaton.controller;

import com.example.brandApplicaton.converter.PostConverter;
import com.example.brandApplicaton.dto.PostDTO;

import com.example.brandApplicaton.entity.Post;

import com.example.brandApplicaton.repository.UserRepository;
import com.example.brandApplicaton.service.PostService;
import com.example.brandApplicaton.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostConverter postConverter;
    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    @PostMapping("/post")
    public List<PostDTO> createPosts(@RequestParam String uniqueId, @RequestBody List<PostDTO> postDTOs) {
        List<Post> posts = postService.createPosts(uniqueId, postDTOs);
        return posts.stream()
                .map(post -> postConverter.convertEntityToDTO(post))
                .collect(Collectors.toList());
    }



    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<PostDTO>> getUserPosts(
            @PathVariable Long userId,
            @RequestParam(required = false) LocalDateTime latestDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<PostDTO> posts = postService.getUserPosts(userId, latestDate, page, size);
        return ResponseEntity.ok(posts);
    }


    @GetMapping("/by-brand")
    public ResponseEntity<Page<PostDTO>> getPostsByBrand(
            @RequestParam Long brandId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime latestDate,
            @RequestParam int page,
            @RequestParam int size) {
        if (latestDate == null) {
            latestDate = LocalDateTime.MIN; // Or some other default value
        }

        Page<Post> posts = postService.getPostsByBrand(brandId, latestDate, page, size);
        Page<PostDTO> postDTOs = posts.map(postConverter::convertEntityToDTO);

        return ResponseEntity.ok(postDTOs);
    }

    @GetMapping("/count/{brandId}")
    public ResponseEntity<Map<String, Long>> getPostCountByBrandAndDay(@PathVariable int brandId) {
        Map<String, Long> postCountByDay = postService.getPostCountByBrandAndDay(brandId);
        return ResponseEntity.ok(postCountByDay);
    }

}
