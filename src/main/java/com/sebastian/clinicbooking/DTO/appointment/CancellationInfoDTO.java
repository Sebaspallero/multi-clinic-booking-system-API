package com.sebastian.clinicbooking.DTO.appointment;

import java.time.LocalDateTime;

import com.sebastian.clinicbooking.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CancellationInfoDTO {

    private Roles canceledBy;
    private LocalDateTime cancellationDate;
    private String cancellationReason;
    private Long rescheduledFromId;
}
