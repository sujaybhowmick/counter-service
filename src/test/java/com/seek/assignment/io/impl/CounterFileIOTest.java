package com.seek.assignment.io.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.seek.assignment.io.CounterIO;
import com.seek.assignment.model.CounterData;

class CounterFileIOTest {

  private static final String counterFile = "/Users/sujaybhowmick/development/rnd/counter-service/src/test/resources/counter-file.txt";
  @org.junit.jupiter.api.BeforeEach
  void setUp() {}

  @org.junit.jupiter.api.AfterEach
  void tearDown() {}

  @org.junit.jupiter.api.Test
  void read() throws Exception {
    CounterIO counterIO = new CounterFileIO(counterFile);
    CounterData counterData = counterIO.read();
    assertEquals(24, counterData.size());
  }
}