package com.practis.web.selenide.page.company.reports;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ReportsPage {

    private final SelenideElement reportsTitle = $("div[data-test='reports-page-subtitle']");

    private final SelenideElement practisSetSummaryCard =
            $("a[data-test='practis-set-summary-link']");
    private final SelenideElement practisSetSummaryIcon =
            $("span[data-test='practis-set-summary-icon']");
    private final SelenideElement practisSetSummaryTitle =
            $("div[data-test='practis-set-summary-title']");
    private final SelenideElement practisSetSummaryText =
            $("div[data-test='practis-set-summary-description']");
    private final SelenideElement practisSetSummaryExcelButton =
            $("div[data-test='practis-set-summary-excel']");

    private final SelenideElement userActivityCard = $("a[data-test='user-activity-link']");
    private final SelenideElement userActivityIcon = $("span[data-test='user-activity-icon']");
    private final SelenideElement userActivityTitle = $("div[data-test='user-activity-title']");
    private final SelenideElement userActivityText =
            $("div[data-test='user-activity-description']");
    private final SelenideElement userActivityExcelButton =
            $("div[data-test='user-activity-excel']");

    private final SelenideElement teamLeaderSummaryCard =
            $("a[data-test='team-leader-summary-link']");
    private final SelenideElement teamLeaderSummaryIcon =
            $("span[data-test='team-leader-summary-icon']");
    private final SelenideElement teamLeaderSummaryTitle =
            $("div[data-test='team-leader-summary-title']");
    private final SelenideElement teamLeaderSummaryText =
            $("div[data-test='team-leader-summary-description']");
    private final SelenideElement teamLeaderSummaryExcelButton =
            $("div[data-test='practis-set-summary-excel']");

    private final SelenideElement generateReportButton =
            $("button[data-test='generate-report-button']");
    private final SelenideElement clearReportButton = $("button[data-test='clear-report-button']");

    private final SelenideElement emailText = $("div[data-test='report-email-info']");
}
