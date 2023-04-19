package com.practis.web.selenide.page.company.reports;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class PractisSetSummaryReportPage {

    private final SelenideElement reportTitle =
            $("div[data-test='practis-set-summary-page-title']");
    private final SelenideElement reportSubtitle =
            $("div[data-test='practis-set-summary-page-subtitle']");
    private final SelenideElement pageDescription =
            $("div[data-test='practis-set-summary-page-description']");
    private final SelenideElement backNavigation = $("div[data-test='back-arrow-button']");

    private final SelenideElement teamFilterTitle = $("div[data-test='team-filter-title']");
    private final ElementsCollection teamTitle = $$("div[data-test='team-filter-item-label']");
    private final ElementsCollection teamRadioButtonView =
            $$("div[data-test='team-filter-item-view']");
    private final ElementsCollection teamRadioButton = $$("input[data-test='team-filter-item']");
    private final ElementsCollection teamSelectedRadioButton =
            $$("input[data-test='team-filter-item-checked']");
    private final SelenideElement teamNotFoundIcon = $("div[data-test='no-teams-icon']");
    private final SelenideElement teamNotFoundText = $("div[data-test='no-teams-label']");

    private final SelenideElement practisSetEmptyStateIcon =
            $("div[data-test='no-practis-sets-icon']");
    private final SelenideElement practisSetNoSearchStateText =
            $("div[data-test='no-practis-sets-label']");
    private final SelenideElement practisSetEmptyStateText =
            $("span[data-test='select-team-to-see-practis-sets']");
    private final ElementsCollection practisSetFilterTitle =
            $$("div[data-test='practis-set-filter-item-label']");
    private final ElementsCollection practisSetTitle =
            $$("div[data-test='practis-set-filter-title']");
    private final ElementsCollection practisSetRadioButton =
            $$("div[data-test='practis-set-filter-item-view']");
    private final ElementsCollection practisSetSelectedRadioButton =
            $$("input[data-test='practis-set-filter-item-checked']");

    private final ElementsCollection filterSearchIcon = $$("div[data-test='filter-search-icon']");
    private final ElementsCollection filterSearchField =
            $$("input[data-test='filter-search-input']");
    private final ElementsCollection filterSearchClear =
            $$("div[data-test='filter-search-input-clear']");
}
