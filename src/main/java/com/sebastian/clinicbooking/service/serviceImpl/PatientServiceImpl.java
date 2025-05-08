package com.sebastian.clinicbooking.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sebastian.clinicbooking.DTO.patient.PatientRequestDTO;
import com.sebastian.clinicbooking.DTO.patient.PatientResponseDTO;
import com.sebastian.clinicbooking.enums.Roles;
import com.sebastian.clinicbooking.exception.ResourceNotFoundException;
import com.sebastian.clinicbooking.mapper.PatientMapper;
import com.sebastian.clinicbooking.model.HealthInsurance;
import com.sebastian.clinicbooking.model.Patient;
import com.sebastian.clinicbooking.repository.PatientRepository;
import com.sebastian.clinicbooking.service.IHealthInsuranceService;
import com.sebastian.clinicbooking.service.IPatientService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PatientServiceImpl implements IPatientService {
    
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final IHealthInsuranceService healthInsuranceService;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, PatientMapper patientMapper, IHealthInsuranceService healthInsuranceService) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
        this.healthInsuranceService = healthInsuranceService;
    }
    
    @Override
    @Transactional
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        List<HealthInsurance> healthInsurances = healthInsuranceService.getHealthInsuranceEntitiesByIds(patientRequestDTO.getInsuranceIds());
        if (healthInsurances.isEmpty()) {
            throw new IllegalArgumentException("Health Insurances cannot be empty");
        } 

        Patient patient = patientMapper.toEntity(patientRequestDTO);
        patient.setInsurances(healthInsurances);
        patient.setRole(Roles.PATIENT);
        patientRepository.save(patient);
        log.info("Patient created with ID: {}", patient.getId());
        return patientMapper.toDto(patient);
        
    }

    @Override
    @Transactional(readOnly = true)
    public PatientResponseDTO getPatientById(Long id) {
        Patient patient = findPatientById(id);
        log.info("Patient found with ID: {}", patient.getId());
        return patientMapper.toDto(patient);
    }

    @Override
    @Transactional(readOnly = true)
    public Patient getPatientEntityById(Long id) {
        Patient patient = findPatientById(id);
        log.info("Patient found with ID: {}", patient.getId());
        return patient;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientResponseDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        log.info("Found {} patients", patients.size());
        return patientMapper.toDtoList(patients);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PatientResponseDTO> getAllPatients(Pageable pageable) {
        Page<Patient> patients = patientRepository.findAll(pageable);
        log.info("Found {} patients", patients.getTotalElements());
        return patientMapper.toDtoPage(patients);
    }

    @Override
    @Transactional
    public PatientResponseDTO updatePatient(Long id, PatientRequestDTO patientRequestDTO) {
        Patient patient = findPatientById(id);
        
        List<HealthInsurance> healthInsurances = healthInsuranceService.getHealthInsuranceEntitiesByIds(patientRequestDTO.getInsuranceIds());
        if (healthInsurances.isEmpty()) {
            throw new IllegalArgumentException("Health Insurances cannot be empty");
        } 

        patientMapper.updateEntityFromDto(patientRequestDTO, patient);
        patient.setInsurances(healthInsurances);
        patientRepository.save(patient);
        log.info("Patient updated with ID: {}", patient.getId());
        return patientMapper.toDto(patient);
    }

    @Override
    @Transactional
    public void deletePatient(Long id) {
        Patient patient = findPatientById(id);
        patientRepository.delete(patient);
        log.info("Patient deleted with ID: {}", patient.getId());
    }

    private Patient findPatientById(Long id) {
        return patientRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + id));
    }
  
    
}
