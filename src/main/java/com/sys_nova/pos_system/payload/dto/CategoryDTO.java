package com.sys_nova.pos_system.payload.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor // السطر ده هو اللي ناقص ومهم جداً
@AllArgsConstructor
public class CategoryDTO {


    
    private Long id;


    
    private String name;

    // private Store store; // المنتج ده تابع لانهي محل؟


    private Long storeId;



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

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

  



    
    

}
