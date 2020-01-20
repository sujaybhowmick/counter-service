package com.seek.assignment.service;

import com.seek.assignment.io.CounterIO;
import com.seek.assignment.service.impl.CounterServiceImpl;
import java.time.LocalDateTime;
import java.util.Map;

public interface CounterService {
  static CounterService newInstance(CounterIO counterIO) {
    return new CounterServiceImpl(counterIO);
  }

  int totalCars();

  Map<String, Integer> dayWiseTotal();

  Map<LocalDateTime, Integer> top3HalfHours();

  Map<LocalDateTime, Integer> leastCarsInOneAndHalfHoursPeriod();
}
