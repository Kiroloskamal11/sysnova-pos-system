package com.sys_nova.pos_system.payload.dto;

import java.time.LocalDateTime;

import com.sys_nova.pos_system.domain.PaymentType;
import com.sys_nova.pos_system.model.ShiftReport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefundDTO {

    private Long id;


    private OrderDTO order;
    private Long orderId;

    private String reason;

    private Double amount;


    private ShiftReport shiftReport;
    private Long shiftReportId;


    private UserDto cashier;
    private String cashierName;
    private Long cashierId;


    private BranchDTO branch;
    private Long branchId;


    private PaymentType paymentType;


    private LocalDateTime createdAt;



    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public OrderDTO getOrder() {
        return order;
    }


    public void setOrder(OrderDTO order) {
        this.order = order;
    }


    public Long getOrderId() {
        return orderId;
    }


    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }


    public String getReason() {
        return reason;
    }


    public void setReason(String reason) {
        this.reason = reason;
    }


    public Double getAmount() {
        return amount;
    }


    public void setAmount(Double amount) {
        this.amount = amount;
    }


    public ShiftReport getShiftReport() {
        return shiftReport;
    }


    public void setShiftReport(ShiftReport shiftReport) {
        this.shiftReport = shiftReport;
    }


    public Long getShiftReportId() {
        return shiftReportId;
    }


    public void setShiftReportId(Long shiftReportId) {
        this.shiftReportId = shiftReportId;
    }



    public BranchDTO getBranch() {
        return branch;
    }


    public void setBranch(BranchDTO branch) {
        this.branch = branch;
    }


    public PaymentType getPaymentType() {
        return paymentType;
    }


    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public UserDto getCashier() {
        return cashier;
    }


    public void setCashier(UserDto cashier) {
        this.cashier = cashier;
    }


    public String getCashierName() {
        return cashierName;
    }


    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }


    public Long getBranchId() {
        return branchId;
    }


    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }


    public Long getCashierId() {
        return cashierId;
    }


    public void setCashierId(Long cashierId) {
        this.cashierId = cashierId;
    }
    

    

    



    


}
