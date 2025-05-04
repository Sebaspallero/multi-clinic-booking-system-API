package com.sebastian.clinicbooking.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.sebastian.clinicbooking.DTO.speciality.SpecialityDetailDTO;
import com.sebastian.clinicbooking.DTO.speciality.SpecialityRequestDTO;
import com.sebastian.clinicbooking.DTO.speciality.SpecialityResponseDTO;
import com.sebastian.clinicbooking.model.Speciality;

@Mapper(componentModel = "spring", uses = DoctorMapper.class)
public interface SpecialityMapper {

    Speciality toEntity(SpecialityRequestDTO dto);

    SpecialityRequestDTO toDto(Speciality speciality);

    Speciality toDetailDto(Speciality speciality);

    List<SpecialityResponseDTO> toDtoList(List<Speciality> specialities);

    List<SpecialityDetailDTO> toDetailDtoList(List<Speciality> specialities);

    default Page<SpecialityResponseDTO> toDtoPage(Page<Speciality> page) {
        List<SpecialityResponseDTO> dtoList = toDtoList(page.getContent());
        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }

    default Page<SpecialityDetailDTO> toDetailDtoPage(Page<Speciality> page) {
        List<SpecialityDetailDTO> dtoList = toDetailDtoList(page.getContent());
        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(SpecialityRequestDTO dto, @MappingTarget Speciality speciality);
    
}
