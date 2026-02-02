package com.sys_nova.pos_system.mapper;

import com.sys_nova.pos_system.model.Product;
import com.sys_nova.pos_system.model.Store;
import com.sys_nova.pos_system.model.Category; // تأكد أن هذا هو الـ import الصحيح
import com.sys_nova.pos_system.payload.dto.ProductDTO;

public class ProductMapper {

    public ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .description(product.getDescription())
                .mrp(product.getMrp())
                .sellingprice(product.getSellingprice())
                .brand(product.getBrand())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .image(product.getImage())
                .storeId(product.getStore() != null ? product.getStore().getId() : null)
                .categoryId(product.getCategory() != null ? product.getCategory().getId() : null) // إضافة الـ ID
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    public Product toEntity(ProductDTO productDTO, Store store, Category category) {
        return Product.builder()
                .name(productDTO.getName())
                .sku(productDTO.getSku())
                .description(productDTO.getDescription())
                .mrp(productDTO.getMrp())
                .sellingprice(productDTO.getSellingprice())
                .brand(productDTO.getBrand())
                .price(productDTO.getPrice())
                .quantity(productDTO.getQuantity())
                .image(productDTO.getImage())
                .store(store)
                .category(category) // ربط الـ Category هنا
                .build();
    }
}