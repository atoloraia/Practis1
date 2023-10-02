package com.practis.web.selenide.validator.company.reports;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.reportsPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamLeaderEngagementReportPage;
import static com.practis.web.selenide.validator.company.reports.ReportsValidator.assertDisabledGenerateButtonReport;

public class TeamLeaderEngagementReportValidator {

    /** Assert elements on Practis Set Summary Reports page. */
    public static void assertElementsOnTeamLeaderEngagementReportsPage() {
        teamLeaderEngagementReportPage().getReportTitle().shouldBe(visible);
        teamLeaderEngagementReportPage().getReportTitle().shouldBe(exactText("Reports"));
        teamLeaderEngagementReportPage().getReportSubtitle().shouldBe(visible);
        teamLeaderEngagementReportPage()
                .getReportSubtitle()
                .shouldBe(exactText("Team Leader Engagement"));
        teamLeaderEngagementReportPage().getPageDescription().shouldBe(visible);
        teamLeaderEngagementReportPage()
                .getPageDescription()
                .shouldBe(exactText("How engaged are Team Leaders in their learner's training."));
        teamLeaderEngagementReportPage().getBackNavigation().shouldBe(visible);

        reportsPage().getFilterSearchIcon().get(0).shouldBe(visible);

        teamLeaderEngagementReportPage().getTeamsSelectionText().shouldBe(visible);
        teamLeaderEngagementReportPage()
                .getTeamsSelectionText()
                .shouldBe(exactText("No Items selected"));
        teamLeaderEngagementReportPage().getTeamsSelectAllButton().shouldBe(visible);
        teamLeaderEngagementReportPage().getTeamsCheckboxButton().get(0).shouldBe(visible);
        teamLeaderEngagementReportPage()
                .getTeamsCheckboxButton()
                .get(0)
                .shouldBe(attribute("size", "12"));
        teamLeaderEngagementReportPage().getTeamsSelectedCheckboxButton().get(0).shouldBe(hidden);

        // Assert Calendar
        teamLeaderEngagementReportPage().getDateRangeFilterTitle().shouldBe(visible);
        teamLeaderEngagementReportPage()
                .getDateRangeFilterTitle()
                .shouldBe(exactText("Date Range"));
        teamLeaderEngagementReportPage().getDateRangeFilterSubtitle().shouldBe(visible);
        teamLeaderEngagementReportPage()
                .getDateRangeFilterSubtitle()
                .shouldBe(exactText("MM/DD/YY – MM/DD/YY"));
        teamLeaderEngagementReportPage().getCalendarContainer().shouldBe(visible);
        teamLeaderEngagementReportPage().getCalendarWeekendDates().get(0).shouldBe(visible);
        teamLeaderEngagementReportPage().getTodayDate().shouldBe(visible);
        teamLeaderEngagementReportPage().getCalendarDates().get(0).shouldBe(visible);
        teamLeaderEngagementReportPage().getCalendarNavigation().shouldBe(visible);

        reportsPage().getGenerateReportButton().shouldBe(visible);
        reportsPage().getGenerateReportButton().shouldBe(attribute("disabled"));
        reportsPage().getGenerateReportButton().shouldBe(attribute("color", "gray"));
        reportsPage().getGenerateReportButton().shouldBe(attribute("width", "186px"));
        reportsPage().getGenerateReportButton().shouldBe(exactText("Generate"));
        reportsPage().getGenerateReportButton().shouldBe(attribute("type", "submit"));

        reportsPage().getClearReportButton().shouldBe(visible);
        reportsPage().getClearReportButton().shouldBe(attribute("disabled"));
        reportsPage().getClearReportButton().shouldBe(attribute("color", "gray"));
        reportsPage().getClearReportButton().shouldBe(attribute("width", "110px"));
        reportsPage().getClearReportButton().shouldBe(exactText("Clear"));
        reportsPage().getClearReportButton().shouldBe(attribute("type", "submit"));

        reportsPage().getEmailText().shouldBe(visible);
        reportsPage().getEmailText().shouldBe(matchText("Report will be sent to"));
    }

