package com.practis.web.selenide.validator.company.reports;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.reportsPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.userActivityReportPage;
import static com.practis.web.selenide.validator.company.reports.ReportsValidator.assertDisabledGenerateButtonReport;

public class UserActivityReportValidator {

    /** Assert elements on User Activity Reports page. */
    public static void assertElementsOnUserActivityReportsPage() {
        userActivityReportPage().getReportTitle().shouldBe(visible);
        userActivityReportPage().getReportTitle().shouldBe(exactText("Reports"));
        userActivityReportPage().getReportSubtitle().shouldBe(visible);
        userActivityReportPage().getReportSubtitle().shouldBe(exactText("User Activity"));
        userActivityReportPage().getPageDescription().shouldBe(visible);
        userActivityReportPage()
                .getPageDescription()
                .shouldBe(exactText("Understand your team(s) progress on all their Practis Sets."));
        userActivityReportPage().getBackNavigation().shouldBe(visible);

        reportsPage().getFilterSearchIcon().get(0).shouldBe(visible);

        userActivityReportPage().getTeamsSelectionText().shouldBe(visible);
        userActivityReportPage().getTeamsSelectionText().shouldBe(exactText("No Items selected"));
        userActivityReportPage().getTeamsSelectAllButton().shouldBe(visible);
        userActivityReportPage().getTeamsCheckboxButton().get(0).shouldBe(visible);
        userActivityReportPage().getTeamsCheckboxButton().get(0).shouldBe(attribute("size", "12"));
        userActivityReportPage().getTeamsSelectedCheckboxButton().get(0).shouldBe(hidden);

        reportsPage().getNoLabelsIcon().shouldBe(visible);
        reportsPage().getNoLabelsText().shouldBe(visible);
        reportsPage().getNoLabelsText().shouldBe(exactText("No Labels yet"));

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
    public static void assertHiddenSearchFiledUserActivity() {
        reportsPage().getFilterSearchField().get(0).shouldBe(hidden);
        userActivityReportPage().getTeamsFilterTitle().shouldBe(visible);
        userActivityReportPage().getTeamsFilterTitle().shouldBe(exactText("Teams"));
        userActivityReportPage().getTeamsTitle().get(0).shouldBe(exactText("All Members"));
    }

    /** Assert search results - Team */
    public static void assertTeamsSearchResultUserActivityReport() {
        assertElementsOnUserActivityReportsPage();
        reportsPage().getFilterSearchClear().get(1).shouldBe(visible);
    }

    /** Assert search results - Labels */
    public static void assertLabelsSearchResultUserActivityReport() {
        assertDisabledGenerateButtonReport();
        userActivityReportPage().getReportTitle().shouldBe(visible);
        userActivityReportPage().getReportTitle().shouldBe(exactText("Reports"));
        userActivityReportPage().getReportSubtitle().shouldBe(visible);
        userActivityReportPage().getReportSubtitle().shouldBe(exactText("User Activity"));
        userActivityReportPage().getPageDescription().shouldBe(visible);
        userActivityReportPage()
                .getPageDescription()
                .shouldBe(exactText("Understand your team(s) progress on all their Practis Sets."));
        userActivityReportPage().getBackNavigation().shouldBe(visible);
        userActivityReportPage().getTeamsSelectionText().shouldBe(visible);
        userActivityReportPage().getTeamsSelectionText().shouldBe(exactText("No Items selected"));
        userActivityReportPage().getTeamsSelectAllButton().shouldBe(visible);
        userActivityReportPage().getTeamsSelectAllButton().shouldBe(exactText("Select All"));
        userActivityReportPage().getLabelsSelectAllButton().shouldBe(visible);
        userActivityReportPage().getLabelsSelectAllButton().shouldBe(exactText("Select All"));
        userActivityReportPage().getTeamsCheckboxButton().get(0).shouldBe(visible);
        userActivityReportPage().getTeamsCheckboxButton().get(0).shouldBe(attribute("size", "12"));
        userActivityReportPage().getTeamsSelectedCheckboxButton().get(0).shouldBe(hidden);
        reportsPage().getNoLabelsIcon().shouldBe(hidden);
        reportsPage().getNoLabelsText().shouldBe(hidden);
        reportsPage().getFilterSearchIcon().get(0).shouldBe(visible);
        reportsPage().getFilterSearchClear().get(1).shouldBe(visible);
    }
}
