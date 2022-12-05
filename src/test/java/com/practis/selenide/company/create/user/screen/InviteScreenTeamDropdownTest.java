package com.practis.selenide.company.create.user.screen;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamModuleService;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertAddedTeam;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertAssignEmptyTeam;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertCancelTeamButton;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertDisabledApplyTeamButton;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertElementsOnTeamSection;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertNoTeamSearchResult;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertSelectedAllStateTeam;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertTeamSearchResult;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertUnSelectedAllStateTeam;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertEmptyTeamList;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewTeamInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.TeamExtension;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class InviteScreenTeamDropdownTest {

    @BeforeEach
    void init() {
        newItemSelector().create("User");
    }

    /** Invite User to the App: Check Team dropdown: Check WEB elements. */
    @TestRailTest(caseId = 14976)
    @DisplayName("InviteTeamDropdownTest: Check Team dropdown: Check WEB elements")
    @TeamExtension(count = 1)
    void checkElementsOnTeamDropdown() {
        Selenide.refresh();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        inviteUsersPage().getTeamsField().click();
        // assert WEB elements
        assertElementsOnTeamSection();
        assertDisabledApplyTeamButton();
        assertCancelTeamButton();
    }

    /** Invite User to the App: Check Teams dropdown: No teams state. */
    @TestRailTest(caseId = 1079)
    @DisplayName("InviteTeamDropdownTest: Check Teams dropdown: No teams state")
    void checkEmptyTeamsDropdown() {
        inviteUsersPage().getTeamsField().click();
        assertAssignEmptyTeam();
    }

    /** Invite User to the App: Check Teams dropdown: Delete team. */
    @TestRailTest(caseId = 1082)
    @DisplayName("InviteTeamDropdownTest: Check Teams dropdown: Delete team")
    @TeamExtension(count = 1)
    void checkDeletingTeam(final List<NewTeamInput> teams) {
        Selenide.refresh();

        await().pollDelay(TWO_SECONDS).until(() -> true);
        assertAddedTeam(teams.get(0).getName());
        practisApi().deleteTeam(teams.get(0).getName());
        Selenide.refresh();
        assertEmptyTeamList();
    }

    /** Invite User to the App: Check Teams dropdown: Search team. */
    @TestRailTest(caseId = 1083)
    @DisplayName("InviteTeamDropdownTest: Check Teams dropdown: Search team")
    @TeamExtension(count = 1)
    void checkSearchTeam(final List<NewTeamInput> teams) {
        Selenide.refresh();
        // Check Team exists
        assertAddedTeam(teams.get(0).getName());

        // Search by not existing team and check results
        teamModuleService().searchTeam("invalid search criteria");
        assertNoTeamSearchResult();

        // Search by existing team and check results
        teamModuleService().searchTeam(teams.get(0).getName());
        assertTeamSearchResult(teams.get(0).getName());
    }

    /** Invite User to the App: Check Teams dropdown: Select All /Unselect All team. */
    @TestRailTest(caseId = 1084)
    @DisplayName("InviteTeamDropdownTest: Check Teams dropdown: Select All/Unselect All team")
    @TeamExtension(count = 1)
    void checkSelectUnselectAllTeam(final List<NewTeamInput> teams) {
        Selenide.refresh();

        await().pollDelay(TWO_SECONDS).until(() -> true);
        assertAddedTeam(teams.get(0).getName());
        // Select all and assert
        teamModuleService().selectAllTeam();
        assertSelectedAllStateTeam();

        // Unselect all and assert
        teamModuleService().unSelectAllTeam();
        assertUnSelectedAllStateTeam();
    }
}
