package com.sys_nova.pos_system.service;

import java.time.LocalDateTime;
import java.util.List;

import com.sys_nova.pos_system.model.User;
import com.sys_nova.pos_system.payload.dto.ShiftReportDTO;

public interface ShiftReportService {

    ShiftReportDTO startShift(User cashier) throws Exception;

    ShiftReportDTO endShift(Long shiftReportId) throws Exception;

    ShiftReportDTO getCurrentShiftProgress(User cashier) throws Exception;

    ShiftReportDTO getShiftReportById(Long id);

    List<ShiftReportDTO> getAllShiftReports();

    List<ShiftReportDTO> getShiftReportsByBranchId(Long branchId);

    List<ShiftReportDTO> getShiftReportsByCashierId(Long cashierId);

    ShiftReportDTO getShiftByCashierAndDate(Long cashierId, LocalDateTime date) throws Exception;
}