package com.sys_nova.pos_system.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sys_nova.pos_system.model.Order;
import com.sys_nova.pos_system.model.User;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // ضيف السطر ده هنا
    List<Order> findByShiftReportId(Long shiftId);

    List<Order> findByCustomerId(Long customerId);
    
    List<Order> findByBranchId(Long branchId);

    // تعديل الاسم من cashairId لـ cashierId
    List<Order> findByCashierId(Long cashierId);

    // تعديل التسمية لتطابق الحقول في الـ Entity (branch.id و createdAt)
    List<Order> findByBranchIdAndCreatedAtBetween(Long branchId, LocalDateTime from, LocalDateTime to);

    // تعديل التسمية من cashiar لـ cashier
    List<Order> findByCashierAndCreatedAtBetween(User cashier, LocalDateTime from, LocalDateTime to);

    List<Order> findTop5ByBranchIdOrderByCreatedAtDesc(Long branchId);
}