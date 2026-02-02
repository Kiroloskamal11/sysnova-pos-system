package com.sys_nova.pos_system.mapper;

import com.sys_nova.pos_system.model.Branch;
import com.sys_nova.pos_system.payload.dto.BranchDTO;

public class BranchMapper {

    public static BranchDTO toDTO(Branch branch) {
        if (branch == null) return null;
        
        return BranchDTO.builder()
                .id(branch.getId())
                .name(branch.getName())
                .phone(branch.getPhone())
                .address(branch.getAddress())
                .email(branch.getEmail())
                .workingDay(branch.getWorkingDay())
                .openTime(branch.getOpenTime())
                .closeTime(branch.getCloseTime())
                .createdAt(branch.getCreatedAt())
                .updatedAt(branch.getUpdatedAt())
                .storeId(branch.getStore() != null ? branch.getStore().getId() : null)
                // ملحوظة: يمكنك إضافة تحويل الـ UserDto و StoreDTO هنا لو احتجت البيانات كاملة
                .build();
    }
}