package com.sebastian.clinicbooking.DTO.doctorAvailability;

import java.time.DayOfWeek;
import java.time.LocalTime;

import com.sebastian.clinicbooking.DTO.doctor.DoctorSummaryDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctorAvailabilityResponseDTO {
    
    private Long id;
    private DoctorSummaryDTO doctor;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer durationInMinutes;
    private boolean active;
}
