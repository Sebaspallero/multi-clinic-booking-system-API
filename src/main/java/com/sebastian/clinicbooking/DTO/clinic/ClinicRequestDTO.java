package com.sebastian.clinicbooking.DTO.clinic;


import com.sebastian.clinicbooking.DTO.addres.AddressRequestDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClinicRequestDTO {

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]+$", message = "Phone number must contain only numbers")
    private String phoneNumber;

    @NotNull(message = "Address is required")
    @Valid
    private AddressRequestDTO address;

    @NotBlank(message = "Contact email is required")
    private String contactEmail;

    private String description;
    
    private String profilePictureUrl;
}
