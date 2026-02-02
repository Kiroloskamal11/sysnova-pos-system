package com.sys_nova.pos_system.controller;

import com.sys_nova.pos_system.domain.StoreStatus;
import com.sys_nova.pos_system.model.Store;
import com.sys_nova.pos_system.model.User;
import com.sys_nova.pos_system.payload.dto.StoreDTO;
import com.sys_nova.pos_system.payload.response.ApiResponse;
import com.sys_nova.pos_system.service.StoreService;
import com.sys_nova.pos_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private UserService userService;

    // 1. إنشاء محل جديد (للمدير)
    @PostMapping
    public ResponseEntity<StoreDTO> createStore(
            @RequestBody StoreDTO storeDTO,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        StoreDTO createdStore = storeService.createStore(storeDTO, user);

        return new ResponseEntity<>(createdStore, HttpStatus.CREATED);
    }

    // 2. جلب كل المحلات (لأدمن النظام مثلاً)
    @GetMapping
    public ResponseEntity<List<StoreDTO>> getAllStores() {
        List<StoreDTO> stores = storeService.getAllStores();
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    // 3. جلب محل معين بواسطة الـ ID
    @GetMapping("/{id}")
    public ResponseEntity<StoreDTO> getStoreById(@PathVariable Long id) throws Exception {
        StoreDTO storeDTO = storeService.getStoreById(id);
        return new ResponseEntity<>(storeDTO, HttpStatus.OK);
    }

    // 4. تعديل بيانات المحل
    @PutMapping("/{id}")
    public ResponseEntity<StoreDTO> updateStore(
            @PathVariable Long id,
            @RequestBody StoreDTO storeDTO) throws Exception {

        StoreDTO updatedStore = storeService.updateStore(id, storeDTO);
        return new ResponseEntity<>(updatedStore, HttpStatus.OK);
    }

    // 5. حذف محل (باستخدام ApiResponse)
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteStore(@PathVariable Long id) throws Exception {
        storeService.deleteStore(id);

        ApiResponse res = new ApiResponse();
        res.setMessage("Store deleted successfully");
        res.setStatus(true);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // 6. جلب محل الأدمن الحالي (من التوكن)
    @GetMapping("/admin")
    public ResponseEntity<Store> getStoreByAdmin(
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        // هنا بننادي ميثود الخدمة اللي بترجع Entity زي ما الـ Interface طلب
        Store store = storeService.getStoreByAdmin();

        return new ResponseEntity<>(store, HttpStatus.OK);
    }

    // تغيير حالة المحل (تفعيل/تعطيل)
    @PutMapping("/{id}/status")
    public ResponseEntity<StoreDTO> moderateStore(
            @PathVariable Long id,
            @RequestParam StoreStatus status) throws Exception {

        StoreDTO updatedStore = storeService.moderateStore(id, status);
        return new ResponseEntity<>(updatedStore, HttpStatus.OK);
    }
}