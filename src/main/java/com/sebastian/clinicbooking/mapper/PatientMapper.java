package com.sebastian.clinicbooking.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.sebastian.clinicbooking.DTO.patient.PatientRequestDTO;
import com.sebastian.clinicbooking.DTO.patient.PatientResponseDTO;
import com.sebastian.clinicbooking.DTO.patient.PatientSummaryDTO;
import com.sebastian.clinicbooking.DTO.patient.PatientWithAppointmentDTO;
import com.sebastian.clinicbooking.model.Patient;


@Mapper(componentModel = "spring", uses = {
    AppointmentMapper.class,
    HealthInsuranceMapper.class,
})
public interface PatientMapper {

    Patient toEntity(PatientRequestDTO dto);

    PatientResponseDTO toDto(Patient patient);

    PatientSummaryDTO toSummaryDto(Patient patient);

    PatientWithAppointmentDTO toWithAppointmentDto(Patient patient);

    List<PatientResponseDTO> toDtoList(List<Patient> patients);

    List<PatientSummaryDTO> toSummaryDtoList(List<Patient> patients);

    List<PatientWithAppointmentDTO> toWithAppointmentDtoList(List<Patient> patients);


    default Page<PatientResponseDTO> toDtoPage(Page<Patient> page) {
        List<PatientResponseDTO> dtoList = toDtoList(page.getContent());
        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }

    default Page<PatientSummaryDTO> toDetailDtoPage(Page<Patient> page) {
        List<PatientSummaryDTO> dtoList = toSummaryDtoList(page.getContent());
        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }

    default Page<PatientWithAppointmentDTO> toWithAppointmentDtoPage(Page<Patient> page) {
        List<PatientWithAppointmentDTO> dtoList = toWithAppointmentDtoList(page.getContent());
        return new PageImpl<>(dtoList, page.getPageable(), page.getTotalElements());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(PatientRequestDTO dto, @MappingTarget Patient patient);

}
