package com.linhhn.employee_service.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvailableEmployeeInfoDTO {
    private Long employeeId;
    private String name;
}