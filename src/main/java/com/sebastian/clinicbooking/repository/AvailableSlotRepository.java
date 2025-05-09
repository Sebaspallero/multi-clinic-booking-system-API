package com.sebastian.clinicbooking.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sebastian.clinicbooking.model.AvailableSlot;
import com.sebastian.clinicbooking.model.DoctorAvailability;

public interface AvailableSlotRepository extends JpaRepository<AvailableSlot, Long> {
    List<AvailableSlot> findByDoctorIdAndAvailableTrue(Long doctorId);
    List<AvailableSlot> findByClinicIdAndSlotDateTimeBetween(Long clinicId, LocalDateTime start, LocalDateTime end);

    @Query("SELECT MAX(s.slotDateTime) FROM AvailableSlot s WHERE s.doctorAvailability = :availability")
    LocalDateTime findMaxSlotDateTimeByAvailability(@Param("availability") DoctorAvailability availability);

    boolean existsByDoctorAvailabilityAndSlotDateTime(DoctorAvailability availability, LocalDateTime slotDateTime);
}

