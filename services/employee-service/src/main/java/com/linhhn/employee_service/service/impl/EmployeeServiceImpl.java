package com.linhhn.employee_service.service.impl;

import com.linhhn.employee_service.dto.AvailableEmployeeInfoDTO;
import com.linhhn.employee_service.dto.EmployeeDTO;
import com.linhhn.employee_service.dto.WorkScheduleDTO;
import com.linhhn.employee_service.model.Employee;
import com.linhhn.employee_service.model.WorkSchedule;
import com.linhhn.employee_service.model.cache.WorkScheduleCache;
import com.linhhn.employee_service.repository.EmployeeRepository;
import com.linhhn.employee_service.repository.WorkScheduleRepository;
import com.linhhn.employee_service.service.EmployeeService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final WorkScheduleRepository workScheduleRepository;

    // private final RedisTemplate<String, Object> redisTemplate;

    // Lấy danh sách nhân viên
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<AvailableEmployeeInfoDTO> getFreeEmployeeByScheduleAndTime(Long serviceId, LocalDateTime time) {
        // List<Employee> result = getFromRedis(serviceId.toString() + time.toString());\
        List<Employee> result = null;
        if(result == null) {
            result = this.employeeRepository.findByScheduleAndTime(serviceId, time.toLocalDate(), time.toLocalTime());
            // this.saveToRedis(serviceId.toString() + time.toString(),result);
        }
        List<AvailableEmployeeInfoDTO> dtos = result.stream()
            .map(Employee::toAvailableInfoDTO)
            .toList();
        return dtos;
    }

   public  List<EmployeeDTO> getEmployeesByServiceId(Long serviceId) {
        List<Employee> employeeList = employeeRepository.findByServiceId(serviceId);
        if(employeeList != null && !employeeList.isEmpty()) {
            return employeeList.stream().map(Employee::toDTO).collect(Collectors.toList());
        }
        return new ArrayList<>();
   }

    public List<WorkScheduleDTO> getWorkSchedulesByDate(LocalDate date) {
        // List<WorkScheduleCache> workScheduleCaches = this.getFromRedis(date.toString());
        List<WorkScheduleDTO> workSchedulesDTO = null;
        // if(workScheduleCaches == null) {
            List<WorkSchedule> workSchedules = workScheduleRepository.findByDate(date);
            workSchedulesDTO = workSchedules.stream().map(WorkSchedule::toDTO).collect(Collectors.toList());
            // this.saveToRedis(date.toString(), workSchedules.stream().map(WorkSchedule::toCache).collect(Collectors.toList()));
        // } else {
            // workSchedulesDTO = workScheduleCaches.stream().map(WorkScheduleCache::toDTO).collect(Collectors.toList());
        // }
        return workSchedulesDTO;
    }

    public List<WorkScheduleDTO> getWorkSchedulesByEmployeeAndTime(Long employeeId, LocalDateTime startDate, LocalDateTime endDate) {
        List<WorkScheduleDTO> todaySchedules = this.getWorkSchedulesByDate(startDate.toLocalDate());
        if(todaySchedules == null || todaySchedules.isEmpty()) {
            return new ArrayList<>();
        }
        else {
            return todaySchedules.stream()
            .filter(schedule -> schedule.getEmployeeId().equals(employeeId))  // Kiểm tra ID nhân viên
            .filter(schedule -> {
                // Kiểm tra xem lịch làm việc có bao trọn thời gian từ startDate đến endDate không
                LocalDateTime scheduleStartTime = schedule.getDate().atTime(schedule.getStartTime());
                LocalDateTime scheduleEndTime = schedule.getDate().atTime(schedule.getEndTime());
                boolean res1 = (scheduleStartTime.isBefore(startDate) || scheduleStartTime.isEqual(startDate));
                boolean res2 = (scheduleEndTime.isAfter(endDate) || scheduleEndTime.isEqual(endDate));
                return res1 && res2;
            })
            .collect(Collectors.toList());
        }
    }

    // public <T> void saveToRedis(String key, T value) {
    //     redisTemplate.opsForValue().set(key, value);
    // }

    // public <T> T getFromRedis(String key) {
        
    //     return (T) redisTemplate.opsForValue().get(key);
    // }
}

