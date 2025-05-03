package com.sebastian.clinicbooking.DTO.patient;

import java.time.LocalDate;
import java.util.List;

import com.sebastian.clinicbooking.DTO.healthInsurance.HealthInsuranceResponseDTO;
import com.sebastian.clinicbooking.enums.Gender;
import com.sebastian.clinicbooking.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientResponseDTO {
    
    private Long id;
    private String name;
    private String lastName;
    private String identificationNumber;
    private LocalDate birthDate;
    private Gender gender;
    private List<HealthInsuranceResponseDTO> insurances;
    private String email;
    private String phoneNumber;
    private String profilePictureUrl;
    private Roles role;
    private boolean active;
}
