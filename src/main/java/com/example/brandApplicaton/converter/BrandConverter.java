package com.example.brandApplicaton.converter;

import com.example.brandApplicaton.dto.BrandDTO;
import com.example.brandApplicaton.entity.Brand;
import org.springframework.stereotype.Component;

@Component
public class BrandConverter {
    public Brand convertBrandDTOToEntity(BrandDTO brandDTO,String uniqueId) {
        return Brand.builder()
                .name(brandDTO.getName())
                .uniqueId(uniqueId)
//                .createAt(brandDTO.getCreateAt())
                .build();
    }

    public BrandDTO convertEntityToDTO(Brand brand) {
        return BrandDTO.builder()
                .name(brand.getName())
//                .uniqueId(brand.getUniqueId())
//                .createAt(brand.getCreateAt())
                .build();
    }

}
