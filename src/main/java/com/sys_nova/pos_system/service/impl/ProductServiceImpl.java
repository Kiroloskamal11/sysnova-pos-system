package com.sys_nova.pos_system.service.impl;

import com.sys_nova.pos_system.model.Product;
import com.sys_nova.pos_system.model.Store;
import com.sys_nova.pos_system.model.User;
import com.sys_nova.pos_system.payload.dto.ProductDTO;
import com.sys_nova.pos_system.repository.ProductRepository;
import com.sys_nova.pos_system.repository.StoreRepository;
import com.sys_nova.pos_system.service.ProductService;
import com.sys_nova.pos_system.mapper.ProductMapper;
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

    private final ProductMapper productMapper = new ProductMapper();

    @Override
    public ProductDTO createProduct(ProductDTO productDTO, User user) {
        // نأتي بالمحل المرتبط بالأدمن الحالي
        Store store = storeRepository.findByStoreAdminId(user.getId());
        
        Product product = productMapper.toEntity(productDTO, store);
        product.setStore(store); // ربط المنتج بالمحل
        
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO, User user) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // تحديث البيانات
        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setQuantity(productDTO.getQuantity());
        existingProduct.setMrp(productDTO.getMrp());
        existingProduct.setSellingprice(productDTO.getSellingprice());
        existingProduct.setBrand(productDTO.getBrand());
        existingProduct.setSku(productDTO.getSku());

        return productMapper.toDTO(productRepository.save(existingProduct));
    }

    @Override
    public void deleteProduct(Long id, User user) {
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