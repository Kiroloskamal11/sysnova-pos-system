package com.sys_nova.pos_system.controller;

import com.sys_nova.pos_system.payload.dto.UserDto;
import com.sys_nova.pos_system.payload.response.AuthResponse;
import com.sys_nova.pos_system.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sys_nova.pos_system.exception.UserException;

@RestController
@RequestMapping("/auth") // كل المسارات هتبدأ بـ /auth
public class AuthController {

    @Autowired
    private AuthService authService;

    // 1. مسار التسجيل (Signup)
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody UserDto userDto) throws UserException {
        // بننادي الـ Service ونبعتلها الـ DTO
        AuthResponse response = authService.signup(userDto);
        // بنرجع النتيجة ومعاها كود 201 (Created)
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 2. مسار تسجيل الدخول (Signin)
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signinHandler(@RequestBody UserDto userDto) {
        // بننادي ميثود الـ login في الـ Service
        AuthResponse response = authService.login(userDto);
        // بنرجع النتيجة ومعاها كود 200 (OK)
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}