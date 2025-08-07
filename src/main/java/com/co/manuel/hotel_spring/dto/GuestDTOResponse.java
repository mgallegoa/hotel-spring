package com.co.manuel.hotel_spring.dto;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.Data;

@Data
public class GuestDTOResponse {

  List<GuestDTO> content;
  Pageable pageable;
  long totalElements;
  int totalPages;
  boolean last;
  int size;
  int number;
  Sort sort;
  int numberOfElements;
  boolean first;
  boolean empty;

}
