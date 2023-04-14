package com.practis.web.selenide.page.company.reports;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ReportsPage {

    private final SelenideElement reportsTitle = $("div[data-test='practis-set-page-title']");

    private final SelenideElement practisSetSummaryIcon =
            $("span[data-test='practis-set-not-published-yet']");
    private final SelenideElement practisSetSummaryTitle =
            $("input[data-test='practis-set-title']");
    private final SelenideElement practisSetSummaryText = $("input[data-test='practis-set-title']");

    private final SelenideElement userActivityIcon =
            $("span[data-test='practis-set-not-published-yet']");
    private final SelenideElement userActivityTitle = $("input[data-test='practis-set-title']");
    private final SelenideElement userActivityText = $("input[data-test='practis-set-title']");

    private final SelenideElement teamLeaderSummaryIcon =
            $("span[data-test='practis-set-not-published-yet']");
    private final SelenideElement teamLeaderSummaryTitle =
            $("input[data-test='practis-set-title']");
    private final SelenideElement teamLeaderSummaryText = $("input[data-test='practis-set-title']");

    private final ElementsCollection excelText =
            $$("button[data-test='save-practis-set-as-draft']");
}
