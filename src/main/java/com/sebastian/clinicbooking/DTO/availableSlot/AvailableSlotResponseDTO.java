package com.sebastian.clinicbooking.DTO.availableSlot;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AvailableSlotResponseDTO {
    
    private Long id;
    private LocalDateTime slotDateTime;
    private boolean available;
    private Long doctorId;
    private Long clinicId;
}