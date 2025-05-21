package com.vhh.appointment_booking_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
// Đây là dữ liệu gốc mà Scheduling Service trả về, có thể giống AppointmentResponse hoặc khác một chút
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchedulingResponsePayload {
    private Long appointmentId; // ID do Scheduling Service tạo/quản lý
    private Long customerId;
    private Long serviceId;
    private Long employeeId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status; // Trạng thái do Scheduling Service quản lý (CONFIRMED, etc.)
    private String notes;
    private LocalDateTime createdAt; // Thời gian tạo trong Scheduling Service
    private LocalDateTime updatedAt; // Thời gian cập nhật trong Scheduling Service
}