package com.practis.selenide.company.navigation.teams.team;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.removeFromTeamPopup;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.membersTabService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.navigationCompanyService;
import static com.practis.web.selenide.service.company.UserService.searchPendingUser;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertElementsEmptyMembersTab;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertSingleActionMember;
import static com.practis.web.selenide.validator.popup.WarningRemoveFromTeamPopUpValidator.assertWarningDeletePopUp;
import static com.practis.web.selenide.validator.selection.NudgeUserValidator.assertEmptyNudgeUserPopUp;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertDownloadedFile;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUser;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertUserData;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertUserProfile;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewUserInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.dto.TeamWithChildren;
import com.practis.support.extension.practis.TeamExtensionWithUsers;
import com.practis.support.extension.practis.TeamExtensionWithUsersAndPractisSets;
import com.practis.web.selenide.configuration.ComponentObjectFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class MembersTabSingleActionTest {

  @BeforeEach
  void init() {
    ComponentObjectFactory.navigationCompany().getTeamsNavigationItem().click();
  }

  @TestRailTest(caseId = 20882)
  @DisplayName("Teams: Members Tab: Check elements on single action menu for the member")
  @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
  void viewMemberTabSingleAction(final TeamWithChildren teamWithChildren) {
    //Open 'Members' page
    Selenide.refresh();
    membersTabService().openTeamMembersTab(teamWithChildren.getTeam().getName());
    Selenide.refresh();
    membersTabService().clickMembersTabSingleAction();

    //Assert Single Actions
    assertSingleActionMember();
  }

  @TestRailTest(caseId = 20883)
  @DisplayName("Team: Members Tab: Single Action: View Profile")
  @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
  void viewProfileSingleAction(final TeamWithChildren teamWithChildren) {
    //Open 'Members' page
    Selenide.refresh();
    membersTabService().openTeamMembersTab(teamWithChildren.getTeam().getName());
    Selenide.refresh();
    membersTabService().clickMembersTabSingleAction();
    membersTabService().clickViewProfileSingleAction();

    //Assert User Profile Page
    assertUserProfile();
  }

  @TestRailTest(caseId = 20884)
  @DisplayName("Team: Members Tab: Single Action: Assign Practis Sets")
  @TeamExtensionWithUsers(users = 1)
  void assignPractisSetsSingleAction(final TeamWithChildren teamWithChildren) {

  }

  @TestRailTest(caseId = 20887)
  @DisplayName("Team: Members Tab: Single Action: Nudge User")
  @TeamExtensionWithUsers(users = 1)
  void nudgeUsersSingleAction(final TeamWithChildren teamWithChildren) {
    //Open 'Members' page
    membersTabService().openTeamMembersTab(teamWithChildren.getTeam().getName());
    Selenide.refresh();
    membersTabService().clickMembersTabSingleAction();
    membersTabService().clickNudgeUserSingleAction();

    //Assert Nudge Popup
    assertEmptyNudgeUserPopUp();
  }

  @TestRailTest(caseId = 20888)
  @DisplayName("Team: Members Tab: Single Action: Export Report")
  @TeamExtensionWithUsers(users = 1)
  void exportReportSingleAction(final TeamWithChildren teamWithChildren) {
    //Open 'Members' page
    membersTabService().openTeamMembersTab(teamWithChildren.getTeam().getName());
    Selenide.refresh();
    membersTabService().clickMembersTabSingleAction();
    membersTabService().clickExportReportSingleAction();

    //Assert exported report
    assertDownloadedFile("team-members.csv");
  }

  @TestRailTest(caseId = 20889)
  @DisplayName("Team: Members Tab: Single Action: Remove From Team")
  @TeamExtensionWithUsers(users = 1)
  void removeFromTeamSingleAction(final TeamWithChildren teamWithChildren) {
    //Open 'Members' page
    membersTabService().openTeamMembersTab(teamWithChildren.getTeam().getName());
    Selenide.refresh();
    membersTabService().clickMembersTabSingleAction();
    membersTabService().clickRemoveFromTeamSingleAction();

    //Assert warning pop-up
    assertWarningDeletePopUp();

    //Click "Proceed" button
    removeFromTeamPopup().getProceedButton().click();
    await().pollDelay(TWO_SECONDS).until(() -> true);

    //assert empty Member Tab
    assertElementsEmptyMembersTab();

    //assert team on User Profile
    navigationCompanyService().openUsersPage();
    searchPendingUser(teamWithChildren.getUsers().get(0));
    //TODO assert team and user
  }
}
