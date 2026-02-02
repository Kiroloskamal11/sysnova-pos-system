package com.sys_nova.pos_system.service.impl;

import com.sys_nova.pos_system.mapper.CategoryMapper;
import com.sys_nova.pos_system.model.Category;
import com.sys_nova.pos_system.model.Store;
import com.sys_nova.pos_system.payload.dto.CategoryDTO;
import com.sys_nova.pos_system.repository.CategoryRepository;
import com.sys_nova.pos_system.repository.StoreRepository;
import com.sys_nova.pos_system.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public CategoryDTO createCategory(CategoryDTO dto) {
        Store store = storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));

        Category category = Category.builder()
                .name(dto.getName())
                .store(store)
                .build();

        return CategoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDTO> getCategoriesByStore(Long storeId) {
        return categoryRepository.findByStoreId(storeId).stream()
                .map(CategoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        
        category.setName(dto.getName());
        return CategoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}