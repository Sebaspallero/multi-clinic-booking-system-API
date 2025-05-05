package com.sebastian.clinicbooking.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sebastian.clinicbooking.DTO.clinic.ClinicRequestDTO;
import com.sebastian.clinicbooking.DTO.clinic.ClinicResponseDTO;
import com.sebastian.clinicbooking.model.Clinic;

public interface IClinicService {

    ClinicResponseDTO createClinic(ClinicRequestDTO clinicRequestDTO);
    ClinicResponseDTO getClinicById(Long id);
    Clinic getClinicEntityById(Long id);
    List<ClinicResponseDTO> getAllClinics();
    Page<ClinicResponseDTO> getAllClinics(Pageable pageable);
    ClinicResponseDTO updateClinic(Long id, ClinicRequestDTO clinicRequestDTO);
    void deleteClinic(Long id);
}
