package com.sebastian.clinicbooking.DTO.clinic;

import java.util.List;

import com.sebastian.clinicbooking.DTO.doctor.DoctorSummaryDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClinicWithDoctorsDTO {

    private Long id;
    private String name;
    private String contactEmail;
    private List<DoctorSummaryDTO> doctors;
    private boolean active;
}
