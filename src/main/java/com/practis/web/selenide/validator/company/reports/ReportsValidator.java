package com.practis.web.selenide.validator.company.reports;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.reportsPage;

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

        reportsPage().getTeamLeaderSummaryCard().shouldBe(visible);
        reportsPage().getTeamLeaderSummaryIcon().shouldBe(visible);
        reportsPage().getTeamLeaderSummaryTitle().shouldBe(visible);
        reportsPage().getTeamLeaderSummaryTitle().shouldBe(exactText("Team Leader Summary"));
        reportsPage().getTeamLeaderSummaryText().shouldBe(visible);
        reportsPage()
                .getTeamLeaderSummaryText()
                .shouldBe(exactText("How engaged are Team Leaders in their learner's training."));
        reportsPage().getTeamLeaderSummaryExcelButton().shouldBe(visible);
        reportsPage().getTeamLeaderSummaryExcelButton().shouldBe(exactText("Excel"));

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
    }
}
