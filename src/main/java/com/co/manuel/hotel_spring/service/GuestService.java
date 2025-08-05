package com.co.manuel.hotel_spring.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.manuel.hotel_spring.dto.GuestDTO;
import com.co.manuel.hotel_spring.mapper.GuestMapper;
import com.co.manuel.hotel_spring.model.Guest;
import com.co.manuel.hotel_spring.repository.GuestRepository;

@Service
public class GuestService {

  private GuestRepository guestRepository;

  private final static Logger LOGGER = LoggerFactory.getLogger(GuestService.class);

  private GuestMapper guestMapper;

  @Autowired
  public GuestService(GuestMapper guestMapper, GuestRepository guestRepository) {
    this.guestMapper = guestMapper;
    this.guestRepository = guestRepository;
  }

  public List<GuestDTO> getAll() {
    List<Guest> guests = guestRepository.findAll();
    // List<GuestDTO> guestDTOs =
    // GuestMapper.INSTANCE.mapperListGuestDtoFromGuest(guests);
    /*
     * Important Note about mapping attributes in children : For this approach the
     * reservationDto.id can't inferred the reservation.idReservation
     */
    // List<GuestDTO> guestDTOs = guests.stream()
    // .map(GuestMapper.INSTANCE::mapperGestDtoFromGuest).toList();

    // List<GuestDTO> guestDTOs = guests.stream()
    // .map(guest -> {
    // List<ReservationDTO> reservationDTOs = guest.getReservations().stream()
    // .map(ReservationMapper.INSTANCE::mapperReservationFromReservationDto).toList();
    // GuestDTO guestDTO = new GuestDTO(guest.getIdGuest(), guest.getFirstName(),
    // guest.getLastName(),
    // guest.getBirthDay(), guest.getNationality(), reservationDTOs);
    // return guestDTO;
    // }).toList();
    //

    List<GuestDTO> guestDTOs = guests.stream()
        .map(guestMapper::mapperCustomGuestDtoFromGuest).toList();

    return guestDTOs;
  }

  public GuestDTO createGuest(GuestDTO guestDto) {
    LOGGER.info("GuestService call to method createGuest");
    Guest guest = guestMapper.mapperCustomGuestFromGuestDto(guestDto);
    guest = guestRepository.save(guest);
    return guestMapper.mapperCustomGuestDtoFromGuest(guest);
  }

  public GuestDTO updateGuest(GuestDTO guestDto, Long id) {
    Guest guest = guestMapper.mapperCustomGuestFromGuestDto(guestDto);
    guest.setIdGuest(id);
    guest = guestRepository.save(guest);
    return guestMapper.mapperCustomGuestDtoFromGuest(guest);
  }

  public void deleteGuest(Long id) {
    guestRepository.deleteById(id);
  }
}
