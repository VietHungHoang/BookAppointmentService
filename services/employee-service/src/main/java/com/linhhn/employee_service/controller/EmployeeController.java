package com.linhhn.employee_service.controller;


import com.linhhn.employee_service.dto.AvailableEmployeeInfoDTO;
import com.linhhn.employee_service.dto.EmployeeDTO;
import com.linhhn.employee_service.dto.WorkScheduleDTO;
import com.linhhn.employee_service.model.Employee;
import com.linhhn.employee_service.service.EmployeeService;

import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{serviceId}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByServiceId(@PathVariable Long serviceId) {
        return ResponseEntity.ok(employeeService.getEmployeesByServiceId(serviceId));
    }

    @GetMapping("/work-schedule/{id}")
    public List<WorkScheduleDTO> getWorkScheduleByEmployeeId(@PathVariable Long id, @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
        @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<WorkScheduleDTO> res = employeeService.getWorkSchedulesByEmployeeAndTime(id, startDate, endDate);
        return res;
    }

    @GetMapping("/{serviceId}/free")
    public ResponseEntity<List<AvailableEmployeeInfoDTO>> getFreeEmployeeByScheduleAndTime(@PathVariable Long serviceId, @RequestParam("time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime time) {
        List<AvailableEmployeeInfoDTO> result = this.employeeService.getFreeEmployeeByScheduleAndTime(serviceId, time);
        return ResponseEntity.ok(result);
    }

}
