package com.linhhn.employee_service.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

import com.linhhn.employee_service.dto.WorkScheduleDTO;
import com.linhhn.employee_service.model.cache.WorkScheduleCache;

@Entity
@Table(name = "work_schedules")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class WorkSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    private String note;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public WorkScheduleDTO toDTO() {
        return WorkScheduleDTO.builder()
            .id(this.id)
            .date(this.date)
            .startTime(this.startTime)
            .endTime(this.endTime)
            .note(this.note)
            .employeeId(this.employee != null ? this.employee.getId() : null)
            .build();
    }

    public WorkScheduleCache toCache() {
        return WorkScheduleCache.builder().id(this.id)
        .date(this.date.toString())
        .startTime(this.startTime.toString())
        .endTime(this.endTime.toString())
        .note(this.note)
        .employeeId(this.employee != null ? this.employee.getId() : null)
        .build();
    }
}

