package com.sebastian.clinicbooking.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sebastian.clinicbooking.DTO.patient.PatientRequestDTO;
import com.sebastian.clinicbooking.DTO.patient.PatientResponseDTO;
import com.sebastian.clinicbooking.model.Patient;

public interface IPatientService {

    PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO);
    PatientResponseDTO getPatientById(Long id);
    Patient getPatientEntityById(Long id); 
    List<PatientResponseDTO> getAllPatients();
    Page<PatientResponseDTO> getAllPatients(Pageable pageable);
    PatientResponseDTO updatePatient(Long id, PatientRequestDTO patientRequestDTO);
    void deletePatient(Long id);
    
}
