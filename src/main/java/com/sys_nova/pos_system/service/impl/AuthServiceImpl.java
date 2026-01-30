package com.sys_nova.pos_system.service.impl;

import com.sys_nova.pos_system.exception.UserException;
import com.sys_nova.pos_system.mapper.UserMapper;
import com.sys_nova.pos_system.model.User;
import com.sys_nova.pos_system.repository.UserRepository;
import com.sys_nova.pos_system.securityconfigration.JwtProvider;
import com.sys_nova.pos_system.service.AuthService;
import com.sys_nova.pos_system.payload.dto.UserDto;
import com.sys_nova.pos_system.payload.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomUserServiceImplementation customUserService;

    @Override
    public AuthResponse signup(UserDto userDto) throws UserException {

        // 1. التأكد من عدم تكرار الإيميل
        User isEmailExist = userRepository.findByEmail(userDto.getEmail());
        if (isEmailExist != null) {
            throw new UserException("Email is already used with another account");
        }

        // 2. استخدام الـ Mapper لتحويل الـ DTO لـ Entity
        User userToSave = UserMapper.toEntity(userDto);

        // 3. تشفير الباسورد قبل الحفظ (خطوة أمان أساسية)
        userToSave.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(userToSave);

        // 4. عمل Authentication أوتوماتيك للمستخدم الجديد
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDto.getEmail(),
                userDto.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 5. توليد التوكن
        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Register Success");
        authResponse.setStatus(true);

        return authResponse;
    }

    @Override
    public AuthResponse login(UserDto userDto) {
        String username = userDto.getEmail();
        String password = userDto.getPassword();

        // التحقق من البيانات عن طريق ميثود authenticate بالأسفل
        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Login Success");
        authResponse.setStatus(true);

        return authResponse;
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserService.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username...");
        }

        // التأكد من تطابق الباسورد المكتوب مع المشفر
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password...");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}