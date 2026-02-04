package com.sys_nova.pos_system.service;

import java.util.List;

import com.sys_nova.pos_system.domain.OrderStatus;
import com.sys_nova.pos_system.domain.PaymentType;
import com.sys_nova.pos_system.payload.dto.OrderDTO;

public interface OrderService {

    OrderDTO createorder(OrderDTO orderDTO) throws Exception;
    OrderDTO getorderById(Long id) throws Exception; 
    List<OrderDTO>getordersByBranch(Long branchId, 
    Long customerId,
    Long cashierId,
    PaymentType paymentType,
    OrderStatus status) throws Exception;
    List<OrderDTO> getorderByCashier(Long cashierId); 
    void deleteorder(Long id) throws Exception; 
    List<OrderDTO> getToday0rdersByBranch(Long branchId) throws Exception; 
    List<OrderDTO> get0rdersByCustomerId(Long customerId) throws Exception; 
    List<OrderDTO> getTop5RecentordersByBranchId(Long branchId) throws Exception; 

}
