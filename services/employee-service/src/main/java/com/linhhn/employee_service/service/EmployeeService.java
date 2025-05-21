package com.linhhn.employee_service.service;

import com.linhhn.employee_service.dto.AvailableEmployeeInfoDTO;
import com.linhhn.employee_service.dto.EmployeeDTO;
import com.linhhn.employee_service.dto.WorkScheduleDTO;
import com.linhhn.employee_service.model.Employee;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface EmployeeService {
    List<Employee> getAllEmployees();
    
    List<EmployeeDTO> getEmployeesByServiceId(Long serviceId);

    List<WorkScheduleDTO> getWorkSchedulesByDate(LocalDate date);

    List<WorkScheduleDTO> getWorkSchedulesByEmployeeAndTime(Long employeeId, LocalDateTime startDate, LocalDateTime endDate);

    List<AvailableEmployeeInfoDTO> getFreeEmployeeByScheduleAndTime(Long serviceId, LocalDateTime time);
}

