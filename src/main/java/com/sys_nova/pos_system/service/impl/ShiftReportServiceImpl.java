package com.sys_nova.pos_system.service.impl;

import com.sys_nova.pos_system.mapper.ShiftReportMapper;
import com.sys_nova.pos_system.model.*;
import com.sys_nova.pos_system.payload.dto.ShiftReportDTO;
import com.sys_nova.pos_system.repository.*;
import com.sys_nova.pos_system.service.ShiftReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShiftReportServiceImpl implements ShiftReportService {

    @Autowired
    private ShiftReportRepository shiftReportRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RefundRepository refundRepository;

    @Override
    @Transactional
    public ShiftReportDTO startShift(User cashier) throws Exception {
        // البحث عن أي وردية لم تغلق بعد لهذا الكاشير
        var activeShift = shiftReportRepository.findTopByCashierAndShiftEndIsNullOrderByShiftStartDesc(cashier);
        if (activeShift.isPresent()) {
            throw new Exception("يوجد وردية مفتوحة بالفعل لهذا المستخدم. يرجى إغلاقها أولاً.");
        }

        ShiftReport shift = ShiftReport.builder()
                .cashier(cashier)
                .branch(cashier.getBranch())
                .shiftStart(LocalDateTime.now())
                .totalSales(0.0)
                .totalRefunds(0.0)
                .netSale(0.0)
                .totalOrders(0)
                .build();

        return ShiftReportMapper.toDTO(shiftReportRepository.save(shift));
    }

    @Override
    @Transactional
    public ShiftReportDTO endShift(Long shiftReportId) throws Exception {
        ShiftReport shift = shiftReportRepository.findById(shiftReportId)
                .orElseThrow(() -> new Exception("تقرير الوردية غير موجود"));

        if (shift.getShiftEnd() != null) {
            throw new Exception("هذه الوردية مغلقة بالفعل.");
        }

        // تحديث الحسابات النهائية قبل الإغلاق
        calculateShiftMetrics(shift);
        shift.setShiftEnd(LocalDateTime.now());

        return ShiftReportMapper.toDTO(shiftReportRepository.save(shift));
    }

    @Override
    @Transactional(readOnly = true)
    public ShiftReportDTO getCurrentShiftProgress(User cashier) throws Exception {
        ShiftReport shift = shiftReportRepository.findTopByCashierAndShiftEndIsNullOrderByShiftStartDesc(cashier)
                .orElseThrow(() -> new Exception("لا توجد وردية مفتوحة حالياً لهذا الكاشير"));

        calculateShiftMetrics(shift);
        return ShiftReportMapper.toDTO(shift);
    }

    // ميثود مساعدة لحساب المبيعات والمرتجعات من الجداول الأخرى
    private void calculateShiftMetrics(ShiftReport shift) {
        List<Order> orders = orderRepository.findByShiftReportId(shift.getId());
        List<Refund> refunds = refundRepository.findByShiftReportId(shift.getId());

        double totalSales = orders.stream().mapToDouble(Order::getTotalAmount).sum();
        double totalRefunds = refunds.stream().mapToDouble(Refund::getAmount).sum();

        shift.setTotalSales(totalSales);
        shift.setTotalRefunds(totalRefunds);
        shift.setTotalOrders(orders.size());
        shift.setRecentOrders(orders); // لملء البيانات في الـ DTO
        shift.setRefunds(refunds);
        shift.calculateNet(); // ميثود الحساب الموجودة في الـ Entity
    }

    @Override
    public ShiftReportDTO getShiftReportById(Long id) {
        return shiftReportRepository.findById(id)
                .map(ShiftReportMapper::toDTO)
                .orElse(null);
    }

    @Override
    public List<ShiftReportDTO> getAllShiftReports() {
        return shiftReportRepository.findAll().stream()
                .map(ShiftReportMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShiftReportDTO> getShiftReportsByBranchId(Long branchId) {
        return shiftReportRepository.findByBranchId(branchId).stream()
                .map(ShiftReportMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShiftReportDTO> getShiftReportsByCashierId(Long cashierId) {
        return shiftReportRepository.findByCashierId(cashierId).stream()
                .map(ShiftReportMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ShiftReportDTO getShiftByCashierAndDate(Long cashierId, LocalDateTime date) throws Exception {
        // يتم البحث عن وردية بدأت في هذا التاريخ لهذا الكاشير
        LocalDateTime startOfDay = date.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = date.toLocalDate().atTime(23, 59, 59);
        
        // تحتاج لإضافة ميثود في الـ Repository لهذا البحث
        return null; 
    }
}