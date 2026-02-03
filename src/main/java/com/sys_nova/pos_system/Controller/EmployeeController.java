package com.sys_nova.pos_system.controller;

import com.sys_nova.pos_system.payload.dto.UserDto;
import com.sys_nova.pos_system.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<UserDto> createEmployee(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(employeeService.createEmployee(userDto));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<UserDto>> getEmployeesByBranch(@PathVariable Long branchId) {
        return ResponseEntity.ok(employeeService.getEmployeesByBranch(branchId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}