package com.sys_nova.pos_system.mapper;

import com.sys_nova.pos_system.model.OrderItem;
import com.sys_nova.pos_system.payload.dto.OrderItemDTO;
import com.sys_nova.pos_system.payload.dto.ProductDTO;

public class OrderItemMapper {
    public static OrderItemDTO toDTO(OrderItem item) {
        if (item == null) return null;
        return OrderItemDTO.builder()
                .id(item.getId())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .productId(item.getProduct() != null ? item.getProduct().getId() : null)
                .orderId(item.getOrder() != null ? item.getOrder().getId() : null)
                // هنا بنحول الـ Product لـ DTO لو محتاجين تفاصيله في الـ Frontend
                .product(item.getProduct() != null ? ProductDTO.builder()
                        .id(item.getProduct().getId())
                        .name(item.getProduct().getName())
                        .build() : null)
                .build();
    }
}