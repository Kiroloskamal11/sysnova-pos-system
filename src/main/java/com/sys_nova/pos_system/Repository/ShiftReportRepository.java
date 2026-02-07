package com.sys_nova.pos_system.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sys_nova.pos_system.model.ShiftReport;
import com.sys_nova.pos_system.model.User;

public interface ShiftReportRepository extends JpaRepository<ShiftReport, Long> {

    List<ShiftReport> findByBranchId(Long id);

    List<ShiftReport> findByCashierId(Long id);

    Optional<ShiftReport> findTopByCashierAndShiftEndIsNullOrderByShiftStartDesc(User cashier);

    Optional<ShiftReport> findByCashierAndShiftStartBetween(User cashier,
            LocalDateTime start,
            LocalDateTime end

    );

    

}
