package com.sebastian.clinicbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebastian.clinicbooking.model.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

}
