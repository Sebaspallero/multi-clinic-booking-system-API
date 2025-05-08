package com.sebastian.clinicbooking.DTO.appointment;

import java.time.LocalDateTime;

import com.sebastian.clinicbooking.DTO.clinic.ClinicSummaryDTO;
import com.sebastian.clinicbooking.DTO.doctor.DoctorSummaryDTO;
import com.sebastian.clinicbooking.DTO.patient.PatientSummaryDTO;
import com.sebastian.clinicbooking.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentResponseDTO {
    
    private Long id;
    private AppointmentStatus status;
    private LocalDateTime appointmentDateTime;
    private String reasonForVisit;
    private String notes;
    private boolean confirmed;
    private DoctorSummaryDTO doctor;
    private PatientSummaryDTO patient;
    private ClinicSummaryDTO clinic;
    private LocalDateTime createdAt;
    private CancellationInfoDTO cancellationInfo;
}
