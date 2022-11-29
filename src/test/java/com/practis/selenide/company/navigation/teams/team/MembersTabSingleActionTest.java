package com.practis.selenide.company.navigation.teams.team;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.keepTrackPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.membersTabService;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertElementsEmptyMembersTab;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertSingleActionMember;
import static com.practis.web.selenide.validator.selection.NudgeUserValidator.assertEmptyNudgeUserPopUp;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertUserProfile;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewTeamInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.dto.TeamWithChildren;
import com.practis.support.extension.practis.TeamExtension;
import com.practis.support.extension.practis.TeamExtensionWithUsers;
import com.practis.support.extension.practis.TeamExtensionWithUsersAndPractisSets;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class MembersTabSingleActionTest {

  @BeforeEach
  void init() {
    navigationCompanies().getTeamsNavigationItem().click();
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
  void assignNudgeUsersSingleAction(final TeamWithChildren teamWithChildren) {
    //Open 'Members' page
    membersTabService().openTeamMembersTab(teamWithChildren.getTeam().getName());
    Selenide.refresh();
    membersTabService().clickMembersTabSingleAction();
    membersTabService().clickNudgeUserSingleAction();

    //Assert Training Page
    assertEmptyNudgeUserPopUp();
  }
}
