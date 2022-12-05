package com.practis.web.selenide.validator.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

public class FileValidator {

  public static void assertFileNameEqual(final File file, final String expectedName) {
    assertEquals(file.getName(), expectedName);
  }

  public static void assertFileNameContains(final File file, final String expectedContains) {
    assertTrue(file.getName().contains(expectedContains));
  }

}
