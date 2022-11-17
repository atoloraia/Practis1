package com.practis.selenide.company.navigation.teams;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.assignUserModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertDataOnTeamsPage;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertDuplicatedTeams;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertElementsEmptyTeamsPage;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertLabelCountOnTeamsPage;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertSingleActionAllMembers;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertSingleActionTeam;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertTeamsRows;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertAllMembersEmptyManageTeamScreen;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertElementsManageTeamPage;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertLabelManageTeam;
import static com.practis.web.selenide.validator.company.team.TeamPageValidator.assertEmptyTeamPage;
import static com.practis.web.selenide.validator.popup.DuplicateTeamPopUpValidator.asserDuplicateUsersPopUp;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertEmptyLabelModel;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSelectedLabel;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewTeamInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.rest.dto.company.RestTeamResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.dto.TeamWithChildren;
import com.practis.support.extension.practis.LabelExtension;
import com.practis.support.extension.practis.TeamExtension;
import com.practis.support.extension.practis.TeamExtensionWithUsersAndPractisSets;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class TeamsPageSingleActionTest {

  @BeforeEach
  void init() {
    navigationCompanies().getTeamsNavigationItem().click();
  }

  @TestRailTest(caseId = 18190)
  @DisplayName("Teams: All Members: Single Action: View Team")
  void viewTeamSingleActionAllMembers() {
    teamsPageService().clickSingleActionAllMembers();
    teamsPageService().clickViewTeamSingleAction();

    //Assert 'Team' page for All Members
    assertEmptyTeamPage("All Members");
  }

  @TestRailTest(caseId = 18191)
  @DisplayName("Teams: All Members: Single Action: Manage Team")
  void manageTeamSingleActionAllMembers() {
    teamsPageService().clickSingleActionAllMembers();
    teamsPageService().clickManageTeamSingleAction();

    //Assert 'Team' page for All Members
    assertAllMembersEmptyManageTeamScreen();
  }

  @TestRailTest(caseId = 18192)
  @DisplayName("Teams: Check elements on single action menu for the team")
  @TeamExtension(count = 1)
  void checkElementsSingleActionTeam(final List<RestTeamResponse> team) {
    Selenide.refresh();
    teamsPageService().clickSingleActionTeam(team.get(0).getName());

    //Assert 'Team' page for All Members
    assertSingleActionTeam();
  }

  @TestRailTest(caseId = 18193)
  @DisplayName("Teams: Single Action: View Team")
  @TeamExtension(count = 1)
  void viewTeamSingleAction(final List<RestTeamResponse> team) {
    Selenide.refresh();
    teamsPageService().clickSingleActionTeam(team.get(0).getName());
    teamsPageService().clickViewTeamSingleAction();

    //Assert 'Team' page for the team
    assertEmptyTeamPage(team.get(0).getName());
  }

  @TestRailTest(caseId = 18194)
  @DisplayName("Teams: Single Action: Manage Team")
  @TeamExtension(count = 1)
  void manageTeamSingleAction(final List<RestTeamResponse> team) {
    Selenide.refresh();
    teamsPageService().clickSingleActionTeam(team.get(0).getName());
    teamsPageService().clickManageTeamSingleAction();

    //Assert 'Manage Team' page
    assertElementsManageTeamPage(team.get(0).getName());
  }

  @TestRailTest(caseId = 18195)
  @DisplayName("Teams: Single Action: Assign Labels")
  @TeamExtension(count = 1)
  void assignLabelsTeamSingleAction(final List<RestTeamResponse> team) {
    Selenide.refresh();
    teamsPageService().clickSingleActionTeam(team.get(0).getName());
    teamsPageService().clickAssignLabelsSingleAction();

    //Assert 'Team' page for All Members
    assertEmptyLabelModel();
  }

  @TestRailTest(caseId = 18196)
  @DisplayName("Teams: Single Action: Assign Labels: Apply")
  @TeamExtension(count = 1)
  @LabelExtension(count = 1)
  void assignLabelsTeamSingleActionApply(final List<RestTeamResponse> team,
      final List<RestCreateLabelResponse> label) {
    Selenide.refresh();
    teamsPageService().clickSingleActionTeam(team.get(0).getName());
    teamsPageService().clickAssignLabelsSingleAction();

    //select one Label and click 'Apply' button
    labelModuleService().selectLabel(label.get(0).getName());
    assignUserModuleService().applyLabel();

    //assert Label has been added
    assertLabelCountOnTeamsPage(team.get(0).getName(), "1");
    teamsPageService().openManageTeamFromTeamsPage(team.get(0).getName());
    assertLabelManageTeam(label.get(0).getName());
  }


  @TestRailTest(caseId = 18197)
  @DisplayName("Teams: Single Action: Assign Labels: Check already assigned label")
  @TeamExtension(count = 1)
  @LabelExtension(count = 2)
  void alreadyAssignLabelsTeamSingleAction(final List<RestTeamResponse> team,
      final List<RestCreateLabelResponse> label) {
    Selenide.refresh();
    practisApi().assignLabelToTeam(team.get(0).getId(),List.of(label.get(0).getId()));

    teamsPageService().clickSingleActionTeam(team.get(0).getName());
    teamsPageService().clickAssignLabelsSingleAction();
    assertSelectedLabel(label.get(0).getName());
  }

  @TestRailTest(caseId = 18198)
  @DisplayName("Teams: Single Action: Duplicate")
  @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
  void duplicateTeamSingleAction(final TeamWithChildren teamWithChildren) {
    final var team = teamWithChildren.getTeam();
    Selenide.refresh();
    //Check number of teams in the list
    assertTeamsRows(1);

    //Duplicate the team
    teamsPageService().clickSingleActionTeam(team.getName());
    teamsPageService().clickDuplicateSingleAction();


    //Check the number of teams has been increased in the list
    assertTeamsRows(2);

    //Check that data was duplicated correctly on Teams page
    assertDuplicatedTeams(team, "1", "1", "—", "0");

  }


}