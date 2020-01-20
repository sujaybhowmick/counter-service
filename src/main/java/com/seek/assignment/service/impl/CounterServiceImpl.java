package com.seek.assignment.service.impl;

import com.seek.assignment.io.CounterIO;
import com.seek.assignment.model.CounterData;
import com.seek.assignment.service.CounterService;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CounterServiceImpl implements CounterService {

  private final CounterIO counterIO;
  private CounterData counterData;

  public CounterServiceImpl(CounterIO counterIO) {
    this.counterIO = counterIO;
    readData();
  }

  private void readData() {
    try {
      this.counterData = this.counterIO.read();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public int totalCars() {
    return this.counterData.getCounterDataLines().values().stream().mapToInt(i -> i)
        .sum();
  }

  @Override
  public Map<String, Integer> dayWiseTotal() {
    return this.counterData.getCounterDataLines().entrySet().stream()
        .collect(
            Collectors.groupingBy(
                e -> e.getKey().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                Collectors.summingInt(Entry::getValue)));
  }

  @Override
  public Map<LocalDateTime, Integer> top3HalfHours() {
    Map<LocalDateTime, Integer> sortedLines = this.counterData.getCounterDataLines();
    List<LocalDateTime> keyList = getKeyList(sortedLines);

    final Map<LocalDateTime, Integer> top3HalfHours = getCarCountForRange(keyList, sortedLines, 30);

    return getCarCountWithComparatorAndLimit(
        top3HalfHours, Entry.comparingByValue(Comparator.reverseOrder()), 3);
  }

  public Map<LocalDateTime, Integer> leastCarsInOneAndHalfHoursPeriod() {
    Map<LocalDateTime, Integer> sortedLines = this.counterData.getCounterDataLines();
    List<LocalDateTime> keyList = getKeyList(sortedLines);

    final Map<LocalDateTime, Integer> leastCars = getCarCountForRange(keyList, sortedLines, 90);
    return getCarCountWithComparatorAndLimit(leastCars, Entry.comparingByValue(), 3);
  }

  private List<LocalDateTime> getKeyList(Map<LocalDateTime, Integer> sortedLines) {
    return sortedLines.entrySet().stream()
        .sorted(Entry.comparingByKey())
        .map(Entry::getKey)
        .collect(Collectors.toList());
  }

  private Map<LocalDateTime, Integer> getCarCountWithComparatorAndLimit(
      Map<LocalDateTime, Integer> carCountMap,
      Comparator<Map.Entry<LocalDateTime, Integer>> comparator,
      int limit) {
    return carCountMap.entrySet().stream()
        .sorted(comparator)
        .limit(limit)
        .collect(
            Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
  }

  private Map<LocalDateTime, Integer> getCarCountForRange(
      List<LocalDateTime> keyList, Map<LocalDateTime, Integer> sortedLines, int minuteRange) {
    Map<LocalDateTime, Integer> carCount = new HashMap<>();
    int offSet = minuteRange / 30;
    IntStream.range(0, keyList.size() - offSet)
        .forEach(
            i -> {
              if (ChronoUnit.MINUTES.between(keyList.get(i), keyList.get(i + offSet))
                  <= minuteRange) {
                carCount.put(keyList.get(i), sortedLines.get(keyList.get(i)));
                carCount.put(keyList.get(i + offSet), sortedLines.get(keyList.get(i + offSet)));
              }
            });
    return carCount;
  }
}
