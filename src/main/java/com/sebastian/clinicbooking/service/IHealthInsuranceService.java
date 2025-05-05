package com.sebastian.clinicbooking.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sebastian.clinicbooking.DTO.healthInsurance.HealthInsuranceRequestDTO;
import com.sebastian.clinicbooking.DTO.healthInsurance.HealthInsuranceResponseDTO;
import com.sebastian.clinicbooking.model.HealthInsurance;

public interface IHealthInsuranceService {
    
    HealthInsuranceResponseDTO createHealthInsurance(HealthInsuranceRequestDTO healthInsuranceRequestDTO);
    HealthInsuranceResponseDTO getHealthInsuranceById(Long id);
    List<HealthInsuranceResponseDTO> getAllHealthInsurances();
    Page<HealthInsuranceResponseDTO> getAllHealthInsurances(Pageable pageable);
    List<HealthInsurance> getHealthInsuranceEntitiesByIds(List<Long> ids);
    HealthInsuranceResponseDTO updateHealthInsurance(Long id, HealthInsuranceRequestDTO healthInsuranceRequestDTO);
    void deleteHealthInsurance(Long id);
}
