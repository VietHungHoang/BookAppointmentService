package com.longld.scheduling_service.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.longld.scheduling_service.entity.AppointmentSlot;
import com.longld.scheduling_service.exception.SchedulingOperationException; 
import com.longld.scheduling_service.exception.SlotUnavailableException;
import com.longld.scheduling_service.integration.client.EmployeeServiceClient;
import com.longld.scheduling_service.dto.request.AppointmentRequestDTO;
import com.longld.scheduling_service.dto.request.AvailableEmployeeInfoDTO;
import com.longld.scheduling_service.dto.response.AppointmentSlotDTO;
import com.longld.scheduling_service.enums.AppointmentStatus;
import com.longld.scheduling_service.repository.AppointmentSlotRepository;

import com.longld.scheduling_service.service.SchedulingService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchedulingServiceImpl implements SchedulingService {
    private final AppointmentSlotRepository appointmentSlotRepository;
    private final EmployeeServiceClient employeeServiceClient;

    private static final List<AppointmentStatus> ACTIVE_STATUSES = Arrays.asList(
            AppointmentStatus.CONFIRMED,
            AppointmentStatus.PENDING_CONFIRMATION
    );

    public List<AvailableEmployeeInfoDTO> getFreeEmployeeByScheduleAndTime(Long serviceId, LocalDateTime time) {
        List<AvailableEmployeeInfoDTO> result = employeeServiceClient.getFreeEmployeeByScheduleAndTime(serviceId, time);
        List<Long> result2 = appointmentSlotRepository.getBusyEmployeeByTime(time);
        List<AvailableEmployeeInfoDTO> finalResult = result.stream()
        .filter(dto -> !result2.contains(dto.getEmployeeId()))
        .toList();
        return finalResult;
    }

    @Override
    @Transactional
    public AppointmentSlotDTO bookAppointmentSlot(AppointmentRequestDTO request) {
        List<AppointmentSlot> lockedSlots = appointmentSlotRepository.findAndLockRelevantSlotsForEmployee(
                request.getEmployeeId(),
                request.getTime(),
                ACTIVE_STATUSES
        );

        if(lockedSlots == null || lockedSlots.isEmpty() ){
            AppointmentSlot newSlot = AppointmentSlot.builder()
                .customerId(request.getCustomerId())
                .serviceId(request.getServiceId())
                .employeeId(request.getEmployeeId())
                .time(request.getTime())
                .status(AppointmentStatus.CONFIRMED) // Mặc định là CONFIRMED khi đặt thành công
                .notes(request.getNotes())
                .build();

            try {
                AppointmentSlot savedSlot = appointmentSlotRepository.save(newSlot);
                log.info("Successfully saved new appointment slot with ID: {}", savedSlot.getId());
                return savedSlot.toDTO();
            } catch (DataIntegrityViolationException e) {
                log.error("Data integrity violation during booking (likely duplicate slot): {}", request, e);
                throw new SlotUnavailableException("Failed to book slot due to a data conflict (slot might be already taken).", e);
            } catch (Exception e) {
                log.error("Failed to save appointment slot for request: {}", request, e);
                throw new SchedulingOperationException("Could not save the appointment slot.", e);
            }
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public AppointmentSlotDTO updateAppointmentSlot(Long appointmentId, AppointmentRequestDTO request) {
        return null;
    }

    @Override
    @Transactional
    public void cancelAppointmentSlot(Long appointmentId) {
    }
}