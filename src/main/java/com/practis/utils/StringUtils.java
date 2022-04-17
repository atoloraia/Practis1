package com.practis.utils;

import static java.lang.String.valueOf;
import static java.time.Instant.now;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtils {

  public static String currentDate() {
    final var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
    return formatter.format(LocalDateTime.now());
  }

  public static String timestamp() {
    return valueOf(now().getEpochSecond() + now().getNano());
  }

  public static String random() {
    return UUID.randomUUID().toString();
  }

}
