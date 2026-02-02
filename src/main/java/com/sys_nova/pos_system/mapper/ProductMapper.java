package com.sys_nova.pos_system.mapper;



import com.sys_nova.pos_system.payload.dto.ProductDTO;
import com.sys_nova.pos_system.model.Product;
import com.sys_nova.pos_system.model.Store;



public class ProductMapper {

    public ProductDTO toDTO(Product product){
        return ProductDTO.builder()
        .id(product.getId())
        .name(product.getName())
        .sku(product.getSku())
        .description(product.getDescription())
        .mrp(product.getMrp())
        .sellingprice(product.getSellingprice())
        .brand(product.getBrand())
        .storeId(product.getStore()!=null?product.getStore().getId():null)
        .image(product.getImage())
        .createdAt(product.getCreatedAt())
        .updatedAt(product.getUpdatedAt())
        // .categoryId(product.ge)
        
        .build();
        
    }



        public Product toEntity(ProductDTO productDTO ,Store store){
        return Product.builder()
        .name(productDTO.getName())
        .sku(productDTO.getSku())
        .description(productDTO.getDescription())
        .mrp(productDTO.getMrp())
        .sellingprice(productDTO.getSellingprice())
        .brand(productDTO.getBrand())
        // .storeId(product.getStore()!=null?product.getStore().getId():null)
        // .image(product.getImage())
        // .createdAt(product.getCreatedAt())
        // .updatedAt(product.getUpdatedAt())
        // // .categoryId(product.ge)
        
        .build();
        
    }



}
