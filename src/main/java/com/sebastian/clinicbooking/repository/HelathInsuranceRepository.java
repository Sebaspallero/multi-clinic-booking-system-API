package com.sebastian.clinicbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebastian.clinicbooking.model.HealthInsurance;

public interface HelathInsuranceRepository extends JpaRepository<HealthInsurance, Long> {

}
