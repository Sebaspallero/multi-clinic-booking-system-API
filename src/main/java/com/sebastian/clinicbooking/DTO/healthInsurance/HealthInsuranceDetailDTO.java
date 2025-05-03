package com.sebastian.clinicbooking.DTO.healthInsurance;

import java.util.List;

import com.sebastian.clinicbooking.DTO.doctor.DoctorSummaryDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HealthInsuranceDetailDTO {
    
    private Long id;
    private String name;
    private boolean active;
    private List<DoctorSummaryDTO> doctors;
}
