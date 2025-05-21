package com.longld.scheduling_service.integration.client;

import java.time.LocalDateTime;
import java.util.List; 

import com.longld.scheduling_service.dto.request.AvailableEmployeeInfoDTO;

public interface EmployeeServiceClient {
    List<AvailableEmployeeInfoDTO> getFreeEmployeeByScheduleAndTime(Long serviceId, LocalDateTime time);
}