package com.sebastian.clinicbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebastian.clinicbooking.model.Speciality;

public interface SpecialityRepository extends JpaRepository<Speciality, Long> {

}
