package com.sys_nova.pos_system.controller;

import com.sys_nova.pos_system.payload.dto.BranchDTO;
import com.sys_nova.pos_system.payload.response.ApiResponse;
import com.sys_nova.pos_system.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    // 1. إنشاء فرع جديد
    @PostMapping
    public ResponseEntity<BranchDTO> createBranch(@RequestBody BranchDTO branchDTO) {
        BranchDTO createdBranch = branchService.createBranch(branchDTO);
        return new ResponseEntity<>(createdBranch, HttpStatus.CREATED);
    }

    // 2. تحديث بيانات فرع موجود
    @PutMapping("/{id}")
    public ResponseEntity<BranchDTO> updateBranch(@PathVariable Long id, @RequestBody BranchDTO branchDTO) {
        return ResponseEntity.ok(branchService.updateBranch(id, branchDTO));
    }

    // 3. جلب كل فروع محل معين (باستخدام الـ storeId)
    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<BranchDTO>> getBranchesByStore(@PathVariable Long storeId) {
        return ResponseEntity.ok(branchService.getBranchesByStore(storeId));
    }

    // 4. جلب بيانات فرع واحد بالـ ID
    @GetMapping("/{id}")
    public ResponseEntity<BranchDTO> getBranchById(@PathVariable Long id) {
        return ResponseEntity.ok(branchService.getBranchById(id));
    }

    // 5. حذف فرع
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBranch(@PathVariable Long id) {
        branchService.deleteBranch(id);
        return ResponseEntity.ok(new ApiResponse("Branch deleted successfully", true));
    }
}