package com.sebastian.clinicbooking.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.sebastian.clinicbooking.DTO.address.AddressRequestDTO;
import com.sebastian.clinicbooking.DTO.address.AddressResponseDTO;
import com.sebastian.clinicbooking.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Address toEntity(AddressRequestDTO dto);

    AddressResponseDTO toDto(Address address);

    List<AddressResponseDTO> toDtoList(List<Address> addresses);

    default Page<AddressResponseDTO> toDtoPage(Page<Address> page) {
        List<AddressResponseDTO> dtoList = toDtoList(page.getContent());
        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(AddressRequestDTO dto, @MappingTarget Address address);
    
}
