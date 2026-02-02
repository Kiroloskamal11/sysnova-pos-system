package com.sys_nova.pos_system.mapper;

import com.sys_nova.pos_system.model.Store;
import com.sys_nova.pos_system.payload.dto.StoreDTO;

public class StoreMapper {

    // تحويل من Entity إلى DTO
    public static StoreDTO mapToStoreDto(Store store) {
        if (store == null) return null;
        
        StoreDTO dto = new StoreDTO();
        dto.setId(store.getId());
        dto.setStoreName(store.getStoreName());
        dto.setDescription(store.getDescription());
        dto.setStoreType(store.getStoreType());
        dto.setStatus(store.getStatus());
        dto.setContactNumber(store.getContactNumber());
        dto.setContect(store.getContect()); // الـ Embedded Object
        dto.setCreatedAt(store.getCreatedAt());
        dto.setUpdatedAt(store.getUpdatedAt());
        
        // لو عندك UserMapper ممكن تستخدمه هنا للـ storeAdmin
        return dto;
    }

    // تحويل من DTO إلى Entity
    public static Store mapToStore(StoreDTO dto) {
        if (dto == null) return null;
        
        Store store = new Store();
        store.setId(dto.getId());
        store.setStoreName(dto.getStoreName());
        store.setDescription(dto.getDescription());
        store.setStoreType(dto.getStoreType());
        store.setStatus(dto.getStatus());
        store.setContactNumber(dto.getContactNumber());
        store.setContect(dto.getContect());
        
        return store;
    }
}