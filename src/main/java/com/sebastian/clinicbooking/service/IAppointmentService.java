package com.sebastian.clinicbooking.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sebastian.clinicbooking.DTO.appointment.AppointmentRequestDTO;
import com.sebastian.clinicbooking.DTO.appointment.AppointmentResponseDTO;

public interface IAppointmentService {
    
    AppointmentResponseDTO createAppointment(AppointmentRequestDTO appointmentRequestDTO);
    AppointmentResponseDTO getAppointmentById(Long id);
    List<AppointmentResponseDTO> getAllAppointments();
    Page<AppointmentResponseDTO> getAllAppointments(Pageable pageable);
    AppointmentResponseDTO updateAppointment(Long id, AppointmentRequestDTO appointmentRequestDTO);
    void deleteAppointment(Long id);
}
