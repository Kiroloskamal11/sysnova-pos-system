package com.sys_nova.pos_system.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.sys_nova.pos_system.model.Order;
import com.sys_nova.pos_system.model.Product;
import com.sys_nova.pos_system.model.Refund;
import com.sys_nova.pos_system.model.ShiftReport;
import com.sys_nova.pos_system.payload.dto.OrderDTO;
import com.sys_nova.pos_system.payload.dto.ProductDTO;
import com.sys_nova.pos_system.payload.dto.RefundDTO;
import com.sys_nova.pos_system.payload.dto.ShiftReportDTO;

public class ShiftReportMapper {
    public static ShiftReportDTO toDTO(ShiftReport entity) {
        if (entity == null)
            return null;

        return ShiftReportDTO.builder()
                .id(entity.getId())
                .shiftStart(entity.getShiftStart())
                .shiftEnd(entity.getShiftEnd())
                .totalSales(entity.getTotalSales())
                .totalRefunds(entity.getTotalRefunds())
                .totalOrders(entity.getTotalOrders())
                .netSale(entity.getNetSale())
                .cashierId(entity.getCashier() != null ? entity.getCashier().getId() : null)
                .branchId(entity.getBranch() != null ? entity.getBranch().getId() : null)
                .recentOrders(mapOrders(entity.getRecentOrders()))
                .topSellingProducts(mapProducts(entity.getTopSellingProducts()))
                .refunds(mapRefunds(entity.getRefunds()))
                .paymentSummaries(entity.getPaymentSummaries())
                .build();
    }

    private static List<OrderDTO> mapOrders(List<Order> recentOrders) {
        if (recentOrders == null || recentOrders.isEmpty())
            return null;
        return recentOrders.stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    private static List<RefundDTO> mapRefunds(List<Refund> refunds) {
        if (refunds == null || refunds.isEmpty())
            return null;
        return refunds.stream()
                .map(RefundMapper::toDTO)
                .collect(Collectors.toList());
    }

    private static List<ProductDTO> mapProducts(List<Product> topSellingProducts) {
        if (topSellingProducts == null || topSellingProducts.isEmpty())
            return null;
        return topSellingProducts.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }
}