package com.vhh.appointment_booking_service.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentCreationRequest {

    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("service_id")
    private Long serviceId;

    @JsonProperty("service_name")
    private String serviceName;

    @JsonProperty("employee_id")
    private Long employeeId; 

    @JsonProperty("employee_name")
    private String employeeName;

    private LocalDateTime time;

    private String notes;
}