package com.practis.web.selenide.validator.company.navigation;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.CollectionCondition;
import com.practis.dto.NewTeamInput;

public class TeamsPageValidator {

  /**
   * Assert elements on Teams page.
   */
  public static void assertElementsEmptyTeamsPage() {
    teamsPage().getTeamsTitle().shouldBe(visible);
    teamsPage().getTeamsTitle().shouldBe(exactText("Teams"));
    teamsPage().getTeamsTimestamp().shouldBe(visible);
    teamsPage().getTeamsTimestampRefresh().shouldBe(visible);
    teamsPage().getTeamsTimestamp().shouldBe(matchText("Updated"));

    teamsPage().getTeamSearchField().shouldBe(visible);
    teamsPage().getTeamSearchFieldIcon().shouldBe(visible);
    teamsPage().getTeamFilterButton().shouldBe(visible);
    teamsPage().getTeamsItemsCounter().shouldBe(visible);
    teamsPage().getTeamsItemsCounter().shouldBe(matchText("1-1 of 1 Items"));
    teamsPage().getTeamsPrevButton().shouldBe(visible);
    teamsPage().getTeamsNextButton().shouldBe(visible);
    teamsPage().getTeamsPrevButton().shouldBe(disabled);
    teamsPage().getTeamsNextButton().shouldBe(disabled);

    teamsPage().getTeamsColumn().shouldBe(visible);
    teamsPage().getTeamsColumn().shouldBe(exactText("Teams"));
    teamsPage().getTeamsColumn().shouldBe(attribute("width", "23"));
    teamsPage().getTeamMembersColumn().shouldBe(visible);
    teamsPage().getTeamMembersColumn().shouldBe(exactText("Members"));
    teamsPage().getTeamMembersColumn().shouldBe(attribute("width", "12"));
    teamsPage().getTeamPractisSetsColumn().shouldBe(visible);
    teamsPage().getTeamPractisSetsColumn().shouldBe(exactText("Practis Sets"));
    teamsPage().getTeamPractisSetsColumn().shouldBe(attribute("width", "12"));
    teamsPage().getTeamTeamLeadersColumn().shouldBe(visible);
    teamsPage().getTeamTeamLeadersColumn().shouldBe(exactText("Team Leaders"));
    teamsPage().getTeamTeamLeadersColumn().shouldBe(attribute("width", "22"));
    teamsPage().getTeamsAllMembersRow().shouldBe(visible);
    teamsPage().getTeamsAllMembersRow().shouldBe(matchText("All Members"));
    teamsPage().getTeamsAllMembersStar().shouldBe(visible);
  }

  /**
   * Assert grid row with input data.
   */
  public static void assertTeamGridRow(final NewTeamInput inputData,
      final String members, final String ps, final String teamsLeader) {
    var teamRow = teamsPageService().searchTeam(inputData.getName());
    teamRow.get("Teams")
        .shouldBe(matchText(inputData.getName()));
    teamRow.get("Members").shouldBe(matchText(members));
    teamRow.get("Practis Sets").shouldBe(matchText(ps));
    teamRow.get("Team Leaders").shouldBe(matchText(teamsLeader));
  }

  /**
   * Assert Label counter.
   */
  public static void assertLabelCountOnTeamsPage(final String team,
      final String count) {
    teamsPageService().findTeamLabelCounter(team).shouldBe(visible);
    teamsPageService().findTeamLabelCounter(team).shouldBe(exactText(count));
  }

  /**
   * Assert Members counter.
   */
  public static void assertMemberCountOnTeamsPage(final NewTeamInput inputData,
      final String count) {
    teamsPageService().findMembersCounter(inputData.getName()).shouldBe(visible);
    teamsPageService().findMembersCounter(inputData.getName()).shouldBe(exactText(count));
  }

  /**
   * Assert number of Teams rows.
   */
  public static void assertTeamsRows(final Integer rows) {
    teamsPage().getTeamRow().shouldBe(CollectionCondition.size(rows));
  }

  /**
   * Search Team on grid by Team Name.
   */
  public static void assertDataOnTeamsPage(final NewTeamInput inputData, String members, String ps,
      String leader, String label) {
    teamsPageService().openTeamsPage();
    var teamRow = teamsPageService().searchTeam(inputData.getName());
    assertTeamGridRow(inputData, members, ps, leader);
    assertLabelCountOnTeamsPage(inputData.getName(), label);
    teamRow.click();
    await().pollDelay(TWO_SECONDS).until(() -> true);
  }

