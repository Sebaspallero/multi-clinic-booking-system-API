package com.sebastian.clinicbooking.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.sebastian.clinicbooking.DTO.healthInsurance.HealthInsuranceDetailDTO;
import com.sebastian.clinicbooking.DTO.healthInsurance.HealthInsuranceRequestDTO;
import com.sebastian.clinicbooking.DTO.healthInsurance.HealthInsuranceResponseDTO;
import com.sebastian.clinicbooking.model.HealthInsurance;

@Mapper(componentModel = "spring")
public interface HealthInsuranceMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "doctors", ignore = true)
    HealthInsurance toEntity(HealthInsuranceRequestDTO dto);

    HealthInsuranceResponseDTO toDto(HealthInsurance healthInsurance);

    HealthInsuranceDetailDTO toDetailDto(HealthInsurance healthInsurance);
    
    List<HealthInsuranceResponseDTO> toDtoList(List<HealthInsurance> healthInsurances);

    List<HealthInsuranceDetailDTO> toDetailDtoList(List<HealthInsurance> healthInsurances);

    default Page<HealthInsuranceResponseDTO> toDtoPage(Page<HealthInsurance> page) {
        List<HealthInsuranceResponseDTO> dtoList = toDtoList(page.getContent());
        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }

    default Page<HealthInsuranceDetailDTO> toDetailDtoPage(Page<HealthInsurance> page) {
        List<HealthInsuranceDetailDTO> dtoList = toDetailDtoList(page.getContent());
        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "doctors", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(HealthInsuranceRequestDTO dto, @MappingTarget HealthInsurance healthInsurance);
}
