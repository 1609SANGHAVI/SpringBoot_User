package com.example.brandApplicaton.service;


import com.example.brandApplicaton.converter.BrandConverter;
import com.example.brandApplicaton.dto.BrandDTO;
import com.example.brandApplicaton.entity.Brand;
import com.example.brandApplicaton.entity.Post;
import com.example.brandApplicaton.entity.User;
import com.example.brandApplicaton.repository.BrandRepository;
import com.example.brandApplicaton.repository.PostRepository;
import com.example.brandApplicaton.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandService {
    @Autowired
    private final BrandConverter brandConverter;
    @Autowired
    private final BrandRepository brandRepository;

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Autowired
    public BrandService(UserRepository userRepository, BrandRepository brandRepository, BrandConverter brandConverter) {
        this.userRepository = userRepository;
        this.brandRepository = brandRepository;
        this.brandConverter = brandConverter;
    }

    public List<Brand> saveBrands(String uniqueId, List<BrandDTO> brandDTOs) {
        // Check if the user exists
        Optional<User> optionalUser = userRepository.findByUniqueId(uniqueId);
        User user = optionalUser.orElseThrow(() -> new RuntimeException("User with uniqueId: " + uniqueId + " does not exist!"));

        // Convert each BrandDTO to a Brand entity and associate it with the user
        List<Brand> brands = brandDTOs.stream()
                .map(brandDTO -> {
                    Brand brand = brandConverter.convertBrandDTOToEntity(brandDTO,uniqueId);
                    brand.setUser(user);
                    return brand;
                })
                .collect(Collectors.toList());

        // Save all the brands in the repository
        return brandRepository.saveAll(brands);
    }
}













