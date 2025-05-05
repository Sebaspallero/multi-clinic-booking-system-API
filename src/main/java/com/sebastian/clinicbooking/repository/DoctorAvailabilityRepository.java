package com.sebastian.clinicbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebastian.clinicbooking.model.DoctorAvailability;

public interface DoctorAvailabilityRepository extends JpaRepository<DoctorAvailability, Long> {
    
}
