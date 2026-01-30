package com.sys_nova.pos_system.service;

import com.sys_nova.pos_system.exciption.UserException;
import com.sys_nova.pos_system.payload.dto.UserDto;
import com.sys_nova.pos_system.payload.response.AuthResponse;

public interface AuthService {
    // هنا بنستخدم UserDto للتسجيل بدل الـ Entity
    AuthResponse signup(UserDto userDto) throws UserException;
    
    // وهنا برضه بنستخدمه للدخول
    AuthResponse login(UserDto userDto);
}