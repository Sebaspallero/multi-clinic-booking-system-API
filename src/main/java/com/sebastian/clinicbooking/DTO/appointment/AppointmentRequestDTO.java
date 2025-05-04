package com.sebastian.clinicbooking.DTO.appointment;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentRequestDTO {

    @NotNull(message = "Appointment date and time cannot be null")
    @Future(message = "Appointment date and time must be in the future")
    private LocalDateTime appointmentDateTime;

    @NotBlank(message = "Reason for visit cannot be blank")
    @Size(min = 2, max = 255, message = "Reason for visit must be between 2 and 255 characters")
    private String reasonForVisit;

    private String notes;

    @NotNull(message = "Doctor ID cannot be null")
    private Long doctorId;

    @NotNull(message = "Patient ID cannot be null")
    private Long patientId;

    @NotNull(message = "Clinic ID cannot be null")
    private Long clinicId;
    
}
