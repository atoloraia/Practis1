package com.practis.selenide.company.navigation.reports;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.practisSetSummaryReportService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.reportsService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.searchService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userActivityReportService;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertElementsOnPractisSetSummaryReportsPage;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertHiddenSearchFiledTeam;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertNoPsSearchResultPractisSetSummaryReport;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertPsSearchResultPractisSetSummaryReport;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertSearchResultPractisSetSummaryReport;
import static com.practis.web.selenide.validator.company.reports.ReportsValidator.assertCleanSearch;
import static com.practis.web.selenide.validator.company.reports.ReportsValidator.assertClickedDisabledGenerateButtonReport;
import static com.practis.web.selenide.validator.company.reports.ReportsValidator.assertElementsOnReportsPage;
import static com.practis.web.selenide.validator.company.reports.ReportsValidator.assertEnabledGenerateButtonReport;
import static com.practis.web.selenide.validator.company.reports.ReportsValidator.assertNoLabelsSearchResultReports;
import static com.practis.web.selenide.validator.company.reports.ReportsValidator.assertNoTeamsSearchResultReports;
import static com.practis.web.selenide.validator.company.reports.ReportsValidator.assertSearchAfter1CharReports;
import static com.practis.web.selenide.validator.company.reports.ReportsValidator.assertVisibleSearchField;
import static com.practis.web.selenide.validator.company.reports.UserActivityReportValidator.assertElementsOnUserActivityReportsPage;
import static com.practis.web.selenide.validator.company.reports.UserActivityReportValidator.assertHiddenSearchFiledUserActivityReport;
import static com.practis.web.selenide.validator.company.reports.UserActivityReportValidator.assertLabelsSearchResultUserActivityReport;
import static com.practis.web.selenide.validator.company.reports.UserActivityReportValidator.assertTeamsSearchResultUserActivityReport;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

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

    @TestRailTest(caseId = 31697)
    @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
    @DisplayName("Reports: Practis Set Summary Report: Practis Set: Search")
    void psSearchOnPractisSetSummaryReportPage(final TeamWithChildren teamWithChildren) {

        reportsService().clickOnPractisSetSummaryCard();
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

    @TestRailTest(caseId = 30068)
    @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
    @DisplayName("Reports: Practis Set Summary Report: Clear, Generate")
    void generatePractisSetSummaryReport() {

        // Make Generate button enabled
        reportsService().clickOnPractisSetSummaryCard();
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
        assertClickedDisabledGenerateButtonReport();

        snackbar()
                .getMessage()
                .shouldBe(
                        exactText(
                                "The report is being generated. Check your email in a few"
                                        + " minutes."));
    }

    @TestRailTest(caseId = 30063)
    @DisplayName("Reports: Users Activity Report: Check Elements")
    void checkElementsOnUserActivityReportPage() {

        reportsService().clickOnUserActivityCard();
        assertElementsOnUserActivityReportsPage();
        assertHiddenSearchFiledUserActivityReport();
    }

    @TestRailTest(caseId = 31703)
    @TeamExtension(count = 1)
    @DisplayName("Reports: Users Activity Report: Teams: Search")
    void teamSearchOnUserActivityReportPage(final List<NewTeamInput> team) {

        reportsService().clickOnUserActivityCard();
        await().pollDelay(TWO_SECONDS).until(() -> true);
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
        assertTeamsSearchResultUserActivityReport();
        reportsService().clickOnFirstModalSearchIcon();

        // Search should be performed after entering 1 character
        assertSearchAfter1CharReports(team.get(0).getName());
        await().pollDelay(TWO_SECONDS).until(() -> true);
        reportsService().clickOnFirstModalSearchIcon();
    }

    @TestRailTest(caseId = 31704)
    @LabelExtension(count = 1)
    @DisplayName("Reports: Users Activity Report: Labels: Search")
    void labelsSearchOnUserActivityReportPage(final List<RestCreateLabelResponse> label) {
        Selenide.refresh();

        reportsService().clickOnUserActivityCard();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        reportsService().clickOnSecondModalSearchIcon();
        assertVisibleSearchField();

        // Assert Clear Search
        assertCleanSearch();

        // Assert no Search results
        searchService().searchPerform("no results");
        assertNoLabelsSearchResultReports();
        reportsService().clickOnClearSearch();

        // Assert Search Results
        searchService().searchPerform(label.get(0).getName());
        assertLabelsSearchResultUserActivityReport();
        reportsService().clickOnClearSearch();

        // Search should be performed after entering 1 character
        assertSearchAfter1CharReports(label.get(0).getName());
        await().pollDelay(TWO_SECONDS).until(() -> true);
        reportsService().clickOnClearSearch();
    }

    @TestRailTest(caseId = 30059)
    @DisplayName("Reports: User Activity Report: Clear, Generate")
    void generateUserActivityReport() {

        // Make Generate button enabled
        reportsService().clickOnUserActivityCard();
        userActivityReportService().clickOnTeamCheckbox();
        assertEnabledGenerateButtonReport();

        // Click on Clear button
        reportsService().clickOnClearButton();
        assertElementsOnUserActivityReportsPage();

        // CLick on Generate button
        userActivityReportService().clickOnTeamCheckbox();
        reportsService().clickOnGenerateButton();
        assertClickedDisabledGenerateButtonReport();

        snackbar()
                .getMessage()
                .shouldBe(
                        exactText(
                                "The report is being generated. Check your email in a few"
                                        + " minutes."));
    }
}
