package com.sebastian.clinicbooking.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sebastian.clinicbooking.DTO.doctor.DoctorRequestDTO;
import com.sebastian.clinicbooking.DTO.doctor.DoctorResponseDTO;
import com.sebastian.clinicbooking.exception.ResourceNotFoundException;
import com.sebastian.clinicbooking.mapper.DoctorMapper;
import com.sebastian.clinicbooking.model.Clinic;
import com.sebastian.clinicbooking.model.Doctor;
import com.sebastian.clinicbooking.model.HealthInsurance;
import com.sebastian.clinicbooking.model.Speciality;
import com.sebastian.clinicbooking.repository.DoctorRepository;
import com.sebastian.clinicbooking.service.IClinicService;
import com.sebastian.clinicbooking.service.IDoctorService;
import com.sebastian.clinicbooking.service.IHealthInsuranceService;
import com.sebastian.clinicbooking.service.ISpecialityService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DoctorServiceImpl implements IDoctorService{

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final IHealthInsuranceService healthInsuranceService;
    private final ISpecialityService specialityService;
    private final IClinicService clinicService;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, DoctorMapper doctorMapper, IHealthInsuranceService healthInsuranceService, ISpecialityService specialityService, IClinicService clinicService) {
        this.clinicService = clinicService;
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
        this.healthInsuranceService = healthInsuranceService;
        this.specialityService = specialityService;
    }

    @Override
    public DoctorResponseDTO createDoctor(DoctorRequestDTO doctorRequestDTO) {
        Clinic clinic = clinicService.getClinicEntityById(doctorRequestDTO.getClinicId());

        List<Speciality> specialities = specialityService.getSpecialityEntitiesByIds(doctorRequestDTO.getSpecialityIds());
        if (specialities.isEmpty()) {
            throw new IllegalArgumentException("Specialities cannot be empty");
        }

        List<HealthInsurance> healthInsurances = healthInsuranceService.getHealthInsuranceEntitiesByIds(doctorRequestDTO.getInsuranceIds());
        if (healthInsurances.isEmpty()) {
            throw new IllegalArgumentException("Health insurances cannot be empty");
        }

        Doctor doctor = doctorMapper.toEntity(doctorRequestDTO);
        doctor.setClinic(clinic);
        doctor.setSpecialities(specialities);
        doctor.setHealthInsurances(healthInsurances);
        doctorRepository.save(doctor);
        log.info("Doctor created with ID: {}", doctor.getId());
        return doctorMapper.toDto(doctor);
        
    }

    @Override
    public DoctorResponseDTO getDoctorById(Long id) {
        Doctor doctor = findDoctorById(id);
                
        log.info("Doctor found with ID: {}", doctor.getId());
        return doctorMapper.toDto(doctor);
    }

    @Override
    public List<DoctorResponseDTO> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        log.info("Found {} doctors", doctors.size());
        return doctorMapper.toDtoList(doctors);
    }

    @Override
    public Page<DoctorResponseDTO> getAllDoctors(Pageable pageable) {
        Page<Doctor> doctors = doctorRepository.findAll(pageable);
        log.info("Found {} doctors", doctors.getTotalElements());
        return doctorMapper.toDtoPage(doctors);
    }

    @Override
    public DoctorResponseDTO updateDoctor(Long id, DoctorRequestDTO doctorRequestDTO) {

        Doctor doctorToUpdate = findDoctorById(id);
        
        Clinic clinic = clinicService.getClinicEntityById(doctorRequestDTO.getClinicId());

        List<Speciality> specialities = specialityService.getSpecialityEntitiesByIds(doctorRequestDTO.getSpecialityIds());
        if (specialities.isEmpty()) {
            throw new IllegalArgumentException("Specialities cannot be empty");
        }

        List<HealthInsurance> healthInsurances = healthInsuranceService.getHealthInsuranceEntitiesByIds(doctorRequestDTO.getInsuranceIds());
        if (healthInsurances.isEmpty()) {
            throw new IllegalArgumentException("Health insurances cannot be empty");
        }

        doctorMapper.updateEntityFromDto(doctorRequestDTO, doctorToUpdate);
        doctorToUpdate.setClinic(clinic);
        doctorToUpdate.setSpecialities(specialities);
        doctorToUpdate.setHealthInsurances(healthInsurances);
        
        doctorRepository.save(doctorToUpdate);
        
        log.info("Doctor updated with ID: {}", doctorToUpdate.getId());
        return doctorMapper.toDto(doctorToUpdate);
    }

    @Override
    public void deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        
        doctorRepository.delete(doctor);
        log.info("Doctor deleted with ID: {}", doctor.getId());
    }

    private Doctor findDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + doctorId)); 
    }
    
}
