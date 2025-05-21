package com.vhh.appointment_booking_service.exception;

import org.springframework.http.HttpStatusCode;

public class ExternalServiceException extends RuntimeException {
    private HttpStatusCode statusCode;

    public ExternalServiceException(String message) {
        super(message);
    }
    public ExternalServiceException(String message, HttpStatusCode statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public ExternalServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }
}