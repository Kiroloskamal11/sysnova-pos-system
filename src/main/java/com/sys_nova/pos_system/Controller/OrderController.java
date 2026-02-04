package com.sys_nova.pos_system.controller;

import com.sys_nova.pos_system.payload.dto.OrderDTO;
import com.sys_nova.pos_system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // إنشاء طلب جديد
    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO) {
        try {
            OrderDTO savedOrder = orderService.createorder(orderDTO);
            return ResponseEntity.ok(savedOrder);
        } catch (Exception e) {
            // هنا بنرجع رسالة الخطأ واضحة (زي نفاذ المخزن) بدال ما السيستم يقع
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // جلب طلب محدد بالـ ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(orderService.getorderById(id));
    }

    // جلب طلبات اليوم لفرع معين
    // العنوان في Postman: /api/orders/branch/1/today
    @GetMapping("/branch/{branchId}/today")
    public ResponseEntity<List<OrderDTO>> getTodayOrders(@PathVariable Long branchId) throws Exception {
        return ResponseEntity.ok(orderService.getToday0rdersByBranch(branchId));
    }

    // جلب آخر 5 طلبات لفرع معين
    // العنوان في Postman: /api/orders/branch/1/recent
    @GetMapping("/branch/{branchId}/recent")
    public ResponseEntity<List<OrderDTO>> getRecentOrders(@PathVariable Long branchId) throws Exception {
        return ResponseEntity.ok(orderService.getTop5RecentordersByBranchId(branchId));
    }
}