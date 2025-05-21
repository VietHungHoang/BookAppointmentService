package com.vhh.appointment_booking_service.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vhh.appointment_booking_service.dto.NotificationDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AppointmentResponse {

    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("appointment_id")
    private Long appointmentId;

    @JsonProperty("service_id")
    private Long serviceId;

    @JsonProperty("service_name")
    private String serviceName;

    @JsonProperty("employee_id")
    private Long employeeId;

    @JsonProperty("employee_name")
    private String employeeName;

    @JsonProperty("time")
    private LocalDateTime time;

    @JsonProperty("status")
    private String status;

    @JsonProperty("notes")
    private String notes;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    public NotificationDTO toNotificationDTO() {
    return NotificationDTO.builder()
            .customerId(this.customerId)
            .appointmentId(this.appointmentId)
            .serviceId(this.serviceId)
            .serviceName(this.serviceName)
            .employeeId(this.employeeId)
            .employeeName(this.employeeName)
            .time(this.time)
            .status(this.status)
            .notes(this.notes)
            .build();
}

}
