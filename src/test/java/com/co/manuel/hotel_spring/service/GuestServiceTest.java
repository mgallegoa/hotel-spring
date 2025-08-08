package com.co.manuel.hotel_spring.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.co.manuel.hotel_spring.dto.GuestDTO;
import com.co.manuel.hotel_spring.dto.GuestDTOResponse;
import com.co.manuel.hotel_spring.dto.ReservationDTO;
import com.co.manuel.hotel_spring.mapper.GuestMapper;
import com.co.manuel.hotel_spring.model.Guest;
import com.co.manuel.hotel_spring.model.Reservation;
import com.co.manuel.hotel_spring.repository.GuestRepository;

// @SpringBootTest
@ExtendWith(MockitoExtension.class)
public class GuestServiceTest {

  // @InjectMocks
  private GuestService guestService;

  @Mock
  private GuestRepository guestRepository;

  @Mock
  private GuestMapper guestMapper;

  GuestDTO guestDTO1;
  List<ReservationDTO> reservationDTOs;
  Guest guest1;
  List<Reservation> reservations;

  Page<Guest> pageGuest;

  @BeforeEach
  public void setUpBeforeEach() {
    // MockitoAnnotations.openMocks(this);
    guestService = new GuestService(guestMapper, guestRepository);
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
    Mockito.verify(guestMapper, Mockito.times(1)).mapperCustomGuestFromGuestDto(guestDTO1);
    Mockito.verify(guestRepository, Mockito.times(1)).save(guest1);
    Mockito.verify(guestMapper, Mockito.times(1)).mapperCustomGuestDtoFromGuest(guest1);

  }

  @Test
  public void getAllGuest_Test() {
    Pageable pageable = PageRequest.of(0, 5);
    List<Guest> guests = Arrays.asList(guest1);
    pageGuest = new PageImpl<>(guests, pageable, 1);
    GuestDTOResponse guestDTOResponseMapping = new GuestDTOResponse();
    guestDTOResponseMapping.setContent(Arrays.asList(guestDTO1));

    Mockito.when(guestRepository.findAll(pageable)).thenReturn(pageGuest);
    Mockito.when(guestMapper.mapperGuestDtoResponseFromPageGuest(pageGuest)).thenReturn(guestDTOResponseMapping);

    GuestDTOResponse guestDTOResponse = guestService.getAll(pageable);

    Assertions.assertThat(guestDTOResponse).isNotNull();
    Assertions.assertThat(guestDTOResponse.getContent()).isNotNull();
    Assertions.assertThat(guestDTOResponse.getContent().get(0)).isNotNull();
    Assertions.assertThat(guestDTOResponse.getContent().get(0).firstName()).isEqualTo(guest1.getFirstName());

    Assertions.assertThat(guestDTOResponse.getContent().get(0).reservationsDto()).isNotNull();
    Assertions.assertThat(guestDTOResponse.getContent().get(0).reservationsDto().get(0)).isNotNull();
    Assertions.assertThat(guestDTOResponse.getContent().get(0).reservationsDto().get(0).dateIn())
        .isEqualTo(reservations.get(0).getDateIn());

  }

}
