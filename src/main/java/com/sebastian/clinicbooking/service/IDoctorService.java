package com.sebastian.clinicbooking.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sebastian.clinicbooking.DTO.doctor.DoctorRequestDTO;
import com.sebastian.clinicbooking.DTO.doctor.DoctorResponseDTO;
import com.sebastian.clinicbooking.model.Doctor;

public interface IDoctorService{

    DoctorResponseDTO createDoctor(DoctorRequestDTO doctorRequestDTO);
    DoctorResponseDTO getDoctorById(Long id);
    Doctor getDoctorEntityById(Long id);
    List<DoctorResponseDTO> getAllDoctors();
    Page<DoctorResponseDTO> getAllDoctors(Pageable pageable);
    DoctorResponseDTO updateDoctor(Long id, DoctorRequestDTO doctorRequestDTO);
    void deleteDoctor(Long id);
   
}
