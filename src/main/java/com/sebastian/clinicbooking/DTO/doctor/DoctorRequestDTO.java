package com.sebastian.clinicbooking.DTO.doctor;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctorRequestDTO {
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters long")
    private String name;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters long")
    private String lastName;

    @NotBlank(message = "License Number is required")
    @Size(min = 10, max = 15, message = "License Number must be between 10 and 15 characters long")
    private String licenseNumber;

    @NotBlank(message = "Identification Number is required")
    @Size(min = 8, max = 15, message = "Identification Number must be between 8 and 15 characters long")
    private String identificationNumber;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]+$", message = "Phone number must contain only numbers")
    private String phoneNumber;

    @NotNull(message = "Clinic ID is required")
    private Long ClinicId;

    @NotEmpty(message = "At least one Speciality must be selected")
    private List<Long> specialityIds;

    @NotEmpty(message = "At least one Health Insurance must be selected")
    private List<Long> insuranceIds;

    private String biography;

    private String profilePictureUrl;

}
