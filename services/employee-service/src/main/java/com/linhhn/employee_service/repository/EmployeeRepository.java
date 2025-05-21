package com.linhhn.employee_service.repository;

import com.linhhn.employee_service.model.Employee;

import io.lettuce.core.dynamic.annotation.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByServiceId(Long serviceId);

    @Query("""
    SELECT e FROM Employee e
    JOIN e.workSchedule ws
    WHERE e.serviceId = :serviceId
    AND ws.date = :date
    AND :time BETWEEN ws.startTime AND ws.endTime
    """)
    List<Employee> findByScheduleAndTime(@Param("serviceId") Long serviceId, 
                                        @Param("date") LocalDate date, 
                                        @Param("time") LocalTime time);

}