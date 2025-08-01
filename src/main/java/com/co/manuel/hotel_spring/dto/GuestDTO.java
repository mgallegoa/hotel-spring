package com.co.manuel.hotel_spring.dto;

import java.util.List;

public record GuestDTO(
    Long id,
    String firstName,
    String last_name,
    String birthDay,
    String nationality,
    List<ReservationDTO> reservationsDto) {
}
