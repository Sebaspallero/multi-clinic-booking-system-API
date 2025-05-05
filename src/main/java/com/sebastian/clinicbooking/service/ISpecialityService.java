package com.sebastian.clinicbooking.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sebastian.clinicbooking.DTO.speciality.SpecialityRequestDTO;
import com.sebastian.clinicbooking.DTO.speciality.SpecialityResponseDTO;
import com.sebastian.clinicbooking.model.Speciality;

public interface ISpecialityService {

    SpecialityResponseDTO createSpeciality(SpecialityRequestDTO specialityRequestDTO);
    SpecialityResponseDTO getSpecialityById(Long id);
    List<SpecialityResponseDTO> getAllSpecialities();
    Page<SpecialityResponseDTO> getAllSpecialities(Pageable pageable);
    List<Speciality> getSpecialityEntitiesByIds(List<Long> ids);
    SpecialityResponseDTO updateSpeciality(Long id, SpecialityRequestDTO specialityRequestDTO);
    void deleteSpeciality(Long id);
}
