package com.practis.web.selenide.validator.user;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.teamModule;
import static com.practis.web.selenide.configuration.PageObjectFactory.userProfilePage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamService;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

public class UserTeamValidator {

  /**
   * Assert no search results.
   */
  public static void assertNoTeamSearchResult() {
    await().pollDelay(TWO_SECONDS).until(() -> true);
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
        element -> Selenide.$(element).has(Condition.attribute("checked"))));
    teamModule().getSelectedText().shouldBe(matchText("Teams selected"));
  }

  /**
   * Assert Unselect All.
   */
  public static void assertUnSelectAllTeam() {
    teamModule().getTeamCheckbox().should(CollectionCondition.allMatch("checked",
        element -> !Selenide.$(element).has(Condition.attribute("checked"))));
    teamModule().getSelectedText().shouldBe(exactText("No Teams selected"));
  }

  /**
   * Assert the Team is selected.
   */
  public static void assertSelectedTeam(final String team) {
    teamService().findTeamCheckbox(team).shouldBe(visible);
    teamService().findSelectedTeamCheckbox(team).has(Condition.attribute("checked"));
  }

  /**
   * Assert selected team.
   */
  public static void assertTeamUserProfile(final String team) {
    userProfilePage().getAssignButton().click();
    assertSelectedTeam(team);
  }

  /**
   * Assert created team.
   */
  public static void assertCreatedTeam(final String team) {
    teamModule().getAllMembersTeam().shouldBe(visible);
    teamService().findTeamCheckbox(team).shouldBe(visible);
    teamModule().getTeamRows().shouldBe(CollectionCondition.size(1));
  }

  /**
   * Assert Apply button.
   */
  public static void assertDisabledApplyButton() {
    teamModule().getApplyButton().shouldBe(disabled);
  }

  /**
   * Assert empty Team model.
   */
  public static void assertEmptyTeamModel() {
    teamModule().getSearchField().shouldBe(visible);
    teamModule().getSelectedText().shouldBe(visible);
    teamModule().getSelectedText().shouldBe(exactText("No Teams selected"));
    teamModule().getSelectedAllButton().shouldBe(visible);
    teamModule().getSelectedAllButton().shouldBe(exactText("Select All"));
    teamModule().getAllMembersTeam().shouldBe(visible);
    teamModule().getApplyButton().shouldBe(visible);
    teamModule().getCancelButton().shouldBe(visible);
    teamModule().getCancelButton().click();
    teamModule().getTeamRows().shouldBe(CollectionCondition.size(0));
  }



}
