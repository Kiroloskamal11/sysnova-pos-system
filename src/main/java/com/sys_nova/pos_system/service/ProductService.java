package com.sys_nova.pos_system.service;

import java.util.List;

import com.sys_nova.pos_system.model.User;
import com.sys_nova.pos_system.payload.dto.ProductDTO;

public interface ProductService {

    ProductDTO createProduct (ProductDTO productDTO ,User user);
    ProductDTO updateProduct (Long id ,ProductDTO productDTO,User user);
    void deleteProduct (Long id ,User user);
    List<ProductDTO> getProductsByStoreId(Long storeId);
    List<ProductDTO> searchByKeyword(Long storeId ,String keyword);

    
}
