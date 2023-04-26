package com.practis.web.selenide.page.company.reports;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class UserActivityReportPage {

    private final SelenideElement reportTitle =
            $("div[data-test='user-activity-report-page-title']");
    private final SelenideElement reportSubtitle =
            $("div[data-test='user-activity-report-page-subtitle']");
    private final SelenideElement pageDescription = $(".sc-hIvYlk.Cawbr");
    private final SelenideElement backNavigation = $("div[data-test='back-arrow-button']");

    private final SelenideElement teamsFilterTitle = $("div[data-test='teams-filter-title']");
    private final ElementsCollection teamsTitle =
            $$("div[data-test='team-filter-item-checkbox-label']");
    private final ElementsCollection teamsCheckboxButton =
            $$("div[data-test='team-filter-item-checkbox-view']");
    private final ElementsCollection teamsSelectedCheckboxButton =
            $$("input[data-test='team-filter-item-checkbox-checked']");
    private final SelenideElement teamsSelectionText =
            $("div[data-test='teams-selection-counter']");
    private final SelenideElement teamsSelectAllButton = $("span[data-test='teams-select-all']");
    private final SelenideElement teamsUnselectAllButton =
            $("span[data-test='teams-unselect-all']");

    private final SelenideElement labelsSelectAllButton = $("span[data-test='labels-select-all']");
    private final SelenideElement labelsUnselectAllButton =
            $("span[data-test='labels-unselect-all']");
    private final ElementsCollection labelsFilterTitle = $$("div[data-test='labels-filter-title']");
    private final ElementsCollection labelsCheckboxButton =
            $$("div[data-test='label-filter-item-checkbox-view']");
    private final ElementsCollection labelsTitle = $$("div[data-test='label-filter-item-name']");
}
