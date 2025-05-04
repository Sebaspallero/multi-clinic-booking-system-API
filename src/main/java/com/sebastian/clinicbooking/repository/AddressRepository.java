package com.sebastian.clinicbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebastian.clinicbooking.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
    
}
