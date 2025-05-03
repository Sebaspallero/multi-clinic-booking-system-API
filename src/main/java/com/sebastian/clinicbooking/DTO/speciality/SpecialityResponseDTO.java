package com.sebastian.clinicbooking.DTO.speciality;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpecialityResponseDTO {
    
    private Long id;
    private String name;
    private boolean active;
}
