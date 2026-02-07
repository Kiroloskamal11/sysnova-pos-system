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

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BranchRepository branchRepository;
    // ضيفها هنا يا هندسة
    @Autowired
    private ShiftReportRepository shiftReportRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional // تأكد من وجود الـ Annotation دي عشان لو حصل مشكلة في المخزن يلغي العملية كلها
    public OrderDTO createorder(OrderDTO orderDTO) throws Exception {
        Order order = new Order();

        // 1. التعامل مع العميل (بشكل اختياري)
        if (orderDTO.getCustomerId() != null) {
            Customer customer = customerRepository.findById(orderDTO.getCustomerId()).orElse(null);
            order.setCustomer(customer);
        }

        // 2. التحقق من وجود الفرع (إلزامي)
        if (orderDTO.getBranchId() == null) {
            throw new Exception("Branch ID must not be null! Please send branchId in your request.");
        }
        Branch branch = branchRepository.findById(orderDTO.getBranchId())
                .orElseThrow(() -> new Exception("Branch not found with ID: " + orderDTO.getBranchId()));
        order.setBranch(branch);

        // 3. ضبط نوع الدفع
        order.setPaymentType(orderDTO.getPaymentType());

        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0.0;

        // 4. معالجة المنتجات (التحقق من المخزن وحساب السعر)
        if (orderDTO.getItems() == null || orderDTO.getItems().isEmpty()) {
            throw new Exception("Order must have at least one item!");
        }

        for (OrderItemDTO itemDTO : orderDTO.getItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new Exception("Product not found with ID: " + itemDTO.getProductId()));

            // التحقق من توافر الكمية في المخزن
            if (product.getQuantity() < itemDTO.getQuantity()) {
                throw new Exception("Stock insufficient for product: " + product.getName() +
                        " (Available: " + product.getQuantity() + ")");
            }

            // خصم الكمية من المخزن وحفظ المنتج
            product.setQuantity(product.getQuantity() - itemDTO.getQuantity());
            productRepository.save(product);

            // إنشاء سطر الفاتورة
            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(itemDTO.getQuantity());
            item.setPrice(product.getPrice()); // السعر بيتاخد من المنتج الحالي
            item.setOrder(order);

            orderItems.add(item);

            // حساب الإجمالي
            totalAmount += item.getPrice() * item.getQuantity();
        }

        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);

        // 1. هنجيب الـ User من الـ ID اللي جوه الـ DTO
        User user = userRepository.findById(orderDTO.getCashierId())
                .orElseThrow(() -> new RuntimeException("المستخدم غير موجود"));
        // 1. جلب الوردية المفتوحة للكاشير الحالي
        ShiftReport currentShift = shiftReportRepository.findTopByCashierAndShiftEndIsNullOrderByShiftStartDesc(user)
                .orElseThrow(() -> new RuntimeException("لا يمكن إتمام البيع بدون فتح وردية!"));

        // 2. ربط الأوردر بالوردية
        order.setShiftReport(currentShift);

        // 5. حفظ الفاتورة وتحويلها لـ DTO
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
    @Override
    public void deleteorder(Long id) throws Exception {
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderDTO> getorderByCashier(Long cashierId) {
        return null;
    } // تنفيذ حسب الحاجة

    @Override
    public List<OrderDTO> get0rdersByCustomerId(Long customerId) {
        return null;
    }

    @Override
    public List<OrderDTO> getordersByBranch(Long b, Long c, Long cash, PaymentType p, OrderStatus s) {
        return null;
    }
}