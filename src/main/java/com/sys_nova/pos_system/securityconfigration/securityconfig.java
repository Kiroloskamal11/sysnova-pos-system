// Roles => Authorization ,Authentication , JWT

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

import java.util.Collections;


//annotation 
@EnableWebSecurity
//ده اللي بيشفل ال springsecurity بدونه مفيش امان اي حد يدخل علي قاعده البيانات

@Configuration
//ده يباغ جافا ان ده مشش كود عادي ولكن ده ملف اعدادات اول ما السيرفر يشتغل نفذه

public class securityconfig{


    // Bean :- يعني انك هتنتج كائن سبرينج هديره طول تشغيل البرنامج 

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//هنا انا عرفت النظام ان انا هستخدم ال JWT
                .authorizeHttpRequests(Authorize -> Authorize
                        .requestMatchers("/api/**").authenticated() // أي طلب بيبدأ بـ /api لازم يكون متسجل
                        .anyRequest().permitAll() // باقي الطلبات متاحة (زي تسجيل الدخول)


                )

                // ده الكلاس اللي هيشوف التوكن ويتحقق منها 
                .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class) // هنكريت الكلاس ده الخطوة الجاية
                .csrf(csrf -> csrf.disable()) // بنعطله لأننا شغالين JWT
                .cors(cors -> cors.configurationSource(corsConfigurationSource()));

        return http.build();
    }



    // ده المسؤول عن تشفير كلمة السر قبل ما تروح للـ phpMyAdmin
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    // إعدادات الـ CORS عشان الـ React يقدر يكلم الـ Java بدون مشاكل

    //يسمح التعامل بين راكت وجافا بدون قيود 
    private CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration cfg = new CorsConfiguration();
            cfg.setAllowedOrigins(Collections.singletonList("http://localhost:3000")); // رابط الـ React بتاعك
            cfg.setAllowedMethods(Collections.singletonList("*"));
            cfg.setAllowCredentials(true);
            cfg.setAllowedHeaders(Collections.singletonList("*"));
            cfg.setExposedHeaders(Collections.singletonList("Authorization"));
            cfg.setMaxAge(3600L);
            return cfg;
        };
    }
}
