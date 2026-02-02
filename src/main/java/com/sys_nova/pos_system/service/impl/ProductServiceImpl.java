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

    private final ProductMapper productMapper = new ProductMapper();

    @Override
    public ProductDTO createProduct(ProductDTO productDTO, User user) {
        // 1. جلب المحل المرتبط بالأدمن (المستخدم الحالي)
        Store store = storeRepository.findByStoreAdminId(user.getId());
        if (store == null) {
            throw new RuntimeException("No store found for this admin");
        }

        // 2. جلب القسم (Category) إذا تم إرسال ID له
        Category category = null;
        if (productDTO.getCategoryId() != null) {
            category = categoryRepository.findById(productDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found with ID: " + productDTO.getCategoryId()));
        }

        // 3. تحويل الـ DTO إلى Entity وربط العلاقات
        Product product = productMapper.toEntity(productDTO, store, category);
        
        // 4. حفظ المنتج في قاعدة البيانات
        Product savedProduct = productRepository.save(product);
        
        // 5. تحويل النتيجة لـ DTO وإرجاعها
        return productMapper.toDTO(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO, User user) {
        // 1. التأكد من وجود المنتج
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // 2. تحديث البيانات الأساسية
        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setQuantity(productDTO.getQuantity());
        existingProduct.setMrp(productDTO.getMrp());
        existingProduct.setSellingprice(productDTO.getSellingprice());
        existingProduct.setBrand(productDTO.getBrand());
        existingProduct.setSku(productDTO.getSku());
        existingProduct.setImage(productDTO.getImage());

        // 3. تحديث القسم (Category) إذا تغير
        if (productDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(productDTO.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            existingProduct.setCategory(category);
        }

        // 4. حفظ التعديلات
        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toDTO(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id, User user) {
        // يمكنك هنا إضافة تحكم إضافي للتأكد من أن الأدمن يمسح منتج تابع لمحله فقط
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> getProductsByStoreId(Long storeId) {
        return productRepository.findByStoreId(storeId).stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> searchByKeyword(Long storeId, String keyword) {
        return productRepository.searchByKeyword(storeId, keyword).stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }
}