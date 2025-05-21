package com.linhhn.employee_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.linhhn.employee_service.dto.AvailableEmployeeInfoDTO;
import com.linhhn.employee_service.dto.EmployeeDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_id", nullable = false)
    private Long serviceId;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(length = 100)
    private String title; // Ví dụ: "Bác sĩ", "Chuyên viên tư vấn"

    @Column(unique = true, length = 100)
    private String email;

    @Column(length = 20)
    private String phoneNumber;

    private int yearsOfExp;

    private String expertise;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private boolean isActive = true; 

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<WorkSchedule> workSchedule;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public EmployeeDTO toDTO() {
        return EmployeeDTO.builder()
        .id(this.id)
        .fullName(this.fullName)
        .title(this.title)
        .expertise(this.expertise)
        .build();
    } 

    public AvailableEmployeeInfoDTO toAvailableInfoDTO() {
    return AvailableEmployeeInfoDTO.builder()
        .employeeId(this.id)
        .name(this.fullName)
        .build();
}
}
