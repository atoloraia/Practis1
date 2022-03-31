package com.practis.configuration.selenium.locator;

import java.lang.reflect.Field;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomAjaxElementLocatorFactory extends AjaxElementLocatorFactory {

  private static final int SELECTOR_LOAD_TIMEOUT = 3;

  private final SearchContext searchContext;
  private final int timeOutInSeconds;

  /**
   * To be added.
   */
  @Autowired
  public CustomAjaxElementLocatorFactory(final SearchContext searchContext) {
    super(searchContext, SELECTOR_LOAD_TIMEOUT);
    this.searchContext = searchContext;
    this.timeOutInSeconds = SELECTOR_LOAD_TIMEOUT;
  }

  /**
   * To be added.
   */
  public CustomAjaxElementLocatorFactory(
      final SearchContext searchContext, final int timeOutInSeconds) {
    super(searchContext, timeOutInSeconds);
    this.searchContext = searchContext;
    this.timeOutInSeconds = timeOutInSeconds;
  }

  @Override
  public ElementLocator createLocator(Field field) {
    return new CustomAjaxOptionalElementLocator(
        searchContext, field, timeOutInSeconds);
  }
}
