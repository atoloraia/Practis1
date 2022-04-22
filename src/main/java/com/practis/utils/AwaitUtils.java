package com.practis.utils;

import static java.lang.Boolean.TRUE;
import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.sleep;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

import com.codeborne.selenide.SelenideElement;
import com.practis.web.selenide.component.GridRow;
import java.util.concurrent.Callable;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;

@UtilityClass
@Slf4j
public class AwaitUtils {

  private static final Long SECONDS_TO_MILLIS_MULTIPLIER = 1000L;

  /**
   * Awaits given number of seconds until callable execution is true.
   */
  @SneakyThrows
  public static SelenideElement awaitElementExists(
      final int seconds, final Callable<SelenideElement> callable) {
    try {
      await().atMost(seconds, SECONDS)
          .until(() -> callable.call().exists());
    } catch (Exception e) {
      log.warn("Element not exists after {} seconds", seconds);
    }
    return callable.call();
  }

  /**
   * Awaits given number of seconds until callable execution is true.
   */
  @SneakyThrows
  public static SelenideElement awaitElementVisible(
      final int seconds, final Callable<SelenideElement> callable) {
    try {
      await().atMost(seconds, SECONDS)
          .until(() -> callable.call().isDisplayed());
    } catch (Exception e) {
      log.warn("Element not visible after {} seconds", seconds);
    }
    return callable.call();
  }

  /**
   * Awaits given number of seconds until callable execution is true.
   */
  @SneakyThrows
  public static GridRow awaitGridRowExists(
      final int seconds, final Callable<GridRow> callable) {
    final var startTime = currentTimeMillis();
    var timeout = seconds * SECONDS_TO_MILLIS_MULTIPLIER;
    var waitTime = 0L;
    while (waitTime < timeout) {
      final var gridRow = callable.call();
      if (gridRow.getRowElement().exists()) {
        return gridRow;
      }
      waitTime = currentTimeMillis() - startTime;
      log.info("Await grid row. Wait time: {}", waitTime);
      sleep(500);
    }
    log.warn("Await grid row completed with no result. Wait time: {}", waitTime);
    return callable.call();
  }

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
  @SneakyThrows
  public static void awaitSoft(final int seconds, final Callable<Boolean> callable) {
    final var startTime = currentTimeMillis();
    var timeout = seconds * SECONDS_TO_MILLIS_MULTIPLIER;
    var waitTime = 0L;
    while (waitTime < timeout) {
      if (TRUE.equals(callable.call())) {
        return;
      }
      waitTime = currentTimeMillis() - startTime;
      log.info("Soft await. Wait time: {}", waitTime);
      sleep(500);
    }
    log.warn("Soft await completed with no result. Wait time: {}", waitTime);
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
