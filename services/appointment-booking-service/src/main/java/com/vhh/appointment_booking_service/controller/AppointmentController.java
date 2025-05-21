package com.vhh.appointment_booking_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vhh.appointment_booking_service.dto.NotificationDTO;
import com.vhh.appointment_booking_service.dto.request.AppointmentCreationRequest;
import com.vhh.appointment_booking_service.dto.response.AppointmentResponse;
import com.vhh.appointment_booking_service.messaging.publisher.AppointmentEventPublisher;
import com.vhh.appointment_booking_service.service.AppointmentService;

@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor 
@Slf4j
public class AppointmentController {
    private final AppointmentEventPublisher publisher;

    private final AppointmentService appointmentService;

    @PostMapping("/slots/book")
    public ResponseEntity<AppointmentResponse> createAppointment(@RequestBody AppointmentCreationRequest request) {
        AppointmentResponse appointmentResponse = appointmentService.createAppointment(request);
        if(appointmentResponse != null) {
            NotificationDTO notificationDTO = appointmentResponse.toNotificationDTO();
            publisher.sendUser(notificationDTO);
            return ResponseEntity.ok(appointmentResponse);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}