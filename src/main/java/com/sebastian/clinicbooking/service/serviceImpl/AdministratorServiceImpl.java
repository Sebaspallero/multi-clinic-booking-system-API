package com.sebastian.clinicbooking.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sebastian.clinicbooking.DTO.administrator.AdministratorRequestDTO;
import com.sebastian.clinicbooking.DTO.administrator.AdministratorResponseDTO;
import com.sebastian.clinicbooking.enums.Roles;
import com.sebastian.clinicbooking.exception.ResourceNotFoundException;
import com.sebastian.clinicbooking.mapper.AdministratorMapper;
import com.sebastian.clinicbooking.model.Administrator;
import com.sebastian.clinicbooking.repository.AdministratorRepository;
import com.sebastian.clinicbooking.service.IAdministratorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdministratorServiceImpl implements IAdministratorService{

    private final AdministratorRepository administratorRepository;
    private final AdministratorMapper administratorMapper;

    @Autowired
    public AdministratorServiceImpl(AdministratorRepository administratorRepository, AdministratorMapper administratorMapper) {
        this.administratorRepository = administratorRepository;
        this.administratorMapper = administratorMapper;
    }

    @Override
    @Transactional
    public AdministratorResponseDTO createAdministrator(AdministratorRequestDTO administratorRequestDTO) {
        Administrator administrator = administratorMapper.toEntity(administratorRequestDTO);
        administrator.setRole(Roles.ADMIN);
        administratorRepository.save(administrator);
        log.info("Administrator created: {}", administrator);
        return administratorMapper.toDto(administrator);
    }

    @Override
    @Transactional(readOnly = true)
    public AdministratorResponseDTO getAdministratorById(Long id) {
        Administrator administrator = findAdministratorById(id);
        
        log.info("Administrator found: {}", administrator);
        return administratorMapper.toDto(administrator);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AdministratorResponseDTO> getAllAdministrators() {
        List<Administrator> administrators = administratorRepository.findAll();
        log.info("Found {} administrators", administrators.size());
        return administratorMapper.toDtoList(administrators);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AdministratorResponseDTO> getAllAdministrators(Pageable pageable) {
        Page<Administrator> administrators = administratorRepository.findAll(pageable);
        log.info("Found {} administrators", administrators.getTotalElements());
        return administratorMapper.toDtoPage(administrators);
    }

    @Override
    @Transactional
    public AdministratorResponseDTO updateAdministrator(Long id, AdministratorRequestDTO AdministratorRequestDTO) {
        Administrator administrator = findAdministratorById(id);

        administratorMapper.updateEntityFromDto(AdministratorRequestDTO, administrator);
        administratorRepository.save(administrator);
        log.info("Administrator updated: {}", administrator);
        return administratorMapper.toDto(administrator);
    }

    @Override
    @Transactional
    public void deleteAdministrator(Long id) {
        Administrator administrator = findAdministratorById(id);

        administratorRepository.delete(administrator);
        log.info("Administrator deleted: {}", administrator);
    }

    private Administrator findAdministratorById(Long administratorId) {
        return administratorRepository.findById(administratorId)
                .orElseThrow(() -> new ResourceNotFoundException("Administrator not found with id: " + administratorId));
    }

}
