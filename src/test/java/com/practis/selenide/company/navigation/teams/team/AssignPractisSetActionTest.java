package com.practis.selenide.company.navigation.teams.team;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.keepTrackPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.PageObjectFactory.membersTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.selenide.validator.company.team.AssignPsAndDueDatesValidator.assertCleanSearchAssignPsModule;
import static com.practis.web.selenide.validator.company.team.AssignPsAndDueDatesValidator.assertNoSearchResultOnAssignPractisSetModule;
import static com.practis.web.selenide.validator.company.team.AssignPsAndDueDatesValidator.assertSearchResultsOnAssignPractisSetsModule;
import static com.practis.web.selenide.validator.selection.AssignPractisSetsAndDueDatesValidator.assertAssignPsAndDueDateModule;
import static com.practis.web.selenide.validator.selection.AssignPractisSetsAndDueDatesValidator.assertAssignPsAndDueDateModuleEmpty;
import static com.practis.web.selenide.validator.selection.AssignPractisSetsAndDueDatesValidator.assertSearchAfter1CharAssignPsModule;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertSearchField;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Selenide;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.dto.TeamWithChildren;
import com.practis.support.extension.practis.RegisteredUserExtension;
import com.practis.support.extension.practis.TeamExtensionWithUsersAndPractisSets;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class AssignPractisSetActionTest {

    @TestRailTest(caseId = 20885)
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
        assertAssignPsAndDueDateModuleEmpty();
    }

    @TestRailTest(caseId = 20884)
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
        membersTab().getMembersThreeDotMenu().click();
        membersTab().getMembersAssignPractisSetOption().click();

        // Assert Assign Practis Set action
        assertAssignPsAndDueDateModule();
    }

    @TestRailTest(caseId = 20886)
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
}
