package com.sebastian.clinicbooking.DTO.healthInsurance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HealthInsuranceResponseDTO {
    
    private Long id;
    private String name;
    private boolean active;
}
