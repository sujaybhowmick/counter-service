package com.seek.assignment.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CounterData {
  private Map<LocalDateTime, Integer> counterDataLines = new HashMap<>();

  public CounterData() {}

  public void addLineItem(String line) {
    String[] tempArr = line.split(" ");
    DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
    CharSequence text;
    LocalDateTime dateTime = LocalDateTime.parse(tempArr[0], formatter);
    int totalCount = Integer.parseInt(tempArr[1]);
    counterDataLines.put(dateTime, totalCount);
  }

  public int size() {
    return this.counterDataLines.size();
  }

  public Map<LocalDateTime, Integer> getCounterDataLines() {
    return counterDataLines;
  }
}
