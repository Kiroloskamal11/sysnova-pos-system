package com.sys_nova.pos_system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import com.sys_nova.pos_system.domain.StoreStatus;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String storeName;

    private String description;

    private String storeType;

    private StoreStatus status;

    private String contactNumber;

    @OneToOne // كل مدير له متجر واحد
    private User storeAdmin;

    

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @Embedded
    private StoreContect contect = new StoreContect();


    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        // لاحظ في الصورة هو اختار PENDING كحالة مبدئية
        if (status == null) {
            status = StoreStatus.PENDING; 
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

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

    public User getStoreAdmin() {
        return storeAdmin;
    }

    public void setStoreAdmin(User storeAdmin) {
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