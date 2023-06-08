package com.practis.selenide.company.create.user.selection.assign;

import static com.codeborne.selenide.Condition.hidden;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.assignUserModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
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
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertUnselectedTeam;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertOneTeamSelected;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertRequiredUserGridRow;
import static java.lang.String.format;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewTeamInput;
import com.practis.dto.NewUserInput;
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

    /** Invite User to the App: Assign: Check WEB elements on Team section. */
    @TestRailTest(caseId = 31940)
    @DisplayName("Invite User to the App: Assign: Team section: Check elements")
    @TeamExtension(count = 1)
    void checkElementsOnTeamSection() {
        Selenide.refresh();
        userService().addRow(inputData, "Admin");
        userService().assignFirstUser();

        assertElementsOnTeamSection();
    }

    /** Invite User to the App: Assign: Teams section: Search. */
    @TestRailTest(caseId = 31941)
    @DisplayName("Invite User to the App: Assign: Team section: Search")
    @TeamExtension(count = 2)
    void assignTeamsSearch(final List<NewTeamInput> team) {
        Selenide.refresh();
        userService().addRow(inputData, "Admin");
        userService().assignFirstUser();

        // assert search team
        assertSearchElementsOnTeamsModal();
        // assert clean search
        assertCleanTeamSearch(2);
        // Search should be performed after entering 1 character
        assertTeamSearchAfter1Char(team.get(0).getName());
        // assert empty state
        teamModuleService().searchTeam("no results");
        assertNoTeamSearchResult();
    }

    /** Invite User to the App: Assign: Teams section: Select All. */
    @TestRailTest(caseId = 31942)
    @DisplayName("Invite User to the App: Assign: Team section: Select All")
    @TeamExtension(count = 2)
    void assignTeamsSelectAll(final List<NewTeamInput> teams) {
        Selenide.refresh();

        userService().addRow(inputData, "Admin");
        userService().assignFirstUser();
        // assert unselected state
        assertUnSelectedAllStateTeam();
        // select one Team
        teamModuleService().selectTeam(teams.get(0).getName());
        // assert modal if one Team is selected
        assertSelectedTeam(teams.get(0).getName());
        assertTeamCounter("1 Team selected");
        assertSelectAllTeamButton();
        // select all
        teamModuleService().selectAllTeam();
        assertSelectedAllStateTeam();
    }

    /** Invite User to the App: Assign: Teams section: Cancel. */
    @TestRailTest(caseId = 31943)
    @DisplayName("Invite User to the App: Assign: Team section: Cancel")
    @TeamExtension(count = 1)
    void assignTeamsCancel(final List<NewTeamInput> teams) {
        Selenide.refresh();

        userService().addRow(inputData, "Admin");
        userService().assignFirstUser();
        // select one Team and click "Cancel"
        teamModuleService().selectTeam(teams.get(0).getName());
        assignUserModuleService().cancel();
        // assert User row
        assertRequiredUserGridRow(inputData, "Admin", 0);
        inviteUsersPage().getTeam().get(0).shouldBe(hidden);
    }

    /** Invite User to the App: Assign: Teams section: Apply. */
    @TestRailTest(caseId = 31944)
    @DisplayName("Invite User to the App: Assign: Team section: Apply")
    @TeamExtension(count = 1)
    void assignTeamsApply(final List<NewTeamInput> teams) {
        Selenide.refresh();

        userService().addRow(inputData, "Admin");
        userService().assignFirstUser();
        // select one Team and click 'Assign' button
        teamModuleService().selectTeam(teams.get(0).getName());
        assignUserModuleService().apply();
        // assert User row
        assertRequiredUserGridRow(inputData, "Admin", 0);
        assertOneTeamSelected(0);
    }

    /** Invite User to the App: Assign: Teams section: Already Assigned Teams. */
    @TestRailTest(caseId = 31945)
    @DisplayName("Invite User to the App: Assign: Team section: Already Assigned Teams")
    @TeamExtension(count = 2)
    void assignTeamsAlreadyAssigned(final List<NewTeamInput> teams) {
        Selenide.refresh();

        final var inputs = userService().generateUserInputs(1);
        inputs.forEach(input -> usersToRemove.add(input.getEmail()));

        userService().addRow(inputs.get(0), "Admin", teams.get(0));
        userService().assignAllUsers();
        // select one Team and click 'Assign' button
        assertSelectedTeam(teams.get(0).getName());
        assertUnselectedTeam(teams.get(1).getName());
    }

    /** Invite User to the App: Assign: Teams section: Empty State. */
    @TestRailTest(caseId = 31946)
    @DisplayName("Invite User to the App: Assign: Team section: Empty state")
    void assignTeamsEmptyState() {
        Selenide.refresh();

        userService().addRow(inputData, "Admin");
        userService().assignFirstUser();
        assertAssignEmptyTeam();
    }

    @AfterEach
    void cleanup() {
        usersToRemove.forEach(email -> practisApi().revokeUser(email));
    }
}
