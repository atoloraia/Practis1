package com.practis.web.selenide.validator.company.team;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.trainingTab;

public class TeamPageValidator {

  /**
   * Assert user counter.
   */
  public static void assertEmptyTeamPage(final String teamName) {
    teamPage().getTeamTitle().shouldBe(visible);
    teamPage().getTeamTitle().shouldBe(exactText("Team"));
    teamPage().getTeamNameTitle().shouldBe(visible);
    teamPage().getTeamNameTitle().shouldBe(exactText(teamName));

    assertUserCounterTeamPage("0");
    assertPsCounterTeamPage("0");
    assertTeamLeaderCounterTeamPage("0");

    teamPage().getBackButton().shouldBe(visible);

    teamPage().getTrainingTab().shouldBe(visible);
    teamPage().getTrainingTab().shouldBe(exactText("Training"));
    teamPage().getMembersTab().shouldBe(visible);
    teamPage().getMembersTab().shouldBe(exactText("Members"));
    teamPage().getUpdateButton().shouldBe(visible);
    teamPage().getUpdatedTimestampText().shouldBe(visible);
    teamPage().getUpdatedTimestampText().shouldBe(matchText("Updated"));

    teamPage().getSearchField().shouldBe(visible);
    teamPage().getSearchFieldIcon().shouldBe(visible);
    teamPage().getFiltersButton().shouldBe(visible);
    teamPage().getPaginationBackButton().shouldBe(visible);
    teamPage().getPaginationNextButton().shouldBe(visible);
    teamPage().getPaginationCounterText().shouldBe(hidden);

    trainingTab().getTrainingPractisSetColumn().shouldBe(visible);
    trainingTab().getTrainingPractisSetColumn().shouldBe(attribute("width", "16.5"));
    trainingTab().getTrainingPractisSetColumn().shouldBe(exactText("Practis Sets"));
    trainingTab().getTrainingOverdueColumn().shouldBe(visible);
    trainingTab().getTrainingOverdueColumn().shouldBe(attribute("width", "8"));
    trainingTab().getTrainingOverdueColumn().shouldBe(exactText("Overdue"));
    trainingTab().getTrainingTeamMemberStatusColumns().shouldBe(visible);
    trainingTab().getTrainingTeamMemberStatusColumns().shouldBe(attribute("width", "25"));
    trainingTab().getTrainingTeamMemberStatusColumns().shouldBe(exactText("Team Member Status\n"
        + "Not Started\n"
        + "In Progress\n"
        + "Completed"));
    trainingTab().getTrainingNotStartedColumn().shouldBe(visible);
    trainingTab().getTrainingInProgressColumn().shouldBe(visible);
    trainingTab().getTrainingCompletedColumn().shouldBe(visible);
    trainingTab().getTrainingLastTrainingColumn().shouldBe(visible);
    trainingTab().getTrainingLastTrainingColumn().shouldBe(attribute("width", "10.3"));
    trainingTab().getTrainingLastTrainingColumn().shouldBe(exactText("Last Training"));
  }

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
