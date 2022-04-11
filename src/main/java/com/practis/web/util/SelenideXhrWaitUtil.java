package com.practis.web.util;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.sleep;
import static java.util.Optional.ofNullable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SelenideXhrWaitUtil {

  /**
   * Adds XHR request interceptor.
   */
  public static void addInterceptor() {
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
  public static void ajaxComplete() {
    long numberOfActiveXhrRequests = getCustomValue();

    while (numberOfActiveXhrRequests > 0) {
      log.info("Waiting for xhr requests. Current number: " + numberOfActiveXhrRequests);
      sleep(400);
      numberOfActiveXhrRequests = getCustomValue();
    }
  }

  private static Long getCustomValue() {
    return ofNullable(executeJavaScript("return XMLHttpRequest.prototype.activeRequestsCount;"))
        .map(Object::toString)
        .map(Long::parseLong)
        .orElse(Long.MAX_VALUE);
  }
}