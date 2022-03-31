package com.practis.utils;

import static java.util.Optional.of;

import lombok.experimental.UtilityClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

@UtilityClass
public class SeleniumUtils {

  /**
   * To be added.
   */
  public static void setDivText(
      final WebDriver driver, final WebElement element, final String input) {
    of(driver)
        .filter(it -> it instanceof JavascriptExecutor)
        .map(it -> (JavascriptExecutor) it)
        .stream()
        .peek(executor ->
            executor.executeScript("arguments[0].innerHTML = arguments[1]", element, input))
        .forEach(executor -> executor.executeScript(
            "arguments[0].dispatchEvent(new InputEvent('input', {data: '', bubbles: true}))",
            element));
  }

  /**
   * To be added.
   */
  public static void setAttribute(
      final WebDriver driver, final WebElement element,
      final String attribute, final String value) {
    of(driver)
        .filter(it -> it instanceof JavascriptExecutor)
        .map(it -> (JavascriptExecutor) it)
        .stream()
        .peek(executor ->
            executor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])",
                element, attribute, value))
        .forEach(executor -> executor.executeScript(
            "arguments[0].dispatchEvent(new InputEvent('input', {data: '', bubbles: true}))",
            element));

  }

  /**
   * To be added.
   */
  public static void jsClick(final WebDriver driver, final WebElement element) {
    of(driver)
        .filter(it -> it instanceof JavascriptExecutor)
        .map(it -> (JavascriptExecutor) it)
        .ifPresent(executor -> executor.executeScript("arguments[0].click()", element));
  }

  /**
   * To be added.
   */
  public static void executeJs(final WebDriver driver, final String jsString) {
    of(driver)
        .filter(it -> it instanceof JavascriptExecutor)
        .map(it -> (JavascriptExecutor) it)
        .ifPresent(executor -> executor.executeScript(jsString));
  }

  /**
   * To be added.
   */
  public static void executeJs(final WebDriver driver, final String jsString, Object... args) {
    of(driver)
        .filter(it -> it instanceof JavascriptExecutor)
        .map(it -> (JavascriptExecutor) it)
        .ifPresent(executor -> executor.executeScript(jsString, args));
  }

  /**
   * To be added.
   */
  public static void doubleClick(final WebDriver driver, final WebElement element) {
    final var actions = new Actions(driver);
    actions.doubleClick(element).perform();
  }
}
