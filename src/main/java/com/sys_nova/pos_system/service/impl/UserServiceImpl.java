package com.sys_nova.pos_system.service.impl;

import com.sys_nova.pos_system.exception.UserException;
import com.sys_nova.pos_system.model.User;
import com.sys_nova.pos_system.repository.UserRepository;
import com.sys_nova.pos_system.securityconfigration.JwtProvider;
import com.sys_nova.pos_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service // بنعرف Spring إن ده كلاس الخدمة
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired // <--- ضيف السطرين دول عشان Spring يحقن الـ Provider صح
    private JwtProvider jwtProvider;

    @Override
    public User getUserFromJwtToken(String jwt) throws UserException {
        // نستخدم jwtProvider (اللي متعرف فوق) مش JwtProvider (اسم الكلاس)
        String email = jwtProvider.getEmailFromJwtToken(jwt);

        return findUserByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) throws UserException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserException("User not found with email: " + email);
        }
        return user;
    }

    @Override
    public User findUserById(Long id) throws UserException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UserException("User not found with id: " + id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}