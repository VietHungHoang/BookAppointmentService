package com.longld.scheduling_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.longld.scheduling_service.enums.AppointmentStatus;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentSlotDTO {
    private Long appointmentId;
    private Long customerId;
    private Long serviceId;
    private Long employeeId;
    private LocalDateTime time;
    private AppointmentStatus status;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
