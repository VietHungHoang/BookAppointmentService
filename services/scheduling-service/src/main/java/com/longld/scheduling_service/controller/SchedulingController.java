package com.longld.scheduling_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.longld.scheduling_service.dto.request.AppointmentRequestDTO;
import com.longld.scheduling_service.dto.request.AvailableEmployeeInfoDTO;
import com.longld.scheduling_service.dto.response.AppointmentSlotDTO;
import com.longld.scheduling_service.service.impl.SchedulingServiceImpl;

@RestController
@RequestMapping("/api/v1/scheduling")
@RequiredArgsConstructor
@Slf4j
public class SchedulingController {

    private final SchedulingServiceImpl schedulingService;

    @PostMapping("/slots/book")
    public ResponseEntity<AppointmentSlotDTO> bookSlot(
            @Valid @RequestBody AppointmentRequestDTO request) {
        AppointmentSlotDTO bookedAppointment = schedulingService.bookAppointmentSlot(request);
        return ResponseEntity.ok(bookedAppointment);
    }

    @PutMapping("/slots/{appointmentId}/update")
    public ResponseEntity<AppointmentSlotDTO> updateSlot(
            @PathVariable Long appointmentId,
            @Valid @RequestBody AppointmentRequestDTO request) {
        log.info("Received request to update slot ID {}: {}", appointmentId, request);
        AppointmentSlotDTO updatedAppointment = schedulingService.updateAppointmentSlot(appointmentId, request);
        log.info("Slot updated successfully for ID {}: {}", appointmentId, updatedAppointment);
        return ResponseEntity.ok(updatedAppointment);
    }

    @PostMapping("/slots/{appointmentId}/cancel")
    public ResponseEntity<Void> cancelSlot(@PathVariable Long appointmentId) {
        schedulingService.cancelAppointmentSlot(appointmentId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/availability/{serviceId}")
    public ResponseEntity<List<AvailableEmployeeInfoDTO>> getFreeEmployeeByScheduleAndTime(@PathVariable Long serviceId
        , @RequestParam("time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime time
        ) {
        List<AvailableEmployeeInfoDTO> result = schedulingService.getFreeEmployeeByScheduleAndTime(serviceId, time);
        return ResponseEntity.ok(result);
    }

}
