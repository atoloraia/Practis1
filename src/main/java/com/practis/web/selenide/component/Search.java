package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.practis.web.util.SelenideXhrWaitUtil.addAjaxInterceptor;
import static com.practis.web.util.SelenideXhrWaitUtil.ajaxComplete;
import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Search {

  private final SelenideElement searchFieldElement = $("input[data-test='tableSearchInput']");

  /**
   * Put input to search field.
   */
  public void search(final String input) {
    addAjaxInterceptor();
    //reset previous search if needed
    if (nonNull(searchFieldElement.attr("value"))
        && requireNonNull(searchFieldElement.attr("value")).length() > 0) {
      searchFieldElement.setValue("");
      ajaxComplete();
    }
    searchFieldElement.setValue(input.substring(0, input.length() - 1));
    ajaxComplete();
    searchFieldElement.append(input.substring(input.length() - 1));
    ajaxComplete();
  }
}
