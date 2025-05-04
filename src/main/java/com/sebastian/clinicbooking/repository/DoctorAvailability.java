package com.sebastian.clinicbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorAvailability extends JpaRepository<DoctorAvailability, Long> {
    
}
