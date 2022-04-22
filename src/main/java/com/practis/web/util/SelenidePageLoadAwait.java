package com.practis.web.util;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.webdriver;
import static java.util.Optional.ofNullable;

import com.codeborne.selenide.SelenideWait;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SelenidePageLoadAwait {

  public static void awaitFullPageLoad(final int timeout, final int pollInterval) {
    addAjaxInterceptor();
    ajaxComplete(timeout, pollInterval);
  }

  /**
   * Adds XHR request interceptor.
   */
  private static void addAjaxInterceptor() {
    executeJavaScript("var requestCount = 0;"
        + "    var origOpen = XMLHttpRequest.prototype.open;"
        + "    XMLHttpRequest.prototype.open = function() {"
        + "        requestCount++; "
        + "        this.addEventListener('load', function() {"
        + "            requestCount--;"
        + "            XMLHttpRequest.prototype.activeRequestsCount = requestCount;"
        + "        });"
        + "        origOpen.apply(this, arguments);"
        + "    };");
  }

  /**
   * Waits until value from 'addInterceptor' to be 0.
   */
  private static void ajaxComplete(final int timeout, final int pollInterval) {
    new SelenideWait(webdriver().object(), timeout, pollInterval).until(
        driver -> {
          log.info("Waiting for xhr requests. Current number: " + getRequestCount());
          return getRequestCount() <= 0;
        });
  }

  private static Long getRequestCount() {
    return ofNullable(executeJavaScript("return XMLHttpRequest.prototype.activeRequestsCount;"))
        .map(Object::toString)
        .map(Long::parseLong)
        .orElse(Long.MAX_VALUE);
  }
}