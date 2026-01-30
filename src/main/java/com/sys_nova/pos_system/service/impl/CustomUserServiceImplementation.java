package com.sys_nova.pos_system.service.impl;



import com.sys_nova.pos_system.model.User;
import com.sys_nova.pos_system.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service // عشان Spring يعرف إن ده كلاس خدمات ويقدر يستخدمه
public class CustomUserServiceImplementation implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; // ده المخزن اللي بيكلم الداتا بيز

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        // 1. البحث عن المستخدم في الداتا بيز باستخدام الإيميل
        User user = userRepository.findByEmail(username);
        
        if (user == null) {
            throw new UsernameNotFoundException("المستخدم ده مش موجود بالإيميل ده: " + username);
        }

        // 2. تجهيز قائمة الصلاحيات (Roles)
        List<GrantedAuthority> authorities = new ArrayList<>();
        // هنا ممكن تضيف الـ roles بتاعة المستخدم لاحقاً

        // 3. تحويل مستخدم "SYSNOVA" لمستخدم بيفهمه "Spring Security"
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), 
                user.getPassword(), 
                authorities
        );
    }
}