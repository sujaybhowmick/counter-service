package com.seek.assignment.service.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.seek.assignment.io.impl.MockCounterFileIO;
import com.seek.assignment.service.CounterService;
import com.seek.assignment.util.DateTimeUtils;
import java.time.LocalDateTime;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CounterServiceImplTest {
  public static String counterDataStr =
      ""
          + "2016-12-01T05:00:00 5\n"
          + "2016-12-01T05:30:00 12\n"
          + "2016-12-01T06:00:00 14\n"
          + "2016-12-01T06:30:00 15\n"
          + "2016-12-01T07:00:00 25\n"
          + "2016-12-01T07:30:00 46\n"
          + "2016-12-01T08:00:00 42\n"
          + "2016-12-01T15:00:00 9\n"
          + "2016-12-01T15:30:00 11\n"
          + "2016-12-01T23:30:00 0\n"
          + "2016-12-05T09:30:00 18\n"
          + "2016-12-05T10:30:00 15\n"
          + "2016-12-05T11:30:00 7\n"
          + "2016-12-05T12:30:00 6\n"
          + "2016-12-05T13:30:00 9\n"
          + "2016-12-05T14:30:00 11\n"
          + "2016-12-05T15:30:00 15\n"
          + "2016-12-08T18:00:00 33\n"
          + "2016-12-08T19:00:00 28\n"
          + "2016-12-08T20:00:00 25\n"
          + "2016-12-08T21:00:00 21\n"
          + "2016-12-08T22:00:00 16\n"
          + "2016-12-08T23:00:00 11\n"
          + "2016-12-09T00:00:00 4\n";

  private CounterService counterService;

  @BeforeEach
  void setUp() {
    this.counterService = new CounterServiceImpl(new MockCounterFileIO(counterDataStr));
  }

  @AfterEach
  void tearDown() {}

  @Test
  void totalCars() {
    int totalCount = this.counterService.totalCars();
    assertEquals(398, totalCount);
  }

  @Test
  void dayWiseTotal() {
    Map<String, Integer> dayWiseTotal = this.counterService.dayWiseTotal();
    assertEquals(179, dayWiseTotal.get("2016-12-01").intValue());
  }

  @Test
  void top3HalfHours() {
    Map<LocalDateTime, Integer> top3HalfHours = this.counterService.top3HalfHours();
    LocalDateTime localDateTime1 =
        DateTimeUtils.getLocalDateTimeForStringDateTimeWithPattern(
            "2016-12-01T07:30:00", "yyyy-MM-dd'T'HH:mm:ss");

    LocalDateTime localDateTime2 =
        DateTimeUtils.getLocalDateTimeForStringDateTimeWithPattern(
            "2016-12-01T08:00:00", "yyyy-MM-dd'T'HH:mm:ss");

    LocalDateTime localDateTime3 =
        DateTimeUtils.getLocalDateTimeForStringDateTimeWithPattern(
            "2016-12-01T07:00:00", "yyyy-MM-dd'T'HH:mm:ss");

    assertAll(
        "Top 3 half hour periods",
        () -> assertEquals(3, top3HalfHours.size()),
        () -> assertEquals(46, top3HalfHours.get(localDateTime1).intValue()),
        () -> assertEquals(42, top3HalfHours.get(localDateTime2).intValue()),
        () -> assertEquals(25, top3HalfHours.get(localDateTime3).intValue()));
  }

  @Test
  void leastCars() {
    Map<LocalDateTime, Integer> leastCars = this.counterService.leastCarsInOneAndHalfHoursPeriod();

    LocalDateTime localDateTime1 =
        DateTimeUtils.getLocalDateTimeForStringDateTimeWithPattern(
            "2016-12-01T05:00:00", "yyyy-MM-dd'T'HH:mm:ss");

    LocalDateTime localDateTime2 =
        DateTimeUtils.getLocalDateTimeForStringDateTimeWithPattern(
            "2016-12-01T05:30:00", "yyyy-MM-dd'T'HH:mm:ss");

    LocalDateTime localDateTime3 =
        DateTimeUtils.getLocalDateTimeForStringDateTimeWithPattern(
            "2016-12-01T06:00:00", "yyyy-MM-dd'T'HH:mm:ss");
    assertAll(
        "Least cars during consecutive 1.5 hrs period",
        () -> assertEquals(3, leastCars.size()),
        () -> assertEquals(5, leastCars.get(localDateTime1).intValue()),
        () -> assertEquals(12, leastCars.get(localDateTime2).intValue()),
        () -> assertEquals(14, leastCars.get(localDateTime3).intValue()));
  }
}
