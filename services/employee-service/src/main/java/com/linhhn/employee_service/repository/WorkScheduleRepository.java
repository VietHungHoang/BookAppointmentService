package com.linhhn.employee_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.linhhn.employee_service.model.WorkSchedule;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, Long> {
        List<WorkSchedule> findByDate(LocalDate date);

        List<WorkSchedule> findByDateBetween(LocalDate startDate, LocalDate endDate);
}