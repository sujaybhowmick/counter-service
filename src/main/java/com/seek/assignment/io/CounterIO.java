package com.seek.assignment.io;

import com.seek.assignment.model.CounterData;
import java.io.IOException;

/**
 * Interface to read data from the counter device
 *
 * @author sujaybhowmick
 * @im
 */
public interface CounterIO {

  /**
   * Returns counter data read from counter device
   *
   * @return counter data object representing the data read from counter device
   * @throws IOException
   */
  CounterData read();
}
