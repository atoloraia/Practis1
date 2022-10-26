package com.practis.web.selenide.validator.company;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.PageObjectFactory.manageTeamPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamCreatePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;

import com.codeborne.selenide.Condition;

public class MembersValidator {

  /**
   * Assert elements on Empty Members page.
   */
  public static void assertElementsMembersPage() {
    teamPage().getMembersTeamsTitle().shouldBe(visible);
    teamPage().getMembersTeamsTitle().shouldBe(exactText("Team"));
    teamPage().getMembersTeamsSubTitle().shouldBe(visible);
    teamPage().getPractisSetCounterIcon().shouldBe(visible);
    teamPage().getPractisSetCounter().shouldBe(visible);
    teamPage().getUsersCounterIcon().shouldBe(visible);
    teamPage().getUsersCounterCounter().shouldBe(visible);
    teamPage().getTeamLeaderCounterIcon().shouldBe(visible);
    teamPage().getTeamLeaderCounter().shouldBe(visible);
    teamPage().getBackButton().shouldBe(visible);
    teamPage().getTrainingTab().shouldBe(visible);
    teamPage().getTrainingTab().shouldBe(exactText("Training"));
    teamPage().getMembersTab().shouldBe(visible);
    teamPage().getMembersTab().shouldBe(exactText("Members"));
    teamPage().getUpdateButton().shouldBe(visible);
    teamPage().getMembersManageTeamButton().shouldBe(visible);
    teamPage().getMembersManageTeamButton().shouldBe(exactText("Manage Team"));
    teamPage().getMembersManageTeamIcon().shouldBe(visible);
    teamPage().getUpdatedTimestampText().shouldBe(visible);
    teamPage().getUpdatedTimestampText().shouldBe(matchText("Updated"));

    teamPage().getSearchField().shouldBe(visible);
    teamPage().getSearchFieldIcon().shouldBe(visible);
    teamPage().getMembersFiltersIcon().shouldBe(visible);
    teamPage().getPaginationBackButton().shouldBe(visible);
    teamPage().getPaginationNextButton().shouldBe(visible);
    teamPage().getPaginationCounterText().shouldBe(visible);
    teamPage().getPaginationCounterText().shouldBe(matchText("Items"));

    teamPage().getMembersTeamMembersColumn().shouldBe(visible);
    teamPage().getMembersTeamMembersColumn().shouldBe(attribute("width", "18"));
    teamPage().getMembersTeamMembersColumn().shouldBe(exactText("Team Members"));
    teamPage().getMembersOverdueColumn().shouldBe(visible);
    teamPage().getTrainingOverdueColumn().shouldBe(attribute("width", "1"));
    teamPage().getMembersOverdueColumn().shouldBe(exactText("Overdue"));
    teamPage().getMembersPractisSetStatusColumns().shouldBe(visible);
    teamPage().getMembersPractisSetStatusColumns().shouldBe(attribute("width", "30"));
    teamPage().getMembersPractisSetStatusColumns().shouldBe(exactText("Practis Sets Status\n"
        + "Not Started\n"
        + "In Progress\n"
        + "Completed"));
    teamPage().getMembersNotStartedColumn().shouldBe(visible);
    teamPage().getMembersInProgressColumn().shouldBe(visible);
    teamPage().getMembersCompletedColumn().shouldBe(visible);
    teamPage().getMembersAccuracyColumn().shouldBe(visible);
    teamPage().getMembersAccuracyColumn().shouldBe(attribute("width", "10"));
    teamPage().getMembersAccuracyColumn().shouldBe(exactText("Accuracy"));
    teamPage().getMembersTrainingTimeColumn().shouldBe(visible);
    teamPage().getMembersTrainingTimeColumn().shouldBe(attribute("width", "10"));
    teamPage().getMembersTrainingTimeColumn().shouldBe(exactText("Training Time"));
    teamPage().getMembersLastTrainingColumn().shouldBe(visible);
    teamPage().getMembersLastTrainingColumn().shouldBe(attribute("width", "10"));
    teamPage().getMembersLastTrainingColumn().shouldBe(exactText("Last Training"));

  }
}
