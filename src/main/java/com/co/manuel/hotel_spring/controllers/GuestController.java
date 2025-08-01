package com.co.manuel.hotel_spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.co.manuel.hotel_spring.dto.GuestDTO;
import com.co.manuel.hotel_spring.service.GuestService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/guest")
public class GuestController {

  @Autowired
  private GuestService guestService;

  @GetMapping
  @ResponseStatus(HttpStatus.ACCEPTED)
  public List<GuestDTO> getAllGuest() {
    return guestService.getAll();
  }

  @PostMapping
  public GuestDTO createGuest(@Valid @RequestBody GuestDTO guestDto) {
    return guestService.createGuest(guestDto);
  }

  @PutMapping("edit/{id}")
  public GuestDTO updateGuest(@Valid @RequestBody GuestDTO guestDto, @PathVariable Long id) {
    return guestService.updateGuest(guestDto, id);
  }

  @DeleteMapping("delete/{id}")
  public void deleteGuest(@PathVariable Long id) {
    guestService.deleteGuest(id);
  }

}
