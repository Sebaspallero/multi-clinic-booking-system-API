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

import com.sebastian.clinicbooking.DTO.administrator.AdministratorRequestDTO;
import com.sebastian.clinicbooking.DTO.administrator.AdministratorResponseDTO;
import com.sebastian.clinicbooking.service.IAdministratorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/administrators")
public class AdministratorController {

    private IAdministratorService addressService;

    @Autowired
    public AdministratorController(IAdministratorService addressService) {
        this.addressService = addressService;
    }
    
    @PostMapping
    public ResponseEntity<AdministratorResponseDTO> createAdministrator(@RequestBody @Valid AdministratorRequestDTO administratorRequestDTO) {
        AdministratorResponseDTO administratorResponseDTO = addressService.createAdministrator(administratorRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(administratorResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministratorResponseDTO> getAdministratorById(@PathVariable Long id) {
        AdministratorResponseDTO administratorResponseDTO = addressService.getAdministratorById(id);
        return ResponseEntity.status(HttpStatus.OK).body(administratorResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<AdministratorResponseDTO>> getAllAdministrators() {
        List<AdministratorResponseDTO> administrators = addressService.getAllAdministrators();
        return ResponseEntity.ok(administrators);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<AdministratorResponseDTO>> getAllAdministratorsPageable(Pageable pageable) {
        Page<AdministratorResponseDTO> administrators = addressService.getAllAdministrators(pageable);
        return ResponseEntity.ok(administrators);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdministratorResponseDTO> updateAdministrator(@PathVariable Long id, @RequestBody @Valid AdministratorRequestDTO administratorRequestDTO) {
        AdministratorResponseDTO administratorResponseDTO = addressService.updateAdministrator(id, administratorRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(administratorResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrator(@PathVariable Long id) {
        addressService.deleteAdministrator(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
}
