package com.vhh.appointment_booking_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.vhh.appointment_booking_service.dto.response.AppointmentResponse;
// Dùng cho cả book và update slot
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchedulingRequestPayload {
    // Dùng cho book
    private Long customerId;
    private Long serviceId;

    // Dùng cho cả book và update
    private Long employeeId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String notes;

    public AppointmentResponse toAppointmentResponse() {
    return AppointmentResponse.builder()
            .customerId(this.getCustomerId())
            .serviceId(this.getServiceId())
            .employeeId(this.getEmployeeId())
            .notes(this.getNotes())
            .build();
}
}