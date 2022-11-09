package com.practis.web.selenide.validator.company.team;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.assignUsersModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.overdueModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.registrationStatus;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.teamMemberStatus;
import static com.practis.web.selenide.configuration.PageObjectFactory.manageTeamPage;
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

    membersTab().getNoMembersText().shouldBe(visible);
    membersTab().getNoMembersText().shouldBe(exactText("No Members Yet"));
    membersTab().getNoMembersIcon().shouldBe(visible);
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

  /**
   * Assert pending User in Members List.
   */
  public static void assertEmptyTeamMemberSection() {
    manageTeamPage().getNoTeamMembersIcon().shouldBe(visible);
    manageTeamPage().getAddMemberLabel().shouldBe(visible);
    manageTeamPage().getAddMemberLabel().shouldBe(exactText("Add Members"));
  }

  /**
   * Assert Filters in Members List.
   */
  public static void assertMembersFiltersModal() {
    registrationStatus().getRegStatusTitle().shouldBe(visible);
    registrationStatus().getRegStatusTitle().shouldBe(exactText("Registration Status"));
    registrationStatus().getActiveCheckbox().shouldBe(visible);
    registrationStatus().getActiveCheckbox().shouldBe(attribute("size", "12"));
    registrationStatus().getPendingRegCheckbox().shouldBe(visible);
    registrationStatus().getPendingRegCheckbox().shouldBe(attribute("size", "12"));
    registrationStatus().getActiveStatus().shouldBe(visible);
    registrationStatus().getActiveStatus().shouldBe(exactText("Active"));
    registrationStatus().getActiveStatus().shouldBe(attribute("color", "#212121"));
    registrationStatus().getPendingRegStatus().shouldBe(visible);
    registrationStatus().getPendingRegStatus().shouldBe(exactText("Pending"));
    registrationStatus().getPendingRegStatus().shouldBe(attribute("color", "#212121"));

    teamMemberStatus().getPractisSetStatusTitle().shouldBe(visible);
    teamMemberStatus().getPractisSetStatusTitle().shouldBe(exactText("Practis Set Status"));
    teamMemberStatus().getNotStartedCheckbox().shouldBe(visible);
    teamMemberStatus().getNotStartedCheckbox().shouldBe(attribute("size", "12"));
    teamMemberStatus().getNotStartedStatus().shouldBe(visible);
    teamMemberStatus().getNotStartedStatus().shouldBe(exactText("Not Started"));
    teamMemberStatus().getNotStartedStatus().shouldBe(attribute("color", "#212121"));
    teamMemberStatus().getInProgressCheckbox().shouldBe(visible);
    teamMemberStatus().getInProgressCheckbox().shouldBe(attribute("size", "12"));
    teamMemberStatus().getInProgressStatus().shouldBe(visible);
    teamMemberStatus().getInProgressStatus().shouldBe(exactText("In Progress"));
    teamMemberStatus().getInProgressStatus().shouldBe(attribute("color", "#212121"));
    teamMemberStatus().getCompletedCheckbox().shouldBe(visible);
    teamMemberStatus().getCompletedCheckbox().shouldBe(attribute("size", "12"));
    teamMemberStatus().getCompletedStatus().shouldBe(visible);
    teamMemberStatus().getCompletedStatus().shouldBe(exactText("Completed"));
    teamMemberStatus().getCompletedStatus().shouldBe(attribute("color", "#212121"));

    overdueModule().getOverdueTitle().shouldBe(visible);
    overdueModule().getOverdueTitle().shouldBe(exactText("Overdue"));
    overdueModule().getOverdueText().shouldBe(visible);
    overdueModule().getOverdueText().shouldBe(attribute("color", "#212121"));
    overdueModule().getOverdueText().shouldBe(exactText("Overdue"));
    overdueModule().getTeamOverdueCheckbox().shouldBe(visible);
    overdueModule().getTeamOverdueCheckbox().shouldBe(attribute("size", "12"));
    overdueModule().getOverdueIcon().shouldBe(visible);
    overdueModule().getOverdueIcon().shouldBe(attribute("width", "16"));
    overdueModule().getOverdueIcon().shouldBe(attribute("height", "16"));

    membersTab().getClearFiltersButton().shouldBe(visible);
    membersTab().getClearFiltersButton().shouldBe(disabled);
    membersTab().getClearFiltersButton().shouldBe(exactText("Clear"));
    membersTab().getClearFiltersButton().shouldBe(attribute("type", "submit"));
    membersTab().getClearFiltersButton().shouldBe(attribute("width", "122px"));
    membersTab().getClearFiltersButton().shouldBe(attribute("color", "default"));

    membersTab().getApplyFiltersButton().shouldBe(visible);
    membersTab().getApplyFiltersButton().shouldBe(enabled);
    membersTab().getApplyFiltersButton().shouldBe(exactText("Apply Filter"));
    membersTab().getApplyFiltersButton().shouldBe(attribute("type", "submit"));
    membersTab().getApplyFiltersButton().shouldBe(attribute("width", "122px"));
    membersTab().getApplyFiltersButton().shouldBe(attribute("color", "default"));

    membersTab().getSelectionCounter().shouldBe(visible);
    membersTab().getSelectionCounter().shouldBe(exactText("0 Selected"));
  }




}
