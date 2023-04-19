package com.practis.selenide.company.navigation.reports;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.practisSetSummaryReportService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.reportsService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.searchService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertCleanSearchPractisSetSummaryReport;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertElementsOnPractisSetSummaryReportsPage;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertEnabledGenerateButtonPractisSetSummaryReport;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertGenerateButtonClickedPractisSetSummaryReport;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertHiddenSearchFiledTeam;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertNoPsSearchResultPractisSetSummaryReport;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertNoTeamsSearchResultPractisSetSummaryReport;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertPsSearchResultPractisSetSummaryReport;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertSearchAfter1CharPractisSetSummaryReport;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertSearchResultPractisSetSummaryReport;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertVisibleSearchField;
import static com.practis.web.selenide.validator.company.reports.ReportsValidator.assertElementsOnReportsPage;

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
class ReportsTest {
    @BeforeEach
    void init() {
        navigationCompany().getReportsItem().click();
    }

    @TestRailTest(caseId = 30076)
    @DisplayName("Reports: Reporting Screen: Check Elements for Admins")
    void checkElementsOnReportsPage() {

        assertElementsOnReportsPage();
    }

    @TestRailTest(caseId = 30062)
    @DisplayName("Reports: Practis Set Summary Report: Check Elements")
    void checkElementsOnPractisSetSummaryReportPage() {

        reportsService().clickOnPractisSetSummaryCard();
        assertElementsOnPractisSetSummaryReportsPage();
        assertHiddenSearchFiledTeam();
    }

    @TestRailTest(caseId = 31696)
    @TeamExtension(count = 1)
    @DisplayName("Reports: Practis Set Summary Report: Team: Search")
    void teamSearchOnPractisSetSummaryReportPage(final List<NewTeamInput> team) {

        reportsService().clickOnPractisSetSummaryCard();
        practisSetSummaryReportService().clickOnSearchIconTeamModal();
        assertVisibleSearchField();

        // Assert Clear Search
        assertCleanSearchPractisSetSummaryReport();

        // Assert no Search results
        searchService().searchPerform("no results");
        assertNoTeamsSearchResultPractisSetSummaryReport();
        practisSetSummaryReportService().clickOnClearSearchTeamModal();

        // Assert Search Results
        teamsPageService().searchTeam(team.get(0).getName());
        assertSearchResultPractisSetSummaryReport();
        practisSetSummaryReportService().clickOnClearSearchTeamModal();

        // Search should be performed after entering 1 character
        assertSearchAfter1CharPractisSetSummaryReport(team.get(0).getName());
        practisSetSummaryReportService().clickOnClearSearchTeamModal();
    }

    @TestRailTest(caseId = 31697)
    @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
    @DisplayName("Reports: Practis Set Summary Report: Practis Set: Search")
    void pSSearchOnPractisSetSummaryReportPage(final TeamWithChildren teamWithChildren) {

        reportsService().clickOnPractisSetSummaryCard();
        practisSetSummaryReportService().clickOnRadioButtonTeamModal();
        practisSetSummaryReportService().clickOnSearchIconPsModal();
        assertVisibleSearchField();

        // Assert Clear Search
        assertCleanSearchPractisSetSummaryReport();

        // Assert no Search results
        searchService().searchPerform("no results");
        assertNoPsSearchResultPractisSetSummaryReport();
        practisSetSummaryReportService().clickOnClearSearchTeamModal();

        // Assert Search Results
        searchService().searchPerform(teamWithChildren.getPractisSets().get(0).getName());
        assertPsSearchResultPractisSetSummaryReport();
        practisSetSummaryReportService().clickOnClearSearchTeamModal();

        // Search should be performed after entering 1 character
        assertSearchAfter1CharPractisSetSummaryReport(
                teamWithChildren.getPractisSets().get(0).getName());
        practisSetSummaryReportService().clickOnClearSearchTeamModal();
    }

    @TestRailTest(caseId = 30068)
    @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
    @DisplayName("Reports: Practis Set Summary Report: Clear, Generate")
    void generatePractisSetSummaryReport() {

        // Make Generate button enabled
        reportsService().clickOnPractisSetSummaryCard();
        practisSetSummaryReportService().clickOnRadioButtonTeamModal();
        practisSetSummaryReportService().clickOnRadioButtonPsModal();
        assertEnabledGenerateButtonPractisSetSummaryReport();

        // Click on Clear button
        practisSetSummaryReportService().clickOnClearButton();
        assertElementsOnPractisSetSummaryReportsPage();

        // CLick on Generate button
        practisSetSummaryReportService().clickOnRadioButtonTeamModal();
        practisSetSummaryReportService().clickOnRadioButtonPsModal();
        practisSetSummaryReportService().clickOnGenerateButton();
        assertGenerateButtonClickedPractisSetSummaryReport();

        snackbar()
                .getMessage()
                .shouldBe(
                        exactText(
                                "The report is being generated. Check your email in a few"
                                        + " minutes."));
    }
}
