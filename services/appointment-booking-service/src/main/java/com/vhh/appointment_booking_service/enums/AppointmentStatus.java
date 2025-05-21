package com.vhh.appointment_booking_service.enums;

public enum AppointmentStatus {
    PENDING_CONFIRMATION, // Chờ xác nhận (ít dùng nếu tự động xác nhận khi đủ điều kiện)
    CONFIRMED,            // Đã xác nhận
    CANCELLED_BY_CUSTOMER,// Khách hàng hủy
    CANCELLED_BY_STAFF,   // Nhân viên/Admin hủy
    COMPLETED,            // Đã hoàn thành
    NO_SHOW               // Khách không đến
}