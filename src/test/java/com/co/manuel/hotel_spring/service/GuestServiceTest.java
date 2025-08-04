package com.co.manuel.hotel_spring.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.co.manuel.hotel_spring.dto.GuestDTO;
import com.co.manuel.hotel_spring.dto.ReservationDTO;
import com.co.manuel.hotel_spring.mapper.GuestMapper;
import com.co.manuel.hotel_spring.model.Guest;
import com.co.manuel.hotel_spring.model.Reservation;
import com.co.manuel.hotel_spring.repository.GuestRepository;

@SpringBootTest
public class GuestServiceTest {

  @InjectMocks
  private GuestService guestService;

  @Mock
  private GuestRepository guestRepository;

  @Mock
  private GuestMapper guestMapper;

  GuestDTO guestDTO1;
  List<ReservationDTO> reservationDTOs;
  Guest guest1;
  List<Reservation> reservations;

  @BeforeEach
  public void setUpBeforeEach() {
    MockitoAnnotations.openMocks(this);
    reservationDTOs = List.of(new ReservationDTO(1L, "14052025", "18052025", "200"));
    guestDTO1 = new GuestDTO(null, "Manuel", "Arias", "08121985", "Colombian", reservationDTOs);
    reservations = List.of(new Reservation(1L, "14052025", "18052025", 200, "Credit Card"));
    guest1 = new Guest(1L, "Manuel", "Arias", "08121985", "Colombian", "311730", reservations);

  }

  @Test
  @DisplayName("Test for createGuest service method to create/save a Guest DTO")
  public void createGuest_test() {

    // Guiven

    // Mock the calls
    Mockito.when(guestMapper.mapperCustomGuestFromGuestDto(guestDTO1)).thenReturn(guest1);
    // It could return a new saved Guest entity object
    Mockito.when(guestRepository.save(guest1)).thenReturn(guest1);
    guest1.setIdGuest(4L);
    Mockito.when(guestMapper.mapperCustomGuestDtoFromGuest(guest1))
        .thenReturn(guestDTO1);

    // When
    GuestDTO guestDTO = guestService.createGuest(guestDTO1);

    // Then

    Assertions.assertThat(guestDTO1.id()).isEqualTo(guestDTO.id());
    Assertions.assertThat(guestDTO1.firstName()).isEqualTo(guestDTO.firstName());
    assertEquals(guestDTO1.firstName(), guestDTO.firstName());
    assertEquals(guestDTO1.last_name(), guestDTO.last_name());
    assertEquals(guestDTO1.birthDay(), guestDTO.birthDay());
    assertEquals(guestDTO1.nationality(), guestDTO.nationality());

  }

}
