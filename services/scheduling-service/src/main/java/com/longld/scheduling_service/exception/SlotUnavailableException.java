package com.longld.scheduling_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) // 409 Conflict thường dùng cho lỗi này
public class SlotUnavailableException extends RuntimeException {
    public SlotUnavailableException(String message) {
        super(message);
    }
     public SlotUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}