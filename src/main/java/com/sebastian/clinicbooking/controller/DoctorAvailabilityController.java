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

import com.sebastian.clinicbooking.DTO.doctorAvailability.DoctorAvailabilityRequestDTO;
import com.sebastian.clinicbooking.DTO.doctorAvailability.DoctorAvailabilityResponseDTO;
import com.sebastian.clinicbooking.service.IDoctorAvailabilityService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/doctor-availability")
public class DoctorAvailabilityController {
    
    private final IDoctorAvailabilityService doctorAvailabilityService;

    @Autowired
    public DoctorAvailabilityController(IDoctorAvailabilityService doctorAvailabilityService) {
        this.doctorAvailabilityService = doctorAvailabilityService;
    }
   
    @PostMapping
    public ResponseEntity<DoctorAvailabilityResponseDTO> createDoctorAvailability(@RequestBody @Valid DoctorAvailabilityRequestDTO doctorAvailabilityRequestDTO) {
        DoctorAvailabilityResponseDTO doctorAvailabilityResponseDTO = doctorAvailabilityService.createDoctorAvailability(doctorAvailabilityRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorAvailabilityResponseDTO);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<DoctorAvailabilityResponseDTO> getDoctorAvailabilityById(@PathVariable Long id) {
        DoctorAvailabilityResponseDTO doctorAvailabilityResponseDTO = doctorAvailabilityService.getDoctorAvailabilityById(id);
        return ResponseEntity.status(HttpStatus.OK).body(doctorAvailabilityResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<DoctorAvailabilityResponseDTO>> getAllDoctorAvailability() {
        List<DoctorAvailabilityResponseDTO> doctorAvailability = doctorAvailabilityService.getAllDoctorAvailabilities();
        return ResponseEntity.status(HttpStatus.OK).body(doctorAvailability);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<DoctorAvailabilityResponseDTO>> getAllDoctorAvailabilityPageable(Pageable pageable) {
        Page<DoctorAvailabilityResponseDTO> doctorAvailability = doctorAvailabilityService.getAllDoctorAvailabilities(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(doctorAvailability);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorAvailabilityResponseDTO> updateDoctorAvailability(@PathVariable Long id, @RequestBody @Valid DoctorAvailabilityRequestDTO doctorAvailabilityRequestDTO) {
        DoctorAvailabilityResponseDTO doctorAvailabilityResponseDTO = doctorAvailabilityService.updateDoctorAvailability(id, doctorAvailabilityRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(doctorAvailabilityResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctorAvailability(@PathVariable Long id) {
        doctorAvailabilityService.deleteDoctorAvailability(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
