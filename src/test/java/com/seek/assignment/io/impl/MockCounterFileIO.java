package com.seek.assignment.io.impl;

import com.seek.assignment.io.CounterIO;
import com.seek.assignment.model.CounterData;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 *
 */
public class MockCounterFileIO implements CounterIO {

  private final String counterDataStr;
  public MockCounterFileIO(String counterDataStr){
    this.counterDataStr = counterDataStr;
  }
  /**
   * Returns counter data read from counter device
   *
   * @return counter data object representing the data read from counter device
   */
  @Override
  public CounterData read() {
    final CounterData counterData = new CounterData();
    Scanner scanner = new Scanner(this.counterDataStr);
    while(scanner.hasNextLine()){
      String line = scanner.nextLine();
      counterData.addLineItem(line);
    }
    scanner.close();
    return counterData;
  }
}
