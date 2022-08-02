package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.scenarioModule;

public class ScenarioSelectionValidator {

  /**
   * Assert Scenarios model on Feed - Filters modal.
   */
  public static void assertScenarioModule() {
    scenarioModule().getSearchField().shouldBe(visible);
    scenarioModule().getSearchFieldIcon().shouldBe(visible);
    scenarioModule().getNoSearchResultsText().shouldBe(visible);
    scenarioModule().getNoSearchResultsText().shouldBe(exactText(("No Scenarios yet")));
    scenarioModule().getNoSearchResultsIcon().shouldBe(visible);
  }
}
