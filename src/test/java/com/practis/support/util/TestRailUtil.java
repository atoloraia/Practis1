package com.practis.support.util;

import static org.reflections.scanners.Scanners.MethodsAnnotated;

import com.practis.support.TestRailTest;
import java.util.List;
import java.util.stream.Collectors;
import org.reflections.Reflections;

public class TestRailUtil {

  /**
   * Retrieve case ids from all tests.
   */
  public static List<Integer> getAllCaseIds() {
    final var reflections = new Reflections("com.practis", MethodsAnnotated);
    return reflections.getMethodsAnnotatedWith(TestRailTest.class).stream()
        .map(method -> method.getAnnotation(TestRailTest.class))
        .map(TestRailTest::caseId)
        .distinct()
        .collect(Collectors.toList());
  }
}
