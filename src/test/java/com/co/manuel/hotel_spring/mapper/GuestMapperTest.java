package com.co.manuel.hotel_spring.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.co.manuel.hotel_spring.dto.GuestDTO;
import com.co.manuel.hotel_spring.model.Guest;
import com.co.manuel.hotel_spring.model.Reservation;

public class GuestMapperTest {

  Reservation reservation;
  Reservation reservation2;
  List<Reservation> reservations;
  Guest guest;
  List<Guest> guests;

  @BeforeAll
  public static void setupClass() {
    System.out.println(
        "Before All setup GuestMapperTest. This should be static because JUnit is PER_METHOD is a new instance class for every @Test method.");
  }

  @BeforeEach
  public void setupEachMethod() {
    System.out.println("Before each setup GuestMapperTest");

    reservation = new Reservation(1L, "2May", "8May", 100, "CreditCard");
    reservation2 = new Reservation(1L, "5August", "25August", 200, "CreditCard");
    reservations = List.of(reservation, reservation2);
    guest = new Guest(1L, "Manuel", "Arias", "12081985", "Colombiano", "311144555", reservations);
    guests = List.of(guest);

  }

  @AfterEach
  public void setupEachAfterMethod() {
    System.out.println("After setup GuestMapperTest");
  }

  @Test
  @DisplayName("Test for mapping custom Guest DTO from a Guest Entity.")
  public void mappingCustomGuestDtoFromGuest_test() {
    // Guiven
    // When
    List<GuestDTO> guestDTOs = guests.stream()
        .map(GuestMapper.INSTANCE::mapperCustomGuestDtoFromGuest).toList();

    // then
    assertNotNull(guestDTOs);
    assertNotNull(guestDTOs.get(0));
    assertEquals(guestDTOs.get(0).firstName(), guest.getFirstName());
    assertNotNull(guestDTOs.get(0).reservationsDto());
    assertNotNull(guestDTOs.get(0).reservationsDto().get(0));
    assertEquals(guestDTOs.get(0).reservationsDto().get(0).dateIn(), guest.getReservations().get(0).getDateIn());

  }
}
