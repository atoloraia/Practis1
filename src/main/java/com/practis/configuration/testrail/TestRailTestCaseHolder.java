package com.practis.configuration.testrail;

import static com.practis.configuration.testrail.TestRailService.testRail;

import java.util.HashSet;
import java.util.Set;

public class TestRailTestCaseHolder {

  private static final Set<Integer> AVAILABLE_TEST_CASES = new HashSet<>();

  public static void setTestCasesFromTestRun() {
    AVAILABLE_TEST_CASES.clear();
    AVAILABLE_TEST_CASES.addAll(testRail().getTestCases());
  }

  public static boolean canRunTestCase(final Integer value) {
    return AVAILABLE_TEST_CASES.isEmpty() || AVAILABLE_TEST_CASES.contains(value);
  }

}
