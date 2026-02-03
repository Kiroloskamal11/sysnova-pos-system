package com.sys_nova.pos_system.service;

import com.sys_nova.pos_system.model.Customer;
import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer customer) throws Exception;
    void deleteCustomer(Long id) throws Exception;
    Customer getCustomerById(Long id) throws Exception;
    List<Customer> getAllCustomers();
    List<Customer> searchCustomers(String keyword);
}













// package com.sys_nova.pos_system.service;

// import java.util.List;

// import com.sys_nova.pos_system.payload.dto.CustomerDTO;

// public interface CustomerService {

//     CustomerDTO createCustomer(CustomerDTO customerDTO);
//     CustomerDTO getCustomerByPhone(String phone);
//     List<CustomerDTO> getAllCustomers();
// }
