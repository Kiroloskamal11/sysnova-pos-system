package com.sys_nova.pos_system.mapper;

import com.sys_nova.pos_system.model.Order;
import com.sys_nova.pos_system.payload.dto.OrderDTO;
import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDTO toDTO(Order order) {
        if (order == null) return null;
        return OrderDTO.builder()
                .id(order.getId())
                .totalAmount(order.getTotalAmount())
                .createdAt(order.getCreatedAt())
                .paymentType(order.getPaymentType())
                .branchId(order.getBranch() != null ? order.getBranch().getId() : null)
                .customerId(order.getCustomer() != null ? order.getCustomer().getId() : null)
                .items(order.getItems() != null ? 
                       order.getItems().stream().map(OrderItemMapper::toDTO).collect(Collectors.toList()) : null)
                .build();
    }
}