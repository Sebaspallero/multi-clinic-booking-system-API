package com.sebastian.clinicbooking.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sebastian.clinicbooking.DTO.doctorAvailability.DoctorAvailabilityRequestDTO;
import com.sebastian.clinicbooking.DTO.doctorAvailability.DoctorAvailabilityResponseDTO;
import com.sebastian.clinicbooking.mapper.DoctorAvailabilityMapper;
import com.sebastian.clinicbooking.model.Doctor;
import com.sebastian.clinicbooking.model.DoctorAvailability;
import com.sebastian.clinicbooking.repository.DoctorAvailabilityRepository;
import com.sebastian.clinicbooking.service.IDoctorAvailabilityService;
import com.sebastian.clinicbooking.service.IDoctorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DoctorAvailabilityImpl implements IDoctorAvailabilityService{
    
    private final DoctorAvailabilityRepository doctorAvailabilityRepository;
    private final DoctorAvailabilityMapper doctorAvailabilityMapper;
    private final IDoctorService doctorService;

    @Autowired
    public DoctorAvailabilityImpl(DoctorAvailabilityRepository doctorAvailabilityRepository, DoctorAvailabilityMapper doctorAvailabilityMapper, IDoctorService doctorService) {
        this.doctorAvailabilityRepository = doctorAvailabilityRepository;
        this.doctorAvailabilityMapper = doctorAvailabilityMapper;
        this.doctorService = doctorService;
    }
    
    
    @Override
    @Transactional
    public DoctorAvailabilityResponseDTO createDoctorAvailability(DoctorAvailabilityRequestDTO requestDTO) {
        Doctor doctor = doctorService.getDoctorEntityById(requestDTO.getDoctorId());

        DoctorAvailability doctorAvailability = doctorAvailabilityMapper.toEntity(requestDTO);
        doctorAvailability.setDoctor(doctor);
        doctorAvailabilityRepository.save(doctorAvailability);
        log.info("Doctor Availability created with ID: {}", doctorAvailability.getId());
        return doctorAvailabilityMapper.toDto(doctorAvailability);
        
    }

    @Override
    @Transactional(readOnly = true)
    public DoctorAvailabilityResponseDTO getDoctorAvailabilityById(Long id) {
        DoctorAvailability doctorAvailability = findDoctorAvailabilityById(id);
        log.info("Doctor Availability found with ID: {}", doctorAvailability.getId());
        return doctorAvailabilityMapper.toDto(doctorAvailability);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DoctorAvailabilityResponseDTO> getAllDoctorAvailabilities() {
        List<DoctorAvailability> doctorAvailabilities = doctorAvailabilityRepository.findAll();
        log.info("Found {} Doctor Availabilities", doctorAvailabilities.size());
        return doctorAvailabilityMapper.toDtoList(doctorAvailabilities);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DoctorAvailabilityResponseDTO> getAllDoctorAvailabilities(Pageable pageable) {
        Page<DoctorAvailability> doctorAvailabilities = doctorAvailabilityRepository.findAll(pageable);
        log.info("Found {} Doctor Availabilities", doctorAvailabilities.getTotalElements());
        return doctorAvailabilities.map(doctorAvailabilityMapper::toDto);
    }

    @Override
    @Transactional
    public DoctorAvailabilityResponseDTO updateDoctorAvailability(Long id, DoctorAvailabilityRequestDTO requestDTO) {
        DoctorAvailability doctorAvailability = findDoctorAvailabilityById(requestDTO.getDoctorId());
        Doctor doctor = doctorService.getDoctorEntityById(id);
        doctorAvailabilityMapper.updateEntityFromDto(requestDTO, doctorAvailability);
        doctorAvailability.setDoctor(doctor);
        doctorAvailabilityRepository.save(doctorAvailability);
        log.info("Doctor Availability updated with ID: {}", doctorAvailability.getId());
        return doctorAvailabilityMapper.toDto(doctorAvailability);
    }

    @Override
    @Transactional
    public void deleteDoctorAvailability(Long id) {
        DoctorAvailability doctorAvailability = findDoctorAvailabilityById(id);
        doctorAvailabilityRepository.delete(doctorAvailability);
        log.info("Doctor Availability deleted with ID: {}", id);
    }

    private DoctorAvailability findDoctorAvailabilityById(Long id) {
        return doctorAvailabilityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Doctor Availability not found with ID: " + id));
    }

    

}
