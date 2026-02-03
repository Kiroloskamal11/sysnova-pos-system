package com.sys_nova.pos_system.repository;

import com.sys_nova.pos_system.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE " +
           "LOWER(c.name) LIKE LOWER(CONCAT('%', :kw, '%')) OR " +
           "LOWER(c.email) LIKE LOWER(CONCAT('%', :kw, '%')) OR " +
           "c.phone LIKE CONCAT(:kw, '%')") // شيلنا الـ % اللي في الأول عند التليفون
    List<Customer> searchCustomers(@Param("kw") String keyword);
    
}