package com.practis.configuration.junit;

import static java.lang.System.getenv;
import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toSet;

import java.util.Set;
import java.util.stream.Stream;

public class LegalTestCaseHolder {

  private static final Set<Integer> AVAILABLE_TEST_CASES;

  static {
    final var availableCases = ofNullable(getenv("AVAILABLE_TEST_CASES")).orElse("");
    AVAILABLE_TEST_CASES = Stream.of(availableCases.split(","))
        .filter(string -> string.length() > 0)
        .map(Integer::parseInt)
        .collect(toSet());
  }

  public static boolean isCaseFilterDisabled() {
    return isNull(getenv("AUTOMATION_RUN_CI"));
  }

  public static boolean canRunTestCase(final Integer value) {
    return AVAILABLE_TEST_CASES.contains(value);
  }

}
