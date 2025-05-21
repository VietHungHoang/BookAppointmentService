package com.vhh.appointment_booking_service.integration;

import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.vhh.appointment_booking_service.dto.EmployeeDTO;

public interface EmployeeServiceClient {

     /**
     * Lấy thông tin cơ bản của nhân viên (ví dụ: tên) để hiển thị.
     * Có thể được gọi bởi AppointmentService sau khi lấy được appointment details từ Scheduling Service.
     *
     * @param employeeId ID nhân viên.
     * @return Mono chứa thông tin chi tiết cơ bản của nhân viên.
     * @throws WebClientResponseException nếu Employee Service trả về lỗi 404.
     */
    ResponseEntity<EmployeeDTO> getEmployeeInfoById(Long employeeId);
}