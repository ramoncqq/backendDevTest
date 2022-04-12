package com.example.demo.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductDTO {

    private String id;
    private String name;
    private Number price;
    private Boolean availability;

    public static ProductDTO convertEntityToDTO(ProductEntity productEntity) {
        return ProductDTO.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .availability(productEntity.getAvailability())
                .build();
    }

}
