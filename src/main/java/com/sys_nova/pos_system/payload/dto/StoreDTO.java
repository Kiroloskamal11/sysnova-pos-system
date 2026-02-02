package com.sys_nova.pos_system.payload.dto;

import java.time.LocalDateTime;
import com.sys_nova.pos_system.domain.StoreStatus;
import com.sys_nova.pos_system.model.StoreContect;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO {


    private Long id;

    private String storeName;

    private String description;

    private String storeType;

    private StoreStatus status;

    private String contactNumber;

    private UserDto storeAdmin; // ممتاز إنك استخدمت UserDto هنا

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private StoreContect contect;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public StoreStatus getStatus() {
        return status;
    }

    public void setStatus(StoreStatus status) {
        this.status = status;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public UserDto getStoreAdmin() {
        return storeAdmin;
    }

    public void setStoreAdmin(UserDto storeAdmin) {
        this.storeAdmin = storeAdmin;
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

    public StoreContect getContect() {
        return contect;
    }

    public void setContect(StoreContect contect) {
        this.contect = contect;
    }


    
}