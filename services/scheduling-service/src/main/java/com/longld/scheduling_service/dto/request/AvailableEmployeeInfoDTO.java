package com.longld.scheduling_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableEmployeeInfoDTO {
    private Long employeeId;
    private String name;
}