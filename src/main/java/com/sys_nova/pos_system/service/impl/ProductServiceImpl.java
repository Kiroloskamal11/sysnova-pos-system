package com.sys_nova.pos_system.service.impl;

import com.sys_nova.pos_system.mapper.ProductMapper;
import com.sys_nova.pos_system.model.Category;
import com.sys_nova.pos_system.model.Product;
import com.sys_nova.pos_system.model.Store;
import com.sys_nova.pos_system.model.User;
import com.sys_nova.pos_system.payload.dto.ProductDTO;
import com.sys_nova.pos_system.repository.CategoryRepository;
import com.sys_nova.pos_system.repository.ProductRepository;
import com.sys_nova.pos_system.repository.StoreRepository;
import com.sys_nova.pos_system.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // تم حذف سطر الـ productMapper = new ProductMapper() لأنه لم يعد له داعي

    @Override
    public ProductDTO createProduct(ProductDTO productDTO, User user) {
        Store store = storeRepository.findByStoreAdminId(user.getId());
        if (store == null) {
            throw new RuntimeException("No store found for this admin");
        }

        Category category = null;
        if (productDTO.getCategoryId() != null) {
            category = categoryRepository.findById(productDTO.getCategoryId())
                    .orElseThrow(
                            () -> new RuntimeException("Category not found with ID: " + productDTO.getCategoryId()));
        }

        // استخدام الـ Static Method مباشرة
        Product product = ProductMapper.toEntity(productDTO, store, category);

        Product savedProduct = productRepository.save(product);

        return ProductMapper.toDTO(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO, User user) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setQuantity(productDTO.getQuantity());
        existingProduct.setMrp(productDTO.getMrp());
        existingProduct.setSellingprice(productDTO.getSellingprice());
        existingProduct.setBrand(productDTO.getBrand());
        existingProduct.setSku(productDTO.getSku());
        existingProduct.setImage(productDTO.getImage());

        if (productDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(productDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            existingProduct.setCategory(category);
        }

        Product updatedProduct = productRepository.save(existingProduct);
        return ProductMapper.toDTO(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id, User user) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> getProductsByStoreId(Long storeId) {
        return productRepository.findByStoreId(storeId).stream()
                .map(ProductMapper::toDTO) // استخدام الـ Method Reference للـ Static Method
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> searchByKeyword(Long storeId, String keyword) {
        return productRepository.searchByKeyword(storeId, keyword).stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }
}