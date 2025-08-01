package com.co.manuel.hotel_spring.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

public record GuestDTO(
    Long id,
    @NotBlank String firstName,
    @NotBlank String last_name,
    @Digits(integer = 8, fraction = 0) String birthDay,
    String nationality,
    @Valid List<ReservationDTO> reservationsDto) {
}
