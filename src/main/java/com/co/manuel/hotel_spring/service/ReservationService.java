package com.co.manuel.hotel_spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.manuel.hotel_spring.model.Reservation;
import com.co.manuel.hotel_spring.repository.ReservationRepository;

@Service
public class ReservationService {

  @Autowired
  private ReservationRepository reservationRepository;

  public List<Reservation> getAll() {
    return reservationRepository.findAll();
  }

  public Reservation createReservation(Reservation guest) {
    return reservationRepository.save(guest);
  }

  public Reservation updateReservation(Reservation guest) {
    return reservationRepository.save(guest);
  }

  public void deleteReservation(Long id) {
    reservationRepository.deleteById(id);
  }
}
