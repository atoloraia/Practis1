package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.teamModule;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamService;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;

public class TeamSelectionValidator {

  /**
   * Assert Teams model on Feed - Filters modal.
   */
  public static void assertEmptyTeamModel() {
    //TODO review and divide if needed
    await().pollDelay(TWO_SECONDS).until(() -> true);
    teamModule().getSearchField().shouldBe(visible);
    teamModule().getSearchField().shouldBe(attribute("font-size", "13px"));
    teamModule().getSearchField().shouldBe(enabled);
    teamModule().getSearchField().shouldBe(attribute("type", "text"));
    teamModule().getSearchFieldIcon().shouldBe(visible);

    teamModule().getSelectedText().shouldBe(visible);
    teamModule().getSelectedText().shouldBe(exactText("No Teams selected"));
    teamModule().getSelectedAllButton().shouldBe(visible);
    teamModule().getSelectedAllButton().shouldBe(exactText("Select All"));
    teamModule().getSelectedAllButton().shouldBe(attribute("color", "#4aa9e2"));

    teamModule().getAllTeamName().shouldBe(visible);
    teamModule().getAllTeamName().shouldBe(exactText("All Members"));
  }

  /**
   * Assert no search results.
   */
  public static void assertNoTeamSearchResult() {
    await().pollDelay(FIVE_SECONDS).until(() -> true);
    teamModule().getNoSearchResultText().shouldBe(visible);
    teamModule().getNoSearchResultImage().shouldBe(visible);
    teamModule().getSelectedText().shouldBe(visible);
    teamModule().getUnSelectedAllButton().shouldBe(visible);
    teamModule().getTeamRows().shouldBe(CollectionCondition.size(0));
  }

  /**
   * Assert search results.
   */
  public static void assertTeamSearchResult(final String team) {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    teamService().findTeamCheckbox(team).shouldBe(visible);
    teamModule().getTeamRows().shouldBe(CollectionCondition.size(1));
  }

  /**
   * Assert Select All.
   */
  public static void assertSelectAllTeam() {
    teamModule().getTeamCheckbox().shouldBe(CollectionCondition.allMatch("checked",
        element -> Selenide.$(element).has(attribute("checked"))));
    teamModule().getSelectedText().shouldBe(matchText("Teams selected"));
  }

  /**
   * Assert Unselect All.
   */
  public static void assertUnSelectAllTeam() {
    teamModule().getTeamCheckbox().should(CollectionCondition.allMatch("checked",
        element -> !Selenide.$(element).has(attribute("checked"))));
    teamModule().getSelectedText().shouldBe(exactText("No Teams selected"));
  }

  /**
   * Assert the Team is selected.
   */
  public static void assertSelectedTeam(final String team) {
    teamService().findTeamCheckbox(team).shouldBe(visible);
    teamService().findSelectedTeamCheckbox(team).has(attribute("checked"));
  }

  /**
   * Assert created team.
   */
  public static void assertCreatedTeam(final String team) {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    teamModule().getTeamName().get(0).shouldBe(visible);
    teamService().findTeamCheckbox(team).shouldBe(visible);
    teamModule().getTeamRows().shouldBe(CollectionCondition.size(1));
  }

  /**
   * Assert Apply button.
   */
  public static void assertDisabledApplyButton() {
    teamModule().getApplyButton().shouldBe(disabled);
  }

}
