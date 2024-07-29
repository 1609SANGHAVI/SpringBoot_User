package com.example.brandApplicaton.converter;

import com.example.brandApplicaton.dto.PostDTO;
import com.example.brandApplicaton.entity.Post;
import com.example.brandApplicaton.entity.User;
import org.springframework.stereotype.Component;
import com.example.brandApplicaton.entity.Brand;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;


@Component
public class PostConverter {
    public Post convertPostDTOToEntity(PostDTO postDTO){
        return Post.builder()
                .content((postDTO.getContent()))
                .uniqueId(postDTO.getUniqueId())
                .createAt(postDTO.getCreateAt())
                .build();

    }



    public PostDTO convertEntityToDTO(Post post) {
        return PostDTO.builder()
                .content(post.getContent())
                .uniqueId(post.getUniqueId())
                .createAt(post.getCreateAt())
                .brandIds(post.getBrands().stream()
                        .map(Brand::getId)
                        .collect(Collectors.toList()))

                .build();

    }


}











