package com.practis.utils;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

import com.codeborne.selenide.Selenide;
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

  /**
   * Awaits given number of seconds until callable execution is true.
   */
  @SneakyThrows
  public static SelenideElement awaitElementExists(
      final int seconds, final Callable<SelenideElement> callable) {
    log.info("start");
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
  public static GridRow awaitGridRowExists(
      final int seconds, final Callable<GridRow> callable) {
    try {
      await().atMost(seconds, SECONDS)
          .until(() -> callable.call().getRowElement().exists());
    } catch (Exception e) {
      log.warn("Element not exists after {} seconds", seconds);
    }
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
