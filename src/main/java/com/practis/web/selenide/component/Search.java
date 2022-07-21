package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.ONE_SECOND;
import static org.awaitility.Duration.TWO_HUNDRED_MILLISECONDS;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.awaitility.Duration;

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
