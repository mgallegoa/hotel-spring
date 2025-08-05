package com.co.manuel.hotel_spring.controllers;

import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.co.manuel.hotel_spring.dto.GuestDTO;
import com.co.manuel.hotel_spring.dto.ReservationDTO;
import com.co.manuel.hotel_spring.repository.GuestRepository;
import com.co.manuel.hotel_spring.service.GuestService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = GuestController.class)
// @AutoConfigureMockMvc(addFilters = false)
public class GuestControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private GuestService guestService;

  @MockBean
  private GuestRepository guestRepository;
  GuestDTO guestDTO1;

  @BeforeEach
  public void setUpBeforeEach() {
    // Not needed when use MockBean
    // MockitoAnnotations.openMocks(this);
    List<ReservationDTO> reservationDTOs = List.of(new ReservationDTO(1L, "14052025", "18052025", "200"));
    guestDTO1 = new GuestDTO(null, "Manuel", "Arias", "08121985", "Colombian", reservationDTOs);

  }

  @Test
  void GuestController_createGuest_Test() throws Exception {
    // Given
    given(guestService.createGuest(ArgumentMatchers.any())).willAnswer(invocation -> invocation.getArgument(0));

    // When
    ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/guest")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(guestDTO1)));

    // Then

    resultActions.andExpect(MockMvcResultMatchers.status().isCreated());

  }

}
