package com.practis.utils;

import java.util.function.Supplier;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionUtils {

  public static Supplier<RuntimeException> exception(final String message) {
    return () -> new RuntimeException(message);
  }

}
