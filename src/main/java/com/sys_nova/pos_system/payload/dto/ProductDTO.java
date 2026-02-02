package com.sys_nova.pos_system.payload.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

      private Long id;


    private String name;

    private String description;

    private String image; // رابط صورة المنتج

    private double price;

    private int quantity; // الكمية المتوفرة في المخزن

    private String sku; // رمز SKU (Stock Keeping Unit) للباركد



    private Double mrp;

    private Double sellingprice;

    private String brand;



    private CategoryDTO category;


   
    




    private Long storeId; // المنتج ده تابع لانهي محل؟

    private Long categoryId; // المنتج ده تابع لانهي محل؟

  

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getSku() {
        return sku;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }
    public Double getMrp() {
        return mrp;
    }
    public void setMrp(Double mrp) {
        this.mrp = mrp;
    }
    public Double getSellingprice() {
        return sellingprice;
    }
    public void setSellingprice(Double sellingprice) {
        this.sellingprice = sellingprice;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public Long getStoreId() {
        return storeId;
    }
    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public CategoryDTO getCategory() {
        return category;
    }
    public void setCategory(CategoryDTO category) {
        this.category = category;
    }
    

    
    




    
}






























