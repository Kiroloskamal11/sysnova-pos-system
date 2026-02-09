package com.sys_nova.pos_system.controller;

import com.sys_nova.pos_system.exception.UserException;
import com.sys_nova.pos_system.model.User;
import com.sys_nova.pos_system.repository.UserRepository;
import com.sys_nova.pos_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // عشان نعرف Spring إن ده API
@RequestMapping("/api/users") // كل المسارات هنا هتبدأ بـ /api/users
public class UserController {

    // السطر ده هو اللي ناقص ومسبب الخطأ الأحمر
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    // ميثود لجلب بروفايل المستخدم الحالي عن طريق التوكن
    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfileHandler(
            @RequestHeader("Authorization") String jwt) throws UserException {

        // بنبعت التوكن للخدمة وهي بترجع لنا اليوزر
        User user = userService.getUserFromJwtToken(jwt);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // ميثود اختيارية لجلب كل المستخدمين (للتجربة حالياً)
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsersHandler() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // في UserController.java
    @PutMapping("/update")
    public ResponseEntity<User> updateProfile(@RequestHeader("Authorization") String jwt, @RequestBody User updatedData)
            throws Exception {
        User currentUser = userService.getUserFromJwtToken(jwt);

        // تحديث الحقول المسموح بها
        currentUser.setFullName(updatedData.getFullName());
        currentUser.setPhone(updatedData.getPhone());
        currentUser.setPosition(updatedData.getPosition());
        // currentUser.setSalary(updatedData.getSalary()); // فكر لو عايز تسمح للموظف
        // يعدل راتبه بنفسه!

        // ملاحظة: لازم تضيف ميثود save في الـ Service بتاعك أو تنادي الـ repository هنا
        // لو للسرعة
        // للتبسيط حالياً:
        User savedUser = userRepository.save(currentUser);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }
}