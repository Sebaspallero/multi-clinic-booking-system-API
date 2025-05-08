package com.sebastian.clinicbooking.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.sebastian.clinicbooking.DTO.doctor.DoctorRequestDTO;
import com.sebastian.clinicbooking.DTO.doctor.DoctorResponseDTO;
import com.sebastian.clinicbooking.DTO.doctor.DoctorSummaryDTO;
import com.sebastian.clinicbooking.DTO.doctor.DoctorWithAppointmentDTO;
import com.sebastian.clinicbooking.model.Doctor;

@Mapper(componentModel = "spring", uses = {
    SpecialityMapper.class,
    HealthInsuranceMapper.class,
    AppointmentMapper.class
})
public interface DoctorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "appointments", ignore = true)
    @Mapping(target = "clinic", ignore = true)
    @Mapping(target = "specialities", ignore = true)
    @Mapping(target = "healthInsurances", ignore = true)
    Doctor toEntity(DoctorRequestDTO dto);

    DoctorResponseDTO toDto(Doctor doctor);

    DoctorSummaryDTO toSummaryDto(Doctor doctor);

    DoctorWithAppointmentDTO toWithAppointmentDto(Doctor doctor);
    
    List<DoctorResponseDTO> toDtoList(List<Doctor> doctors);

    List<DoctorSummaryDTO> toSummaryDtoList(List<Doctor> doctors);

    List<DoctorWithAppointmentDTO> toWithAppointmentDtoList(List<Doctor> doctors);

    default Page<DoctorResponseDTO> toDtoPage(Page<Doctor> page) {
        List<DoctorResponseDTO> dtoList = toDtoList(page.getContent());
        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }

    default Page<DoctorWithAppointmentDTO> toWithAppointmentDtoPage(Page<Doctor> page) {
        List<DoctorWithAppointmentDTO> dtoList = toWithAppointmentDtoList(page.getContent());
        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }

    default Page<DoctorSummaryDTO> toSummaryDtoPage(Page<Doctor> page) {
        List<DoctorSummaryDTO> dtoList = toSummaryDtoList(page.getContent());
        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "appointments", ignore = true)
    @Mapping(target = "clinic", ignore = true)
    @Mapping(target = "specialities", ignore = true)
    @Mapping(target = "healthInsurances", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(DoctorRequestDTO dto, @MappingTarget Doctor doctor);
}
