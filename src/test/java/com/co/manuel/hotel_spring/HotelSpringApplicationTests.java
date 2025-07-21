package com.co.manuel.hotel_spring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;

@SpringBootTest
class HotelSpringApplicationTests {

  @Test
  void contextLoads() {
  }

  @Test
  void getSpringVersion() {
    assertEquals("6.2.8", SpringVersion.getVersion());
  }
}
