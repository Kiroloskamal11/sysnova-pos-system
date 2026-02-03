package com.sys_nova.pos_system.service.impl;

import com.sys_nova.pos_system.mapper.UserMapper;
import com.sys_nova.pos_system.model.Branch;
import com.sys_nova.pos_system.model.User;
import com.sys_nova.pos_system.payload.dto.UserDto;
import com.sys_nova.pos_system.repository.BranchRepository;
import com.sys_nova.pos_system.repository.UserRepository;
import com.sys_nova.pos_system.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public UserDto createEmployee(UserDto userDto) {
        // 1. تحويل الـ DTO لـ Entity باستخدام الـ Mapper بتاعك
        User user = UserMapper.toEntity(userDto);

        // 2. ربط الفرع (بما إن الـ UserEntity مفيهاش حقل branch حالياً، 
        // لازم تتأكد إنك ضفت private Branch branch في الـ User.java زي ما شرحنا فوق)
        if (userDto.getBranchId() != null) {
            Branch branch = branchRepository.findById(userDto.getBranchId())
                    .orElseThrow(() -> new RuntimeException("Branch not found"));
            user.setBranch(branch);
        }

        user.setCreatedAt(LocalDateTime.now());
        User savedUser = userRepository.save(user);
        return UserMapper.toDto(savedUser);
    }

    @Override
    public List<UserDto> getEmployeesByBranch(Long branchId) {
        return userRepository.findByBranchId(branchId).stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getEmployeeById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return UserMapper.toDto(user);
    }

    @Override
    public void deleteEmployee(Long id) {
        userRepository.deleteById(id);
    }
}