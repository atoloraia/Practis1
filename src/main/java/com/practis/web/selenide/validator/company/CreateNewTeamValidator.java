package com.practis.web.selenide.validator.company;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamCreatePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;

import com.codeborne.selenide.Condition;

public class CreateNewTeamValidator {

  /**
   * Assert elements on Create New Team page.
   */
  public static void assertElementsEmptyCreateNewTeam() {
    teamCreatePage().getCreateNewTeamTitle().shouldBe(visible);
    teamCreatePage().getCreateNewTeamTitle().shouldBe(Condition.exactText("Create New Team"));

    teamCreatePage().getTitleField().shouldBe(visible);
    teamCreatePage().getTitleField().shouldBe(attribute("maxlength", "50"));
    teamCreatePage().getTitleField().shouldBe(attribute("placeholder", "Team Name"));

    teamCreatePage().getCancelButton().shouldBe(visible);
    teamCreatePage().getCancelButton().shouldBe(attribute("width", "126px"));
    teamCreatePage().getCancelButton().shouldBe(exactText("Cancel"));

    teamCreatePage().getCancelButton().shouldBe(visible);
    teamCreatePage().getCancelButton().shouldBe(attribute("width", "126px"));
    teamCreatePage().getCancelButton().shouldBe(exactText("Cancel"));

    teamCreatePage().getCreateButton().shouldBe(visible);
    teamCreatePage().getCreateButton().shouldBe(disabled);
    teamCreatePage().getCancelButton().shouldBe(attribute("width", "126px"));
    teamCreatePage().getCancelButton().shouldBe(visible);
    teamCreatePage().getCreateButton().shouldBe(exactText("Create"));
  }

  /**
   * Assert elements on Create New Team page: Already exists Name.
   */
  public static void assertElementsCreateNewTeamWithWarning() {
    teamCreatePage().getCreateNewTeamTitle().shouldBe(visible);
    teamCreatePage().getCreateNewTeamTitle().shouldBe(Condition.exactText("Create New Team"));

    teamCreatePage().getTitleField().shouldBe(visible);
    teamCreatePage().getTitleField().shouldBe(attribute("maxlength", "50"));
    teamCreatePage().getTitleField().shouldBe(attribute("placeholder", "Team Name"));

    teamCreatePage().getCancelButton().shouldBe(visible);
    teamCreatePage().getCancelButton().shouldBe(attribute("width", "126px"));
    teamCreatePage().getCancelButton().shouldBe(exactText("Cancel"));

    teamCreatePage().getCancelButton().shouldBe(visible);
    teamCreatePage().getCancelButton().shouldBe(attribute("width", "126px"));
    teamCreatePage().getCancelButton().shouldBe(exactText("Cancel"));

    teamCreatePage().getCreateButton().shouldBe(visible);
    teamCreatePage().getCreateButton().shouldBe(enabled);
    teamCreatePage().getCancelButton().shouldBe(attribute("width", "126px"));
    teamCreatePage().getCancelButton().shouldBe(visible);
    teamCreatePage().getCreateButton().shouldBe(exactText("Create"));
  }

  /**
   * Assert elements on Teams page.
   */
  public static void assertElementsTeamsPage() {
    teamPage().getTeamsTitle().shouldBe(visible);
    teamPage().getTeamsTitle().shouldBe(exactText("Teams"));
    teamPage().getTeamsTimestamp().shouldBe(visible);
    teamPage().getTeamsTimestampRefresh().shouldBe(visible);
    teamPage().getTeamsTimestamp().shouldBe(matchText("Updated"));
    teamPage().getBackButton().shouldBe(hidden);

    teamPage().getTeamSearchField().shouldBe(visible);
    teamPage().getTeamSearchFieldIcon().shouldBe(visible);
    teamPage().getTeamFilterButton().shouldBe(visible);
    teamPage().getTeamsItemsCounter().shouldBe(visible);
    teamPage().getTeamsItemsCounter().shouldBe(matchText("Items"));
    teamPage().getTeamsPrevButton().shouldBe(visible);
    teamPage().getTeamsNextButton().shouldBe(visible);
    teamPage().getTeamsPrevButton().shouldBe(disabled);
    teamPage().getTeamsNextButton().shouldBe(disabled);

    teamPage().getTeamsColumn().shouldBe(visible);
    teamPage().getTeamsColumn().shouldBe(exactText("Teams"));
    teamPage().getTeamsColumn().shouldBe(attribute("width", "23"));
    teamPage().getTeamMembersColumn().shouldBe(visible);
    teamPage().getTeamMembersColumn().shouldBe(exactText("Members"));
    teamPage().getTeamMembersColumn().shouldBe(attribute("width", "12"));
    teamPage().getTeamPractisSetsColumn().shouldBe(visible);
    teamPage().getTeamPractisSetsColumn().shouldBe(exactText("Practis Sets"));
    teamPage().getTeamPractisSetsColumn().shouldBe(attribute("width", "12"));
    teamPage().getTeamTeamLeadersColumn().shouldBe(visible);
    teamPage().getTeamTeamLeadersColumn().shouldBe(exactText("Team Leaders"));
    teamPage().getTeamTeamLeadersColumn().shouldBe(attribute("width", "22"));
    teamPage().getTeamsAllMembersItem().shouldBe(visible);
    teamPage().getTeamsAllMembersItem().shouldBe(matchText("All Members"));
    teamPage().getTeamsAllMembersStar().shouldBe(visible);
  }

}
