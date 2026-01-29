package com.sys_nova.pos_system.Repository;

import com.sys_nova.pos_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email); // دي اللي هتشيل اللون الأحمر من الـ Service
}