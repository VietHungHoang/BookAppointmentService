
package com.vhh.appointment_booking_service.dto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDTO implements Serializable{
    private Long customerId;
    private Long appointmentId;
    private Long serviceId;
    private String serviceName;
    private Long employeeId;
    private String employeeName;
    private LocalDateTime time;
    private String status;
    private String notes;
}