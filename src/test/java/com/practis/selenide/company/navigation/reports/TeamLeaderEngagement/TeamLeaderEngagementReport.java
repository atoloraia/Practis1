package com.practis.selenide.company.navigation.reports.TeamLeaderEngagement;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.reportsService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.searchService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamLeaderEngagementReportService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userActivityReportService;
import static com.practis.web.selenide.validator.company.reports.ReportsValidator.assertCleanSearch;
import static com.practis.web.selenide.validator.company.reports.ReportsValidator.assertEnabledGenerateButtonReport;
import static com.practis.web.selenide.validator.company.reports.ReportsValidator.assertNoLabelsSearchResultReports;
import static com.practis.web.selenide.validator.company.reports.ReportsValidator.assertNoTeamsSearchResultReports;
import static com.practis.web.selenide.validator.company.reports.ReportsValidator.assertSearchAfter1CharReports;
import static com.practis.web.selenide.validator.company.reports.ReportsValidator.assertVisibleSearchField;
import static com.practis.web.selenide.validator.company.reports.TeamLeaderEngagementReportValidator.assertClearedDatePicker;
import static com.practis.web.selenide.validator.company.reports.TeamLeaderEngagementReportValidator.assertElementsOnTeamLeaderEngagementReportsPage;
import static com.practis.web.selenide.validator.company.reports.TeamLeaderEngagementReportValidator.assertHiddenSearchFiledTlEngagement;
import static com.practis.web.selenide.validator.company.reports.TeamLeaderEngagementReportValidator.assertLabelsSearchResultTlEngagementReport;
import static com.practis.web.selenide.validator.company.reports.TeamLeaderEngagementReportValidator.assertTeamsSearchResultTlEngagementReport;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewTeamInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.LabelExtension;
import com.practis.support.extension.practis.TeamExtension;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
class TeamLeaderEngagementReport {

    @BeforeEach
    void init() {
        navigationCompany().getReportsItem().click();
        reportsService().clickOnTeamLeaderEngagementCard();
    }

    @TestRailTest(caseId = 32060)
    @DisplayName("Reports: Team Leader Engagement Report: Check Elements")
    void checkElementsOnTlEngagementReportPage() {

        assertElementsOnTeamLeaderEngagementReportsPage();
        assertHiddenSearchFiledTlEngagement();
    }

    @TestRailTest(caseId = 32061)
    @TeamExtension(count = 1)
    @DisplayName("Reports: Team Leader Engagement Report: Teams: Search")
    void teamSearchOnTlEngagementReportPage(final List<NewTeamInput> team) {

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
    @DisplayName("Reports: Team Leader Engagement Report: Labels: Search")
    void labelsSearchOnTlEngagementReportPage(final List<RestCreateLabelResponse> label) {
        Selenide.refresh();

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
    @DisplayName("Reports: Team Leader Engagement Report: Generate")
    void generateTlEngagementReport() {

        // Make Generate button enabled
        userActivityReportService().clickOnTeamCheckbox();
        assertEnabledGenerateButtonReport();

        // Click on Clear button
        reportsService().clickOnClearButton();
        assertElementsOnTeamLeaderEngagementReportsPage();

        // CLick on Generate button
        teamLeaderEngagementReportService().clickOnTeamCheckbox();
        reportsService().clickOnGenerateButton();
        assertEnabledGenerateButtonReport();

        snackbar()
                .getMessage()
                .shouldBe(
                        exactText(
                                "The report is being generated. Check your email in a few"
                                        + " minutes."));
    }

    @TestRailTest(caseId = 32303)
    @DisplayName("Reports: Team Leader Engagement Report: Verify Date Picker")
    void checkDatePicker() {
        // Select Date
        teamLeaderEngagementReportService().selectDatesOnTlEngagementReport();

        // CLick on Generate button
        teamLeaderEngagementReportService().clickOnTeamCheckbox();
        reportsService().clickOnGenerateButton();
        assertEnabledGenerateButtonReport();

        snackbar()
                .getMessage()
                .shouldBe(
                        exactText(
                                "The report is being generated. Check your email in a few"
                                        + " minutes."));

        // Click on Clear
        teamLeaderEngagementReportService().clickOnClear();
        teamLeaderEngagementReportService().clickOnTeamCheckbox();
        assertClearedDatePicker();
    }
}
