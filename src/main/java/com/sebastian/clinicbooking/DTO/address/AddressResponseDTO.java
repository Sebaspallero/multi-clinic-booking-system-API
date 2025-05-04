package com.sebastian.clinicbooking.DTO.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressResponseDTO {
    
    private Long id;
    private String street;
    private String number;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
