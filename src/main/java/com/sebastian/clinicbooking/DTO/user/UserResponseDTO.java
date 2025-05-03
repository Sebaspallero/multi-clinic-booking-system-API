package com.sebastian.clinicbooking.DTO.user;

import java.time.LocalDateTime;

import com.sebastian.clinicbooking.enums.Roles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponseDTO {
    
    private Long id;
    private String email;
    private String phoneNumber;
    private Roles role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean active;
}
