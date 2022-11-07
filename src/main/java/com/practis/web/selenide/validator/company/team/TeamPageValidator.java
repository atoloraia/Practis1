package com.practis.web.selenide.validator.company.team;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;

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


}
