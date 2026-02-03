package com.sys_nova.pos_system.service.impl;

import com.sys_nova.pos_system.model.Customer;
import com.sys_nova.pos_system.repository.CustomerRepository;
import com.sys_nova.pos_system.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer updatedCustomer) throws Exception {
        Customer existingCustomer = getCustomerById(id);
        
        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setPhone(updatedCustomer.getPhone());
        existingCustomer.setAddress(updatedCustomer.getAddress());
        
        return customerRepository.save(existingCustomer);
    }

    @Override
    public void deleteCustomer(Long id) throws Exception {
        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
    }

    @Override
    public Customer getCustomerById(Long id) throws Exception {
        return customerRepository.findById(id)
                .orElseThrow(() -> new Exception("Customer not found with id: " + id));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> searchCustomers(String keyword) {
        // لو الكلمة null أو عبارة عن مسافات، رجع لستة فاضية فوراً
        if (keyword == null || keyword.trim().isEmpty()) {
            return new java.util.ArrayList<>(); 
        }
        
        // نبعت الكلمة نضيفة (بدون مسافات في الأول أو الآخر)
        return customerRepository.searchCustomers(keyword.trim());
    }
}