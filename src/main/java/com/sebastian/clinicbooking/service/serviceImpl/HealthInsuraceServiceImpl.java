package com.sebastian.clinicbooking.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sebastian.clinicbooking.DTO.healthInsurance.HealthInsuranceRequestDTO;
import com.sebastian.clinicbooking.DTO.healthInsurance.HealthInsuranceResponseDTO;
import com.sebastian.clinicbooking.exception.ResourceNotFoundException;
import com.sebastian.clinicbooking.mapper.HealthInsuranceMapper;
import com.sebastian.clinicbooking.model.HealthInsurance;
import com.sebastian.clinicbooking.repository.HelathInsuranceRepository;
import com.sebastian.clinicbooking.service.IHealthInsuranceService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HealthInsuraceServiceImpl implements IHealthInsuranceService {

    private final HelathInsuranceRepository healthInsuranceRepository;
    private final HealthInsuranceMapper healthInsuranceMapper;

    @Autowired
    public HealthInsuraceServiceImpl(HelathInsuranceRepository healthInsuranceRepository,
            HealthInsuranceMapper healthInsuranceMapper) {
        this.healthInsuranceRepository = healthInsuranceRepository;
        this.healthInsuranceMapper = healthInsuranceMapper;
    }
    
    @Override
    @Transactional
    public HealthInsuranceResponseDTO createHealthInsurance(HealthInsuranceRequestDTO healthInsuranceRequestDTO) {
        HealthInsurance healthInsurance = healthInsuranceMapper.toEntity(healthInsuranceRequestDTO);
        healthInsuranceRepository.save(healthInsurance);
        log.info("Health Insurance created with ID: {}", healthInsurance.getId());
        return healthInsuranceMapper.toDto(healthInsurance);
    }

    @Override
    @Transactional(readOnly = true)
    public HealthInsuranceResponseDTO getHealthInsuranceById(Long id) {
        HealthInsurance healthInsurance = findHealthInsuranceById(id);
                
        log.info("Health Insurance found with ID: {}", healthInsurance.getId());
        return healthInsuranceMapper.toDto(healthInsurance);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HealthInsuranceResponseDTO> getAllHealthInsurances() {
        List<HealthInsurance> healthInsurances = healthInsuranceRepository.findAll();
        log.info("Found {} Health Insurances", healthInsurances.size());
        return healthInsuranceMapper.toDtoList(healthInsurances);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<HealthInsuranceResponseDTO> getAllHealthInsurances(Pageable pageable) {
        Page<HealthInsurance> healthInsurances = healthInsuranceRepository.findAll(pageable);
        log.info("Found {} Health Insurances", healthInsurances.getTotalElements());
        return healthInsuranceMapper.toDtoPage(healthInsurances);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HealthInsurance> getHealthInsuranceEntitiesByIds(List<Long> ids) {
        List<HealthInsurance> healthInsurances = healthInsuranceRepository.findAllById(ids);
        log.info("Found {} Health Insurances by IDs", healthInsurances.size());
        return healthInsurances;
    }
    

    @Override
    @Transactional
    public HealthInsuranceResponseDTO updateHealthInsurance(Long id, HealthInsuranceRequestDTO healthInsuranceRequestDTO) {
        HealthInsurance healthInsurance = findHealthInsuranceById(id);
        
        healthInsuranceMapper.updateEntityFromDto(healthInsuranceRequestDTO, healthInsurance);
        healthInsuranceRepository.save(healthInsurance);
        
        log.info("Health Insurance updated with ID: {}", healthInsurance.getId());
        return healthInsuranceMapper.toDto(healthInsurance);
    }

    @Override
    @Transactional
    public void deleteHealthInsurance(Long id) {
        HealthInsurance healthInsurance = findHealthInsuranceById(id);
        
        healthInsuranceRepository.delete(healthInsurance);
        log.info("Health Insurance deleted with ID: {}", id);
    }

    private HealthInsurance findHealthInsuranceById(Long healthInsuranceId) {
        return healthInsuranceRepository.findById(healthInsuranceId)
                .orElseThrow(() -> new ResourceNotFoundException("Health Insurance not found with id: " + healthInsuranceId));
    }

    
}
