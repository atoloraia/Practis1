package com.practis.web.selenide.validator.company.team;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.assignUsersModule;
import static com.practis.web.selenide.configuration.PageObjectFactory.membersTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;
import static com.practis.web.selenide.service.company.team.MembersTabService.searchMember;
import static org.awaitility.Awaitility.await;

import com.practis.dto.NewUserInput;
import com.practis.web.selenide.component.GridRow;
import com.practis.web.selenide.validator.user.InviteUserValidator;

public class MembersTabValidator {

  /**
   * Assert elements on Empty Members page.
   */
  public static void assertElementsEmptyMembersTab() {
    membersTab().getMembersTeamsTitle().shouldBe(visible);
    membersTab().getMembersTeamsTitle().shouldBe(exactText("Team"));
    membersTab().getMembersTeamsSubTitle().shouldBe(visible);
    teamPage().getTeamNameTitle().shouldBe(visible);
    membersTab().getMembersTeamsSubTitle().shouldBe(visible);
    teamPage().getPractisSetCounterIcon().shouldBe(visible);
    teamPage().getPractisSetCounter().shouldBe(visible);
    teamPage().getUsersCounterIcon().shouldBe(visible);
    teamPage().getUsersCounter().shouldBe(visible);
    teamPage().getTeamLeaderCounterIcon().shouldBe(visible);
    teamPage().getTeamLeaderCounter().shouldBe(visible);
    teamPage().getBackButton().shouldBe(visible);
    teamPage().getTrainingTab().shouldBe(visible);
    teamPage().getTrainingTab().shouldBe(exactText("Training"));
    teamPage().getMembersTab().shouldBe(visible);
    teamPage().getMembersTab().shouldBe(exactText("Members"));
    teamPage().getUpdateButton().shouldBe(visible);
    membersTab().getMembersManageTeamButton().shouldBe(visible);
    membersTab().getMembersManageTeamButton().shouldBe(exactText("Manage Team"));
    membersTab().getMembersManageTeamIcon().shouldBe(visible);
    teamPage().getUpdatedTimestampText().shouldBe(visible);
    teamPage().getUpdatedTimestampText().shouldBe(matchText("Updated"));

    teamPage().getSearchField().shouldBe(visible);
    teamPage().getSearchFieldIcon().shouldBe(visible);
    membersTab().getMembersFiltersIcon().shouldBe(visible);
    teamPage().getPaginationBackButton().shouldBe(visible);
    teamPage().getPaginationNextButton().shouldBe(visible);
    teamPage().getPaginationCounterText().shouldBe(visible);
    teamPage().getPaginationCounterText().shouldBe(matchText("Items"));

    membersTab().getMembersTeamMembersColumn().shouldBe(visible);
    membersTab().getMembersTeamMembersColumn().shouldBe(attribute("width", "18"));
    membersTab().getMembersTeamMembersColumn().shouldBe(exactText("Team Members"));
    membersTab().getMembersOverdueColumn().shouldBe(visible);
    membersTab().getMembersOverdueColumn().shouldBe(attribute("width", "1"));
    membersTab().getMembersOverdueColumn().shouldBe(exactText("Overdue"));
    membersTab().getMembersPractisSetStatusColumns().shouldBe(visible);
    membersTab().getMembersPractisSetStatusColumns().shouldBe(attribute("width", "30"));
    membersTab().getMembersPractisSetStatusColumns().shouldBe(exactText("Practis Sets Status\n"
        + "Not Started\n"
        + "In Progress\n"
        + "Completed"));
    membersTab().getMembersNotStartedColumn().shouldBe(visible);
    membersTab().getMembersInProgressColumn().shouldBe(visible);
    membersTab().getMembersCompletedColumn().shouldBe(visible);
    membersTab().getMembersAccuracyColumn().shouldBe(visible);
    membersTab().getMembersAccuracyColumn().shouldBe(attribute("width", "10"));
    membersTab().getMembersAccuracyColumn().shouldBe(exactText("Accuracy"));
    membersTab().getMembersTrainingTimeColumn().shouldBe(visible);
    membersTab().getMembersTrainingTimeColumn().shouldBe(attribute("width", "10"));
    membersTab().getMembersTrainingTimeColumn().shouldBe(exactText("Training Time"));
    membersTab().getMembersLastTrainingColumn().shouldBe(visible);
    membersTab().getMembersLastTrainingColumn().shouldBe(attribute("width", "10"));
    membersTab().getMembersLastTrainingColumn().shouldBe(exactText("Last Training"));
  }

  /**
   * Assert grid row with input data.
   */
  public static void assertTeamMemberRow(final NewUserInput inputData, final GridRow gridRow) {
    gridRow.get("Team Members")
        .shouldBe(matchText(inputData.getFirstName() + " " + inputData.getLastName()));
  }

  /**
   * Assert registered User in Members List.
   */
  public static void assertTeamMember(final NewUserInput input, final String team) {
    var userRow = searchMember(input.getFirstName());
    assertTeamMemberRow(input, userRow);
    //view User Profile
    userRow.click();
    InviteUserValidator.assertUser(input, team);
    assignUsersModule().getCancelButton().click();
    teamPage().getBackButton().click();
  }


  /**
   * Assert pending User in Members List.
   */
  public static void assertTeamMemberPending(final NewUserInput input, final String team) {
    var userRow = searchMember(input.getFirstName());
    assertTeamMemberRow(input, userRow);
    //view User Profile
    userRow.click();
    InviteUserValidator.assertUser(input, team);
    InviteUserValidator.assertPendingUser(input);
    assignUsersModule().getCancelButton().click();
    teamPage().getBackButton().click();
  }


}
