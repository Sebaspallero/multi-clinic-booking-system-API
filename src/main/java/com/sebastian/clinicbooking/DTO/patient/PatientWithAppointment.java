package com.sebastian.clinicbooking.DTO.patient;

import java.util.List;

import com.sebastian.clinicbooking.DTO.appointment.AppointmentResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientWithAppointment {

    private Long id;
    private String name;
    private String lastName;
    private String identificationNumber;
    private List <AppointmentResponseDTO> appointments;
}
