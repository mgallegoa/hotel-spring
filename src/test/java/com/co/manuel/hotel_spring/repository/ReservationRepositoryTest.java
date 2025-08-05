package com.co.manuel.hotel_spring.repository;

import static org.junit.jupiter.api.Assertions.assertAll;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.co.manuel.hotel_spring.model.Reservation;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
public class ReservationRepositoryTest {

  @Autowired
  ReservationRepository reservationRepository;

  @Test
  public void ReservationRepository_Save_ReturnSaved() {
    // Arrange
    Reservation reservation = Reservation.builder().dateIn("06062025").dateOut("06072025").costToPay(300)
        .payUsed("credit card").build();

    // Act
    Reservation savedReservation = reservationRepository.save(reservation);

    // Assertion
    Assertions.assertThat(savedReservation).isNotNull();
    Assertions.assertThat(savedReservation.getIdReservation()).isEqualTo(1L);
    Assertions.assertThat(savedReservation.getDateIn()).isEqualTo(reservation.getDateIn());

  }

  @Test
  public void ReservationRepository_Delete_ReturnVoid() {
    // Arrange
    Reservation reservation = Reservation.builder().dateIn("06062025").dateOut("06072025").costToPay(300)
        .payUsed("credit card").build();

    // Act
    Reservation savedReservation = reservationRepository.save(reservation);

    // Assertion
    assertAll(() -> reservationRepository.deleteById(savedReservation.getIdReservation()));

  }

}
