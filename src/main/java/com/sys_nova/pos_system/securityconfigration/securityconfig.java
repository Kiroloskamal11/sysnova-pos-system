package com.sys_nova.pos_system.securityconfigration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class securityconfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // 1. تفعيل الـ CORS باستخدام الميثود اللي تحت
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            // 2. تعطيل الـ CSRF تماماً
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(Authorize -> Authorize
                    // 3. تأكد إن مسار اللوجين مفتوح تماماً
                    .requestMatchers("/auth/**").permitAll() 
                    .requestMatchers("/api/**").authenticated()
                    .anyRequest().permitAll()
            )
            // 4. إضافة الفلتر بتاعك
            .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration cfg = new CorsConfiguration();
            // تحديد الـ Origins بدقة
            cfg.setAllowedOrigins(Arrays.asList(
                "http://localhost:5173", 
                "http://127.0.0.1:5173", 
                "http://localhost:3000"
            ));
            // السماح بكل الـ Methods بما فيها الـ OPTIONS المهمة للمتصفح
            cfg.setAllowedMethods(Collections.singletonList("*"));
            cfg.setAllowCredentials(true);
            cfg.setAllowedHeaders(Collections.singletonList("*"));
            cfg.setExposedHeaders(Arrays.asList("Authorization", "Content-Type"));
            cfg.setMaxAge(3600L);
            return cfg;
        };
    }
}