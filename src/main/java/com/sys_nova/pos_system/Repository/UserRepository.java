package com.sys_nova.pos_system.repository;

import com.sys_nova.pos_system.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email); // دي اللي هتشيل اللون الأحمر من الـ Service

    // الميثود الجديدة اللي هنحتاجها في الـ EmployeeService
    List<User> findByBranchId(Long branchId);
}