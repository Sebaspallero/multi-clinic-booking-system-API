package com.sebastian.clinicbooking.DTO.doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctorSummaryDTO {
    
    private Long id;
    private String email;
    private String name;
    private String lastName;
    private String licenseNumber;
    private String profilePictureUrl;
}
