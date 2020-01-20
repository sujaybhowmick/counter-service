package com.seek.assignment.io;

import com.seek.assignment.io.impl.CounterFileIO;
import com.seek.assignment.model.CounterData;
import java.io.IOException;

public interface CounterIO {
  static CounterIO newInstance(String fileName) {
    return new CounterFileIO(fileName);
  }

  CounterData read() throws IOException;
}
