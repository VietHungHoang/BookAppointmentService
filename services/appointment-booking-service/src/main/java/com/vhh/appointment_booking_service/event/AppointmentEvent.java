package com.vhh.appointment_booking_service.event;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

import com.vhh.appointment_booking_service.enums.AppointmentStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentEvent {
    private String eventType; // "CREATED", "UPDATED", "CANCELLED", "COMPLETED"
    private Long appointmentId;
    private Long customerUserId;
    private Long serviceId;
    private Long staffId; // Có thể null
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;
    private AppointmentStatus newStatus; // Trạng thái mới của lịch hẹn
    private AppointmentStatus oldStatus; // Trạng thái cũ (cho event UPDATED)
    private String notes;
    private String cancellationReason;
    private OffsetDateTime eventTimestamp;

    // Optional: Gửi thêm thông tin chi tiết để consumer không cần gọi lại service
    // private ServiceDto serviceDetails;
    // private StaffDto staffDetails;
    // private String customerEmail; // Nếu NotificationService cần ngay
}
