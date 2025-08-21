package com.co.manuel.hotel_spring.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.co.manuel.hotel_spring.dto.GuestDTO;
import com.co.manuel.hotel_spring.dto.GuestDTOResponse;
import com.co.manuel.hotel_spring.service.GuestService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/guests")
public class GuestController {

  private GuestService guestService;

  @Autowired
  public GuestController(GuestService guestService) {
    this.guestService = guestService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.ACCEPTED)
  public List<GuestDTO> getAllGuest() {
    return guestService.getAll();
  }

  @GetMapping("pageable")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public GuestDTOResponse getAllGuest(@PageableDefault(size = 5, page = 2, sort = "idGuest") Pageable pageable) {
    return guestService.getAll(pageable);
  }

  @GetMapping("pageableNotDefault")
  @ResponseStatus(HttpStatus.CREATED)
  public GuestDTOResponse getAllGuest(@RequestParam Map<String, String> params) {
    System.out.println(params);
    System.out.println("size: " + params.get("size"));
    int size = Integer.parseInt(params.get("size"));
    System.out.println("page: " + params.get("page"));
    int page = Integer.parseInt(params.get("page"));
    Pageable pageable = PageRequest.of(page, size);
    return guestService.getAll(pageable);
  }

  @GetMapping("sorted")
  @ResponseStatus(HttpStatus.OK)
  public List<GuestDTO> getAllGuestWithSorting(@RequestParam String field) {
    return guestService.getAllWithSorting(field);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
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
