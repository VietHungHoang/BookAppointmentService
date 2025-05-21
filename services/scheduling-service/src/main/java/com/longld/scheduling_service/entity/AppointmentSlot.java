package com.longld.scheduling_service.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.longld.scheduling_service.dto.response.AppointmentSlotDTO;
import com.longld.scheduling_service.enums.AppointmentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "appointment_slots",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = {"employee_id", "start_time"})
       })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AppointmentSlot {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "service_id", nullable = false)
    private Long serviceId;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private AppointmentStatus status;

    @Column(name = "notes", length = 500)
    private String notes;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public AppointmentSlotDTO toDTO() {
        AppointmentSlotDTO dto = new AppointmentSlotDTO();
        dto.setAppointmentId(this.getId());
        dto.setCustomerId(this.getCustomerId());
        dto.setServiceId(this.getServiceId());
        dto.setEmployeeId(this.getEmployeeId());
        dto.setTime(this.getTime());
        dto.setStatus(this.getStatus());
        dto.setNotes(this.getNotes());
        dto.setCreatedAt(this.getCreatedAt());
        dto.setUpdatedAt(this.getUpdatedAt());
        return dto;
    }
}
