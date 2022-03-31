package com.practis.configuration.selenium.locator;

import static com.practis.utils.AwaitUtils.awaitSeconds;
import static java.util.Objects.nonNull;

import com.practis.configuration.selenium.element.EmptyElement;
import java.lang.reflect.Field;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class CustomAjaxOptionalElementLocator implements ElementLocator {

  private final int timeout;
  private final SearchContext context;
  private final By by;

  /**
   * To be added.
   */
  public CustomAjaxOptionalElementLocator(
      final SearchContext context, final Field field, final int timeout) {
    this.context = context;
    this.timeout = timeout;
    this.by = new Annotations(field).buildBy();
  }

  /**
   * To be added.
   */
  @Override
  public WebElement findElement() {
    try {
      awaitSeconds(timeout, () -> nonNull(findElementOrNull()));
      return findElementOrNull();
    } catch (Exception e) {
      return new EmptyElement();
    }
  }

  /**
   * To be added.
   */
  @Override
  public List<WebElement> findElements() {
    try {
      awaitSeconds(timeout, () -> nonNull(findElementsOrNull()));
      return findElementsOrNull();
    } catch (Exception e) {
      return List.of();
    }
  }

  private WebElement findElementOrNull() {
    try {
      return context.findElement(by);
    } catch (Exception e) {
      return null;
    }
  }

  private List<WebElement> findElementsOrNull() {
    try {
      return context.findElements(by);
    } catch (Exception e) {
      return null;
    }
  }
}
