package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.scenarioModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.teamModule;
import static com.practis.web.selenide.configuration.PageObjectFactory.feedPage;

public class TeamSelectionValidator {

  /**
   * Assert Teams model on Feed - Filters modal.
   */
  public static void assertTeamModule() {
    teamModule().getSearchField().shouldBe(visible);
    teamModule().getSearchField().shouldBe(attribute("font-size", "13px"));
    teamModule().getSearchField().shouldBe(enabled);
    teamModule().getSearchField().shouldBe(attribute("type", "text"));
    teamModule().getSearchFieldIcon().shouldBe(visible);

    teamModule().getTeamCheckbox().get(0).shouldBe(attribute("type", "checkbox"));
    teamModule().getTeamCheckbox().get(0).shouldBe(attribute("size", "20"));
    teamModule().getTeamName().get(0).shouldBe(visible);
    teamModule().getTeamName().get(0).shouldBe(exactText("All Members"));

    teamModule().getSelectedText().shouldBe(visible);
    teamModule().getSelectedText().shouldBe(exactText("No Teams selected"));
    teamModule().getSelectedAllButton().shouldBe(visible);
    teamModule().getSelectedAllButton().shouldBe(exactText("Select All"));
    teamModule().getSelectedAllButton().shouldBe(attribute("color", "#4aa9e2"));

    teamModule().getTeamName().get(0).click();
    teamModule().getSelectedText().shouldBe(exactText("1 Team selected"));
    feedPage().getFiltersClearButton().shouldBe(enabled);

    feedPage().getFiltersClearButton().click();
    teamModule().getSelectedAllButton().click();
    teamModule().getUnSelectedAllButton().shouldBe(visible);

    teamModule().getUnSelectedAllButton().shouldBe(attribute("color", "#4aa9e2"));
    teamModule().getUnSelectedAllButton().click();
    teamModule().getSelectedText().shouldBe(visible);
    teamModule().getSelectedText().shouldBe(exactText("No Teams selected"));
  }
}
