package com.practis.selenide.company.navigation.teams.team.tab.members;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.removeFromTeamPopup;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.membersTabService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.navigationCompanyService;
import static com.practis.web.selenide.service.company.UserService.searchPendingUser;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertBulkActionMember;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertElementsEmptyMembersTab;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertSingleActionMember;
import static com.practis.web.selenide.validator.popup.WarningRemoveFromTeamPopUpValidator.assertWarningDeletePopUp;
import static com.practis.web.selenide.validator.selection.NudgeUserValidator.assertEmptyNudgeUserPopUp;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertDownloadedFile;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertUserProfile;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Selenide;
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
public class MembersTabBulkActionTest {

  @BeforeEach
  void init() {
    ComponentObjectFactory.navigationCompany().getTeamsNavigationItem().click();
  }

  @TestRailTest(caseId = 20890)
  @DisplayName("Team: Members Tab: Check elements on bulk action menu for the member")
  @TeamExtensionWithUsers(users = 2)
  void viewMemberTabSingleAction(final TeamWithChildren teamWithChildren) {
    //Open 'Members' page
    membersTabService().openTeamMembersTab(teamWithChildren.getTeam().getName());
    Selenide.refresh();
    membersTabService().clickSelectAllButton();
    membersTabService().clickMembersTabBulkAction();

    //Assert Single Actions
    assertBulkActionMember();
  }
}
