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

import com.sebastian.clinicbooking.DTO.clinic.ClinicRequestDTO;
import com.sebastian.clinicbooking.DTO.clinic.ClinicResponseDTO;
import com.sebastian.clinicbooking.service.IClinicService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/clinics")
public class ClinicController {
    
    private final IClinicService clinicService;

    @Autowired
    public ClinicController(IClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @PostMapping
    public ResponseEntity<ClinicResponseDTO> createClinic(@RequestBody @Valid ClinicRequestDTO appointmentRequestDTO) {
        ClinicResponseDTO clinicResponseDTO = clinicService.createClinic(appointmentRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clinicResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicResponseDTO> getClinicById(@PathVariable Long id) {
        ClinicResponseDTO clinicResponseDTO = clinicService.getClinicById(id);
        return ResponseEntity.status(HttpStatus.OK).body(clinicResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ClinicResponseDTO>> getAllClinics() {
        List<ClinicResponseDTO> clinics = clinicService.getAllClinics();
        return ResponseEntity.status(HttpStatus.OK).body(clinics);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<ClinicResponseDTO>> getAllClinicsPageable(Pageable pageable) {
        Page<ClinicResponseDTO> clinics = clinicService.getAllClinics(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(clinics);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicResponseDTO> updateClinic(@PathVariable Long id, @RequestBody @Valid ClinicRequestDTO clinicRequestDTO) {
        ClinicResponseDTO ClinicResponseDTO = clinicService.updateClinic(id, clinicRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(ClinicResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClinic(@PathVariable Long id) {
        clinicService.deleteClinic(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
