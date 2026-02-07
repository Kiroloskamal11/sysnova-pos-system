package com.sys_nova.pos_system.mapper;

import com.sys_nova.pos_system.model.Refund;
import com.sys_nova.pos_system.payload.dto.RefundDTO;

public class RefundMapper {

    public static RefundDTO toDTO(Refund refund) {
        if (refund == null) return null;

        return RefundDTO.builder()
                .id(refund.getId())
                .orderId(refund.getOrder() != null ? refund.getOrder().getId() : null)
                .branchId(refund.getBranch() != null ? refund.getBranch().getId() : null)
                .cashierId(refund.getCashier() != null ? refund.getCashier().getId() : null)
                .shiftReportId(refund.getShiftReport() != null ? refund.getShiftReport().getId() : null)
                .amount(refund.getAmount())
                .reason(refund.getReason())
                .paymentType(refund.getPaymentType())
                // هنا السر: لازم نستخدم .createdAt() لأن ده الاسم الموجود في الـ RefundDTO بتاعك
                .createdAt(refund.getCreatedAt()) 
                .build();
    }
}