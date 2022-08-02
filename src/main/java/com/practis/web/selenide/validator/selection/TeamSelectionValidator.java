package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.teamModule;

public class TeamSelectionValidator {

  /**
   * Assert Teams model on Feed - Filters modal.
   */
  public static void assertTeamModule() {
    teamModule().getSearchField().shouldBe(visible);
    teamModule().getSearchFieldIcon().shouldBe(visible);
    teamModule().getTeamName().shouldBe(visible);
    teamModule().getTeamName().shouldBe(exactText("All Members"));
    teamModule().getSelectedText().shouldBe(visible);
    teamModule().getSelectedText().shouldBe(exactText("No Teams selected"));
    teamModule().getSelectedAllButton().shouldBe(visible);
    teamModule().getSelectedAllButton().shouldBe(exactText("Select All"));
  }
}
