package com.sys_nova.pos_system.securityconfigration;

public class JwtConstant {
// مفتاح السر: لازم يكون طويل جداً ومعقد (زي ما شفت في فيديو Zosh)
    public static final String SECRET_KEY = "wpemwoivnweoipnveonpweoivnpweoivnpweoivnpweoivn"; 
    
    // اسم الهيدر اللي التوكن هيمشي فيه بين الـ React والجافا
    public static final String JWT_HEADER = "Authorization";
}