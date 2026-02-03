package com.sys_nova.pos_system.mapper;

import com.sys_nova.pos_system.model.User;
import com.sys_nova.pos_system.payload.dto.UserDto;

public class UserMapper {
    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFullName(user.getFullName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setRole(user.getRole());
        userDto.setSalary(user.getSalary());
        userDto.setPosition(user.getPosition());
        if (user.getBranch() != null) {
            userDto.setBranchId(user.getBranch().getId());
        }
        return userDto;
    }

    public static User toEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhone(userDto.getPhone());
        user.setRole(userDto.getRole());
        user.setSalary(userDto.getSalary());
        user.setPosition(userDto.getPosition());
        // الـ Branch هيتم ربطه في الـ Service باستخدام الـ branchId
        return user;
    }

}
















    // // تحويل من Entity إلى DTO (عشان نبعت بيانات للمستخدم)
    // public static UserDto toDto(User user) {
    //     UserDto userDto = new UserDto();
    //     userDto.setId(user.getId());
    //     userDto.setFullName(user.getFullName());
    //     userDto.setEmail(user.getEmail());
    //     userDto.setRole(user.getRole());
    //     // الباسورد مش بنبعته في الـ DTO غالباً للأمان
    //     return userDto;
    // }

    // // تحويل من DTO إلى Entity (عشان نحفظ بيانات في الداتا بيز)
    // public static User toEntity(UserDto userDto) {
    //     User user = new User();
    //     user.setId(userDto.getId());
    //     user.setFullName(userDto.getFullName());
    //     user.setEmail(userDto.getEmail());
    //     user.setPassword(userDto.getPassword());
    //     user.setRole(userDto.getRole());
    //     return user;
    // }