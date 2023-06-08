package com.practis.selenide.company.navigation.teams.team;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.practisSetDetailsService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.reportsService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.searchService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertSelectedElementsPractisSetSummaryReportsPage;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertSelectedPsPsSummaryReport;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertSelectedTeamPsSummaryReport;
import static com.practis.web.selenide.validator.company.team.PractisSetDetailsValidator.assertCleanSearchPractisSetDetailsPage;
import static com.practis.web.selenide.validator.company.team.PractisSetDetailsValidator.assertElementsPractisSetDetailsPage;
import static com.practis.web.selenide.validator.company.team.PractisSetDetailsValidator.assertFiltersModalPSDetailsPage;
import static com.practis.web.selenide.validator.company.team.PractisSetDetailsValidator.assertNoSearchResultPractisSetDetailsPage;
import static com.practis.web.selenide.validator.company.team.PractisSetDetailsValidator.assertSearchAfter1CharPractisSetDetailsPage;
import static com.practis.web.selenide.validator.company.team.PractisSetDetailsValidator.assertSearchResultsOnPractisSetDetailsPage;
import static com.practis.web.selenide.validator.selection.FilterValidator.assertFiltersElementsDefaultState;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Selenide;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.dto.TeamWithChildren;
import com.practis.support.extension.practis.TeamExtensionWithUsersAndPractisSets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class PractisSetDetailsTest {

    @BeforeEach
    void init() {
        navigationCompany().getTeamsNavigationItem().click();
    }

    @TestRailTest(caseId = 31804)
    @DisplayName("Training: Practis Set Details: Check Elements")
    @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
    void assertElementsPsDetailsPage() {
        // Open 'Practis Set Details' page
        Selenide.refresh();
        practisSetDetailsService().openPractiSetDetailsPage();

        // Assert Team Practis Set Details Page
        assertElementsPractisSetDetailsPage();
    }

    @TestRailTest(caseId = 31808)
    @DisplayName("Training: Practis Set Details: Filters: Check Elements")
    @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
    void assertElementsFiltersPsDetailsPage() {
        // Open 'Practis Set Details' page
        practisSetDetailsService().openPractiSetDetailsPage();

        // CLick on Filters button and assert Filters modal
        practisSetDetailsService().clickOnFilters();
        assertFiltersModalPSDetailsPage();
        assertFiltersElementsDefaultState();
    }

    @TestRailTest(caseId = 31809)
    @DisplayName("Training: Practis Set Details: Search")
    @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
    void assertSearchPsDetailsPage(final TeamWithChildren teamWithChildren) {
        // Open 'Practis Set Details' page
        practisSetDetailsService().openPractiSetDetailsPage();

        // Assert No Search Results
        userService().searchUser("no results");
        assertNoSearchResultPractisSetDetailsPage();

        // Assert Search Results
        userService().searchUser(teamWithChildren.getUsers().get(0).getFirstName());
        assertSearchResultsOnPractisSetDetailsPage();

        // Assert Search result after entering 1 char
        assertSearchAfter1CharPractisSetDetailsPage(
                teamWithChildren.getUsers().get(0).getFirstName());

        // Assert Clear Search
        assertCleanSearchPractisSetDetailsPage(1);
    }

    @TestRailTest(caseId = 31810)
    @DisplayName("Training: Practis Set Progress Details: Generate Report: Click")
    @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
    void assertGenerateReportPsDetailsPage(final TeamWithChildren teamWithChildren) {
        // Open 'Practis Set Details' page
        Selenide.refresh();
        searchService().searchPerform(teamWithChildren.getTeam().getName());
        await().pollDelay(TWO_SECONDS).until(() -> true);
        practisSetDetailsService().openTeamPractiSetDetailsPage();

        // Click on Generate Report button
        await().pollDelay(TWO_SECONDS).until(() -> true);
        practisSetDetailsService().clickOnGenerateReport();

        // Assert Practis Set Summary Report page with selected PS and Team
        assertSelectedElementsPractisSetSummaryReportsPage();

        // Check that proper Team is selected
        reportsService().clickOnFirstModalSearchIcon();
        searchService().searchPerform(teamWithChildren.getTeam().getName());
        assertSelectedTeamPsSummaryReport(teamWithChildren.getTeam().getName());
        reportsService().clickOnClearSearch();

        // Check that proper PS is selected
        reportsService().clickOnSecondModalSearchIcon();
        searchService().searchPerform(teamWithChildren.getPractisSets().get(0).getName());
        assertSelectedPsPsSummaryReport(teamWithChildren.getPractisSets().get(0).getName());
    }
}
