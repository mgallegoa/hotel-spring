package com.co.manuel.hotel_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.manuel.hotel_spring.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
