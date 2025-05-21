package com.longld.scheduling_service.repository;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.longld.scheduling_service.entity.AppointmentSlot;
import com.longld.scheduling_service.enums.AppointmentStatus;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentSlotRepository extends JpaRepository<AppointmentSlot, Long> {

    @Query("SELECT a.employeeId FROM AppointmentSlot a WHERE a.time = :time")
    List<Long> getBusyEmployeeByTime(@Param("time") LocalDateTime time);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM AppointmentSlot a WHERE a.employeeId = :employeeId " +
    "AND a.status IN :relevantStatuses " +
    "AND a.time = :time")
    List<AppointmentSlot> findAndLockRelevantSlotsForEmployee(
            @Param("employeeId") Long employeeId,
            @Param("time") LocalDateTime time,
            @Param("relevantStatuses") List<AppointmentStatus> relevantStatuses
    );
}
