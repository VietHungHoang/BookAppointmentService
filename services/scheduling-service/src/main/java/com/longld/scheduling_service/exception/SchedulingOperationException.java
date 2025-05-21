package com.longld.scheduling_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
// Cho các lỗi nội bộ khác, ví dụ lỗi DB không mong muốn
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SchedulingOperationException extends RuntimeException {
    public SchedulingOperationException(String message) {
        super(message);
    }
    public SchedulingOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}