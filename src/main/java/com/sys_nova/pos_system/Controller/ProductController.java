package com.sys_nova.pos_system.controller;

import com.sys_nova.pos_system.model.User;
import com.sys_nova.pos_system.payload.dto.ProductDTO;
import com.sys_nova.pos_system.payload.response.ApiResponse;
import com.sys_nova.pos_system.service.ProductService;
import com.sys_nova.pos_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(
            @RequestBody ProductDTO productDTO,
            @RequestHeader("Authorization") String jwt) throws Exception {
        
        User user = userService.findUserByJwtToken(jwt);
        ProductDTO createdProduct = productService.createProduct(productDTO, user);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<ProductDTO>> getStoreProducts(@PathVariable Long storeId) {
        List<ProductDTO> products = productService.getProductsByStoreId(storeId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProducts(
            @RequestParam Long storeId,
            @RequestParam String keyword) {
        List<ProductDTO> products = productService.searchByKeyword(storeId, keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductDTO productDTO,
            @RequestHeader("Authorization") String jwt) throws Exception {
        
        User user = userService.findUserByJwtToken(jwt);
        ProductDTO updatedProduct = productService.updateProduct(id, productDTO, user);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception {
        
        User user = userService.findUserByJwtToken(jwt);
        productService.deleteProduct(id, user);
        
        ApiResponse res = new ApiResponse("Product deleted successfully", true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}