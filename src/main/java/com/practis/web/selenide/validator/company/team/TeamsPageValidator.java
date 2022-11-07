package com.practis.web.selenide.validator.company.team;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;

public class TeamsPageValidator {


  /**
   * Assert elements on Teams page.
   */
  public static void assertElementsTeamsPage() {
    teamsPage().getTeamsTitle().shouldBe(visible);
    teamsPage().getTeamsTitle().shouldBe(exactText("Teams"));
    teamsPage().getTeamsTimestamp().shouldBe(visible);
    teamsPage().getTeamsTimestampRefresh().shouldBe(visible);
    teamsPage().getTeamsTimestamp().shouldBe(matchText("Updated"));

    teamsPage().getTeamSearchField().shouldBe(visible);
    teamsPage().getTeamSearchFieldIcon().shouldBe(visible);
    teamsPage().getTeamFilterButton().shouldBe(visible);
    teamsPage().getTeamsItemsCounter().shouldBe(visible);
    teamsPage().getTeamsItemsCounter().shouldBe(matchText("Items"));
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
    teamsPage().getTeamsAllMembersItem().shouldBe(visible);
    teamsPage().getTeamsAllMembersItem().shouldBe(matchText("All Members"));
    teamsPage().getTeamsAllMembersStar().shouldBe(visible);
  }

}
