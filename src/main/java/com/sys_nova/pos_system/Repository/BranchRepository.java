package com.sys_nova.pos_system.repository;

import com.sys_nova.pos_system.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    List<Branch> findByStoreId(Long storeId);
}