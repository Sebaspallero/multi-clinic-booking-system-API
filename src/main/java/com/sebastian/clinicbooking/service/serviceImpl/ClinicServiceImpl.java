package com.sebastian.clinicbooking.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sebastian.clinicbooking.DTO.clinic.ClinicRequestDTO;
import com.sebastian.clinicbooking.DTO.clinic.ClinicResponseDTO;
import com.sebastian.clinicbooking.enums.Roles;
import com.sebastian.clinicbooking.exception.ResourceNotFoundException;
import com.sebastian.clinicbooking.mapper.ClinicMapper;
import com.sebastian.clinicbooking.model.Clinic;
import com.sebastian.clinicbooking.repository.ClinicRepository;
import com.sebastian.clinicbooking.service.IClinicService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClinicServiceImpl implements IClinicService{

    private final ClinicRepository clinicRepository;
    private final ClinicMapper clinicMapper;

    @Autowired
    public ClinicServiceImpl(ClinicRepository clinicRepository, ClinicMapper clinicMapper) {
        this.clinicRepository = clinicRepository;
        this.clinicMapper = clinicMapper;
    }

    @Override
    @Transactional
    public ClinicResponseDTO createClinic(ClinicRequestDTO clinicRequestDTO) {
        Clinic clinic = clinicMapper.toEntity(clinicRequestDTO);
        clinic.setRole(Roles.CLINIC);
        clinicRepository.save(clinic);
        log.info("Clinic created with ID: {}", clinic.getId());
        return clinicMapper.toDto(clinic);
    }

    @Override
    @Transactional(readOnly = true)
    public ClinicResponseDTO getClinicById(Long id) {
        Clinic clinic = findClinicById(id);
                
        log.info("Clinic found with ID: {}", clinic.getId());
        return clinicMapper.toDto(clinic);
    }

    @Override
    @Transactional(readOnly = true)
    public Clinic getClinicEntityById(Long id) {
        Clinic clinic = findClinicById(id);
                
        log.info("Clinic found with ID: {}", clinic.getId());
        return clinic;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClinicResponseDTO> getAllClinics() {
        List<Clinic> clinics = clinicRepository.findAll();
        log.info("Found {} clinics", clinics.size());
        return clinicMapper.toDtoList(clinics);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClinicResponseDTO> getAllClinics(Pageable pageable) {
        Page<Clinic> clinics = clinicRepository.findAll(pageable);
        log.info("Found {} clinics", clinics.getTotalElements());
        return clinicMapper.toDtoPage(clinics);
    }

    @Override
    @Transactional
    public ClinicResponseDTO updateClinic(Long id, ClinicRequestDTO clinicRequestDTO) {
        Clinic clinic = findClinicById(id);
        
        clinicMapper.updateEntityFromDto(clinicRequestDTO, clinic);
        clinicRepository.save(clinic);
        
        log.info("Clinic updated with ID: {}", clinic.getId());
        return clinicMapper.toDto(clinic);
    }

    @Override
    @Transactional
    public void deleteClinic(Long id) {
        Clinic clinic = findClinicById(id);
        
        clinicRepository.delete(clinic);
        log.info("Clinic deleted with ID: {}", clinic.getId());
    }

    private Clinic findClinicById(Long clinicId) {
        return clinicRepository.findById(clinicId)
                    .orElseThrow(() -> new ResourceNotFoundException("Clinic not found with id: " + clinicId));
    }
    
}
