package com.sys_nova.pos_system.mapper;

import com.sys_nova.pos_system.model.Product;
import com.sys_nova.pos_system.model.Store;
import com.sys_nova.pos_system.model.Category;
import com.sys_nova.pos_system.payload.dto.ProductDTO;

public class ProductMapper {

    // إضافة static هنا هي اللي هتحل مشكلة الخط الأحمر في المابير التاني
    public static ProductDTO toDTO(Product product) {
        if (product == null)
            return null; // حماية من الـ Null

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
                .categoryId(product.getCategory() != null ? product.getCategory().getId() : null)
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    // جعلتها static أيضاً لتسهيل استخدامها في الـ Service
    public static Product toEntity(ProductDTO productDTO, Store store, Category category) {
        if (productDTO == null)
            return null;

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
                .category(category)
                .build();
    }
}