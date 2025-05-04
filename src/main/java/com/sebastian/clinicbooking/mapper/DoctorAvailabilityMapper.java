package com.sebastian.clinicbooking.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.sebastian.clinicbooking.DTO.doctorAvailability.DoctorAvailabilityRequestDTO;
import com.sebastian.clinicbooking.DTO.doctorAvailability.DoctorAvailabilityResponseDTO;
import com.sebastian.clinicbooking.model.DoctorAvailability;

@Mapper(componentModel = "spring", uses = {
    DoctorMapper.class,
})
public interface DoctorAvailabilityMapper {
    
    DoctorAvailability toEntity(DoctorAvailabilityRequestDTO dto);

    DoctorAvailabilityResponseDTO toDto(DoctorAvailability doctorAvailability);

    List<DoctorAvailabilityResponseDTO> toDtoList(List<DoctorAvailability> doctorAvailabilities);

    default Page<DoctorAvailabilityResponseDTO> toDtoPage(Page<DoctorAvailability> page) {
        List<DoctorAvailabilityResponseDTO> dtoList = toDtoList(page.getContent());
        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(DoctorAvailabilityRequestDTO dto, @MappingTarget DoctorAvailability doctorAvailability);
}
