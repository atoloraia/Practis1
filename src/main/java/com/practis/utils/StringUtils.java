package com.practis.utils;

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

  public static String random() {
    return UUID.randomUUID().toString();
  }

}
