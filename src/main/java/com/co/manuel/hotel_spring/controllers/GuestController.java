package com.co.manuel.hotel_spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.manuel.hotel_spring.model.Guest;
import com.co.manuel.hotel_spring.service.GuestService;

@RestController
@RequestMapping("api/v1/guest")
public class GuestController {

  @Autowired
  private GuestService guestService;

  @GetMapping
  public List<Guest> getAllGuest() {
    return guestService.getAll();
  }

  @PostMapping
  public Guest createGuest(@RequestBody Guest guest) {
    return guestService.createGuest(guest);
  }

  @PutMapping("edit/{id}")
  public Guest updateGuest(@RequestBody Guest guest, @PathVariable Long id) {
    guest.setIdGuest(id);
    return guestService.updateGuest(guest);
  }

  @DeleteMapping("delete/{id}")
  public void deleteGuest(@PathVariable Long id) {
    guestService.deleteGuest(id);
  }

}
