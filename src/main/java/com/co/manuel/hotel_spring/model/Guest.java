package com.co.manuel.hotel_spring.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Guest {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idGuest;

  private String firstName;
  private String lastName;
  private String birthDay;
  private String nationality;
  private String phone;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Reservation> reservations = new ArrayList<>();
}
