package com.vhh.appointment_booking_service.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vhh.appointment_booking_service.dto.request.AppointmentCreationRequest;
import com.vhh.appointment_booking_service.dto.response.AppointmentResponse;
import com.vhh.appointment_booking_service.integration.SchedulingServiceClient;
import com.vhh.appointment_booking_service.service.AppointmentService;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {
    private final SchedulingServiceClient schedulingServiceClient;

    @Override
    public AppointmentResponse createAppointment(AppointmentCreationRequest request) {
        ResponseEntity<AppointmentResponse> result = schedulingServiceClient.bookSlot(request);
        if(result.getStatusCode() == HttpStatus.OK) {
            return result.getBody();
        }
        return null;

    }

    @Override
    public AppointmentResponse updateAppointment(Long appointmentId, AppointmentCreationRequest request) {
        return null;
    }

    @Override
    public void cancelAppointment(Long appointmentId) {
        log.info("Processing cancel appointment request for ID {}", appointmentId);
        schedulingServiceClient.cancelSlot(appointmentId);
    }
}