package com.sebastian.clinicbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebastian.clinicbooking.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
