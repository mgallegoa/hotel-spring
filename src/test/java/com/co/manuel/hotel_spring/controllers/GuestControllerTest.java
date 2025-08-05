package com.co.manuel.hotel_spring.controllers;

import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.co.manuel.hotel_spring.dto.GuestDTO;
import com.co.manuel.hotel_spring.dto.ReservationDTO;
import com.co.manuel.hotel_spring.service.GuestService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class GuestControllerTest {

  @Mock
  private GuestService guestService;

  private MockMvc mockMvc;
  private GuestController guestController;
  private ObjectMapper objectMapper;

  GuestDTO guestDTO1;

  @BeforeEach
  public void setUpBeforeEach() {

    List<ReservationDTO> reservationDTOs = List.of(new ReservationDTO(1L, "14052025", "18052025", "200"));
    guestDTO1 = new GuestDTO(null, "Manuel", "Arias", "08121985", "Colombian", reservationDTOs);
    guestController = new GuestController(guestService);
    mockMvc = MockMvcBuilders.standaloneSetup(guestController).build();
    objectMapper = new ObjectMapper();

  }

  @Test
  void GuestController_createGuest_Test() throws Exception {
    // Given
    given(guestService.createGuest(ArgumentMatchers.any())).willReturn(guestDTO1);

    // When
    ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/guest")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(guestDTO1)));

    // Then

    resultActions.andExpect(MockMvcResultMatchers.status().isCreated());

  }

  @Test
  void GuestController_deleteGuest_Test() throws Exception {
    // Given
    Mockito.doNothing().when(guestService).deleteGuest(1L);

    // When
    ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/guest/delete/1")
        .contentType(MediaType.APPLICATION_JSON));

    // Then

    resultActions.andExpect(MockMvcResultMatchers.status().isOk());

  }

}
