package com.sys_nova.pos_system.mapper;

import com.sys_nova.pos_system.model.Category;
import com.sys_nova.pos_system.payload.dto.CategoryDTO;

public class CategoryMapper {
    public static CategoryDTO toDTO(Category category){
        return CategoryDTO.builder()
        .id(category.getId()) // لازم نرجع الـ ID عشان الـ Frontend يحتاجه في الـ Update
        .name(category.getName())
        .storeId(category.getStore() != null ? category.getStore().getId() : null)
        .build();
    }
}