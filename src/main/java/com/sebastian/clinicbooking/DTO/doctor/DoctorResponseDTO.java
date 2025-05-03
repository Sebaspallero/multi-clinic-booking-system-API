package com.sebastian.clinicbooking.DTO.doctor;


import java.util.List;

import com.sebastian.clinicbooking.DTO.healthInsurance.HealthInsuranceResponseDTO;
import com.sebastian.clinicbooking.DTO.speciality.SpecialityResponseDTO;
import com.sebastian.clinicbooking.enums.Roles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctorResponseDTO {
    
    private Long id;
    private String email;
    private String name;
    private String lastName;
    private String licenseNumber;
    private String identificationNumber;
    private String phoneNumber;
    private String biography;
    private List<SpecialityResponseDTO> specialities;
    private List<HealthInsuranceResponseDTO> healthInsurances;
    private String profilePictureUrl;
    private Roles role;
    private boolean active;
}
