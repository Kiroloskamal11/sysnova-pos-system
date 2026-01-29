package com.sys_nova.pos_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email; // الـ Import الصحيح للـ Email
import lombok.Data; // لازم تكون دي مش الـ interface اللي عملناه غلط
import java.time.LocalDateTime; // الـ Import الصحيح للوقت

@Entity
@Data // لو الـ @Data شغالة، تقدر تمسح الـ Getters والـ Setters اللي تحت
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true) // خليناها true عشان الأمان
    @Email(message = "Email should be valid")
    private String email;

    private String phone;

    @Column(nullable = false)
    private String password; 

    // private String role; // هنعتمد String زي ما Zosh شغال حالياً (ROLE_ADMIN)

    @Enumerated(EnumType.STRING) // عشان يتخزن في MySQL كـ (ROLE_ADMIN) مش (0)
    private UserRole role;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;

    // بما إنك كتبت الـ Getters والـ Setters يدوي، الجافا هتشغلهم عادي
    // بس لو استخدمت @Data (Lombok) الكود بيبقى أنظف بكتير
}