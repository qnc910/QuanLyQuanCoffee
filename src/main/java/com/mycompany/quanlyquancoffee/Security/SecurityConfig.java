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
            .csrf(csrf -> csrf.disable()) // ❌ Tắt CSRF cho REST API
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/api/**" // ✅ Cho phép toàn bộ API
                ).permitAll()
                .anyRequest().authenticated()
            );

        return http.build();
    }
}

