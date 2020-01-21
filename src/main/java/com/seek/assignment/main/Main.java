package com.seek.assignment.main;

import com.seek.assignment.controller.CounterOutputController;
import com.seek.assignment.io.CounterIO;
import com.seek.assignment.io.impl.CounterIOImpl;
import com.seek.assignment.service.CounterService;
import com.seek.assignment.service.impl.CounterServiceImpl;

public class Main {

  public static void main(String[] args) {
    if(args.length < 1) {
      System.out.println("Usage: Main <counter.out>");
      System.exit(1);
    }
    final String fileName = args[0];
    CounterIO counterIO = new CounterIOImpl(fileName);
    CounterService counterService = new CounterServiceImpl(counterIO);
    CounterOutputController outputController = new CounterOutputController(counterService);
    System.out.println(outputController.getTotalCars());
    System.out.println("--- Number of cars seen on that day ---");
    System.out.println(outputController.getDayWiseTotal());
    System.out.println("--- Top 3 half hours with most cars ---");
    System.out.println(outputController.getTop3HalfHours());
    System.out.println("--- Least cars in 1.5 hours period ---");
    System.out.println(outputController.getLeastCarsInOneAndHalfHoursPeriod());
  }
}
