package com.sys_nova.pos_system.payload.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private Double totalSpent;
    private Integer loyaltyPoints;



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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Double getTotalSpent() {
        return totalSpent;
    }
    public void setTotalSpent(Double totalSpent) {
        this.totalSpent = totalSpent;
    }
    public Integer getLoyaltyPoints() {
        return loyaltyPoints;
    }
    public void setLoyaltyPoints(Integer loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }


    


}