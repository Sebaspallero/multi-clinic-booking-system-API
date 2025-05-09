package com.sebastian.clinicbooking.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.sebastian.clinicbooking.DTO.availableSlot.AvailableSlotResponseDTO;
import com.sebastian.clinicbooking.model.AvailableSlot;
import com.sebastian.clinicbooking.model.DoctorAvailability;

public interface IAvailableSlotService {

    AvailableSlot findAvailableSlotEntityById(Long id);
    List<AvailableSlotResponseDTO> getAvailableSlotsForDoctor(Long doctorId);
    AvailableSlotResponseDTO getAvailableSlotById(Long id);
    void generateSlotsFromAvailability(DoctorAvailability availability, LocalDate from, LocalDate to);
    void markSlotAsBooked(AvailableSlot availableSlot);
    LocalDateTime findMaxAvailableSlotDateByAvailability(DoctorAvailability availability);
}

