package com.longld.scheduling_service.service;

import com.longld.scheduling_service.dto.request.AppointmentRequestDTO;
import com.longld.scheduling_service.dto.response.AppointmentSlotDTO;

public interface SchedulingService {

    AppointmentSlotDTO bookAppointmentSlot(AppointmentRequestDTO request);

    AppointmentSlotDTO updateAppointmentSlot(Long appointmentId, AppointmentRequestDTO request);

    void cancelAppointmentSlot(Long appointmentId);
}