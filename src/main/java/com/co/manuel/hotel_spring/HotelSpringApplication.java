package com.co.manuel.hotel_spring;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.co.manuel.hotel_spring.model.Guest;
import com.co.manuel.hotel_spring.repository.GuestRepository;

@SpringBootApplication
public class HotelSpringApplication {

  public static void main(String[] args) {
    SpringApplication.run(HotelSpringApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunnerTestRepository(GuestRepository repository) {
    return args -> {
      var guest = Guest.builder()
          .firstName("Manuel")
          .lastName("Test from command line")
          .birthDay("August")
          .nationality("Col")
          .phone("311730")
          .build();
      repository.save(guest);

      List<Guest> guests = IntStream.rangeClosed(2, 202)
          .mapToObj(g -> Guest.builder()
              .firstName("Manuel" + g)
              .lastName("Test from command line" + g)
              .birthDay("August" + g)
              .nationality("Col" + g)
              .phone("311730" + g)
              .build())
          .collect(Collectors.toList());

      repository.saveAll(guests);
    };
  }

}
