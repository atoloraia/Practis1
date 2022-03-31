package com.practis.utils;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

import java.util.concurrent.Callable;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.WebElement;

@UtilityClass
public class AwaitUtils {

  /**
   * Awaits given number of seconds until callable execution is true.
   */
  public static void awaitSeconds(final int seconds, final Callable<Boolean> callable) {
    await().atMost(seconds, SECONDS)
        .until(callable);
  }

  /**
   * Awaits given number of seconds until callable execution is true.
   */
  public static void awaitSoft(final int seconds, final Callable<Boolean> callable) {
    try {
      await().atMost(seconds, SECONDS).until(callable);
    } catch (Exception e) {
      //do nothing
    }
  }

  /**
   * Awaits given number of seconds until callable execution is true.
   */
  public static WebElement awaitElementHard(final int seconds, final WebElement element) {
    try {
      awaitSeconds(seconds, () -> !element.getTagName().equals("empty"));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return element;
  }

  /**
   * Awaits given number of seconds until callable execution is true.
   */
  public static WebElement awaitElementSoft(final int seconds, final WebElement element) {
    try {
      awaitSeconds(seconds, () -> !element.getTagName().equals("empty"));
    } catch (Exception e) {
      //do nothing
    }
    return element;
  }
}
