package com.seek.assignment.model;

import com.seek.assignment.util.DateTimeUtils;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to hold counter data from counter input device/files
 *
 * @author sujaybhowmick
 */
public class CounterData {
  private Map<LocalDateTime, Integer> counterDataLines = new HashMap<>();

  public CounterData() {}

  /**
   * Add one line read from counter to map holding the counter data lines from the device/files
   *
   * @param line
   */
  public void addLineItem(String line) {
    String[] tempArr = line.split(" ");
    CharSequence text;
    LocalDateTime dateTime =
        LocalDateTime.parse(
            tempArr[0], DateTimeUtils.getDateTimeFormatter("yyyy-MM-dd'T'HH:mm:ss"));
    int totalCount = Integer.parseInt(tempArr[1]);
    counterDataLines.put(dateTime, totalCount);
  }

  /**
   * Returns the counter data lines read
   *
   * @return the number of the counter lines
   */
  public int size() {
    return this.counterDataLines.size();
  }

  /**
   * Returns a map containing the data lines read
   *
   * @return a Map holding the counter data line keyed by the recorded date time
   */
  public Map<LocalDateTime, Integer> getCounterDataLines() {
    return counterDataLines;
  }
}
