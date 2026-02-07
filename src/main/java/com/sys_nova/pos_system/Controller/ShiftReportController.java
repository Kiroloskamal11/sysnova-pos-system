package com.sys_nova.pos_system.controller;

import com.sys_nova.pos_system.model.User;
import com.sys_nova.pos_system.payload.dto.ShiftReportDTO;
import com.sys_nova.pos_system.service.ShiftReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/shifts")
public class ShiftReportController {

    @Autowired
    private ShiftReportService shiftReportService;

    // 1. فتح وردية جديدة للكاشير الحالي
    @PostMapping("/start")
    public ResponseEntity<?> startShift(@AuthenticationPrincipal User cashier) {
        try {
            ShiftReportDTO report = shiftReportService.startShift(cashier);
            return ResponseEntity.ok(report);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("خطأ في فتح الوردية: " + e.getMessage());
        }
    }

    // 2. متابعة حالة الوردية الحالية (المبيعات والمرتجع اللحظي)
    @GetMapping("/current")
    public ResponseEntity<?> getCurrentProgress(@AuthenticationPrincipal User cashier) {
        try {
            ShiftReportDTO report = shiftReportService.getCurrentShiftProgress(cashier);
            return ResponseEntity.ok(report);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // 3. إنهاء الوردية الحالية
    @PutMapping("/end/{id}")
    public ResponseEntity<?> endShift(@PathVariable Long id) {
        try {
            ShiftReportDTO report = shiftReportService.endShift(id);
            return ResponseEntity.ok(report);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("خطأ في إغلاق الوردية: " + e.getMessage());
        }
    }

    // 4. جلب تقرير معين عن طريق الـ ID
    @GetMapping("/{id}")
    public ResponseEntity<ShiftReportDTO> getById(@PathVariable Long id) {
        ShiftReportDTO report = shiftReportService.getShiftReportById(id);
        return report != null ? ResponseEntity.ok(report) : ResponseEntity.notFound().build();
    }

    // 5. جلب كل التقارير (غالباً للأدمن)
    @GetMapping("/all")
    public ResponseEntity<List<ShiftReportDTO>> getAllReports() {
        return ResponseEntity.ok(shiftReportService.getAllShiftReports());
    }

    // 6. جلب تقارير فرع معين
    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<ShiftReportDTO>> getByBranch(@PathVariable Long branchId) {
        return ResponseEntity.ok(shiftReportService.getShiftReportsByBranchId(branchId));
    }

    // 7. جلب تقارير كاشير معين
    @GetMapping("/cashier/{cashierId}")
    public ResponseEntity<List<ShiftReportDTO>> getByCashier(@PathVariable Long cashierId) {
        return ResponseEntity.ok(shiftReportService.getShiftReportsByCashierId(cashierId));
    }

    // 8. جلب تقرير كاشير في تاريخ محدد
    @GetMapping("/history")
    public ResponseEntity<?> getByDate(
            @RequestParam Long cashierId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        try {
            return ResponseEntity.ok(shiftReportService.getShiftByCashierAndDate(cashierId, date));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}