package com.sebastian.clinicbooking.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sebastian.clinicbooking.DTO.doctorAvailability.DoctorAvailabilityRequestDTO;
import com.sebastian.clinicbooking.DTO.doctorAvailability.DoctorAvailabilityResponseDTO;

public interface IDoctorAvailabilityService {

    DoctorAvailabilityResponseDTO createDoctorAvailability(DoctorAvailabilityRequestDTO requestDTO);
    DoctorAvailabilityResponseDTO getDoctorAvailabilityById(Long id);
    List<DoctorAvailabilityResponseDTO> getAllDoctorAvailabilities();
    Page<DoctorAvailabilityResponseDTO> getAllDoctorAvailabilities(Pageable pageable);
    DoctorAvailabilityResponseDTO updateDoctorAvailability(Long id, DoctorAvailabilityRequestDTO requestDTO);
    void deleteDoctorAvailability(Long id);
}
