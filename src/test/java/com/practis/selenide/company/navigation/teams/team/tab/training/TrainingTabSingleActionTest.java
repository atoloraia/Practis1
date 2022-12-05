package com.practis.selenide.company.navigation.teams.team.tab.training;

import static com.practis.web.selenide.configuration.ServiceObjectFactory.trainingTabService;
import static com.practis.web.selenide.validator.company.PractisSetValidator.assertElementsViewPractisSet;
import static com.practis.web.selenide.validator.company.team.AssignUsersAndDueDatesValidator.assertAssignUsersAndDueDatesModule;
import static com.practis.web.selenide.validator.company.team.PractisSetDetailsValidator.assertElementsPractisSetDetailsPage;
import static com.practis.web.selenide.validator.company.team.TrainingTabValidator.assertSingleActionTraining;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertDownloadedFile;

import com.codeborne.selenide.Selenide;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.dto.TeamWithChildren;
import com.practis.support.extension.practis.TeamExtensionWithUsersAndPractisSets;
import com.practis.web.selenide.configuration.ComponentObjectFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class TrainingTabSingleActionTest {

  @BeforeEach
  void init() {
    ComponentObjectFactory.navigationCompany().getTeamsNavigationItem().click();
  }

  @TestRailTest(caseId = 20892)
  @DisplayName("Team: Training Tab: Check elements on single action menu for the Practis Set")
  @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
  void viewMemberTabSingleAction(final TeamWithChildren teamWithChildren) {
    //Open 'Training Tab'
    trainingTabService().openTeamTrainingTab(teamWithChildren.getTeam().getName());
    Selenide.refresh();
    trainingTabService().clickTrainingTabSingleAction();

    //Assert Single Actions
    assertSingleActionTraining();
  }

  @TestRailTest(caseId = 20891)
  @DisplayName("Team: Training Tab: Single Action: View Progress")
  @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
  void viewProgressSingleAction(final TeamWithChildren teamWithChildren) {
    //Open 'Training Tab'
    trainingTabService().openTeamTrainingTab(teamWithChildren.getTeam().getName());
    trainingTabService().clickTrainingTabSingleAction();
    trainingTabService().clickViewProgressSingleAction();

    //Assert Single Actions: View Progress
    assertElementsPractisSetDetailsPage();
  }

  @TestRailTest(caseId = 20893)
  @DisplayName("Team: Training Tab: Single Action: Assign Users")
  @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
  void assignUsersSingleAction(final TeamWithChildren teamWithChildren) {
    //Open 'Training Tab'
    trainingTabService().openTeamTrainingTab(teamWithChildren.getTeam().getName());
    trainingTabService().clickTrainingTabSingleAction();
    trainingTabService().clickAssignUsersSingleAction();

    //Assert Single Actions: Assign Users
    assertAssignUsersAndDueDatesModule(teamWithChildren.getPractisSets().get(0).getName());
  }

  @TestRailTest(caseId = 20894)
  @DisplayName("Team: Training Tab: Single Action: Export Report")
  @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
  void exportReportSingleAction(final TeamWithChildren teamWithChildren) {
    //Open 'Training Tab'
    trainingTabService().openTeamTrainingTab(teamWithChildren.getTeam().getName());
    trainingTabService().clickTrainingTabSingleAction();
    trainingTabService().clickExportReportSingleAction();

    //Assert exported report
    assertDownloadedFile("team-practis-sets");
  }

  @TestRailTest(caseId = 20895)
  @DisplayName("Team: Training Tab: Single Action: Edit Practis Set")
  @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
  void editPractisSetSingleAction(final TeamWithChildren teamWithChildren) {
    //Open 'Training Tab'
    trainingTabService().openTeamTrainingTab(teamWithChildren.getTeam().getName());
    trainingTabService().clickTrainingTabSingleAction();
    trainingTabService().clickEditPsSingleAction();

    //Assert View Practis Set page
    assertElementsViewPractisSet();
  }



}
