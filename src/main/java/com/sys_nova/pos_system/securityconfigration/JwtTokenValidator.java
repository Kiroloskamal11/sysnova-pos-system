package com.sys_nova.pos_system.securityconfigration;


// ده اللي بيتاكد من التوكن لما 

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

// الـ Syntax: OncePerRequestFilter بيضمن إن الفلتر يتنفذ مرة واحدة بس مع كل طلب
public class JwtTokenValidator extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
              // 1. استخراج الـ Token من الـ Header بتاع الطلب
              // 1. استخراج الـ Header بالكامل الأول
            String authHeader = request.getHeader("Authorization");

            // هنا بنقوله: لو الـ Header مش فاضي وكمان بيبدأ بكلمة "Bearer " صح، ادخل نفذ
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
    
            // بنقص أول 7 حروف (B-e-a-r-e-r-مسافة) عشان ناخد التوكن بس
            String jwt = authHeader.substring(7);

    
            try {
                // 2. استخدام الـ Secret Key لفك التشفير (نفس اللي في الـ properties)
                // SecretKey key = Keys.hmacShaKeyFor("YourSuperSecretKeyForSysnovaPOS2026Project".getBytes());//المفتاح السري يفتح ويتاكد من الكود اللي كان داخل ال JWT


                // بدل ما تكتب النص يدوي، اسحبه من الثوابت
                SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());


                // 3. قراءة البيانات (Claims) اللي جوه الـ Token
                Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt).getPayload();

                
                String email = String.valueOf(claims.get("email"));
                String authorities = String.valueOf(claims.get("authorities"));

                // 4. تحويل الصلاحيات من نص لـ قائمة مفهومة لجافا
                List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

                // 5. إنشاء هوية المستخدم وإبلاغ Spring إن الشخص ده "تمام"
                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, auths);
                SecurityContextHolder.getContext().setAuthentication(authentication);//خزنه بحط فيها معلومات الشخص المهمه زي الايميل بعد ما نتاكد من صلاحياته 

            } catch (Exception e) {
                // لو التوكن بايظ أو منتهي، بنرمي Error
                throw new BadCredentialsException("Invalid token... من فضلك سجل دخول تاني");
            }
        }

        // 6. كمل طريقك لباقي الفلاتر أو الـ Controller
        filterChain.doFilter(request, response);
    }
}