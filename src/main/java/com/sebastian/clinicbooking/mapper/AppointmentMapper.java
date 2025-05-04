package com.sebastian.clinicbooking.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.sebastian.clinicbooking.DTO.appointment.AppointmentRequestDTO;
import com.sebastian.clinicbooking.DTO.appointment.AppointmentResponseDTO;
import com.sebastian.clinicbooking.model.Appointment;

@Mapper(componentModel = "spring", uses = {
    PatientMapper.class,
    ClinicMapper.class,
    DoctorMapper.class,
})
public interface AppointmentMapper {
    

    Appointment toEntity(AppointmentRequestDTO dto);

    AppointmentResponseDTO toDto(Appointment appointment);

    List <AppointmentResponseDTO> toDtoList(List<Appointment> appointments);

    default Page<AppointmentResponseDTO> toDtoPage(Page<Appointment> page) {
        List<AppointmentResponseDTO> dtoList = toDtoList(page.getContent());
        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(AppointmentRequestDTO dto, @MappingTarget Appointment appointment);

}
