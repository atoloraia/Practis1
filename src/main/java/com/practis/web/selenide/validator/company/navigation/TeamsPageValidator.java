package com.practis.web.selenide.validator.company.navigation;

import static com.codeborne.selenide.Condition.matchText;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;

import com.practis.dto.NewTeamInput;

public class TeamsPageValidator {

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




}
