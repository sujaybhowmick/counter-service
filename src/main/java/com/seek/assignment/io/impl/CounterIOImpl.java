package com.seek.assignment.io.impl;

import com.seek.assignment.io.CounterIO;
import com.seek.assignment.model.CounterData;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * File implementation of CounterIO interface to read counter data from a file
 *
 * @author sujaybhowmick
 */
public class CounterIOImpl implements CounterIO {

  // Default file
  private String file = "./counter-file.txt";

  public CounterIOImpl(String file) {
    if (file != null) {
      this.file = file;
    }
  }

  @Override
  public CounterData read() {
    final CounterData counterData = new CounterData();
    try (Stream<String> stream = Files.lines(Paths.get(this.file))) {
      stream.forEach(counterData::addLineItem);
    } catch (IOException ioe) {
      throw new RuntimeException(ioe);
    }
    return counterData;
  }
}
