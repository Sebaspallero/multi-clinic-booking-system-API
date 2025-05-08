package com.sebastian.clinicbooking.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.sebastian.clinicbooking.DTO.clinic.ClinicRequestDTO;
import com.sebastian.clinicbooking.DTO.clinic.ClinicResponseDTO;
import com.sebastian.clinicbooking.DTO.clinic.ClinicSummaryDTO;
import com.sebastian.clinicbooking.DTO.clinic.ClinicWithDoctorsDTO;
import com.sebastian.clinicbooking.model.Clinic;

@Mapper(componentModel = "spring", uses = AddressMapper.class)
public interface ClinicMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "doctors", ignore = true)
    @Mapping(target = "active", ignore = true)
    Clinic toEntity(ClinicRequestDTO dto);

    ClinicResponseDTO toDto(Clinic clinic);

    ClinicWithDoctorsDTO toDtoWithDoctors(Clinic clinic);

    ClinicSummaryDTO toSummaryDto(Clinic clinic);
    
    List<ClinicResponseDTO> toDtoList(List<Clinic> clinics);

    List<ClinicWithDoctorsDTO> toWithDoctorDtoList(List<Clinic> clinics);

    List<ClinicSummaryDTO> toSummaryDtoList(List<Clinic> clinics);

    default Page<ClinicResponseDTO> toDtoPage(Page<Clinic> page) {
        List<ClinicResponseDTO> dtoList = toDtoList(page.getContent());
        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }

    default Page<ClinicWithDoctorsDTO> toWithDoctorDtoPage(Page<Clinic> page) {
        List<ClinicWithDoctorsDTO> dtoList = toWithDoctorDtoList(page.getContent());
        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }

    default Page<ClinicSummaryDTO> toSummaryDtoPage(Page<Clinic> page) {
        List<ClinicSummaryDTO> dtoList = toSummaryDtoList(page.getContent());
        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "doctors", ignore = true)
    @Mapping(target = "active", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ClinicRequestDTO dto, @MappingTarget Clinic clinic);
}

