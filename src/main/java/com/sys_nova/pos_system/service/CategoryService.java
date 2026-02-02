package com.sys_nova.pos_system.service;

import java.util.List;

import com.sys_nova.pos_system.payload.dto.CategoryDTO;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO dto);
    List<CategoryDTO> getCategoriesByStore(Long storeId);
    CategoryDTO updateCategory(Long id , CategoryDTO dto);
    void deleteCategory(Long id);
    

}
