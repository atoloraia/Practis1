package com.practis.web.selenide.validator.company.reports;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.reportsPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.reportsService;

public class ReportsValidator {

    /** Assert elements on Reports page. */
    public static void assertElementsOnReportsPage() {
        reportsPage().getReportsTitle().shouldBe(visible);
        reportsPage().getReportsTitle().shouldBe(exactText("Reports"));

        reportsPage().getPractisSetSummaryCard().shouldBe(visible);
        reportsPage().getPractisSetSummaryIcon().shouldBe(visible);
        reportsPage().getPractisSetSummaryTitle().shouldBe(visible);
        reportsPage().getPractisSetSummaryTitle().shouldBe(exactText("Practis Set Summary"));
        reportsPage().getPractisSetSummaryText().shouldBe(visible);
        reportsPage()
                .getPractisSetSummaryText()
                .shouldBe(exactText("Shows more information about a particular practis set."));
        reportsPage().getPractisSetSummaryExcelButton().shouldBe(visible);
        reportsPage().getPractisSetSummaryExcelButton().shouldBe(exactText("Excel"));

        reportsPage().getTeamLeaderEngagementCard().shouldBe(visible);
        reportsPage().getTeamLeaderEngagementIcon().shouldBe(visible);
        reportsPage().getTeamLeaderEngagementTitle().shouldBe(visible);
        reportsPage().getTeamLeaderEngagementTitle().shouldBe(exactText("Team Leader Engagement"));
        reportsPage().getTeamLeaderEngagementText().shouldBe(visible);
        reportsPage()
                .getTeamLeaderEngagementText()
                .shouldBe(exactText("How engaged are Team Leaders in their learner's training."));
        reportsPage().getTeamLeaderEngagementExcelButton().shouldBe(visible);
        reportsPage().getTeamLeaderEngagementExcelButton().shouldBe(exactText("Excel"));

        reportsPage().getUserActivityCard().shouldBe(visible);
        reportsPage().getUserActivityIcon().shouldBe(visible);
        reportsPage().getUserActivityTitle().shouldBe(visible);
        reportsPage().getUserActivityTitle().shouldBe(exactText("User Activity"));
        reportsPage().getUserActivityText().shouldBe(visible);
        reportsPage()
                .getUserActivityText()
                .shouldBe(exactText("Understand your team(s) progress on all their Practis Sets."));
        reportsPage().getUserActivityExcelButton().shouldBe(visible);
        reportsPage().getUserActivityExcelButton().shouldBe(exactText("Excel"));

        reportsPage().getBillingCard().shouldBe(visible);
        reportsPage().getBillingIcon().shouldBe(visible);
        reportsPage().getBillingTitle().shouldBe(visible);
        reportsPage().getBillingTitle().shouldBe(exactText("Billing Report"));
        reportsPage().getBillingText().shouldBe(visible);
        reportsPage()
            .getBillingText()
            .shouldBe(exactText("Generate a list of monthly active users on the platform."));
        reportsPage().getBillingExcelButton().shouldBe(visible);
        reportsPage().getBillingExcelButton().shouldBe(exactText("Excel"));
    }

    /** Assert visible search field. */
    public static void assertVisibleSearchField() {
        reportsPage().getFilterSearchField().get(0).shouldBe(visible);
        reportsPage().getFilterSearchClose().get(0).shouldBe(visible);
        reportsPage().getFilterSearchField().get(0).shouldBe(attribute("placeholder", "Search"));
        reportsPage().getFilterSearchField().get(0).shouldBe(attribute("font-size", "13px"));
        reportsPage().getFilterSearchField().get(0).shouldBe(attribute("type", "text"));
    }

    /** Assert clear search. */
    public static void assertCleanSearch() {
        reportsPage().getFilterSearchClear().get(1).shouldBe(hidden);
        reportsPage().getFilterSearchField().get(0).append(("check clean icon"));
        reportsPage().getFilterSearchClear().get(1).shouldBe(visible);
        reportsPage().getFilterSearchClear().get(1).shouldBe(exactText("Clear"));
        reportsPage().getFilterSearchClose().get(0).shouldBe(visible);
        reportsService().clickOnClearSearch();
        reportsPage().getFilterSearchClear().get(1).shouldBe(hidden);
        reportsPage().getFilterSearchClose().get(0).shouldBe(visible);
    }

    /** Assert Search should be performed after entering 1 characters. */
    public static void assertSearchAfter1CharReports(final String searchString) {
        final var input = searchString.charAt(searchString.length() - 1);
        reportsPage().getFilterSearchField().get(0).append(String.valueOf(input));
        reportsPage().getFilterSearchClear().get(1).shouldBe(visible);
        reportsPage().getFilterSearchClose().get(0).shouldBe(visible);
    }

    /** Assert no search results - Team. */
    public static void assertNoTeamsSearchResultReports() {
        reportsPage().getFilterSearchClear().get(1).shouldBe(visible);
        reportsPage().getFilterSearchClose().get(0).shouldBe(visible);
        reportsPage().getTeamNotFoundIcon().shouldBe(visible);
        reportsPage().getTeamNotFoundText().shouldBe(visible);
        reportsPage().getTeamNotFoundText().shouldBe(exactText("No Search Results"));
    }

    /** Assert enabled Generate Button */
    public static void assertEnabledGenerateButtonReport() {
        reportsPage().getGenerateReportButton().shouldBe(visible);
        reportsPage().getGenerateReportButton().shouldBe(enabled);
        reportsPage().getGenerateReportButton().shouldBe(attribute("color", "default"));
        reportsPage().getGenerateReportButton().shouldBe(attribute("width", "186px"));
        reportsPage().getGenerateReportButton().shouldBe(exactText("Generate"));
        reportsPage().getGenerateReportButton().shouldBe(attribute("type", "submit"));
    }

    /** Assert clicked disabled Generate Button */
    public static void assertClickedDisabledGenerateButtonReport() {
        reportsPage().getGenerateReportButton().shouldBe(visible);
        reportsPage().getGenerateReportButton().shouldBe(attribute("disabled"));
        reportsPage().getGenerateReportButton().shouldBe(attribute("color", "gray"));
        reportsPage().getGenerateReportButton().shouldBe(attribute("width", "186px"));
        reportsPage().getGenerateReportButton().shouldBe(matchText("Generate in"));
        reportsPage().getGenerateReportButton().shouldBe(attribute("type", "submit"));
    }

    /** Assert disabled Generate Button */
    public static void assertDisabledGenerateButtonReport() {
        reportsPage().getGenerateReportButton().shouldBe(visible);
        reportsPage().getGenerateReportButton().shouldBe(attribute("disabled"));
        reportsPage().getGenerateReportButton().shouldBe(attribute("color", "gray"));
        reportsPage().getGenerateReportButton().shouldBe(attribute("width", "186px"));
        reportsPage().getGenerateReportButton().shouldBe(matchText("Generate"));
        reportsPage().getGenerateReportButton().shouldBe(attribute("type", "submit"));
    }

    /** Assert no search results - Labels. */
    public static void assertNoLabelsSearchResultReports() {
        reportsPage().getFilterSearchClear().get(1).shouldBe(visible);
        reportsPage().getFilterSearchClose().get(0).shouldBe(visible);
        reportsPage().getNoLabelsIcon().shouldBe(visible);
        reportsPage().getNoLabelsText().shouldBe(visible);
        reportsPage().getNoLabelsText().shouldBe(exactText("No Search Results"));
    }
}
