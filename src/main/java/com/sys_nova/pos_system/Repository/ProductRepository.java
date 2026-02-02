package com.sys_nova.pos_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sys_nova.pos_system.model.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByStoreId(Long storeId);



@Query("SELECT p FROM Product p " + // ضيف مسافة بعد حرف الـ p هنا
       "WHERE p.store.id = :storeId AND ( " + // شيل المسافة اللي بعد النقطتين
       "LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%')) " +
       "OR LOWER(p.brand) LIKE LOWER(CONCAT('%', :query, '%')) " +
       "OR LOWER(p.sku) LIKE LOWER(CONCAT('%', :query, '%'))" +
       ")")
List<Product> searchByKeyword(@Param("storeId") Long storeId, @Param("query") String keyword);

}
