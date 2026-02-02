package com.sys_nova.pos_system.model;

import java.time.LocalDateTime;

import com.sys_nova.pos_system.payload.dto.BranchDTO;
import com.sys_nova.pos_system.payload.dto.ProductDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne
    private Branch branch;


    @ManyToOne
    private Product product;


    @Column(nullable = false)
    private Integer quentity;


    private LocalDateTime updatedAt;


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


    public Branch getBranch() {
        return branch;
    }


    public void setBranch(Branch branch) {
        this.branch = branch;
    }


    public Product getProduct() {
        return product;
    }


    public void setProduct(Product product) {
        this.product = product;
    }


    public Integer getQuentity() {
        return quentity;
    }


    public void setQuentity(Integer quentity) {
        this.quentity = quentity;
    }


    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }


    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


    



    

}
