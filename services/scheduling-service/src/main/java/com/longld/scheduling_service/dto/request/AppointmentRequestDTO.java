package com.longld.scheduling_service.dto.request;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentRequestDTO {

    @NotNull(message = "Customer ID cannot be null")
    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("service_id")
    @NotNull(message = "Service ID cannot be null")
    private Long serviceId;

    @JsonProperty("employee_id")
    private Long employeeId;

    private LocalDateTime time;

    @Size(max = 500, message = "Notes cannot exceed 500 characters")
    private String notes;
}