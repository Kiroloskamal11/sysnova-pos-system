package com.sys_nova.pos_system.mapper;

import com.sys_nova.pos_system.model.Inventory;
import com.sys_nova.pos_system.payload.dto.InventoryDTO;

public class InventoryMapper {

    public static InventoryDTO toDTO(Inventory inventory) {
        if (inventory == null) return null;

        return InventoryDTO.builder()
                .id(inventory.getId())
                .quentity(inventory.getQuentity())
                .updatedAt(inventory.getUpdatedAt())
                .productId(inventory.getProduct() != null ? inventory.getProduct().getId() : null)
                .branchId(inventory.getBranch() != null ? inventory.getBranch().getId() : null)
                .build();
    }
}