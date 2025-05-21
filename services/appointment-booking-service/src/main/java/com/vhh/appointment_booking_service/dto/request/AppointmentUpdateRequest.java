package com.vhh.appointment_booking_service.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentUpdateRequest {

    

    private LocalDateTime startTime; // Thời gian bắt đầu mới

    private Long employeeId; // Nhân viên mới (nếu muốn thay đổi)

    private String notes; // Cập nhật ghi chú
    
    // serviceId thường ít khi cho thay đổi, nếu cần thì thêm vào
    // private String serviceId; 
}