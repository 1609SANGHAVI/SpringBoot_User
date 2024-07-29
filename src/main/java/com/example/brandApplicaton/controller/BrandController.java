package com.example.brandApplicaton.controller;



import com.example.brandApplicaton.dto.BrandDTO;

import com.example.brandApplicaton.entity.Brand;

import com.example.brandApplicaton.repository.UserRepository;
import com.example.brandApplicaton.service.BrandService;
import com.example.brandApplicaton.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


@PostMapping("/brand")
public ResponseEntity<List<Brand>> createBrands(
        @RequestParam String uniqueId,
        @RequestBody List<BrandDTO> brandDTOs) {
    List<Brand> createdBrands = brandService.saveBrands(uniqueId, brandDTOs);
    return new ResponseEntity<>(createdBrands, HttpStatus.CREATED);
}

}