    /** Assert hidden search field. */
    public static void assertHiddenSearchFiledTlEngagement() {
        reportsPage().getFilterSearchField().get(0).shouldBe(hidden);
        teamLeaderEngagementReportPage().getTeamsFilterTitle().shouldBe(visible);
        teamLeaderEngagementReportPage().getTeamsFilterTitle().shouldBe(exactText("Teams"));
        teamLeaderEngagementReportPage().getTeamsTitle().get(0).shouldBe(exactText("All Members"));
        teamLeaderEngagementReportPage().getLabelsFilterTitle().shouldBe(visible);
        teamLeaderEngagementReportPage().getLabelsFilterTitle().shouldBe(exactText("Labels"));
    }

    /** Assert search results - Team */
    public static void assertTeamsSearchResultTlEngagementReport() {
        assertElementsOnTeamLeaderEngagementReportsPage();
        reportsPage().getFilterSearchClear().get(1).shouldBe(visible);
    }

    /** Assert search results - Labels */
    public static void assertLabelsSearchResultTlEngagementReport() {
        assertDisabledGenerateButtonReport();
        teamLeaderEngagementReportPage().getReportTitle().shouldBe(visible);
        teamLeaderEngagementReportPage().getReportTitle().shouldBe(exactText("Reports"));
        teamLeaderEngagementReportPage().getReportSubtitle().shouldBe(visible);
        teamLeaderEngagementReportPage()
                .getReportSubtitle()
                .shouldBe(exactText("Team Leader Engagement"));
        teamLeaderEngagementReportPage().getPageDescription().shouldBe(visible);
        teamLeaderEngagementReportPage()
                .getPageDescription()
                .shouldBe(exactText("How engaged are Team Leaders in their learner's training."));
        teamLeaderEngagementReportPage().getBackNavigation().shouldBe(visible);
        teamLeaderEngagementReportPage().getTeamsSelectionText().shouldBe(visible);
        teamLeaderEngagementReportPage()
                .getTeamsSelectionText()
                .shouldBe(exactText("No Items selected"));
        teamLeaderEngagementReportPage().getTeamsSelectAllButton().shouldBe(visible);
        teamLeaderEngagementReportPage()
                .getTeamsSelectAllButton()
                .shouldBe(exactText("Select All"));
        teamLeaderEngagementReportPage().getLabelsSelectAllButton().shouldBe(visible);
        teamLeaderEngagementReportPage()
                .getLabelsSelectAllButton()
                .shouldBe(exactText("Select All"));
        teamLeaderEngagementReportPage().getTeamsCheckboxButton().get(0).shouldBe(visible);
        teamLeaderEngagementReportPage()
                .getTeamsCheckboxButton()
                .get(0)
                .shouldBe(attribute("size", "12"));
        teamLeaderEngagementReportPage().getTeamsSelectedCheckboxButton().get(0).shouldBe(hidden);
        reportsPage().getNoLabelsIcon().shouldBe(hidden);
        reportsPage().getNoLabelsText().shouldBe(hidden);
        reportsPage().getFilterSearchIcon().get(0).shouldBe(visible);
        reportsPage().getFilterSearchClear().get(1).shouldBe(visible);
    }

    /** Assert search results - Team */
    public static void assertSelectedDatePicker() {
        teamLeaderEngagementReportPage().getSelectedDates().get(0).shouldBe(visible);
        teamLeaderEngagementReportPage()
                .getDateRangeFilterSubtitle()
                .shouldNotBe(exactText("MM/DD/YY – MM/DD/YY"));
        reportsPage().getGenerateReportButton().shouldBe(visible);
        reportsPage().getGenerateReportButton().shouldBe(attribute("disabled"));
        reportsPage().getGenerateReportButton().shouldBe(attribute("color", "gray"));
        reportsPage().getGenerateReportButton().shouldBe(attribute("width", "186px"));
        reportsPage().getGenerateReportButton().shouldBe(exactText("Generate"));
        reportsPage().getGenerateReportButton().shouldBe(attribute("type", "submit"));

        reportsPage().getClearReportButton().shouldBe(visible);
        reportsPage().getClearReportButton().shouldBe(attribute("disabled"));
        reportsPage().getClearReportButton().shouldBe(attribute("color", "gray"));
        reportsPage().getClearReportButton().shouldBe(attribute("width", "110px"));
        reportsPage().getClearReportButton().shouldBe(exactText("Clear"));
        reportsPage().getClearReportButton().shouldBe(attribute("type", "submit"));
    }
}
