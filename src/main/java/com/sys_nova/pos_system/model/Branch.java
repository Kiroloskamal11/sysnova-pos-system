package com.sys_nova.pos_system.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data    // عشان الـ Getters/Setters
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String phone;


    private String address;


    private String email;


    @ElementCollection
    private List<String> workingDay;


    private LocalTime openTime;

    private LocalTime closeTime;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



    
    @ManyToOne
    private Store store;


    @OneToOne(cascade = CascadeType.REMOVE)
    private User manager;


    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
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


    public Store getStore() {
        return store;
    }


    public void setStore(Store store) {
        this.store = store;
    }


    public User getManager() {
        return manager;
    }


    public void setManager(User manager) {
        this.manager = manager;
    }




    

}
