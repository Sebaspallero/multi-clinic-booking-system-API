package com.sebastian.clinicbooking.DTO.doctorAvailability;

import java.time.DayOfWeek;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctorAvailabilityRequestDTO {
    
    @NotNull(message = "Doctor ID cannot be null")
    private Long doctorId;

    @NotNull(message = "Day of the week cannot be null")
    private DayOfWeek dayOfWeek;

    @NotNull(message = "Start time cannot be null")
    private LocalTime startTime;

    @NotNull(message = "End time cannot be null")
    private LocalTime endTime;

    @NotNull(message = "Duration in minutes cannot be null")
    private Integer durationInMinutes;
}
