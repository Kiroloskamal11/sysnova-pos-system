package com.sys_nova.pos_system.service;

import com.sys_nova.pos_system.payload.dto.InventoryDTO;
import java.util.List;

public interface InventoryService {
    // ميثودز الفيديو (CRUD)
    InventoryDTO createInventory(InventoryDTO inventoryDTO);
    InventoryDTO updateInventory(InventoryDTO inventoryDTO);
    InventoryDTO getInventoryById(Long id);
    void deleteInventory(Long id);
    
    // ميثودز البحث والمنطق المضافة
    List<InventoryDTO> getAllInventoryByBranchId(Long branchId);
    InventoryDTO getInventoryByProductIdAndBranchId(Long productId, Long branchId);
    InventoryDTO updateStock(Long branchId, Long productId, Integer amount);
}














// package com.sys_nova.pos_system.service;

// import com.sys_nova.pos_system.payload.dto.InventoryDTO;
// import java.util.List;

// public interface InventoryService {
//     InventoryDTO updateStock(Long branchId, Long productId, Integer amount);
//     List<InventoryDTO> getBranchInventory(Long branchId);
//     InventoryDTO getProductStockInBranch(Long branchId, Long productId);
//     // التأكد إذا كان المنتج متاح في الفرع بكمية معينة قبل البيع
//     boolean isAvailable(Long branchId, Long productId, Integer quentity);

//     // جلب المنتجات اللي وصلت لـ "حد الأمان" (أقل من كمية معينة)
//     List<InventoryDTO> getLowStockProducts(Long branchId, Integer threshold);

//     // تصفير المخزون لمنتج معين في فرع (مثلاً في حالة التلف)
//     void resetStock(Long branchId, Long productId);
// }