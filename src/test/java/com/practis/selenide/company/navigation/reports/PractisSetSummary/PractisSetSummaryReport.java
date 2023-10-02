package com.practis.selenide.company.navigation.reports.PractisSetSummary;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.practisSetSummaryReportService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.reportsService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.searchService;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertElementsOnPractisSetSummaryReportsPage;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertHiddenSearchFiledPsSummary;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertNoPsSearchResultPractisSetSummaryReport;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertPsSearchResultPractisSetSummaryReport;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertSearchResultPractisSetSummaryReport;
import static com.practis.web.selenide.validator.company.reports.ReportsValidator.assertCleanSearch;
import static com.practis.web.selenide.validator.company.reports.ReportsValidator.assertEnabledGenerateButtonReport;
import static com.practis.web.selenide.validator.company.reports.ReportsValidator.assertNoTeamsSearchResultReports;
import static com.practis.web.selenide.validator.company.reports.ReportsValidator.assertSearchAfter1CharReports;
import static com.practis.web.selenide.validator.company.reports.ReportsValidator.assertVisibleSearchField;

import com.practis.dto.NewTeamInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.dto.TeamWithChildren;
import com.practis.support.extension.practis.TeamExtension;
import com.practis.support.extension.practis.TeamExtensionWithUsersAndPractisSets;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
class PractisSetSummaryReport {

    @BeforeEach
    void init() {
        navigationCompany().getReportsItem().click();
        reportsService().clickOnPractisSetSummaryCard();
    }

    @TestRailTest(caseId = 32052)
    @DisplayName("Reports: Practis Set Summary Report: Check Elements")
    void checkElementsOnPractisSetSummaryReportPage() {

        assertElementsOnPractisSetSummaryReportsPage();
        assertHiddenSearchFiledPsSummary();
    }

    @TestRailTest(caseId = 32053)
    @TeamExtension(count = 1)
    @DisplayName("Reports: Practis Set Summary Report: Team: Search")
    void teamSearchOnPractisSetSummaryReportPage(final List<NewTeamInput> team) {

        reportsService().clickOnFirstModalSearchIcon();
        assertVisibleSearchField();

        // Assert Clear Search
        assertCleanSearch();

        // Assert no Search results
        searchService().searchPerform("no results");
        assertNoTeamsSearchResultReports();
        reportsService().clickOnClearSearch();

        // Assert Search Results
        searchService().searchPerform(team.get(0).getName());
        assertSearchResultPractisSetSummaryReport();
        reportsService().clickOnClearSearch();

        // Search should be performed after entering 1 character
        assertSearchAfter1CharReports(team.get(0).getName());
        reportsService().clickOnClearSearch();
    }

    @TestRailTest(caseId = 32054)
    @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
    @DisplayName("Reports: Practis Set Summary Report: Practis Set: Search")
    void psSearchOnPractisSetSummaryReportPage(final TeamWithChildren teamWithChildren) {

        practisSetSummaryReportService().clickOnRadioButtonTeamModal();
        reportsService().clickOnSecondModalSearchIcon();
        assertVisibleSearchField();

        // Assert Clear Search
        assertCleanSearch();

        // Assert no Search results
        searchService().searchPerform("no results");
        assertNoPsSearchResultPractisSetSummaryReport();
        reportsService().clickOnClearSearch();

        // Assert Search Results
        searchService().searchPerform(teamWithChildren.getPractisSets().get(0).getName());
        assertPsSearchResultPractisSetSummaryReport();
        reportsService().clickOnClearSearch();

        // Search should be performed after entering 1 character
        assertSearchAfter1CharReports(teamWithChildren.getPractisSets().get(0).getName());
        reportsService().clickOnClearSearch();
    }

    @TestRailTest(caseId = 32055)
    @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
    @DisplayName("Reports: Practis Set Summary Report: Clear, Generate")
    void generatePractisSetSummaryReport() {

        // Make Generate button enabled
        practisSetSummaryReportService().clickOnRadioButtonTeamModal();
        practisSetSummaryReportService().clickOnRadioButtonPsModal();
        assertEnabledGenerateButtonReport();

        // Click on Clear button
        reportsService().clickOnClearButton();
        assertElementsOnPractisSetSummaryReportsPage();

        // CLick on Generate button
        practisSetSummaryReportService().clickOnRadioButtonTeamModal();
        practisSetSummaryReportService().clickOnRadioButtonPsModal();
        reportsService().clickOnGenerateButton();
        assertEnabledGenerateButtonReport();

        snackbar()
                .getMessage()
                .shouldBe(
                        exactText(
                                "The report is being generated. Check your email in a few"
                                        + " minutes."));
    }
}
