package com.sys_nova.pos_system.controller;

import com.sys_nova.pos_system.payload.dto.CategoryDTO;
import com.sys_nova.pos_system.payload.response.ApiResponse;
import com.sys_nova.pos_system.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO dto) {
        return new ResponseEntity<>(categoryService.createCategory(dto), HttpStatus.CREATED);
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<CategoryDTO>> getCategoriesByStore(@PathVariable Long storeId) {
        return ResponseEntity.ok(categoryService.getCategoriesByStore(storeId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO dto) {
        return ResponseEntity.ok(categoryService.updateCategory(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(new ApiResponse("Category deleted successfully", true));
    }
}