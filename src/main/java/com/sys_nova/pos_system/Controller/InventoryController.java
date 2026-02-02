package com.sys_nova.pos_system.controller;

import com.sys_nova.pos_system.payload.dto.InventoryDTO;
import com.sys_nova.pos_system.payload.response.ApiResponse;
import com.sys_nova.pos_system.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // إنشاء سجل مخزني (POST)
    @PostMapping
    public ResponseEntity<InventoryDTO> create(@RequestBody InventoryDTO dto) {
        return new ResponseEntity<>(inventoryService.createInventory(dto), HttpStatus.CREATED);
    }

    // تحديث كمية يدوياً (PUT)
    @PutMapping
    public ResponseEntity<InventoryDTO> update(@RequestBody InventoryDTO dto) {
        return ResponseEntity.ok(inventoryService.updateInventory(dto));
    }

    // تزويد أو نقص المخزون بعملية حسابية (POST)
    @PostMapping("/update-stock")
    public ResponseEntity<InventoryDTO> updateStock(
            @RequestParam Long branchId,
            @RequestParam Long productId,
            @RequestParam Integer amount) {
        return ResponseEntity.ok(inventoryService.updateStock(branchId, productId, amount));
    }

    // جلب كل مخزون فرع معين (GET)
    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<InventoryDTO>> getByBranch(@PathVariable Long branchId) {
        return ResponseEntity.ok(inventoryService.getAllInventoryByBranchId(branchId));
    }

    // جلب سجل منتج معين في فرع معين (GET)
    @GetMapping("/product-branch")
    public ResponseEntity<InventoryDTO> getByProductAndBranch(
            @RequestParam Long productId,
            @RequestParam Long branchId) {
        return ResponseEntity.ok(inventoryService.getInventoryByProductIdAndBranchId(productId, branchId));
    }

    // حذف سجل مخزني (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.ok(new ApiResponse("Inventory record deleted successfully", true));
    }
}