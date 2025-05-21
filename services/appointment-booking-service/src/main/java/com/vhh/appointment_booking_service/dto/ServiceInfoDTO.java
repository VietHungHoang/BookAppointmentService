package com.vhh.appointment_booking_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceInfoDTO {
    private Long serviceId;
    private String name;
    private Integer durationMinutes;
}