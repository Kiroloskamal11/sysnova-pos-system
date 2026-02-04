package com.sys_nova.pos_system.service.impl;

import com.sys_nova.pos_system.model.*;
import com.sys_nova.pos_system.payload.dto.*;
import com.sys_nova.pos_system.repository.*;
import com.sys_nova.pos_system.service.OrderService;
import com.sys_nova.pos_system.mapper.OrderMapper;
import com.sys_nova.pos_system.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired private OrderRepository orderRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private BranchRepository branchRepository;

    @Override
    @Transactional // ضروري جداً عشان لو خطوة فشلت كلو يتلغي
    public OrderDTO createorder(OrderDTO orderDTO) throws Exception {
        Order order = new Order();
        
        // 1. جلب العميل والفرع والكاشير
        Customer customer = customerRepository.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new Exception("Customer not found"));
        Branch branch = branchRepository.findById(orderDTO.getBranchId())
                .orElseThrow(() -> new Exception("Branch not found"));
        // نفترض أن الكاشير هو اليوزر الحالي (ممكن تجيبه من السكيورتي)
        
        order.setCustomer(customer);
        order.setBranch(branch);
        order.setPaymentType(orderDTO.getPaymentType());
        
        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0.0;

        // 2. معالجة بنود الفاتورة والتحقق من المخزن
        for (OrderItemDTO itemDTO : orderDTO.getItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new Exception("Product not found"));
        
            // التحقق من المخزن
            if (product.getQuantity() < itemDTO.getQuantity()) {
                throw new Exception("Stock insufficient for: " + product.getName());
            }
        
            // تحديث المخزن
            product.setQuantity(product.getQuantity() - itemDTO.getQuantity());
            productRepository.save(product);
        
            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(itemDTO.getQuantity());
            
            // ركز هنا: اتأكد من اسم الحقل في الـ Product Entity عندك 
            // لو اسمه price خليه product.getPrice()
            item.setPrice(product.getPrice()); 
            
            item.setOrder(order);

            orderItems.add(item);
        
            // حساب الإجمالي لكل سطر وجمعه على الإجمالي الكلي
            totalAmount += item.getPrice() * item.getQuantity();
        }

        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);
        
        Order savedOrder = orderRepository.save(order);
        return OrderMapper.toDTO(savedOrder);
    }

    @Override
    public List<OrderDTO> getToday0rdersByBranch(Long branchId) throws Exception {
        LocalDateTime start = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        // التعديل: استعملنا الميثود اللي أنت عرفته في الـ Repository
        return orderRepository.findByBranchIdAndCreatedAtBetween(branchId, start, end)
                .stream().map(OrderMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDTO getorderById(Long id) throws Exception {
        Order order = orderRepository.findById(id).orElseThrow(() -> new Exception("Order not found"));
        return OrderMapper.toDTO(order);
    }

    @Override
    public List<OrderDTO> getTop5RecentordersByBranchId(Long branchId) throws Exception {
        return orderRepository.findTop5ByBranchIdOrderByCreatedAtDesc(branchId)
                .stream().map(OrderMapper::toDTO).collect(Collectors.toList());
    }

    // باقي الميثودز (Delete, GetByCustomer...) تنفذ بنفس النمط
    @Override public void deleteorder(Long id) throws Exception { orderRepository.deleteById(id); }
    @Override public List<OrderDTO> getorderByCashier(Long cashierId) { return null; } // تنفيذ حسب الحاجة
    @Override public List<OrderDTO> get0rdersByCustomerId(Long customerId) { return null; }
    @Override public List<OrderDTO> getordersByBranch(Long b, Long c, Long cash, PaymentType p, OrderStatus s) { return null; }
}