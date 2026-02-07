package com.sys_nova.pos_system.service.impl;

import com.sys_nova.pos_system.mapper.RefundMapper;
import com.sys_nova.pos_system.model.*;
import com.sys_nova.pos_system.payload.dto.RefundDTO;
import com.sys_nova.pos_system.repository.*;
import com.sys_nova.pos_system.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RefundServiceImpl implements RefundService {

    @Autowired private RefundRepository refundRepository;
    @Autowired private OrderRepository orderRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private BranchRepository branchRepository;
    @Autowired private UserRepository userRepository; // تأكد أن الاسم مطابق عندك (User أو Cashier)

    @Override
    @Transactional
    public RefundDTO createRefund(RefundDTO refundDTO) throws Exception {
        // 1. جلب الفاتورة الأصلية
        Order order = orderRepository.findById(refundDTO.getOrderId())
                .orElseThrow(() -> new Exception("Order not found"));

        // 2. جلب الفرع والكاشير
        Branch branch = branchRepository.findById(refundDTO.getBranchId())
                .orElseThrow(() -> new Exception("Branch not found"));

        User cashier = userRepository.findById(refundDTO.getCashierId())
                .orElseThrow(() -> new Exception("Cashier not found"));

        // 3. تحديث المخزن: زيادة الكميات اللي كانت في الفاتورة
        for (OrderItem item : order.getItems()) {
            Product product = item.getProduct();
            product.setQuantity(product.getQuantity() + item.getQuantity());
            productRepository.save(product);
        }

        // 4. بناء الـ Refund Entity
        Refund refund = new Refund();
        refund.setOrder(order);
        refund.setBranch(branch);
        refund.setCashier(cashier);
        refund.setAmount(order.getTotalAmount()); // استرجاع كامل المبلغ
        refund.setReason(refundDTO.getReason());
        refund.setPaymentType(order.getPaymentType());
        
        // هنا تجاهلنا الـ ShiftReport خالص لأنه مش موجود لسه
        
        Refund savedRefund = refundRepository.save(refund);
        return RefundMapper.toDTO(savedRefund);
    }

    @Override
    public List<RefundDTO> getAllRefunds() {
        return refundRepository.findAll().stream()
                .map(RefundMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<RefundDTO> getRefundByBranch(Long branchId) {
        return refundRepository.findByBranchId(branchId).stream()
                .map(RefundMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public RefundDTO getRefundById(Long refundId) throws Exception {
        Refund refund = refundRepository.findById(refundId)
                .orElseThrow(() -> new Exception("Refund record not found"));
        return RefundMapper.toDTO(refund);
    }

    @Override
    public void deleteRefund(Long refundId) {
        refundRepository.deleteById(refundId);
    }

    @Override
    public List<RefundDTO> getRefundByCashier(Long cashierId) {
        return refundRepository.findByCashierId(cashierId).stream()
                .map(RefundMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<RefundDTO> getRefundByShiftReport(Long shiftReportId) {
        return refundRepository.findByShiftReportId(shiftReportId).stream()
                .map(RefundMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<RefundDTO> getRefundByCashierAndDateRange(Long cashierId, LocalDateTime start, LocalDateTime end) {
        return refundRepository.findByCashierIdAndCreatedAtBetween(cashierId, start, end).stream()
                .map(RefundMapper::toDTO).collect(Collectors.toList());
    }
}