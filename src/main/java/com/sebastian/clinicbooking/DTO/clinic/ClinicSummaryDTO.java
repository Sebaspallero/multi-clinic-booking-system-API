package com.sebastian.clinicbooking.DTO.clinic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClinicSummaryDTO {

    private Long id;
    private String name;
    private String contactEmail;
    private String phoneNumber;
    private boolean active;
}
