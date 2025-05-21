package com.longld.notification_service.dto;

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
    private String customerEmail;
    private Long appointmentId;
    private Long serviceId;
    private String serviceName;
    private Long employeeId;
    private String employeeName;
    private LocalDateTime time;
    private String status;
    private String notes;

    public String getSuccessMessage() {
    return String.format(
        "✅ Đặt lịch thành công!\n" +
        "Khách hàng ID: %d\n" +
        "Dịch vụ: %s\n" +
        "Nhân viên: %s\n" +
        "Thời gian: %s\n" +
        "Ghi chú: %s",
        customerId,
        serviceName,
        employeeName,
        time != null ? time.toString() : "Chưa xác định",
        notes != null ? notes : "Không có"
    );
}

}