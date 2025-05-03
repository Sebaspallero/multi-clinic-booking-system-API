package com.sebastian.clinicbooking.DTO.administrator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdministratorResponseDTO {
    
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String role;
    private boolean active;
}
