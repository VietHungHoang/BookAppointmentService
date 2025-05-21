package com.vhh.appointment_booking_service.config;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    // @Bean(name = "employeeServiceRestTemplate") // Đặt tên bean để inject cụ thể
    // public RestTemplate employeeServiceRestTemplate(RestTemplateBuilder builder) {
    //     // Bạn có thể cấu hình timeouts, interceptors, error handlers ở đây
    //     return builder
    //             .setConnectTimeout(Duration.ofSeconds(5)) // Ví dụ timeout kết nối
    //             .setReadTimeout(Duration.ofSeconds(10))   // Ví dụ timeout đọc
    //             // .errorHandler(new CustomRestTemplateErrorHandler()) // Nếu cần xử lý lỗi tùy chỉnh
    //             .build();
    // }

    // Nếu bạn chỉ cần một RestTemplate chung cho toàn ứng dụng và không cần tên cụ thể:
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}