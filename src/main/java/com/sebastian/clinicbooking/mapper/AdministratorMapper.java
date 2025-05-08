package com.sebastian.clinicbooking.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.sebastian.clinicbooking.DTO.administrator.AdministratorRequestDTO;
import com.sebastian.clinicbooking.DTO.administrator.AdministratorResponseDTO;
import com.sebastian.clinicbooking.model.Administrator;

@Mapper(componentModel = "spring")
public interface AdministratorMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "active", ignore = true)
    Administrator toEntity(AdministratorRequestDTO dto);

    AdministratorResponseDTO toDto(Administrator administrator);

    List<AdministratorResponseDTO> toDtoList(List<Administrator> administrators);

    default Page<AdministratorResponseDTO> toDtoPage(Page<Administrator> page) {
        List<AdministratorResponseDTO> dtoList = toDtoList(page.getContent());
        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "active", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(AdministratorRequestDTO dto, @MappingTarget Administrator administrator);
}
