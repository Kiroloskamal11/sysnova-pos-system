package com.sys_nova.pos_system.service;

import com.sys_nova.pos_system.payload.dto.UserDto;
import java.util.List;

public interface EmployeeService {
    UserDto createEmployee(UserDto userDto);
    List<UserDto> getEmployeesByBranch(Long branchId);
    UserDto getEmployeeById(Long id);
    void deleteEmployee(Long id);
}