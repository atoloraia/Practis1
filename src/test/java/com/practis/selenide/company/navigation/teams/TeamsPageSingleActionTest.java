package com.practis.selenide.company.navigation.teams;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.confirmationAndWarningPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.keepTrackPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.assignUserModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertElementsEmptyTeamsPage;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertLabelCountOnTeamsPage;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertSingleActionTeam;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertTeamsRows;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertElementsManageTeamPage;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertLabelManageTeam;
import static com.practis.web.selenide.validator.company.team.TeamPageValidator.assertEmptyTeamPage;
import static com.practis.web.selenide.validator.popup.ConfirmAndWarningPopUpsValidator.assertConfirmationModal;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertEmptyLabelModel;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSelectedLabel;
import static com.practis.web.util.AwaitUtils.awaitElementExists;
import static com.practis.web.util.AwaitUtils.awaitSoft;
import static com.practis.web.util.SelenidePageLoadAwait.awaitFullPageLoad;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewTeamInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.dto.TeamWithChildren;
import com.practis.support.extension.practis.LabelExtension;
import com.practis.support.extension.practis.TeamExtension;
import com.practis.support.extension.practis.TeamExtensionWithUsersAndPractisSets;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class TeamsPageSingleActionTest {

    private List<String> teamsToRemove;

    @BeforeEach
    void init() {
        navigationCompany().getTeamsNavigationItem().click();
        teamsToRemove = new ArrayList<>();
    }

    @TestRailTest(caseId = 31757)
    @DisplayName("Teams: Single Action: Check Elements")
    @TeamExtension(count = 1)
    void checkElementsSingleActionTeam(final List<NewTeamInput> team) {
        Selenide.refresh();
        teamsPageService().clickSingleActionTeam(team.get(0).getName());

        // asser single action team
        assertSingleActionTeam();
    }

    @TestRailTest(caseId = 31759)
    @DisplayName("Teams: Single Action: View Team")
    @TeamExtension(count = 1)
    void viewTeamSingleAction(final List<NewTeamInput> team) {
        Selenide.refresh();
        teamsPageService().clickSingleActionTeam(team.get(0).getName());
        teamsPageService().clickViewTeamSingleAction();
        keepTrackPopUp().getGotItButton().click();

        // Assert 'Team' page for the team
        assertEmptyTeamPage(team.get(0).getName());
    }

    @TestRailTest(caseId = 31760)
    @DisplayName("Teams: Single Action: Manage Team")
    @TeamExtension(count = 1)
    void manageTeamSingleAction(final List<NewTeamInput> team) {
        Selenide.refresh();
        teamsPageService().clickSingleActionTeam(team.get(0).getName());
        teamsPageService().clickManageTeamSingleAction();

        // Assert 'Manage Team' page
        assertElementsManageTeamPage(team.get(0).getName());
    }

    @TestRailTest(caseId = 31761)
    @DisplayName("Teams: Single Action: Assign Labels")
    @TeamExtension(count = 1)
    void assignLabelsTeamSingleAction(final List<NewTeamInput> team) {
        Selenide.refresh();
        teamsPageService().clickSingleActionTeam(team.get(0).getName());
        teamsPageService().clickAssignLabelsSingleAction();

        // Assert 'Team' page for All Members
        assertEmptyLabelModel();
    }

    @TestRailTest(caseId = 31762)
    @DisplayName("Teams: Single Action: Assign Labels: Apply")
    @TeamExtension(count = 1)
    @LabelExtension(count = 1)
    void assignLabelsTeamSingleActionApply(
            final List<NewTeamInput> team, final List<RestCreateLabelResponse> label) {
        Selenide.refresh();
        teamsPageService().clickSingleActionTeam(team.get(0).getName());
        teamsPageService().clickAssignLabelsSingleAction();

        // select one Label and click 'Apply' button
        labelModuleService().selectLabel(label.get(0).getName());
        assignUserModuleService().applyLabel();

        // assert Label has been added
        assertLabelCountOnTeamsPage(team.get(0).getName(), "1");
        teamsPageService().openManageTeamFromTeamsPage(team.get(0).getName());
        assertLabelManageTeam(label.get(0).getName());
    }

    @TestRailTest(caseId = 31763)
    @DisplayName("Teams: Single Action: Assign Labels: Check already assigned label")
    @TeamExtension(count = 1)
    @LabelExtension(count = 2)
    void alreadyAssignLabelsTeamSingleAction(
            final List<NewTeamInput> team, final List<RestCreateLabelResponse> label) {
        Selenide.refresh();
        practisApi().assignLabelToTeam(team.get(0).getId(), List.of(label.get(0).getId()));

        teamsPageService().clickSingleActionTeam(team.get(0).getName());
        teamsPageService().clickAssignLabelsSingleAction();
        assertSelectedLabel(label.get(0).getName());
    }

    @TestRailTest(caseId = 31764)
    @DisplayName("Teams: Single Action: Duplicate")
    @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
    void duplicateTeamSingleAction(final TeamWithChildren teamWithChildren) {
        final var team = teamWithChildren.getTeam();
        teamsPageService().searchTeam(team.getName());
        teamsPageService().awaitTheRow(team);
        Selenide.refresh();

        // Check number of teams in the list
        assertTeamsRows(1);

        // Duplicate the team
        teamsPageService().clickSingleActionTeam(team.getName());
        teamsPageService().clickDuplicateSingleAction();
        teamsToRemove.add(team.getName());

        awaitSoft(10, () -> teamsPage().getTeamRow().size() == 2);
        assertTeamsRows(2);
        final var originalTeam = teamsPageService().getOriginalTeam(team.getName());
        final var duplicatedTeam = teamsPageService().getDuplicatedTeam(team.getName());

        awaitFullPageLoad(10);
        assertEquals(originalTeam.get("Members").text(), duplicatedTeam.get("Members").text());
        assertEquals(
                duplicatedTeam.get("Practis Sets").text(),
                duplicatedTeam.get("Practis Sets").text());
    }

    @TestRailTest(caseId = 31765)
    @DisplayName("Teams: Single Action: Delete")
    @TeamExtension(count = 1)
    void deleteTeamSingleAction(final List<NewTeamInput> team) {
        Selenide.refresh();
        // click 'Delete' single action
        teamsPageService().clickSingleActionTeam(team.get(0).getName());
        teamsPageService().clickDeleteSingleAction();

        // assert warning message
        assertConfirmationModal(
                "Warning",
                "You will delete the selected team(s) from the system. This action cannot be"
                        + " undone. Are you sure?",
                "Proceed",
                "Go Back");
        confirmationAndWarningPopUp().getConfirmButton().click();

        // check snackbar
        awaitElementExists(10, () -> snackbar().getMessage())
                .shouldBe(exactText("Team has been deleted"));

        // assert team has been deleted
        teamsPageService().searchTeam(team.get(0).getName());
        teamsPageService().awaitTheRow(team.get(0));
        assertTeamsRows(0);
        assertElementsEmptyTeamsPage();
    }

    @AfterEach
    void cleanup() {
        teamsToRemove.forEach(name -> practisApi().deleteTeam(name));
    }
}
