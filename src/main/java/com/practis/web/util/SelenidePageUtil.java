package com.practis.web.util;

import static com.codeborne.selenide.Selenide.open;

public class SelenidePageUtil {

  public static void openPage(final String url) {
    open(url);
    SelenidePageLoadAwait.addAjaxInterceptor();
  }

}
