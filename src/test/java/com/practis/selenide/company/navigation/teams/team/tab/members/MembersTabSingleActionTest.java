package com.practis.selenide.company.navigation.teams.team.tab.members;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.confirmationAndWarningPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.keepTrackPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.PageObjectFactory.membersTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.membersTabService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.navigationCompanyService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.selenide.service.company.UsersService.searchUser;
import static com.practis.web.selenide.validator.company.team.AssignPsAndDueDatesValidator.assertCleanSearchAssignPsModule;
import static com.practis.web.selenide.validator.company.team.AssignPsAndDueDatesValidator.assertNoSearchResultOnAssignPractisSetModule;
import static com.practis.web.selenide.validator.company.team.AssignPsAndDueDatesValidator.assertSearchResultsOnAssignPractisSetsModule;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertElementsEmptyMembersTab;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertSingleActionMember;
import static com.practis.web.selenide.validator.popup.WarningRemoveFromTeamPopUpValidator.assertWarningDeletePopUp;
import static com.practis.web.selenide.validator.selection.AssignPractisSetsAndDueDatesValidator.assertAssignPsAndDueDate;
import static com.practis.web.selenide.validator.selection.AssignPractisSetsAndDueDatesValidator.assertAssignPsAndDueDateEmpty;
import static com.practis.web.selenide.validator.selection.AssignPractisSetsAndDueDatesValidator.assertSearchAfter1CharAssignPsModule;
import static com.practis.web.selenide.validator.selection.NudgeUserValidator.assertEmptyNudgeUserPopUp;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertDownloadedFile;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertSearchField;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertUserProfile;
import static com.practis.web.util.AwaitUtils.awaitElementExists;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Selenide;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.dto.TeamWithChildren;
import com.practis.support.extension.practis.RegisteredUserExtension;
import com.practis.support.extension.practis.TeamExtensionWithUsers;
import com.practis.support.extension.practis.TeamExtensionWithUsersAndPractisSets;
import com.practis.web.selenide.configuration.ComponentObjectFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class MembersTabSingleActionTest {

    @BeforeEach
    void init() {
        ComponentObjectFactory.navigationCompany().getTeamsNavigationItem().click();
    }

    @TestRailTest(caseId = 31792)
    @DisplayName("Team: Members Tab: Single Action: Check Elements")
    @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
    void viewMemberTabSingleAction(final TeamWithChildren teamWithChildren) {
        // Open 'Members' page
        Selenide.refresh();
        membersTabService().openTeamMembersTab(teamWithChildren.getTeam().getName());
        Selenide.refresh();
        membersTabService().clickMembersTabSingleAction();

        // Assert Single Actions
        assertSingleActionMember();
    }

    @TestRailTest(caseId = 31794)
    @DisplayName("Team: Members Tab: Single Action: View Profile")
    @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
    void viewProfileSingleAction(final TeamWithChildren teamWithChildren) {
        // Open 'Members' page
        Selenide.refresh();
        membersTabService().openTeamMembersTab(teamWithChildren.getTeam().getName());
        Selenide.refresh();
        membersTabService().clickMembersTabSingleAction();
        membersTabService().clickViewProfileSingleAction();

        // Assert User Profile Page
        assertUserProfile();
    }

    @TestRailTest(caseId = 31793)
    @DisplayName("Team: Members Tab: Single Action: Assign Practis Sets: Empty State")
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    void assertElementsOnEmptyAssignPractisSet() {
        // Open 'Members' page
        navigationCompany().getTeamsNavigationItem().click();
        Selenide.refresh();
        teamsPage().getTeamsAllMembersRow().click();
        keepTrackPopUp().getGotItButton().click();
        teamPage().getMembersTab().click();

        // Open 3dot menu
        membersTab().getMembersThreeDotMenu().click();
        membersTab().getMembersAssignPractisSetOption().click();

        // Assert Empty Assign Practis Set action
        assertAssignPsAndDueDateEmpty();
    }

    @TestRailTest(caseId = 31796)
    @DisplayName("Team: Members Tab: Single Action: Assign Practis Sets")
    @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
    void assertElementsOnAssignPractisSet() {
        Selenide.refresh();
        // Open 'Members' page
        navigationCompany().getTeamsNavigationItem().click();
        teamsPage().getTeamRow().get(0).click();
        keepTrackPopUp().getGotItButton().click();
        teamPage().getMembersTab().click();

        // Open 3dot menu
        awaitElementExists(10, () -> membersTab().getMembersThreeDotMenu());
        membersTab().getMembersThreeDotMenu().click();
        membersTab().getMembersAssignPractisSetOption().click();

        // Assert Assign Practis Set action
        assertAssignPsAndDueDate("1 Practis Set selected");
    }

    @TestRailTest(caseId = 32070)
    @DisplayName("Team: Members Tab: Single Action: Assign Practis Sets: Search")
    @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
    void assertSearchFieldOnAssignPractisSet(final TeamWithChildren teamWithChildren) {
        Selenide.refresh();
        // Open 'Members' page
        navigationCompany().getTeamsNavigationItem().click();
        teamsPage().getTeamRow().get(0).click();
        keepTrackPopUp().getGotItButton().click();
        teamPage().getMembersTab().click();

        // Open 3dot menu
        membersTab().getMembersThreeDotMenu().click();
        membersTab().getMembersAssignPractisSetOption().click();
        await().pollDelay(TWO_SECONDS).until(() -> true);

        // Assert Search Field
        assertSearchField();

        // Assert no Search results
        teamsPageService().searchPsOnAssignPsModel("no results");
        assertNoSearchResultOnAssignPractisSetModule();

        // Assert Search Results
        teamsPageService().clearSearchPsOnAssignPsModel();
        teamsPageService()
                .searchPsOnAssignPsModel(teamWithChildren.getPractisSets().get(0).getName());
        assertSearchResultsOnAssignPractisSetsModule();

        // Search should be performed after entering 1 character
        teamsPageService().clearSearchPsOnAssignPsModel();
        assertSearchAfter1CharAssignPsModule(teamWithChildren.getPractisSets().get(0).getName());

        // Assert Clear Search
        assertCleanSearchAssignPsModule(1);
    }

    @TestRailTest(caseId = 31797)
    @DisplayName("Team: Members Tab: Single Action: Nudge User")
    @TeamExtensionWithUsers(users = 1)
    void nudgeUsersSingleAction(final TeamWithChildren teamWithChildren) {
        // Open 'Members' page
        membersTabService().openTeamMembersTab(teamWithChildren.getTeam().getName());
        Selenide.refresh();
        membersTabService().clickMembersTabSingleAction();
        membersTabService().clickNudgeUserSingleAction();

        // Assert Nudge Popup
        assertEmptyNudgeUserPopUp();
    }

    @TestRailTest(caseId = 31798)
    @DisplayName("Team: Members Tab: Single Action: Export Report")
    @TeamExtensionWithUsers(users = 1)
    void exportReportSingleAction(final TeamWithChildren teamWithChildren) {
        // Open 'Members' page
        membersTabService().openTeamMembersTab(teamWithChildren.getTeam().getName());
        Selenide.refresh();
        membersTabService().clickMembersTabSingleAction();
        membersTabService().clickExportReportSingleAction();

        // Assert exported report
        assertDownloadedFile("team-members.csv");
    }

    @TestRailTest(caseId = 31799)
    @DisplayName("Team: Members Tab: Single Action: Remove From Team")
    @TeamExtensionWithUsers(users = 1)
    void removeFromTeamSingleAction(final TeamWithChildren teamWithChildren) {
        // Open 'Members' page
        membersTabService().openTeamMembersTab(teamWithChildren.getTeam().getName());
        Selenide.refresh();
        membersTabService().clickMembersTabSingleAction();
        membersTabService().clickRemoveFromTeamSingleAction();

        // Assert warning pop-up
        assertWarningDeletePopUp();

        // Click "Proceed" button
        confirmationAndWarningPopUp().getConfirmButton().click();
        await().pollDelay(TWO_SECONDS).until(() -> true);

        // assert empty Member Tab
        assertElementsEmptyMembersTab();

        // assert team on User Profile
        navigationCompanyService().openUsersPage();
        searchUser(teamWithChildren.getUsers().get(0));
    }
}
