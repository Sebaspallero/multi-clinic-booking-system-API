package com.sebastian.clinicbooking.DTO.patient;

import java.time.LocalDate;

import com.sebastian.clinicbooking.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientSummaryDTO {
    
    private Long id;
    private String name;
    private String lastName;
    private String identificationNumber;
    private LocalDate birthDate;
    private Gender gender;
}
