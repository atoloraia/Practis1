package com.practis.selenide.company.navigation.reports;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.billingReportService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.practisSetSummaryReportService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.reportsService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.searchService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamLeaderEngagementReportService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userActivityReportService;
import static com.practis.web.selenide.validator.company.reports.BillingReportValidator.assertElementsOnBillingReportPage;
import static com.practis.web.selenide.validator.company.reports.BillingReportValidator.assertHiddenCurrentMonthOnBillingReportPage;
import static com.practis.web.selenide.validator.company.reports.BillingReportValidator.assertSelectedMonthOnBillingReportPage;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertElementsOnPractisSetSummaryReportsPage;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertHiddenSearchFiledPsSummary;
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
import static com.practis.web.selenide.validator.company.reports.TeamLeaderEngagementReportValidator.assertElementsOnTeamLeaderEngagementReportsPage;
import static com.practis.web.selenide.validator.company.reports.TeamLeaderEngagementReportValidator.assertHiddenSearchFiledTlEngagement;
import static com.practis.web.selenide.validator.company.reports.TeamLeaderEngagementReportValidator.assertLabelsSearchResultTlEngagementReport;
import static com.practis.web.selenide.validator.company.reports.TeamLeaderEngagementReportValidator.assertTeamsSearchResultTlEngagementReport;
import static com.practis.web.selenide.validator.company.reports.UserActivityReportValidator.assertElementsOnUserActivityReportsPage;
import static com.practis.web.selenide.validator.company.reports.UserActivityReportValidator.assertHiddenSearchFiledUserActivity;
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

    @TestRailTest(caseId = 32051)
    @DisplayName("Reports: Reporting Screen: Check Elements for Admins")
    void checkElementsOnReportsPage() {

        assertElementsOnReportsPage();
    }

    @TestRailTest(caseId = 32052)
    @DisplayName("Reports: Practis Set Summary Report: Check Elements")
    void checkElementsOnPractisSetSummaryReportPage() {

        reportsService().clickOnPractisSetSummaryCard();
        assertElementsOnPractisSetSummaryReportsPage();
        assertHiddenSearchFiledPsSummary();
    }

    @TestRailTest(caseId = 32053)
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

    @TestRailTest(caseId = 32054)
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

    @TestRailTest(caseId = 32055)
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

    @TestRailTest(caseId = 32056)
    @DisplayName("Reports: Users Activity Report: Check Elements")
    void checkElementsOnUserActivityReportPage() {

        reportsService().clickOnUserActivityCard();
        assertElementsOnUserActivityReportsPage();
        assertHiddenSearchFiledUserActivity();
    }

    @TestRailTest(caseId = 32057)
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

    @TestRailTest(caseId = 32058)
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

    @TestRailTest(caseId = 32059)
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

    @TestRailTest(caseId = 32060)
    @DisplayName("Reports: Team Leader Activity Report: Check Elements")
    void checkElementsOnTlEngagementReportPage() {

        reportsService().clickOnTeamLeaderEngagementCard();
        assertElementsOnTeamLeaderEngagementReportsPage();
        assertHiddenSearchFiledTlEngagement();
    }

    @TestRailTest(caseId = 32061)
    @TeamExtension(count = 1)
    @DisplayName("Reports: Team Leader Activity Report: Teams: Search")
    void teamSearchOnTlEngagementReportPage(final List<NewTeamInput> team) {

        reportsService().clickOnTeamLeaderEngagementCard();
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
        assertTeamsSearchResultTlEngagementReport();
        reportsService().clickOnFirstModalSearchIcon();

        // Search should be performed after entering 1 character
        assertSearchAfter1CharReports(team.get(0).getName());
        await().pollDelay(TWO_SECONDS).until(() -> true);
        reportsService().clickOnFirstModalSearchIcon();
    }

    @TestRailTest(caseId = 32062)
    @LabelExtension(count = 1)
    @DisplayName("Reports: Team Leader Activity Report: Labels: Search")
    void labelsSearchOnTlEngagementReportPage(final List<RestCreateLabelResponse> label) {
        Selenide.refresh();

        reportsService().clickOnTeamLeaderEngagementCard();
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
        assertLabelsSearchResultTlEngagementReport();
        reportsService().clickOnClearSearch();

        // Search should be performed after entering 1 character
        assertSearchAfter1CharReports(label.get(0).getName());
        await().pollDelay(TWO_SECONDS).until(() -> true);
        reportsService().clickOnClearSearch();
    }

    @TestRailTest(caseId = 32063)
    @DisplayName("Reports: Team Leader Activity Report: Generate")
    void generateTlEngagementReport() {

        // Make Generate button enabled
        reportsService().clickOnTeamLeaderEngagementCard();
        userActivityReportService().clickOnTeamCheckbox();
        assertEnabledGenerateButtonReport();

        // Click on Clear button
        reportsService().clickOnClearButton();
        assertElementsOnTeamLeaderEngagementReportsPage();

        // CLick on Generate button
        teamLeaderEngagementReportService().clickOnTeamCheckbox();
        reportsService().clickOnGenerateButton();
        assertClickedDisabledGenerateButtonReport();

        snackbar()
                .getMessage()
                .shouldBe(
                        exactText(
                                "The report is being generated. Check your email in a few"
                                        + " minutes."));
    }

    @TestRailTest(caseId = 32064)
    @DisplayName("Reports: Billing Report: Check Elements")
    void checkElementsOnBillingReportPage() {

        reportsService().clickOnBillingCard();
        assertElementsOnBillingReportPage();
    }

    @TestRailTest(caseId = 32065)
    @DisplayName("Reports: Billing Report: Check Calendar Picker")
    void checkCalendarPickerOnBillingReportPage() {

        reportsService().clickOnBillingCard();
        billingReportService().clickOnPrevArrow();
        assertHiddenCurrentMonthOnBillingReportPage();
        billingReportService().clickOnNextArrow();
        assertElementsOnBillingReportPage();
    }

    @TestRailTest(caseId = 32066)
    @DisplayName("Reports: Billing Report: Generate")
    void generateBillingReportPage() {

        // Make Generate button enabled
        reportsService().clickOnBillingCard();
        billingReportService().clickOnMonth();
        assertEnabledGenerateButtonReport();

        // Click on Clear button
        reportsService().clickOnClearButton();
        assertElementsOnBillingReportPage();

        // CLick on Generate button
        billingReportService().clickOnMonth();
        assertSelectedMonthOnBillingReportPage();
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
