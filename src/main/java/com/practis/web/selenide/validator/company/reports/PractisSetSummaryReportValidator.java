package com.practis.web.selenide.validator.company.reports;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetSummaryReportPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.reportsPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.practisSetSummaryReportService;

public class PractisSetSummaryReportValidator {
    /** Assert elements on Practis Set Summary Reports page. */
    public static void assertElementsOnPractisSetSummaryReportsPage() {
        practisSetSummaryReportPage().getReportTitle().shouldBe(visible);
        practisSetSummaryReportPage().getReportTitle().shouldBe(exactText("Reports"));
        practisSetSummaryReportPage().getReportSubtitle().shouldBe(visible);
        practisSetSummaryReportPage()
                .getReportSubtitle()
                .shouldBe(exactText("Practis Set Summary"));
        practisSetSummaryReportPage().getPageDescription().shouldBe(visible);
        practisSetSummaryReportPage()
                .getPageDescription()
                .shouldBe(exactText("Understand progress for a specific Practis Set."));
        practisSetSummaryReportPage().getBackNavigation().shouldBe(visible);

        practisSetSummaryReportPage().getFilterSearchIcon().get(0).shouldBe(visible);

        practisSetSummaryReportPage().getTeamTitle().get(0).shouldBe(visible);
        practisSetSummaryReportPage().getTeamRadioButtonView().get(0).shouldBe(visible);
        practisSetSummaryReportPage()
                .getTeamRadioButtonView()
                .get(0)
                .shouldBe(attribute("size", "12"));
        practisSetSummaryReportPage().getTeamSelectedRadioButton().get(0).shouldBe(hidden);

        practisSetSummaryReportPage().getPractisSetEmptyStateIcon().shouldBe(visible);
        practisSetSummaryReportPage().getPractisSetEmptyStateText().shouldBe(visible);
        practisSetSummaryReportPage()
                .getPractisSetEmptyStateText()
                .shouldBe(exactText("Select the team to see Practis Sets"));
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
    public static void assertHiddenSearchFiledTeam() {
        practisSetSummaryReportPage().getFilterSearchField().get(0).shouldBe(hidden);
        practisSetSummaryReportPage().getTeamFilterTitle().shouldBe(visible);
        practisSetSummaryReportPage().getTeamFilterTitle().shouldBe(exactText("Team"));
        practisSetSummaryReportPage().getTeamTitle().get(0).shouldBe(exactText("All Members"));
    }

    /** Assert visible search field. */
    public static void assertVisibleSearchField() {
        practisSetSummaryReportPage().getFilterSearchField().get(0).shouldBe(visible);
        practisSetSummaryReportPage()
                .getFilterSearchField()
                .get(0)
                .shouldBe(attribute("placeholder", "Search"));
        practisSetSummaryReportPage()
                .getFilterSearchField()
                .get(0)
                .shouldBe(attribute("font-size", "13px"));
        practisSetSummaryReportPage()
                .getFilterSearchField()
                .get(0)
                .shouldBe(attribute("type", "text"));
    }

    /** Assert clear search. */
    public static void assertCleanSearchPractisSetSummaryReport() {
        practisSetSummaryReportPage().getFilterSearchClear().get(0).shouldBe(hidden);
        practisSetSummaryReportPage().getFilterSearchField().get(0).append(("check clean icon"));
        practisSetSummaryReportPage().getFilterSearchClear().get(0).shouldBe(visible);
        practisSetSummaryReportService().clickOnClearSearchTeamModal();
        practisSetSummaryReportPage().getFilterSearchClear().get(0).shouldBe(hidden);
    }

    /** Assert Search should be performed after entering 1 characters. */
    public static void assertSearchAfter1CharPractisSetSummaryReport(final String searchString) {
        final var input = searchString.charAt(searchString.length() - 1);
        practisSetSummaryReportPage().getFilterSearchField().get(0).append(String.valueOf(input));
        practisSetSummaryReportPage().getFilterSearchClear().get(0).shouldBe(visible);
        practisSetSummaryReportPage().getTeamTitle().get(0).shouldBe(visible);
    }

    /** Assert no search results - Team. */
    public static void assertNoTeamsSearchResultPractisSetSummaryReport() {
        practisSetSummaryReportPage().getFilterSearchClear().get(0).shouldBe(visible);
        practisSetSummaryReportPage().getTeamNotFoundIcon().shouldBe(visible);
        practisSetSummaryReportPage().getTeamNotFoundText().shouldBe(visible);
        practisSetSummaryReportPage()
                .getTeamNotFoundText()
                .shouldBe(exactText("No Search Results"));
    }

    /** Assert search results - Team */
    public static void assertSearchResultPractisSetSummaryReport() {
        assertElementsOnPractisSetSummaryReportsPage();
        practisSetSummaryReportPage().getFilterSearchClear().get(0).shouldBe(visible);
    }

    /** Assert no search results - PS. */
    public static void assertNoPsSearchResultPractisSetSummaryReport() {
        practisSetSummaryReportPage().getFilterSearchClear().get(0).shouldBe(visible);
        practisSetSummaryReportPage().getPractisSetNoSearchStateText().shouldBe(visible);
        practisSetSummaryReportPage().getPractisSetEmptyStateIcon().shouldBe(visible);
        practisSetSummaryReportPage()
                .getPractisSetNoSearchStateText()
                .shouldBe(exactText("No Search Result"));
    }

    /** Assert search results - PS */
    public static void assertPsSearchResultPractisSetSummaryReport() {
        practisSetSummaryReportPage().getPractisSetFilterTitle().get(0).shouldBe(visible);
        practisSetSummaryReportPage().getPractisSetRadioButton().get(0).shouldBe(visible);
        practisSetSummaryReportPage().getFilterSearchClear().get(0).shouldBe(visible);
    }

    /** Assert enabled Generate Button */
    public static void assertEnabledGenerateButtonPractisSetSummaryReport() {
        reportsPage().getGenerateReportButton().shouldBe(visible);
        reportsPage().getGenerateReportButton().shouldBe(enabled);
        reportsPage().getGenerateReportButton().shouldBe(attribute("color", "default"));
        reportsPage().getGenerateReportButton().shouldBe(attribute("width", "186px"));
        reportsPage().getGenerateReportButton().shouldBe(exactText("Generate"));
        reportsPage().getGenerateReportButton().shouldBe(attribute("type", "submit"));
    }

    /** Assert enabled Generate Button */
    public static void assertGenerateButtonClickedPractisSetSummaryReport() {
        reportsPage().getGenerateReportButton().shouldBe(visible);
        reportsPage().getGenerateReportButton().shouldBe(attribute("disabled"));
        reportsPage().getGenerateReportButton().shouldBe(attribute("color", "gray"));
        reportsPage().getGenerateReportButton().shouldBe(attribute("width", "186px"));
        reportsPage().getGenerateReportButton().shouldBe(matchText("Generate in"));
        reportsPage().getGenerateReportButton().shouldBe(attribute("type", "submit"));
    }
}
