package com.sebastian.clinicbooking.DTO.clinic;

import com.sebastian.clinicbooking.DTO.addres.AddressResponseDTO;
import com.sebastian.clinicbooking.enums.Roles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClinicResponseDTO {

    private Long id;
    private String name;
    private String contactEmail;
    private String email;
    private String phoneNumber;
    private AddressResponseDTO address;
    private String description;
    private String profilePictureUrl;
    private Roles role;
    private boolean active;
}
