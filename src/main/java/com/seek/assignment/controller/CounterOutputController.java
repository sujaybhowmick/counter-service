package com.seek.assignment.controller;

import com.seek.assignment.service.CounterService;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Controller class to output results of the counter device.
 *
 * @author sujaybhowmick
 */
public class CounterOutputController {
  private CounterService counterService;

  public CounterOutputController(CounterService counterService) {
    this.counterService = counterService;
  }

  public String getTotalCars() {
    return String.format("Total Cars: %d", this.counterService.totalCars());
  }

  public String getDayWiseTotal() {
    Map<String, Integer> dayWiseTotal = this.counterService.dayWiseTotal();
    StringBuilder sb = new StringBuilder();
    dayWiseTotal.forEach((k, v) -> sb.append(String.format("%s %d\n", k, v)));
    return sb.toString();
  }

  public String getTop3HalfHours() {
    StringBuilder sb = new StringBuilder();
    Map<LocalDateTime, Integer> top3HalfHours = this.counterService.top3HalfHours();
    top3HalfHours.forEach((k, v) -> sb.append(String.format("%s %d\n", k, v)));
    return sb.toString();
  }

  public String getLeastCarsInOneAndHalfHoursPeriod() {
    StringBuilder sb = new StringBuilder();
    Map<LocalDateTime, Integer> top3HalfHours =
        this.counterService.leastCarsInOneAndHalfHoursPeriod();
    top3HalfHours.forEach((k, v) -> sb.append(String.format("%s %d\n", k, v)));
    return sb.toString();
  }
}
