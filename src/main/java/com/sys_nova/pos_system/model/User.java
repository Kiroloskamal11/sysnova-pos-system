package com.sys_nova.pos_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email; // الـ Import الصحيح للـ Email

// لازم تكون دي مش الـ interface اللي عملناه غلط
import java.time.LocalDateTime; // الـ Import الصحيح للوقت

@Entity
// لو الـ @Data شغالة، تقدر تمسح الـ Getters والـ Setters اللي تحت

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

    // employee

    private Double salary; // الراتب
    private String position; // المسمى الوظيفي (مثلاً كاشير، محاسب)

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    // الـ Getters والـ Setters للحقول الجديدة
    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    // بما إنك كتبت الـ Getters والـ Setters يدوي، الجافا هتشغلهم عادي
    // بس لو استخدمت @Data (Lombok) الكود بيبقى أنظف بكتير

}
