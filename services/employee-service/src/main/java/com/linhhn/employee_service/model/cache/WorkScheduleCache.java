package com.linhhn.employee_service.model.cache;
import java.time.LocalDate;
import java.time.LocalTime;

import com.linhhn.employee_service.dto.WorkScheduleDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class WorkScheduleCache {

    private Long id;
    
    private String date;

    private String startTime;

    private String endTime;

    private String note;

    private Long employeeId;

    public WorkScheduleDTO toDTO() {
        return WorkScheduleDTO.builder()
        .id(this.id)
        .date(LocalDate.parse(this.date))
        .startTime(LocalTime.parse(this.startTime))
        .endTime(LocalTime.parse(this.endTime))
        .note(this.note)
        .employeeId(this.employeeId)
        .build();
    }
}

