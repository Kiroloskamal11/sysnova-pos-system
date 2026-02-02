package com.sys_nova.pos_system.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;


@Embeddable
public class StoreContect {

    private String address;
    private String phone;


    @Email(message = "invalid email format")
    private String email;


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
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


    

}
