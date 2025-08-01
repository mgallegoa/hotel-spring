package com.co.manuel.hotel_spring.mapper;

import java.util.List;

import com.co.manuel.hotel_spring.dto.GuestDTO;
import com.co.manuel.hotel_spring.dto.ReservationDTO;
import com.co.manuel.hotel_spring.model.Guest;
import com.co.manuel.hotel_spring.model.Reservation;

public abstract class AbstractGuestMapper {

  public Guest mapperCustomGuestFromGuestDto(GuestDTO guestDto) {
    List<Reservation> reservations = guestDto.reservationsDto().stream()
        .map(ReservationMapper.INSTANCE::mapperReservationDtoFromReservation).toList();

    Guest guest = new Guest(guestDto.id(), guestDto.firstName(), guestDto.last_name(),
        guestDto.birthDay(), guestDto.nationality(), null, reservations);

    return guest;
  }

  public GuestDTO mapperCustomGuestDtoFromGuest(Guest guest) {
    List<ReservationDTO> reservationDTOs = guest.getReservations().stream()
        .map(ReservationMapper.INSTANCE::mapperReservationFromReservationDto).toList();

    GuestDTO guestDTO = new GuestDTO(guest.getIdGuest(), guest.getFirstName(), guest.getLastName(),
        guest.getBirthDay(), guest.getNationality(), reservationDTOs);

    return guestDTO;
  }
}
