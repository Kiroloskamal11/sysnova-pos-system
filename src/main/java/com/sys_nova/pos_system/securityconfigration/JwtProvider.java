package com.sys_nova.pos_system.securityconfigration;

// وظيفة ملف الـ JwtProvider:

//     إنشاء التوكن (Generate Token): يأخذ إيميل المستخدم وصلاحياته ويحولهم للكود المشفر اللي بنشوفه.

//     تحديد وقت الصلاحية (Expiration Date): هو اللي بيحدد التوكن ده يعيش قد إيه (ساعتين، يوم، أسبوع) قبل ما ينتهي ويطلب من المستخدم يسجل دخول تاني.

//     استخدام الـ Secret Key: بيسحب المفتاح من ملف الـ JwtConstant اللي إحنا لسه عاملينه عشان "يختم" بيه التوكن

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class JwtProvider {

    // 1. إنشاء المفتاح السري باستخدام المفتاح اللي حطيناه في JwtConstant
    static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    // 2. ميثود إنشاء التوكن (Generate Token)
    public static String generateToken(Authentication auth) {

        // استخراج الصلاحيات (Roles) وتحويلها لنص مفصول بفواصل
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        String roles = populateAuthorities(authorities);

        // بناء التوكن
        String jwt = Jwts.builder()
                .issuedAt(new Date()) // حذفنا كلمة "set" وبقت أول حرف صغير
                .expiration(new Date(new Date().getTime() + (86400000 * 1))) // نفس الكلام هنا
                .claim("email", auth.getName()) // تخزين الإيميل جوه التوكن
                .claim("authorities", roles) // تخزين الأدوار (Admin, User) جوه التوكن
                .signWith(key) // التوقيع بالمفتاح السري
                .compact(); // تحويله لنص نهائي

        return jwt;
    }

    // 3. ميثود استخراج الإيميل من التوكن (عشان لو احتاجنا نعرف مين صاحب التوكن)
    public static String getEmailFromJwtToken(String jwt) {
        // حذف كلمة Bearer لو موجودة
        if (jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);
        }

        Claims claims = Jwts.parser()
                .verifyWith(key) // السنتاكس الجديد بدل setSigningKey
                .build()
                .parseSignedClaims(jwt) // بدل parseClaimsJws
                .getPayload(); // بدل getBody

        return String.valueOf(claims.get("email"));
    }

    // ميثود مساعدة لتحويل الصلاحيات لنص
    private static String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> auths = new HashSet<>();
        for (GrantedAuthority authority : authorities) {
            auths.add(authority.getAuthority());
        }
        return String.join(",", auths);
    }
}

// الملف ده هو اللي بيطبع التوكن