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

import com.sebastian.clinicbooking.DTO.doctor.DoctorRequestDTO;
import com.sebastian.clinicbooking.DTO.doctor.DoctorResponseDTO;
import com.sebastian.clinicbooking.service.IDoctorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    private final IDoctorService doctorService;

    @Autowired
    public DoctorController(IDoctorService doctorService) {
        this.doctorService = doctorService;
    }

       @PostMapping
    public ResponseEntity<DoctorResponseDTO> createDoctor(@RequestBody @Valid DoctorRequestDTO doctorRequestDTO) {
        DoctorResponseDTO doctorResponseDTO = doctorService.createDoctor(doctorRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> getDoctorById(@PathVariable Long id) {
        DoctorResponseDTO doctorResponseDTO = doctorService.getDoctorById(id);
        return ResponseEntity.status(HttpStatus.OK).body(doctorResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponseDTO>> getAllDoctors() {
        List<DoctorResponseDTO> doctors = doctorService.getAllDoctors();
        return ResponseEntity.status(HttpStatus.OK).body(doctors);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<DoctorResponseDTO>> getAllDoctorsPageable(Pageable pageable) {
        Page<DoctorResponseDTO> doctors = doctorService.getAllDoctors(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(doctors);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> updateDoctor(@PathVariable Long id, @RequestBody @Valid DoctorRequestDTO DoctorRequestDTO) {
        DoctorResponseDTO doctorResponseDTO = doctorService.updateDoctor(id, DoctorRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(doctorResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
}
