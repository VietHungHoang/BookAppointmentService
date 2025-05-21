package com.vhh.appointment_booking_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailabilityCheckRequest {
    private Long serviceId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long employeeId; // Có thể null nếu khách hàng không chọn cụ thể
}