package com.practis.web.selenide.page.company.reports;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
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

    private final SelenideElement teamLeaderEngagementCard =
            $("a[data-test='team-leader-engagement-link']");
    private final SelenideElement teamLeaderEngagementIcon =
            $("span[data-test='team-leader-engagement-icon']");
    private final SelenideElement teamLeaderEngagementTitle =
            $("div[data-test='team-leader-engagement-title']");
    private final SelenideElement teamLeaderEngagementText =
            $("div[data-test='team-leader-engagement-description']");
    private final SelenideElement teamLeaderEngagementExcelButton =
            $("div[data-test='team-leader-engagement-excel']");

    private final SelenideElement teamNotFoundIcon = $("div[data-test='no-teams-icon']");
    private final SelenideElement teamNotFoundText = $("div[data-test='no-teams-label']");

    private final SelenideElement noLabelsIcon = $("div[data-test='no-labels-icon']");
    private final SelenideElement noLabelsText = $("div[data-test='no-labels-label']");

    private final ElementsCollection filterSearchIcon = $$("div[data-test='filter-search-icon']");
    private final ElementsCollection filterSearchField =
            $$("input[data-test='filter-search-input']");
    private final ElementsCollection filterSearchClear =
            $$("div[data-test='filter-search-input-clear']");
    private final ElementsCollection filterSearchClose =
            $$("div[data-test='filter-search-input-close']");

    private final SelenideElement generateReportButton =
            $("button[data-test='generate-report-button']");
    private final SelenideElement clearReportButton = $("button[data-test='clear-report-button']");

    private final SelenideElement emailText = $("div[data-test='report-email-info']");
}
