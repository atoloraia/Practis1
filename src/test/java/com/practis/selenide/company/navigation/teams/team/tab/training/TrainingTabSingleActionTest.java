package com.practis.selenide.company.navigation.teams.team.tab.training;

import static com.practis.web.selenide.configuration.PageObjectFactory.manageTeamPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.membersTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.membersTabService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.trainingTabService;
import static com.practis.web.selenide.validator.company.CreatePractisSetValidator.assertElementsViewPractisSet;
import static com.practis.web.selenide.validator.company.team.AssignPsAndDueDatesValidator.assertCleanSearchAssignUsersModule;
import static com.practis.web.selenide.validator.company.team.AssignUsersAndDueDatesValidator.assertAssignUsersAndDueDatesModule;
import static com.practis.web.selenide.validator.company.team.AssignUsersAndDueDatesValidator.assertEmptyAssignUsersAndDueDatesModule;
import static com.practis.web.selenide.validator.company.team.AssignUsersAndDueDatesValidator.assertNoSearchResultAssignUsersAndDueDatesModule;
import static com.practis.web.selenide.validator.company.team.AssignUsersAndDueDatesValidator.assertSearchFieldAssignUsersAndDueDatesModule;
import static com.practis.web.selenide.validator.company.team.PractisSetDetailsValidator.assertElementsPractisSetDetailsPage;
import static com.practis.web.selenide.validator.company.team.TrainingTabValidator.assertSingleActionTraining;
import static com.practis.web.selenide.validator.selection.AssignPractisSetsAndDueDatesValidator.assertSearchAfter1CharAssignUsersModule;
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
    @DisplayName("Team: Training Tab: Single Action: Check Elements")
    @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
    void viewMemberTabSingleAction(final TeamWithChildren teamWithChildren) {
        // Open 'Training Tab'
        trainingTabService().openTeamTrainingTab(teamWithChildren.getTeam().getName());
        Selenide.refresh();
        trainingTabService().clickTrainingTabSingleAction();

        // Assert Single Actions
        assertSingleActionTraining();
    }

    @TestRailTest(caseId = 20891)
    @DisplayName("Team: Training Tab: Single Action: View Progress")
    @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
    void viewProgressSingleAction(final TeamWithChildren teamWithChildren) {
        // Open 'Training Tab'
        trainingTabService().openTeamTrainingTab(teamWithChildren.getTeam().getName());
        trainingTabService().clickTrainingTabSingleAction();
        trainingTabService().clickViewProgressSingleAction();

        // Assert Single Actions: View Progress
        assertElementsPractisSetDetailsPage();
    }

    @TestRailTest(caseId = 20893)
    @DisplayName("Team: Training Tab: Single Action: Assign Users")
    @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
    void assignUsersSingleAction(final TeamWithChildren teamWithChildren) {
        // Open 'Training Tab'
        trainingTabService().openTeamTrainingTab(teamWithChildren.getTeam().getName());
        trainingTabService().clickTrainingTabSingleAction();
        trainingTabService().clickAssignUsersSingleAction();

        // Assert Single Actions: Assign Users
        assertAssignUsersAndDueDatesModule(teamWithChildren.getPractisSets().get(0).getName());
    }

    @TestRailTest(caseId = 20894)
    @DisplayName("Team: Training Tab: Single Action: Export Report")
    @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
    void exportReportSingleAction(final TeamWithChildren teamWithChildren) {
        // Open 'Training Tab'
        trainingTabService().openTeamTrainingTab(teamWithChildren.getTeam().getName());
        trainingTabService().clickTrainingTabSingleAction();
        trainingTabService().clickExportReportSingleAction();

        // Assert exported report
        assertDownloadedFile("team-practis-sets");
    }

    @TestRailTest(caseId = 20895)
    @DisplayName("Team: Training Tab: Single Action: Edit Practis Set")
    @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
    void editPractisSetSingleAction(final TeamWithChildren teamWithChildren) {
        // Open 'Training Tab'
        trainingTabService().openTeamTrainingTab(teamWithChildren.getTeam().getName());
        trainingTabService().clickTrainingTabSingleAction();
        trainingTabService().clickEditPsSingleAction();

        // Assert View Practis Set page
        assertElementsViewPractisSet();
    }

    @TestRailTest(caseId = 21919)
    @DisplayName("Team: Training Tab: Single Action: Assign Users and Due Dates: Empty State")
    @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
    void assertElementsOnEmptyAssignPractisSet(final TeamWithChildren teamWithChildren) {
        // Open 'Training' page
        trainingTabService().openTeamTrainingTab(teamWithChildren.getTeam().getName());

        // Open 3dots and click on Assign Users
        trainingTabService().clickTrainingTabSingleAction();
        trainingTabService().clickAssignUsersSingleAction();

        // Assert Empty Assign Users and Due Date screen
        assertEmptyAssignUsersAndDueDatesModule(teamWithChildren.getPractisSets().get(0).getName());
    }

    @TestRailTest(caseId = 21920)
    @DisplayName("Team: Training Tab: Single Action: Assign Users and Due Dates: Search")
    @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
    void assertSearchFieldOnAssignPractisSet(final TeamWithChildren teamWithChildren) {
        // Add new user to the Team
        membersTabService().openTeamMembersTab(teamWithChildren.getTeam().getName());
        membersTab().getMembersManageTeamButton().click();
        manageTeamPage().getCheckboxUserRowButton().get(0).click();
        manageTeamPage().getAddSelectedUsersButton().click();
        manageTeamPage().getCloseButton().click();
        teamPage().getTrainingTab().click();

        // Open 3dots and click on Assign Users
        Selenide.refresh();
        trainingTabService().clickTrainingTabSingleAction();
        trainingTabService().clickAssignUsersSingleAction();

        // Assert Search Field
        assertSearchFieldAssignUsersAndDueDatesModule();

        // Assert no Search results
        teamsPageService().searchUsersOnAssignPsModel("no results");
        assertNoSearchResultAssignUsersAndDueDatesModule();

        // Search should be performed after entering 1 character
        teamsPageService().clearSearchUsersOnAssignPsModel();
        assertSearchAfter1CharAssignUsersModule(teamWithChildren.getUsers().get(0).getFirstName());

        // Assert Clear Search
        assertCleanSearchAssignUsersModule(1);
    }
}