  /**
   * Assert duplicated Teams.
   */
  public static void assertDuplicatedTeams(final NewTeamInput inputData, String members, String ps,
      String leader, String label) {

  }

  /**
   * Assert single action for All Members.
   */
  public static void assertSingleActionAllMembers() {
    teamsPage().getViewTeamSingleAction().shouldBe(visible);
    teamsPage().getViewTeamSingleAction().shouldBe(exactText("View Team"));
    teamsPage().getManageTeamSingleAction().shouldBe(visible);
    teamsPage().getManageTeamSingleAction().shouldBe(exactText("Manage Team"));
    teamsPage().getAssignLabelsSingleAction().shouldBe(hidden);
    teamsPage().getDuplicateSingleAction().shouldBe(hidden);
    teamsPage().getDeleteSingleAction().shouldBe(hidden);
  }

  /**
   * Assert single action for the Team.
   */
  public static void assertSingleActionTeam() {
    teamsPage().getViewTeamSingleAction().shouldBe(visible);
    teamsPage().getViewTeamSingleAction().shouldBe(exactText("View Team"));
    teamsPage().getManageTeamSingleAction().shouldBe(visible);
    teamsPage().getManageTeamSingleAction().shouldBe(exactText("Manage Team"));
    teamsPage().getAssignLabelsSingleAction().shouldBe(visible);
    teamsPage().getAssignLabelsSingleAction().shouldBe(exactText("Assign Labels"));
    teamsPage().getDuplicateSingleAction().shouldBe(visible);
    teamsPage().getDuplicateSingleAction().shouldBe(exactText("Duplicate"));
    teamsPage().getDeleteSingleAction().shouldBe(visible);
    teamsPage().getDeleteSingleAction().shouldBe(exactText("Delete"));
  }

  /**
   * Assert Search should be performed after entering 1 characters.
   */
  public static void assertTeamsSearchAfter1CharTeamsPage(final String searchString) {
    final var input = searchString.charAt(searchString.length() - 1);
    teamsPage().getTeamSearchField().append(String.valueOf(input));
    teamsPage().getTeamSearchFieldCrossButton().shouldBe(visible);
    teamsPage().getTeamRow().get(0).shouldBe(visible);
    teamsPage().getTeamSearchFieldCrossButton().click();
  }

  /**
   * Assert no search results.
   */
  public static void assertNoTeamSearchResultTeamsPage() {
    await().pollDelay(FIVE_SECONDS).until(() -> true);
    teamsPage().getNoTeamsFoundIcon().shouldBe(visible);
    teamsPage().getNoTeamsFoundText().shouldBe(visible);
    teamsPage().getNoTeamsFoundText().shouldBe(exactText("No Teams Found"));
    teamsPage().getTeamsItemsCounter().shouldBe(visible);
    teamsPage().getTeamsItemsCounter().shouldBe(exactText("0 Items"));
    teamsPage().getTeamFilterButton().shouldBe(visible);
    teamsPage().getTeamFilterButton().shouldBe(disabled);
    teamsPage().getTeamsColumn().shouldBe(visible);
    teamsPage().getTeamMembersColumn().shouldBe(visible);
    teamsPage().getTeamPractisSetsColumn().shouldBe(visible);
    teamsPage().getTeamTeamLeadersColumn().shouldBe(visible);
    teamsPage().getTeamRow().shouldBe(CollectionCondition.size(0));
    teamsPage().getTeamSearchFieldCrossButton().click();
  }

  /**
   * Assert clean search on Teams page.
   */
  public static void assertCleanSearchTeamPage(int teamRows) {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    teamsPage().getTeamSearchFieldCrossButton().shouldNotBe(visible);
    teamsPage().getTeamRow().shouldHave(CollectionCondition.size(teamRows));
    teamsPage().getTeamSearchField().append(("check clean icon"));
    teamsPage().getTeamSearchFieldIcon().shouldBe(visible);
    teamsPage().getTeamRow().shouldHave(CollectionCondition.size(0));
    teamsPage().getTeamSearchFieldCrossButton().click();
    teamsPage().getTeamSearchFieldCrossButton().shouldNotBe(visible);
    teamsPage().getTeamRow().shouldHave(CollectionCondition.size(teamRows));
  }


}
