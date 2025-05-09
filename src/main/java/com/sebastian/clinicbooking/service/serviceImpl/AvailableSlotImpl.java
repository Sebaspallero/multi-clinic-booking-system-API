package com.sebastian.clinicbooking.service.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebastian.clinicbooking.DTO.availableSlot.AvailableSlotResponseDTO;
import com.sebastian.clinicbooking.exception.ResourceNotFoundException;
import com.sebastian.clinicbooking.mapper.AvailableSlotMapper;
import com.sebastian.clinicbooking.model.AvailableSlot;
import com.sebastian.clinicbooking.model.Clinic;
import com.sebastian.clinicbooking.model.Doctor;
import com.sebastian.clinicbooking.model.DoctorAvailability;
import com.sebastian.clinicbooking.repository.AvailableSlotRepository;
import com.sebastian.clinicbooking.service.IAvailableSlotService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AvailableSlotImpl implements IAvailableSlotService {

    private final AvailableSlotRepository availableSlotRepository;
    private final AvailableSlotMapper availableSlotMapper;

    @Autowired
    public AvailableSlotImpl(AvailableSlotRepository availableSlotRepository, AvailableSlotMapper availableSlotMapper) {
        this.availableSlotMapper = availableSlotMapper;
        this.availableSlotRepository = availableSlotRepository;
    }

    @Override
    public List<AvailableSlotResponseDTO> getAvailableSlotsForDoctor(Long doctorId) {
        List<AvailableSlot> slots = availableSlotRepository.findByDoctorIdAndAvailableTrue(doctorId);
        return availableSlotMapper.toDtoList(slots);
    }

    @Override
    public AvailableSlotResponseDTO getAvailableSlotById(Long id) {
        AvailableSlot slot = availableSlotRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Available Slot not found with id: " + id));
        return availableSlotMapper.toDto(slot);
    }


    @Override
    public void generateSlotsFromAvailability(DoctorAvailability availability, LocalDate from, LocalDate to) {

        Doctor doctor = availability.getDoctor();
        Clinic clinic = doctor.getClinic();

        List<AvailableSlot> slots = new ArrayList<>();

        for (LocalDate date = from; !date.isAfter(to); date = date.plusDays(1)) {
            if (date.getDayOfWeek() == availability.getDayOfWeek()) {
                LocalTime time = availability.getStartTime();
                while (time.plusMinutes(availability.getDurationInMinutes()).isBefore(availability.getEndTime().plusSeconds(1))) {
                    LocalDateTime slotDateTime = LocalDateTime.of(date, time);
                    if (!availableSlotRepository.existsByDoctorAvailabilityAndSlotDateTime(availability, slotDateTime)) {
                        slots.add(new AvailableSlot(null, slotDateTime, true, doctor, clinic, null, availability, null, null));
                        log.info("Generated slot: {} for doctor: {} on date: {}", slotDateTime, doctor.getId(), date);
                    }
                    time = time.plusMinutes(availability.getDurationInMinutes());
                }
            }
        }
        availableSlotRepository.saveAll(slots);
    }

    @Override
    public AvailableSlot findAvailableSlotEntityById(Long id) {
        AvailableSlot slot = availableSlotRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Available Slot not found with id: " + id));
        return slot;
    }

    @Override
    public void markSlotAsBooked(AvailableSlot availableSlot) {
        availableSlot.setAvailable(false);
        availableSlotRepository.save(availableSlot);
    }

    @Override
    public LocalDateTime findMaxAvailableSlotDateByAvailability(DoctorAvailability availability) {
        return availableSlotRepository.findMaxSlotDateTimeByAvailability(availability);
    }

    
}
