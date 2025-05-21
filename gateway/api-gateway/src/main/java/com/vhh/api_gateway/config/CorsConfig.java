package com.vhh.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Collections.singletonList("*")); // Hoặc chỉ định domain cụ thể
        corsConfig.setMaxAge(3600L); // Thời gian pre-flight request được cache
        corsConfig.addAllowedMethod("*"); // GET, POST, PUT, DELETE, OPTIONS
        corsConfig.addAllowedHeader("*"); // Tất cả các header
        // corsConfig.setAllowCredentials(true); // Nếu cần thiết

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig); // Áp dụng cho tất cả các path

        return new CorsWebFilter(source);
    }
}