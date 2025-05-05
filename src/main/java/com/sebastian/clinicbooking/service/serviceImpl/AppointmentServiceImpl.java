package com.sebastian.clinicbooking.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sebastian.clinicbooking.DTO.appointment.AppointmentRequestDTO;
import com.sebastian.clinicbooking.DTO.appointment.AppointmentResponseDTO;
import com.sebastian.clinicbooking.enums.AppointmentStatus;
import com.sebastian.clinicbooking.exception.ResourceNotFoundException;
import com.sebastian.clinicbooking.mapper.AppointmentMapper;
import com.sebastian.clinicbooking.model.Appointment;
import com.sebastian.clinicbooking.model.Clinic;
import com.sebastian.clinicbooking.model.Doctor;
import com.sebastian.clinicbooking.model.Patient;
import com.sebastian.clinicbooking.repository.AppointmentRepository;
import com.sebastian.clinicbooking.repository.ClinicRepository;
import com.sebastian.clinicbooking.repository.DoctorRepository;
import com.sebastian.clinicbooking.repository.PatientRepository;
import com.sebastian.clinicbooking.service.IAppointmentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AppointmentServiceImpl implements IAppointmentService{

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final ClinicRepository clinicRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper, 
                                  ClinicRepository clinicRepository, DoctorRepository doctorRepository, 
                                  PatientRepository patientRepository) {
        this.clinicRepository = clinicRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }

    @Override
    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO appointmentRequestDTO) {
        
        Clinic clinic = getClinicById(appointmentRequestDTO.getClinicId());
        Doctor doctor = getDoctorById(appointmentRequestDTO.getDoctorId());
        Patient patient = getPatientById(appointmentRequestDTO.getPatientId());
        
        Appointment appointment = appointmentMapper.toEntity(appointmentRequestDTO);

        appointment.setStatus(AppointmentStatus.PENDING);
        appointment.setClinic(clinic);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        appointmentRepository.save(appointment);

        log.info("Appointment created with ID: {}, Doctor: {}, Patient: {}", appointment.getId(), doctor.getId(), patient.getId());
        return appointmentMapper.toDto(appointment);
    }

    @Override
    public AppointmentResponseDTO getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
                
        log.info("Appointment found: {}", appointment);
        return appointmentMapper.toDto(appointment);
    }

    @Override
    public List<AppointmentResponseDTO> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        log.info("Found {} appointments", appointments.size());
        return appointmentMapper.toDtoList(appointments);
    }

    @Override
    public Page<AppointmentResponseDTO> getAllAppointments(Pageable pageable) {
        Page<Appointment> appointments = appointmentRepository.findAll(pageable);
        log.info("Found {} appointments", appointments.getTotalElements());
        return appointmentMapper.toDtoPage(appointments);
    }

    @Override
    public AppointmentResponseDTO updateAppointment(Long id, AppointmentRequestDTO appointmentRequestDTO) {
        Appointment appointmentToUpdate = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
        
        Clinic clinic = getClinicById(appointmentRequestDTO.getClinicId());
        Doctor doctor = getDoctorById(appointmentRequestDTO.getDoctorId());
        Patient patient = getPatientById(appointmentRequestDTO.getPatientId());
        
        appointmentMapper.updateEntityFromDto(appointmentRequestDTO, appointmentToUpdate);

        appointmentToUpdate.setStatus(AppointmentStatus.PENDING);
        appointmentToUpdate.setClinic(clinic);
        appointmentToUpdate.setDoctor(doctor);
        appointmentToUpdate.setPatient(patient);

        appointmentRepository.save(appointmentToUpdate);
        log.info("Appointment updated with ID: {}, Doctor: {}, Patient: {}", appointmentToUpdate.getId(), doctor.getId(), patient.getId());
        return appointmentMapper.toDto(appointmentToUpdate);
    }

    @Override
    public void deleteAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
        
        appointmentRepository.delete(appointment);
        log.info("Appointment deleted: {}", appointment);
    }

    private Clinic getClinicById(Long clinicId) {
        return clinicRepository.findById(clinicId)
                .orElseThrow(() -> new ResourceNotFoundException("Clinic not found with id: " + clinicId));
    }

    private Doctor getDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + doctorId)); 
    }

    private Patient getPatientById(Long patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + patientId));
    }
    
}
