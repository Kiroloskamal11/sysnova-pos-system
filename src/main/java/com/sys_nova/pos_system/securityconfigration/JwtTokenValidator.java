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
        
        // --- الإضافة المنقذة هنا ---
        String path = request.getRequestURI();
        // لو المسار بيبدأ بـ /auth/، تجاهل الفلتر ده تماماً وكمل طريقك
        if (path.startsWith("/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }
        // ---------------------------

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);

            try {
                SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

                Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt).getPayload();
                
                String email = String.valueOf(claims.get("email"));
                String authorities = String.valueOf(claims.get("authorities"));

                List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, auths);
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e) {
                // نصيحة: في مرحلة الـ Signup/Login الـ Postman أحياناً بيبعت الهيدر القديم آلياً
                // فإحنا بنمنع الـ Exception دي إنها توقف السيرفر
                throw new BadCredentialsException("Invalid token... من فضلك سجل دخول تاني");
            }
        }

        filterChain.doFilter(request, response);
    }
}