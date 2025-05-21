// package com.vhh.appointment_booking_service.entity;

// import java.time.LocalDateTime;

// import org.hibernate.annotations.CreationTimestamp;
// import org.hibernate.annotations.UpdateTimestamp;

// import com.vhh.appointment_booking_service.enums.AppointmentStatus;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.EnumType;
// import jakarta.persistence.Enumerated;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Entity
// @Table(name = "appointments")
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// public class Appointment {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "appointment_id")
//     private Long appointmentId;

//     @Column(name = "customer_id", nullable = false)
//     private Long customerId;

//     @Column(name = "service_id", nullable = false)
//     private Long serviceId; 

//     @Column(name = "staff_id") // Có thể null nếu không chọn nhân viên cụ thể
//     private Long staffId; 

//     @Column(name = "start_time", nullable = false)
//     private LocalDateTime startTime;

//     @Column(name = "end_time", nullable = false)
//     private LocalDateTime endTime;

//     @Enumerated(EnumType.STRING)
//     @Column(nullable = false, length = 30)
//     private AppointmentStatus status;

//     @Column(name = "notes", columnDefinition = "TEXT")
//     private String notes;

//     @Column(name = "cancellation_reason", columnDefinition = "TEXT")
//     private String cancellationReason;

//     @CreationTimestamp
//     @Column(name = "created_at", nullable = false, updatable = false)
//     private LocalDateTime createdAt;

//     @UpdateTimestamp
//     @Column(name = "updated_at", nullable = false)
//     private LocalDateTime updatedAt;
// }