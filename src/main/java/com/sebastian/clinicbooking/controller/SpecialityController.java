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

import com.sebastian.clinicbooking.DTO.speciality.SpecialityRequestDTO;
import com.sebastian.clinicbooking.DTO.speciality.SpecialityResponseDTO;
import com.sebastian.clinicbooking.service.ISpecialityService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/specialities")
public class SpecialityController {


    private final ISpecialityService specialityService;

    @Autowired
    public SpecialityController(ISpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @PostMapping
    public ResponseEntity<SpecialityResponseDTO> createSpeciality(@RequestBody @Valid SpecialityRequestDTO specialityRequestDTO) {
        SpecialityResponseDTO specialityResponseDTO = specialityService.createSpeciality(specialityRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(specialityResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialityResponseDTO> getSpecialityById(@PathVariable Long id) {
        SpecialityResponseDTO specialityResponseDTO = specialityService.getSpecialityById(id);
        return ResponseEntity.status(HttpStatus.OK).body(specialityResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<SpecialityResponseDTO>> getAllSpecialities() {
        List<SpecialityResponseDTO> specialities = specialityService.getAllSpecialities();
        return ResponseEntity.status(HttpStatus.OK).body(specialities);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<SpecialityResponseDTO>> getAllSpecialitiesPageable(Pageable pageable) {
        Page<SpecialityResponseDTO> specialities = specialityService.getAllSpecialities(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(specialities);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialityResponseDTO> updateSpeciality(@PathVariable Long id, @RequestBody @Valid SpecialityRequestDTO specialityRequestDTO) {
        SpecialityResponseDTO specialityResponseDTO = specialityService.updateSpeciality(id, specialityRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(specialityResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpeciality(@PathVariable Long id) {
        specialityService.deleteSpeciality(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
}
