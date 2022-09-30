package com.practis.selenide.company.create.user.assign;

import static com.codeborne.selenide.Condition.hidden;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.assignUserModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertAllSelectedStateTeam;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertCleanSearch;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertCounter;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertEmptyTeamModelAssignModel;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertNoTeamSearchResult;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertSearchElementsOnTeamsModal;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertSelectAllButtonTeam;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertSelectedTeam;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertTeamSearchingAfter1Char;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertUnSelectedStateTeam;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertUnselectedTeam;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertOneTeamSelected;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertRequiredUserGridRow;
import static java.lang.String.format;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewUserInput;
import com.practis.rest.dto.company.RestTeamResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.TeamExtension;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class InviteAssignTeamsTest {

  private List<String> usersToRemove;
  private NewUserInput inputData;

  @BeforeEach
  void init() {
    newItemSelector().create("User");
    inputData = getNewUserInput();
    inputData.setEmail(format(inputData.getEmail(), timestamp()));
    inputData.setFirstName(format(inputData.getFirstName(), timestamp()));

    usersToRemove = new ArrayList<>();
    usersToRemove.add(inputData.getEmail());
  }

  /**
   * Invite User to the App: Assign: Teams section: Search.
   */
  @TestRailTest(caseId = 13315)
  @DisplayName("AssignTeams: Search")
  @TeamExtension(count = 2)
  void assignTeamsSearch(final List<RestTeamResponse> team) {
    Selenide.refresh();
    userService().addRow(inputData, "Admin");
    userService().assignFirstUser();

    //assert search team
    assertSearchElementsOnTeamsModal();
    //assert clean search
    assertCleanSearch(2);
    //Search should be performed after entering 1 character
    assertTeamSearchingAfter1Char(team.get(0).getName());
    //assert empty state
    teamService().searchTeam("no results");
    assertNoTeamSearchResult();
  }

  /**
   * Invite User to the App: Assign: Teams section: Select All.
   */
  @TestRailTest(caseId = 13316)
  @DisplayName("AssignTeams: Select All")
  @TeamExtension(count = 2)
  void assignTeamsSelectAll(final List<RestTeamResponse> teams) {
    Selenide.refresh();

    userService().addRow(inputData, "Admin");
    userService().assignFirstUser();
    //assert unselected state
    assertUnSelectedStateTeam();
    //select one Team
    teamService().selectTeam(teams.get(0).getName());
    //assert modal if one Team is selected
    assertSelectedTeam(teams.get(0).getName());
    assertCounter("1 Team selected");
    assertSelectAllButtonTeam();
    //select all
    teamService().selectAllTeam();
    assertAllSelectedStateTeam();
  }

  /**
   * Invite User to the App: Assign: Teams section: Cancel.
   */
  @TestRailTest(caseId = 13318)
  @DisplayName("AssignTeams: Cancel")
  @TeamExtension(count = 1)
  void assignTeamsCancel(final List<RestTeamResponse> teams) {
    Selenide.refresh();

    userService().addRow(inputData, "Admin");
    userService().assignFirstUser();
    //select one Team and click "Cancel"
    teamService().selectTeam(teams.get(0).getName());
    assignUserModuleService().cancel();
    //assert User row
    assertRequiredUserGridRow(inputData, "Admin", 0);
    inviteUsersPage().getTeam().get(0).shouldBe(hidden);
  }

  /**
   * Invite User to the App: Assign: Teams section: Apply.
   */
  @TestRailTest(caseId = 13317)
  @DisplayName("AssignTeams: Apply")
  @TeamExtension(count = 1)
  void assignTeamsApply(final List<RestTeamResponse> teams) {
    Selenide.refresh();

    userService().addRow(inputData, "Admin");
    userService().assignFirstUser();
    //select one Team and click 'Assign' button
    teamService().selectTeam(teams.get(0).getName());
    assignUserModuleService().apply();
    //assert User row
    assertRequiredUserGridRow(inputData, "Admin", 0);
    assertOneTeamSelected(0);
  }


  /**
   * Invite User to the App: Assign: Teams section: Already Assigned Teams.
   */
  @TestRailTest(caseId = 13319)
  @DisplayName("AssignTeams: Already Assigned Teams")
  @TeamExtension(count = 2)
  void assignTeamsAlreadyAssigned(final List<RestTeamResponse> teams) {
    Selenide.refresh();

    final var inputs = userService().generateUserInputs(2);
    inputs.forEach(input -> usersToRemove.add(input.getEmail()));

    userService().addRow(inputs.get(0), "Admin", teams.get(0));
    userService().addRow(inputs.get(1), "Admin");
    userService().assignAllUsers();
    //select one Team and click 'Assign' button
    assertSelectedTeam(teams.get(0).getName());
    assertUnselectedTeam(teams.get(1).getName());
  }

  /**
   * Invite User to the App: Assign: Teams section: Already Assigned Teams.
   */
  @TestRailTest(caseId = 13320)
  @DisplayName("AssignTeams: Empty state")
  void assignTeamsEmptyState() {
    Selenide.refresh();

    userService().addRow(inputData, "Admin");
    userService().assignFirstUser();
    assertEmptyTeamModelAssignModel();
  }

  @AfterEach
  void cleanup() {
    usersToRemove.forEach(email -> practisApi().revokeUser(email));
  }
}
