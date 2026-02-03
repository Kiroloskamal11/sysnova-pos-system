package com.sys_nova.pos_system.controller;

import com.sys_nova.pos_system.model.Customer;
import com.sys_nova.pos_system.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // 1. إنشاء عميل جديد
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    // 2. البحث عن عملاء (بالاسم أو الإيميل)
    // URL: /api/customers/search?keyword=ahmed
     @GetMapping("/search")
    public ResponseEntity<List<Customer>> searchCustomers(
        @RequestParam(value = "keyword", required = false) String keyword) {
        
        // لو الـ keyword مش مبعوتة أصلاً أو فاضية، نرجع لستة فاضية بدل ما نكلم الـ DB
        if (keyword == null || keyword.trim().isEmpty()) {
            return ResponseEntity.ok(new java.util.ArrayList<>());
        }
    
        List<Customer> customers = customerService.searchCustomers(keyword.trim());
        return ResponseEntity.ok(customers);
    }

    // 3. جلب كل العملاء
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    // 4. جلب عميل محدد بالـ ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    // 5. تحديث بيانات عميل
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer)
            throws Exception {
        return ResponseEntity.ok(customerService.updateCustomer(id, customer));
    }

    // 6. حذف عميل
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) throws Exception {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Customer deleted successfully!");
    }
}