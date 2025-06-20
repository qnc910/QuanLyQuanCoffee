package com.mycompany.quanlyquancoffee.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // ❌ Tắt CSRF (do dùng REST API)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/taikhoan/**","/api/nhanvien","/api/nhanvien/**","/api/loaimon","/api/loaimon/**",
                        "/api/sanpham/**","/api/sanpham").permitAll() // ✅ Cho phép không cần auth
                .anyRequest().authenticated() // 🔐 Các request khác cần xác thực
            );
        
        return http.build(); // ✅ Không dùng httpBasic()
    }
}
