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
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import java.util.stream.IntStream;

public class TeamSelectionValidator {

  /**
   * Assert Teams model: "Feed" page: Filter.
   */
  public static void assertEmptyTeamModel() {
    //TODO review and divide if needed
    await().pollDelay(TWO_SECONDS).until(() -> true);
    assertSearchElementsOnTeamsModal();

    teamModule().getSelectedText().shouldBe(visible);
    teamModule().getSelectedText().shouldBe(exactText("No Teams selected"));
    teamModule().getSelectedAllButton().shouldBe(visible);
    teamModule().getSelectedAllButton().shouldBe(exactText("Select All"));
    teamModule().getSelectedAllButton().shouldBe(attribute("color", "#4aa9e2"));

    teamModule().getTeamName().get(0).shouldBe(visible);
    teamModule().getTeamName().get(0).shouldBe(exactText("All Members"));
  }


  /**
   * Assert Teams model: Invite User to the App: Assign .
   */
  public static void assertEmptyTeamModelAssignModel() {
    //TODO review and divide if needed
    await().pollDelay(TWO_SECONDS).until(() -> true);
    assertSearchElementsOnTeamsModal();

    teamModule().getSelectedText().shouldBe(visible);
    teamModule().getSelectedText().shouldBe(exactText("No Teams selected"));
    teamModule().getSelectedAllButton().shouldBe(visible);
    teamModule().getSelectedAllButton().shouldBe(exactText("Select All"));
    teamModule().getSelectedAllButton().shouldBe(attribute("color", "#4aa9e2"));

    teamModule().getAllTeamName().shouldBe(visible);
    teamModule().getAllTeamName().shouldBe(exactText("All Members"));
  }

  /**
   * Assert Teams model: User profile.
   */
  public static void assertSelectedAllMembersModel() {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    assertSearchElementsOnTeamsModal();

    teamModule().getSelectedText().shouldBe(visible);
    teamModule().getSelectedText().shouldBe(exactText("1 Team selected"));
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
   * Assert Search should be performed after entering 3 characters.
   */
  public static void assertStartSearchingAfter3Char(final String searchString) {
    final var initSize = teamModule().getTeamRows().size();
    IntStream.range(0, 2).forEach(idx -> {
      final var input = searchString.charAt(searchString.length() - 1 - idx);
      teamModule().getSearchField().append(String.valueOf(input));
      teamModule().getCleanSearchIcon().shouldBe(Condition.visible);
      teamModule().getTeamRows().shouldHave(CollectionCondition.size(initSize));
      assertNoTeamSearchResult();
    });
    final var input = searchString.charAt(searchString.length() - 4);
    teamModule().getSearchField().append(String.valueOf(input));
  }


  /**
   * Assert Search should be performed after entering 1 characters.
   */
  public static void assertStartSearchingAfter1Char(final String searchString) {
    final var input = searchString.charAt(searchString.length() - 1);
    teamModule().getSearchField().append(String.valueOf(input));
    teamModule().getCleanSearchIcon().shouldBe(Condition.visible);
    teamModule().getTeamRows().get(0).shouldBe(visible);
  }

  /**
   * Assert search on Teams model.
   */
  public static void assertSearchElementsOnTeamsModal() {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    teamModule().getSearchField().shouldBe(visible);
    teamModule().getSearchField().shouldBe(attribute("font-size", "13px"));
    teamModule().getSearchField().shouldBe(enabled);
    teamModule().getSearchField().shouldBe(attribute("type", "text"));
    teamModule().getSearchFieldIcon().shouldBe(visible);
    teamModule().getCleanSearchIcon().shouldNotBe(visible);
  }

  /**
   * Assert clean search on Teams model.
   */
  public static void assertCleanSearch(int teamRows) {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    teamModule().getCleanSearchIcon().shouldNotBe(visible);
    teamModule().getTeamRows().shouldHave(CollectionCondition.size(teamRows));
    teamModule().getSearchField().append("check clean icon");
    teamModule().getCleanSearchIcon().shouldBe(visible);
    teamModule().getTeamRows().shouldHave(CollectionCondition.size(0));
    teamModule().getCleanSearchIcon().click();
    teamModule().getCleanSearchIcon().shouldNotBe(visible);
    teamModule().getTeamRows().shouldHave(CollectionCondition.size(teamRows));
  }

  /**
   * Assert Select All.
   */
  public static void assertAllSelectedStateTeam() {
    teamModule().getTeamCheckbox().shouldBe(CollectionCondition.allMatch("checked",
        element -> Selenide.$(element).has(attribute("checked"))));
    teamModule().getSelectedText().shouldBe(matchText("Teams selected"));
    assertUnSelectAllButtonTeam();
  }

  /**
   * Assert Unselect All.
   */
  public static void assertUnSelectedStateTeam() {
    teamModule().getTeamCheckbox().should(CollectionCondition.allMatch("checked",
        element -> !Selenide.$(element).has(attribute("checked"))));
    teamModule().getSelectedText().shouldBe(exactText("No Teams selected"));
    assertSelectAllButtonTeam();
  }

  /**
   * Assert Select All button.
   */
  public static void assertSelectAllButtonTeam() {
    teamModule().getSelectedAllButton().shouldBe(exactText("Select All"));
    teamModule().getSelectedAllButton().shouldBe(attribute("color", "#4aa9e2"));
  }

  /**
   * Assert Unselect All button.
   */
  public static void assertUnSelectAllButtonTeam() {
    teamModule().getUnSelectedAllButton().shouldBe(exactText("Unselect All"));
    teamModule().getUnSelectedAllButton().shouldBe(attribute("color", "#4aa9e2"));
  }

  /**
   * Assert the Team is selected.
   */
  public static void assertSelectedTeam(final String team) {
    teamService().findTeamCheckbox(team).shouldBe(visible);
    teamService().findSelectedTeamCheckbox(team).has(attribute("checked"));
  }

  /**
   * Assert the Team is selected.
   */
  public static void assertCounter(String counter) {
    teamModule().getSelectedText().shouldBe(visible);
    teamModule().getSelectedText().shouldBe(matchText(counter));
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
