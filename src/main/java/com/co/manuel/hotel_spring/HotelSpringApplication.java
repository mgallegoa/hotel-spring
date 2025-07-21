package com.co.manuel.hotel_spring;

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
    };
  }

}
