package com.practis.web.util;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.webdriver;
import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.sleep;
import static java.util.Optional.ofNullable;

import com.codeborne.selenide.SelenideWait;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SelenidePageLoadAwait {

  private static final Long SECONDS_TO_MILLIS_MULTIPLIER = 1000L;

  public static void awaitFullPageLoad(final int timeout) {
    addAjaxInterceptor();
    ajaxComplete(timeout);
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
  @SneakyThrows
  private static void ajaxComplete(final int seconds) {
    final var startTime = currentTimeMillis();
    var timeout = seconds * SECONDS_TO_MILLIS_MULTIPLIER;
    var waitTime = 0L;
    while (waitTime < timeout) {
      final var result =  getRequestCount() <= 0;
      if (result) {
        return;
      }
      waitTime = currentTimeMillis() - startTime;
      log.info("Waiting for xhr requests. Current number: " + getRequestCount());
      sleep(500);
    }
    log.warn("Exit from ajax complete await. Wait time: {}", waitTime);
  }

  private static Long getRequestCount() {
    return ofNullable(executeJavaScript("return XMLHttpRequest.prototype.activeRequestsCount;"))
        .map(Object::toString)
        .map(Long::parseLong)
        .orElse(Long.MAX_VALUE);
  }
}