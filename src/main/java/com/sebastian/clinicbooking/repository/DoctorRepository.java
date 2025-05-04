package com.sebastian.clinicbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebastian.clinicbooking.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    
}
