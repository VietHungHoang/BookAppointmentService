package com.longld.scheduling_service.enums;

public enum AppointmentStatus {
    PENDING_CONFIRMATION, // Chờ xác nhận (ví dụ: nếu có bước thanh toán hoặc admin duyệt)
    CONFIRMED,            // Đã xác nhận
    CANCELLED_BY_CUSTOMER,// Khách hàng hủy
    CANCELLED_BY_STAFF,   // Nhân viên/Hệ thống hủy (ví dụ: nhân viên nghỉ)
    COMPLETED,            // Đã hoàn thành
    NO_SHOW               // Khách không đến
}