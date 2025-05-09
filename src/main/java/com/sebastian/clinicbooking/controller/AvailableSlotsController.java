package com.sebastian.clinicbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sebastian.clinicbooking.DTO.availableSlot.AvailableSlotResponseDTO;
import com.sebastian.clinicbooking.service.IAvailableSlotService;

@RestController
@RequestMapping("/api/v1/available-slots")
public class AvailableSlotsController {

    private final IAvailableSlotService availableSlotService;

    @Autowired
    public AvailableSlotsController(IAvailableSlotService availableSlotService) {
        this.availableSlotService = availableSlotService;
    }

    @GetMapping
    public ResponseEntity<List<AvailableSlotResponseDTO>> getAvailableSlots(Long doctorId) {
        List<AvailableSlotResponseDTO> availableSlots = availableSlotService.getAvailableSlotsForDoctor(doctorId);
        return ResponseEntity.status(HttpStatus.OK).body(availableSlots);
    }

    @GetMapping("{id}")
    public ResponseEntity<AvailableSlotResponseDTO> getAvailableSlotById(Long id) {
        AvailableSlotResponseDTO availableSlot = availableSlotService.getAvailableSlotById(id);
        return ResponseEntity.status(HttpStatus.OK).body(availableSlot);
    }
}
