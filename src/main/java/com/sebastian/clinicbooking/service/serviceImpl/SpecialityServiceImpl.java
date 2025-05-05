package com.sebastian.clinicbooking.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sebastian.clinicbooking.DTO.speciality.SpecialityRequestDTO;
import com.sebastian.clinicbooking.DTO.speciality.SpecialityResponseDTO;
import com.sebastian.clinicbooking.exception.ResourceNotFoundException;
import com.sebastian.clinicbooking.mapper.SpecialityMapper;
import com.sebastian.clinicbooking.model.Speciality;
import com.sebastian.clinicbooking.repository.SpecialityRepository;
import com.sebastian.clinicbooking.service.ISpecialityService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SpecialityServiceImpl implements ISpecialityService{

    private final SpecialityRepository specialityRepository;
    private final SpecialityMapper specialityMapper;

    @Autowired
    public SpecialityServiceImpl(SpecialityRepository specialityRepository, SpecialityMapper specialityMapper) {
        this.specialityRepository = specialityRepository;
        this.specialityMapper = specialityMapper;
    }

    @Override
    public SpecialityResponseDTO createSpeciality(SpecialityRequestDTO specialityRequestDTO) {
        Speciality speciality = specialityMapper.toEntity(specialityRequestDTO);
        specialityRepository.save(speciality);
        log.info("Speciality created with ID: {}", speciality.getId());
        return specialityMapper.toDto(speciality);
    }

    @Override
    public SpecialityResponseDTO getSpecialityById(Long id) {
        Speciality speciality = findSpecialityById(id);
        
        log.info("Speciality found with ID: {}", speciality.getId());
        return specialityMapper.toDto(speciality);
    }

    @Override
    public List<SpecialityResponseDTO> getAllSpecialities() {
        List<Speciality> specialities = specialityRepository.findAll();
        log.info("Found {} specialities", specialities.size());
        return specialityMapper.toDtoList(specialities);
    }

    @Override
    public Page<SpecialityResponseDTO> getAllSpecialities(Pageable pageable) {
        Page<Speciality> specialities = specialityRepository.findAll(pageable);
        log.info("Found {} specialities", specialities.getTotalElements());
        return specialityMapper.toDtoPage(specialities);
    }

    @Override
    public List<Speciality> getSpecialityEntitiesByIds(List<Long> ids) {
        List<Speciality> specialities = specialityRepository.findAllById(ids);
        log.info("Found {} specialities by IDs", specialities.size());
        return specialities;
    }

    @Override
    public SpecialityResponseDTO updateSpeciality(Long id, SpecialityRequestDTO specialityRequestDTO) {
        Speciality speciality = findSpecialityById(id);

        specialityMapper.updateEntityFromDto(specialityRequestDTO, speciality);
        specialityRepository.save(speciality);

        log.info("Speciality updated with ID: {}", speciality.getId());
        return specialityMapper.toDto(speciality);
    }

    @Override
    public void deleteSpeciality(Long id) {
        Speciality speciality = findSpecialityById(id);

        specialityRepository.delete(speciality);
        log.info("Speciality deleted with ID: {}", speciality.getId());
    }

    private Speciality findSpecialityById(Long specialityId) {
        return specialityRepository.findById(specialityId)
                .orElseThrow(() -> new ResourceNotFoundException("Speciality not found with id: " + specialityId));
    }

    
    
}
