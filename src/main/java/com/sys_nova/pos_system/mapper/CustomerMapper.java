package com.sys_nova.pos_system.mapper;

import com.sys_nova.pos_system.model.Customer;
import com.sys_nova.pos_system.payload.dto.CustomerDTO;

public class CustomerMapper {
    public static CustomerDTO toDTO(Customer customer) {
        if (customer == null) return null;
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setPhone(customer.getPhone());
        dto.setEmail(customer.getEmail());
        dto.setAddress(customer.getAddress());
        dto.setTotalSpent(customer.getTotalSpent());
        dto.setLoyaltyPoints(customer.getLoyaltyPoints());
        return dto;
    }

    public static Customer toEntity(CustomerDTO dto) {
        if (dto == null) return null;
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setName(dto.getName());
        customer.setPhone(dto.getPhone());
        customer.setEmail(dto.getEmail());
        customer.setAddress(dto.getAddress());
        // الـ Points والـ TotalSpent بياخدوا قيمهم الافتراضية من الـ Entity لو جديدة
        return customer;
    }
}