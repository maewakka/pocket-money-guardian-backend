package com.woo.backend.global.security.config;

import com.woo.backend.global.security.filter.JwtFilter;
import com.woo.backend.global.security.service.GuardianUserDetailsService;
import com.woo.backend.global.security.service.JwtValidateService;
import com.woo.backend.global.security.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final GuardianUserDetailsService guardianUserDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtValidateService jwtValidateService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/users/sign-up", "/users/sign-in").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtFilter(guardianUserDetailsService, jwtTokenProvider, jwtValidateService),UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}