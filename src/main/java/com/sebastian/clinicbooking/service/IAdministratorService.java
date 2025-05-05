package com.sebastian.clinicbooking.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sebastian.clinicbooking.DTO.administrator.AdministratorRequestDTO;
import com.sebastian.clinicbooking.DTO.administrator.AdministratorResponseDTO;

public interface IAdministratorService {

    AdministratorResponseDTO createAdministrator(AdministratorRequestDTO administratorRequestDTO);
    AdministratorResponseDTO getAdministratorById(Long id);
    List<AdministratorResponseDTO> getAllAdministrators();
    Page<AdministratorResponseDTO> getAllAdministrators(Pageable pageable);
    AdministratorResponseDTO updateAdministrator(Long id, AdministratorRequestDTO AdministratorRequestDTO);
    void deleteAdministrator(Long id);
}
