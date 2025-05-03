package com.sebastian.clinicbooking.DTO.speciality;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpecialityRequestDTO {
    
    @NotBlank(message = "Name is required")
    private String name;
   
}
