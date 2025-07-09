package com.co.manuel.hotel_spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.manuel.hotel_spring.model.Guest;
import com.co.manuel.hotel_spring.repository.GuestRepository;

@Service
public class GuestService {

  @Autowired
  private GuestRepository guestRepository;

  public List<Guest> getAll() {
    return guestRepository.findAll();
  }

  public Guest createGuest(Guest guest) {
    return guestRepository.save(guest);
  }

  public Guest updateGuest(Guest guest) {
    return guestRepository.save(guest);
  }

  public void deleteGuest(Long id) {
    guestRepository.deleteById(id);
  }
}
