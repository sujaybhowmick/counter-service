package com.seek.assignment.io.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.seek.assignment.io.CounterIO;
import com.seek.assignment.model.CounterData;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class CounterFileIOTest {

  private String fileName;

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    File testCounterFile = new File("src/test/resources/counter.out");
    this.fileName = testCounterFile.getAbsolutePath();
  }

  @org.junit.jupiter.api.AfterEach
  void tearDown() {}


  @org.junit.jupiter.api.Test
  void read() {
    CounterIO counterIO = new CounterIOImpl(fileName);
    CounterData counterData = counterIO.read();
    assertEquals(24, counterData.size());
  }
}
