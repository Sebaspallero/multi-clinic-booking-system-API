package com.sebastian.clinicbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebastian.clinicbooking.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

}
