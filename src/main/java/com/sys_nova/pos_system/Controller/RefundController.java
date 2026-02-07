package com.sys_nova.pos_system.controller;

import com.sys_nova.pos_system.payload.dto.RefundDTO;
import com.sys_nova.pos_system.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/refunds")
public class RefundController {

    @Autowired
    private RefundService refundService;

    @PostMapping("/create")
    public ResponseEntity<?> createRefund(@RequestBody RefundDTO refundDTO) {
        try {
            return ResponseEntity.ok(refundService.createRefund(refundDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<RefundDTO>> getAllRefunds() throws Exception {
        return ResponseEntity.ok(refundService.getAllRefunds());
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<RefundDTO>> getRefundsByBranch(@PathVariable Long branchId) throws Exception {
        return ResponseEntity.ok(refundService.getRefundByBranch(branchId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RefundDTO> getRefundById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(refundService.getRefundById(id));
    }
}