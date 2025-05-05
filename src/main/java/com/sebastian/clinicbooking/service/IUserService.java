package com.sebastian.clinicbooking.service;

import java.util.List;

import com.sebastian.clinicbooking.DTO.user.UserResponseDTO;

public interface IUserService {
    
    UserResponseDTO getUserById(Long id);
    List<UserResponseDTO> getAllUsers();
    
}
