package com.vhh.appointment_booking_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Cho các lỗi chung khác không thuộc các loại trên, ví dụ lỗi khi publish message
// Mặc định trả về 500 Internal Server Error
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class OperationFailedException extends RuntimeException {
    public OperationFailedException(String message) {
        super(message);
    }

     public OperationFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}