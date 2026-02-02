package com.sys_nova.pos_system.payload.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data    // عشان الـ Getters/Setters
@Builder
@NoArgsConstructor // السطر ده هو اللي ناقص ومهم جداً
@AllArgsConstructor
public class BranchDTO {


    private Long id;

    private String name;

    private String phone;


    private String address;


    private String email;


    private List<String> workingDay;


    private LocalTime openTime;

    private LocalTime closeTime;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



    
    private StoreDTO store;

    private Long storeId;


    private UserDto manager;


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


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public List<String> getWorkingDay() {
        return workingDay;
    }


    public void setWorkingDay(List<String> workingDay) {
        this.workingDay = workingDay;
    }


    public LocalTime getOpenTime() {
        return openTime;
    }


    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }


    public LocalTime getCloseTime() {
        return closeTime;
    }


    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
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


    public StoreDTO getStore() {
        return store;
    }


    public void setStore(StoreDTO store) {
        this.store = store;
    }


    public Long getStoreId() {
        return storeId;
    }


    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }


    public UserDto getManager() {
        return manager;
    }


    public void setManager(UserDto manager) {
        this.manager = manager;
    }


    
}
