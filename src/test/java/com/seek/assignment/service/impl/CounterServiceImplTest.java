package com.seek.assignment.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.seek.assignment.io.CounterIO;
import com.seek.assignment.service.CounterService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CounterServiceImplTest {

  private CounterService counterService;
  private static final String counterFile = "/Users/sujaybhowmick/development/rnd/counter-service/src/test/resources/counter-file.txt";

  @BeforeEach
  void setUp() {
    this.counterService  = new CounterServiceImpl(CounterIO.newInstance(counterFile));
  }

  @AfterEach
  void tearDown() {}

  @Test
  void totalCars() {
    int totalCount = this.counterService.totalCars();
    assertEquals(398, totalCount);
  }

  @Test
  void dayWiseTotal() {
    Map<String, Integer> dayWiseTotal = this.counterService.dayWiseTotal();
    assertEquals(179, dayWiseTotal.get("2016-12-01").intValue());
  }

  @Test
  void top3HalfHours() {
    Map<LocalDateTime, Integer>  top3HalfHours = this.counterService.top3HalfHours();
    assertEquals(3, top3HalfHours.size());
  }

  @Test
  void leastCars() {
    Map<LocalDateTime, Integer> leastCars = this.counterService.leastCarsInOneAndHalfHoursPeriod();
    assertEquals(1, leastCars.size());
  }
}