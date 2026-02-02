package com.sys_nova.pos_system.repository;

import com.sys_nova.pos_system.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    // ميثود لجلب المتجر عن طريق الـ Admin ID
    Store findByStoreAdminId(Long adminId);
}