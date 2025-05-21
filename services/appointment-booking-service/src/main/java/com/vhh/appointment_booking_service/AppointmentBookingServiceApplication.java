package com.vhh.appointment_booking_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppointmentBookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppointmentBookingServiceApplication.class, args);
	}

// Nếu Scheduling Service chỉ được gọi bởi các service backend khác (như Appointment Booking Service), và bạn không cần throughput siêu cao ở tầng API của service này, thì Spring Web (MVC truyền thống) có thể đơn giản hơn để implement, đặc biệt khi làm việc với JPA (blocking).
// Nếu bạn muốn toàn bộ hệ thống là non-blocking hoặc service này cũng có thể được gọi từ nguồn khác cần hiệu năng cao, thì dùng Spring WebFlux. Tuy nhiên, việc kết hợp WebFlux với JPA (blocking) cần cẩn thận (ví dụ, chạy các thao tác JPA trên một thread pool riêng bằng Schedulers.boundedElastic()).
// Khuyến nghị ban đầu: Bắt đầu với Spring Web (MVC) cho Scheduling Service để giữ cho phần tương tác DB đơn giản hơn. Chúng ta vẫn có thể tối ưu sau nếu cần.

}
