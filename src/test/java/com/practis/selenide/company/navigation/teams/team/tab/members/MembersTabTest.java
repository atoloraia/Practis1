package com.practis.selenide.company.navigation.teams.team.tab.members;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.keepTrackPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.PageObjectFactory.membersTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertCleanSearchMembersPage;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertElementsEmptyMembersTab;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertMembersFiltersModal;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertNoSearchResultMembersPage;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertSearchAfter1CharMembersPage;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertSearchFieldOnMembersPage;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertSearchResultsOnMembersPage;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertEmptyLabelModel;
import static com.practis.web.util.SelenideJsUtils.jsClick;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewUserInput;
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
public class MembersTabTest {

    @TestRailTest(caseId = 15692)
    @DisplayName("Team: Members Tab: Check Web Elements on Members tab")
    @TeamExtension(count = 1)
    void assertElementMembersPage() {
        // Open 'Members' page
        navigationCompany().getTeamsNavigationItem().click();
        Selenide.refresh();
        teamsPage().getTeamRow().get(0).click();
        keepTrackPopUp().getGotItButton().click();
        teamPage().getMembersTab().click();

        // Assert Empty Members screen
        assertElementsEmptyMembersTab();
    }

    @TestRailTest(caseId = 17126)
    @DisplayName("Team: Members Tab: Filter: Check Web Elements")
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    void assertMemberFiltersModal() {
        // Open 'Training' page
        navigationCompany().getTeamsNavigationItem().click();
        Selenide.refresh();
        teamPage().getTeamRowTitle().get(0).click();

        // Open Members tab
        keepTrackPopUp().getGotItButton().click();
        teamPage().getMembersTab().click();

        // Open Filters
        membersTab().getMembersFiltersButton().click();

        // Assert Filters Modal
        assertMembersFiltersModal();

        // Assert Labels
        assertEmptyLabelModel();
    }

    @TestRailTest(caseId = 18207)
    @DisplayName("Team: Members Tab: Search")
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    void searchFieldMembersScreen(final List<NewUserInput> users) {

        // Open 'Members' page
        teamPage().getTeamRowTitle().get(0).click();
        jsClick(keepTrackPopUp().getGotItButton());
        teamPage().getMembersTab().click();

        // Assert Search Field
        assertSearchFieldOnMembersPage();

        // Assert no Search results
        teamsPageService().searchTeam("no results");
        assertNoSearchResultMembersPage();

        // Assert Search Results
        userService().searchUser(users.get(0).getFirstName());
        assertSearchResultsOnMembersPage();

        // Search should be performed after entering 1 character
        assertSearchAfter1CharMembersPage(users.get(0).getFirstName());

        // Assert Clear Search
        assertCleanSearchMembersPage(1);
    }
}
