package com.sys_nova.pos_system.service;

import com.sys_nova.pos_system.exception.UserException;
import com.sys_nova.pos_system.model.User;
import java.util.List;

public interface UserService {

    // 1. جلب بيانات المستخدم من خلال التوكن مباشرة
    User getUserFromJwtToken(String jwt) throws UserException;

    // 2. البحث عن مستخدم من خلال الإيميل
    User findUserByEmail(String email) throws UserException;

    // 3. البحث عن مستخدم من خلال الـ ID
    User findUserById(Long userId) throws UserException;

    // 4. جلب قائمة بكل المستخدمين في النظام
    List<User> getAllUsers();
}