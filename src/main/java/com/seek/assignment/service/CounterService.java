package com.seek.assignment.service;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Interface representing counter service to calculate various metrics
 *
 * @author sujaybhowmick
 */
public interface CounterService {

  /**
   * Returns the count of total cars
   *
   * @return count of total cars
   */
  int totalCars();

  /**
   * Returns sequence of lines containing day wise count of cars
   *
   * @return sequence of lines
   */
  Map<String, Integer> dayWiseTotal();

  /**
   * Returns sequence of lines containing top 3 half hours with most cars
   *
   * @return sequence of lines
   */
  Map<LocalDateTime, Integer> top3HalfHours();

  /**
   * Returns sequence of lines containing least cars during consecutive 1.5 hours period
   *
   * @return sequence of lines
   */
  Map<LocalDateTime, Integer> leastCarsInOneAndHalfHoursPeriod();
}
