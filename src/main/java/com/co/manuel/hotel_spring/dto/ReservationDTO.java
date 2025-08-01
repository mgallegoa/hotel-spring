package com.co.manuel.hotel_spring.dto;

import jakarta.validation.constraints.NotBlank;

public record ReservationDTO(
    Long id,
    @NotBlank String dateIn,
    @NotBlank String dateOut,
    @NotBlank String costToPay) {
}
