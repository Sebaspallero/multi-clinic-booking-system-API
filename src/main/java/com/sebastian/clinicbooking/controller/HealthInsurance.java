package com.sebastian.clinicbooking.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sebastian.clinicbooking.DTO.healthInsurance.HealthInsuranceRequestDTO;
import com.sebastian.clinicbooking.DTO.healthInsurance.HealthInsuranceResponseDTO;
import com.sebastian.clinicbooking.service.IHealthInsuranceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/health-insurance")
public class HealthInsurance {

    private final IHealthInsuranceService healthInsuranceService;

    @Autowired
    public HealthInsurance(IHealthInsuranceService healthInsuranceService) {
        this.healthInsuranceService = healthInsuranceService;
    }

    @PostMapping
    public ResponseEntity<HealthInsuranceResponseDTO> createHealthInsurance(@RequestBody @Valid HealthInsuranceRequestDTO healthInsuranceRequestDTO) {
        HealthInsuranceResponseDTO createdHealthInsurance = healthInsuranceService.createHealthInsurance(healthInsuranceRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHealthInsurance);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HealthInsuranceResponseDTO> getHealthInsuranceById(@PathVariable Long id) {
        HealthInsuranceResponseDTO healthInsurance = healthInsuranceService.getHealthInsuranceById(id);
        return ResponseEntity.status(HttpStatus.OK).body(healthInsurance);
    }

    @GetMapping
    public ResponseEntity<List<HealthInsuranceResponseDTO>> getAllHealthInsurances() {
        List<HealthInsuranceResponseDTO> healthInsurances = healthInsuranceService.getAllHealthInsurances();
        return ResponseEntity.status(HttpStatus.OK).body(healthInsurances);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<HealthInsuranceResponseDTO>> getAllHealthInsurancesPageable(Pageable pageable) {
        Page<HealthInsuranceResponseDTO> healthInsurances = healthInsuranceService.getAllHealthInsurances(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(healthInsurances);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HealthInsuranceResponseDTO> updateHealthInsurance(@PathVariable Long id, @RequestBody @Valid HealthInsuranceRequestDTO healthInsuranceRequestDTO) {
        HealthInsuranceResponseDTO updatedHealthInsurance = healthInsuranceService.updateHealthInsurance(id, healthInsuranceRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedHealthInsurance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHealthInsurance(@PathVariable Long id) {
        healthInsuranceService.deleteHealthInsurance(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }   
}
