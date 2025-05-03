package com.sebastian.clinicbooking.DTO.speciality;

import java.util.List;

import com.sebastian.clinicbooking.DTO.doctor.DoctorSummaryDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpecialityDetailDTO {
    
    private Long id;
    private String name;
    private boolean active;
    private List<DoctorSummaryDTO> doctors;
}
