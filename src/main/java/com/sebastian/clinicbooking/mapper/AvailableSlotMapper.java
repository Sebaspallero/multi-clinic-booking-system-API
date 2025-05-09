package com.sebastian.clinicbooking.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sebastian.clinicbooking.DTO.availableSlot.AvailableSlotResponseDTO;
import com.sebastian.clinicbooking.model.AvailableSlot;

@Mapper(componentModel = "spring")
public interface AvailableSlotMapper {
    
    @Mapping(target = "clinicId", source = "clinic.id")
    @Mapping(target = "doctorId", source = "doctor.id")
    AvailableSlotResponseDTO toDto(AvailableSlot slot);
    
    List<AvailableSlotResponseDTO> toDtoList(List<AvailableSlot> slots);
}

