package com.sys_nova.pos_system.payload.dto;


import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor // السطر ده هو اللي ناقص ومهم جداً
@AllArgsConstructor
public class InventoryDTO {

    private Long id;

    private Long branchId;
    private Long productId;


    
    public Long getBranchId() {
        return branchId;
    }


    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }


    public Long getProductId() {
        return productId;
    }


    public void setProductId(Long productId) {
        this.productId = productId;
    }


    private BranchDTO branch;


    private ProductDTO product;


    private Integer quentity;


    private LocalDateTime updatedAt;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    // public Branch getBranch() {
    //     return branch;
    // }


    // public void setBranch(Branch branch) {
    //     this.branch = branch;
    // }


    // public Product getProduct() {
    //     return product;
    // }


    // public void setProduct(Product product) {
    //     this.product = product;
    // }


    public BranchDTO getBranch() {
        return branch;
    }


    public void setBranch(BranchDTO branch) {
        this.branch = branch;
    }


    public ProductDTO getProduct() {
        return product;
    }


    public void setProduct(ProductDTO product) {
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
