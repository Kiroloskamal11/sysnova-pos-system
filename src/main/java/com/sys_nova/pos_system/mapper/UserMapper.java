package com.sys_nova.pos_system.mapper;

import com.sys_nova.pos_system.model.User;
import com.sys_nova.pos_system.payload.dto.UserDto;

public class UserMapper {

    // تحويل من Entity إلى DTO (عشان نبعت بيانات للمستخدم)
    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFullName(user.getFullName());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        // الباسورد مش بنبعته في الـ DTO غالباً للأمان
        return userDto;
    }

    // تحويل من DTO إلى Entity (عشان نحفظ بيانات في الداتا بيز)
    public static User toEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        return user;
    }
}