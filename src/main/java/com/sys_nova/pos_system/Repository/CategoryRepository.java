package com.sys_nova.pos_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sys_nova.pos_system.model.Category;



public interface CategoryRepository extends JpaRepository<Category ,Long> {

    List<Category> findByStoreId (Long storeId);

}
