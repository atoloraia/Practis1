package com.practis.selenide.company.user.pending.assign;

import static com.codeborne.selenide.Condition.hidden;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.userProfilePage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.assignUserModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertSelectedPractisSet;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertUnSelectedAllStatePs;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertAssignEmptyTeam;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertCleanTeamSearch;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertElementsOnTeamSection;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertNoTeamSearchResult;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertSearchElementsOnTeamsModal;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertSelectAllTeamButton;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertSelectedAllStateTeam;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertSelectedTeam;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertTeamCounter;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertTeamSearchAfter1Char;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertUnSelectedAllStateTeam;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertOneTeamSelected;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertUserData;
import static com.practis.web.util.SelenidePageUtil.openPage;

import com.practis.dto.NewUserInput;
import com.practis.rest.dto.company.RestTeamResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.PendingUserExtension;
import com.practis.support.extension.practis.TeamExtension;
import java.util.List;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class UserProfilePendingAssignTeamsTest {

  /**
   * User Profile: Pending: Assign: Check WEB elements on Team section.
   */
  @TestRailTest(caseId = 14995)
  @DisplayName("User Profile: Pending: Assign Team: Check WEB elements")
  @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
  @TeamExtension(count = 1)
  void checkElementsOnTeamSection(final List<NewUserInput> users) {

    openPage(webApplicationConfig().getUrl() + "/user/performance/" + users.get(0).getId());
    userProfilePage().getAssignButton().click();

    //TODO will be Passed after fixing DEV-10454
    assertElementsOnTeamSection();
  }

  /**
   * User Profile: Pending: Assign: Teams section: Search.
   */
  @TestRailTest(caseId = 14996)
  @DisplayName("User Profile: Pending: Assign Team: Search")
  @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
  @TeamExtension(count = 2)
  void assignTeamsSearch(final List<NewUserInput> users, final List<RestTeamResponse> team) {

    openPage(webApplicationConfig().getUrl() + "/user/performance/" + users.get(0).getId());
    userProfilePage().getAssignButton().click();

    //assert search team
    assertSearchElementsOnTeamsModal();
    //assert clean search
    assertCleanTeamSearch(2);
    //Search should be performed after entering 1 character
    assertTeamSearchAfter1Char(team.get(0).getName());
    //assert empty state
    teamModuleService().searchTeam("no results");
    assertNoTeamSearchResult();
  }

  /**
   * User Profile: Pending: Assign: Teams section: Select All.
   */
  @TestRailTest(caseId = 14997)
  @DisplayName("User Profile: Pending: Assign Team: Select All")
  @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
  @TeamExtension(count = 2)
  void assignTeamsSelectAll(final List<NewUserInput> users, final List<RestTeamResponse> teams) {

    openPage(webApplicationConfig().getUrl() + "/user/performance/" + users.get(0).getId());
    userProfilePage().getAssignButton().click();
    //TODO will be Passed after fixing DEV-10454

    //assert unselected state
    assertUnSelectedAllStateTeam();
    //select one Team
    teamModuleService().selectTeam(teams.get(0).getName());
    //assert modal if one Team is selected
    assertSelectedTeam(teams.get(0).getName());
    assertTeamCounter("1 Team selected");
    assertSelectAllTeamButton();
    //select all
    teamModuleService().selectAllTeam();
    assertSelectedAllStateTeam();
  }

  /**
   * User Profile: Pending: Assign: Teams section: Cancel.
   */
  @TestRailTest(caseId = 14999)
  @DisplayName("User Profile: Pending: Assign Team: Cancel")
  @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
  @TeamExtension(count = 1)
  void assignTeamsCancel(final List<NewUserInput> users, final List<RestTeamResponse> teams) {

    openPage(webApplicationConfig().getUrl() + "/user/performance/" + users.get(0).getId());
    userProfilePage().getAssignButton().click();

    //select one Team and click "Cancel"
    teamModuleService().selectTeam(teams.get(0).getName());
    assignUserModuleService().cancel();
    //assert User row
    userProfilePage().getAssignButton().click();
    assertUnSelectedAllStateTeam();
  }

  /**
   * User Profile: Pending: Assign: Teams section: Apply.
   */
  @TestRailTest(caseId = 14998)
  @DisplayName("User Profile: Pending: Assign Team: Apply")
  @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
  @TeamExtension(count = 1)
  void assignTeamsApply(final List<NewUserInput> users, final List<RestTeamResponse> teams) {
    openPage(webApplicationConfig().getUrl() + "/user/performance/" + users.get(0).getId());
    userProfilePage().getAssignButton().click();

    //select one Team and click 'Assign' button
    teamModuleService().selectTeam(teams.get(0).getName());
    assignUserModuleService().apply();

    //TODO Should be updated after fixing DEV-10320
    //assert User row
    assertUserData(users.get(0));
    userProfilePage().getAssignButton().click();
    assertSelectedTeam(teams.get(0).getName());
  }

  /**
   * User Profile: Pending: Assign: Teams section: Empty State.
   */
  @TestRailTest(caseId = 15002)
  @DisplayName("User Profile: Pending: Assign Team: Empty state")
  @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
  void assignTeamsEmptyState(final List<NewUserInput> users) {

    openPage(webApplicationConfig().getUrl() + "/user/performance/" + users.get(0).getId());
    userProfilePage().getAssignButton().click();

    //TODO will be Passed after fixing DEV-10454
    assertAssignEmptyTeam();
  }

}
