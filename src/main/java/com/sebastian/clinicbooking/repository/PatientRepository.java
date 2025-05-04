package com.sebastian.clinicbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebastian.clinicbooking.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
