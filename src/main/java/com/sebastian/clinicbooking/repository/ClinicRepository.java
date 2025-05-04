package com.sebastian.clinicbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebastian.clinicbooking.model.Clinic;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {

}
