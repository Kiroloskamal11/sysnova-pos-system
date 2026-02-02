package com.sys_nova.pos_system.service.impl;

import com.sys_nova.pos_system.mapper.InventoryMapper;
import com.sys_nova.pos_system.model.Branch;
import com.sys_nova.pos_system.model.Inventory;
import com.sys_nova.pos_system.model.Product;
import com.sys_nova.pos_system.payload.dto.InventoryDTO;
import com.sys_nova.pos_system.repository.BranchRepository;
import com.sys_nova.pos_system.repository.InventoryRepository;
import com.sys_nova.pos_system.repository.ProductRepository;
import com.sys_nova.pos_system.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BranchRepository branchRepository;

    // --- 1. عمليات الـ CRUD (من الفيديو) ---

    @Override
    public InventoryDTO createInventory(InventoryDTO inventoryDTO) {
        Product product = productRepository.findById(inventoryDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Branch branch = branchRepository.findById(inventoryDTO.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        Inventory inventory = Inventory.builder()
                .product(product)
                .branch(branch)
                .quentity(inventoryDTO.getQuentity())
                .build();

        return InventoryMapper.toDTO(inventoryRepository.save(inventory));
    }

    @Override
    public InventoryDTO updateInventory(InventoryDTO inventoryDTO) {
        Inventory inventory = inventoryRepository.findById(inventoryDTO.getId())
                .orElseThrow(() -> new RuntimeException("Inventory record not found"));
        
        inventory.setQuentity(inventoryDTO.getQuentity());
        inventory.setUpdatedAt(LocalDateTime.now());
        
        return InventoryMapper.toDTO(inventoryRepository.save(inventory));
    }

    @Override
    public InventoryDTO getInventoryById(Long id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
        return InventoryMapper.toDTO(inventory);
    }

    @Override
    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }

    // --- 2. عمليات البحث والمنطق (المدمجة) ---

    @Override
    public List<InventoryDTO> getAllInventoryByBranchId(Long branchId) {
        return inventoryRepository.findByBranchId(branchId).stream()
                .map(InventoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryDTO getInventoryByProductIdAndBranchId(Long productId, Long branchId) {
        Inventory inventory = inventoryRepository.findByProductIdAndBranchId(productId, branchId);
        return InventoryMapper.toDTO(inventory);
    }

    @Override
    @Transactional
    public InventoryDTO updateStock(Long branchId, Long productId, Integer amount) {
        Inventory inventory = inventoryRepository.findByProductIdAndBranchId(productId, branchId);

        if (inventory == null) {
            // لو مش موجود، بنستخدم ميثود الـ Create اللي عملناها فوق
            InventoryDTO newInv = InventoryDTO.builder()
                    .branchId(branchId)
                    .productId(productId)
                    .quentity(amount)
                    .build();
            return createInventory(newInv);
        } else {
            // لو موجود، بنزود على الكمية الحالية
            inventory.setQuentity(inventory.getQuentity() + amount);
            inventory.setUpdatedAt(LocalDateTime.now());
            return InventoryMapper.toDTO(inventoryRepository.save(inventory));
        }
    }
}




































// package com.sys_nova.pos_system.service.impl;

// import com.sys_nova.pos_system.mapper.InventoryMapper;
// import com.sys_nova.pos_system.model.Branch;
// import com.sys_nova.pos_system.model.Inventory;
// import com.sys_nova.pos_system.model.Product;
// import com.sys_nova.pos_system.payload.dto.InventoryDTO;
// import com.sys_nova.pos_system.repository.BranchRepository;
// import com.sys_nova.pos_system.repository.InventoryRepository;
// import com.sys_nova.pos_system.repository.ProductRepository;
// import com.sys_nova.pos_system.service.InventoryService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.stream.Collectors;

// @Service
// public class InventoryServiceImpl implements InventoryService {

//     @Autowired
//     private InventoryRepository inventoryRepository;

//     @Autowired
//     private ProductRepository productRepository;

//     @Autowired
//     private BranchRepository branchRepository;

//     @Override
//     @Transactional
//     public InventoryDTO updateStock(Long branchId, Long productId, Integer amount) {
//         Inventory inventory = inventoryRepository.findByProductIdAndBranchId(productId, branchId);

//         if (inventory == null) {
//             // لو أول مرة يدخل المنتج الفرع ده
//             Product product = productRepository.findById(productId)
//                     .orElseThrow(() -> new RuntimeException("Product not found"));
//             Branch branch = branchRepository.findById(branchId)
//                     .orElseThrow(() -> new RuntimeException("Branch not found"));

//             inventory = Inventory.builder()
//                     .product(product)
//                     .branch(branch)
//                     .quentity(amount)
//                     .updatedAt(LocalDateTime.now())
//                     .build();
//         } else {
//             // تحديث الكمية الموجودة
//             inventory.setQuentity(inventory.getQuentity() + amount);
//             inventory.setUpdatedAt(LocalDateTime.now());
//         }

//         return InventoryMapper.toDTO(inventoryRepository.save(inventory));
//     }

//     @Override
//     public List<InventoryDTO> getBranchInventory(Long branchId) {
//         return inventoryRepository.findByBranchId(branchId).stream()
//                 .map(InventoryMapper::toDTO)
//                 .collect(Collectors.toList());
//     }

//     @Override
//     public InventoryDTO getProductStockInBranch(Long branchId, Long productId) {
//         Inventory inventory = inventoryRepository.findByProductIdAndBranchId(productId, branchId);
//         return InventoryMapper.toDTO(inventory);
//     }


//     @Override
//     public boolean isAvailable(Long branchId, Long productId, Integer quentity) {
//         Inventory inventory = inventoryRepository.findByProductIdAndBranchId(productId, branchId);
//         return inventory != null && inventory.getQuentity() >= quentity;
//     }
    
//     @Override
//     public List<InventoryDTO> getLowStockProducts(Long branchId, Integer threshold) {
//         // بنجيب كل مخزون الفرع ونفلتر اللي أقل من الـ threshold
//         return inventoryRepository.findByBranchId(branchId).stream()
//                 .filter(inv -> inv.getQuentity() <= threshold)
//                 .map(InventoryMapper::toDTO)
//                 .collect(Collectors.toList());
//     }
    
//     @Override
//     @Transactional
//     public void resetStock(Long branchId, Long productId) {
//         Inventory inventory = inventoryRepository.findByProductIdAndBranchId(productId, branchId);
//         if (inventory != null) {
//             inventory.setQuentity(0);
//             inventoryRepository.save(inventory);
//         }
//     }
// }