package com.practis.web.selenide.validator.company.team;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.teamModule;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;

import com.codeborne.selenide.CollectionCondition;

public class TeamPageValidator {

  /**
   * Assert user counter.
   */
  public static void assertUserCounterTeamPage(final String usersCounter) {
    teamPage().getUsersCounterIcon().shouldBe(visible);
    teamPage().getUsersCounter().shouldBe(visible);
    teamPage().getUsersCounter().shouldBe(exactText(usersCounter));
  }

  /**
   * Assert practis set counter.
   */
  public static void assertPsCounterTeamPage(final String psCounter) {
    teamPage().getPractisSetCounterIcon().shouldBe(visible);
    teamPage().getPractisSetCounter().shouldBe(visible);
    teamPage().getPractisSetCounter().shouldBe(exactText(psCounter));
  }

  /**
   * Assert Team Leader counter.
   */
  public static void assertTeamLeaderCounterTeamPage(final String teamLeaderCounter) {
    teamPage().getTeamLeaderCounterIcon().shouldBe(visible);
    teamPage().getTeamLeaderCounter().shouldBe(visible);
    teamPage().getTeamLeaderCounter().shouldBe(exactText(teamLeaderCounter));
  }

  /**
   * Assert counters.
   */
  public static void assertCountersOnTeamPage(final String psCounter, final String usersCounter,
      final String teamLeaderCounter) {
    assertUserCounterTeamPage(usersCounter);
    assertPsCounterTeamPage(psCounter);
    assertTeamLeaderCounterTeamPage(teamLeaderCounter);
  }

  /**
   * Assert Search field on Teams page.
   */
  public static void assertSearchFieldOnTeamPage() {
    teamsPage().getTeamSearchField().shouldBe(visible);
    teamsPage().getTeamSearchField().shouldBe(enabled);
    teamsPage().getTeamSearchFieldIcon().shouldBe(visible);
    teamsPage().getTeamSearchFieldCrossButton().shouldBe(hidden);
  }


  /**
   * Assert Search Results.
   */
  public static void assertSearchResultsOnTeamsPage() {
    teamsPage().getTeamSearchFieldCrossButton().shouldBe(visible);
    teamsPage().getTeamsAllMembersRow().shouldBe(visible);
    teamsPage().getTeamsAllMembersStar().shouldBe(visible);
    teamsPage().getTeamsAllMembersRow().shouldBe(matchText("All Members"));
    teamsPage().getTeamsItemsCounter().shouldBe(exactText("1-1 of 1 Items"));
    teamsPage().getTeamFilterButton().shouldBe(enabled);
    teamsPage().getTeamFilterButton().shouldBe(enabled);
    teamsPage().getTeamSearchFieldCrossButton().click();
    teamsPage().getTeamSearchFieldCrossButton().shouldBe(hidden);
  }

}
