package com.sebastian.clinicbooking.DTO.doctor;

import java.util.List;

import com.sebastian.clinicbooking.DTO.appointment.AppointmentResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctorWithAppointment {
    
    private Long id;
    private String name;
    private String lastName;
    private String licenseNumber;
    private String profilePictureUrl;
    private List <AppointmentResponseDTO> appointments;
}
