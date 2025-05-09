package com.sebastian.clinicbooking.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.sebastian.clinicbooking.DTO.appointment.AppointmentRequestDTO;
import com.sebastian.clinicbooking.DTO.appointment.AppointmentResponseDTO;
import com.sebastian.clinicbooking.model.Appointment;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "cancellationReason", ignore = true)
    @Mapping(target = "cancellationDate", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "clinic", ignore = true)
    @Mapping(target = "canceledBy", ignore = true)
    @Mapping(target = "rescheduledFrom", ignore = true)
    @Mapping(target = "confirmed", ignore = true)
    @Mapping(target = "availableSlot", ignore = true)
    Appointment toEntity(AppointmentRequestDTO dto);

    @Mapping(target = "cancellationInfo", ignore = true)
    @Mapping(target = "appointmentDateTime", source = "appointment.availableSlot.slotDateTime")
    AppointmentResponseDTO toDto(Appointment appointment);

    List <AppointmentResponseDTO> toDtoList(List<Appointment> appointments);

    default Page<AppointmentResponseDTO> toDtoPage(Page<Appointment> page) {
        List<AppointmentResponseDTO> dtoList = toDtoList(page.getContent());
        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "cancellationReason", ignore = true)
    @Mapping(target = "cancellationDate", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "clinic", ignore = true)
    @Mapping(target = "canceledBy", ignore = true)
    @Mapping(target = "rescheduledFrom", ignore = true)
    @Mapping(target = "confirmed", ignore = true)
    @Mapping(target = "availableSlot", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(AppointmentRequestDTO dto, @MappingTarget Appointment appointment);

}
