package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;
import static com.practis.web.util.SelenidePageLoadAwait.awaitAjaxComplete;

import com.codeborne.selenide.SelenideElement;
import com.practis.web.util.SelenidePageLoadAwait;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Search {

  private final SelenideElement searchFieldElement = $("input[data-test*='-search-input']");

  /**
   * Put input to search field.
   */
  public void search(final String input) {
    searchFieldElement.setValue(input.substring(0, input.length() - 1));
    searchFieldElement.append(input.substring(input.length() - 1));
  }

}
