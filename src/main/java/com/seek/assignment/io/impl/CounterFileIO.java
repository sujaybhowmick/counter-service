package com.seek.assignment.io.impl;

import com.seek.assignment.io.CounterIO;
import com.seek.assignment.model.CounterData;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class CounterFileIO implements CounterIO {

  // Default current directory
  private String file = "./counter-file.txt";

  public CounterFileIO(String file) {
    if (file != null) {
      this.file = file;
    }
  }

  @Override
  public CounterData read() throws IOException {
    final CounterData counterData = new CounterData();
    try (Stream<String> stream = Files.lines(Paths.get(this.file))) {
      stream.forEach(
          counterData::addLineItem);
    }
    return counterData;
  }
}
