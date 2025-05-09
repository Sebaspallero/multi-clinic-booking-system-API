package com.sebastian.clinicbooking.service.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sebastian.clinicbooking.model.DoctorAvailability;
import com.sebastian.clinicbooking.repository.DoctorAvailabilityRepository;
import com.sebastian.clinicbooking.service.IAvailableSlotService;
import com.sebastian.clinicbooking.service.ISlotGeneratorScheduler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SlotGeneratorSchedulerService implements ISlotGeneratorScheduler {

    private final IAvailableSlotService availableSlotService;
    private final DoctorAvailabilityRepository doctorAvailabilityRepository;

    @Autowired
    public SlotGeneratorSchedulerService(IAvailableSlotService availableSlotService,
            DoctorAvailabilityRepository doctorAvailabilityRepository) {
        this.availableSlotService = availableSlotService;
        this.doctorAvailabilityRepository = doctorAvailabilityRepository;
    }

    @Override
    @Scheduled(cron = "0 0 0 * * ?")
    public void generateUpcomingSlots() {

        List<DoctorAvailability> doctorAvailabilities = doctorAvailabilityRepository.findAll();

        for (DoctorAvailability doctorAvailability : doctorAvailabilities) {
            generateSlotsAsync(doctorAvailability);
        }
    }

    @Async
    private void generateSlotsAsync(DoctorAvailability doctorAvailability) {

        LocalDateTime maxSlotDate = availableSlotService.findMaxAvailableSlotDateByAvailability(doctorAvailability);
        LocalDate from = LocalDate.now().plusDays(1);
        LocalDate to = LocalDate.now().plusDays(30);

        if (maxSlotDate == null || maxSlotDate.isBefore(LocalDate.now().plusDays(2).atStartOfDay())) {
            availableSlotService.generateSlotsFromAvailability(doctorAvailability, from, to);
            log.info("Generated slots for doctor availability ID: {}", doctorAvailability.getId());
        }
    }
}
