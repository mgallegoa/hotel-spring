package com.co.manuel.hotel_spring.dto;

public record ReservationDTO(
    Long id,
    String dateIn,
    String dateOut,
    String costToPay) {
}
