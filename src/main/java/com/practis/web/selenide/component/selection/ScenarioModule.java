package com.practis.web.selenide.component.selection;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ScenarioModule {

    private final SelenideElement scenarioTitle = $("span[data-test='scenarios-section-title']");
    private final SelenideElement searchField = $("input[data-test='scenarios-search']");
    private final SelenideElement searchFieldIcon = $("div[data-test='scenarios-search-icon']");

    private final SelenideElement selectedText = $("span[data-test='scenarios-selected-text']");
    private final SelenideElement selectedAllButton = $("span[data-test='scenarios-select-all']");
    private final SelenideElement unSelectedAllButton =
            $("span[data-test='scenarios-unselect-all']");
    private final SelenideElement noSearchResultImage =
            $("div[data-test='no-scenarios-found-icon']");
    private final SelenideElement noSearchResultText =
            $("div[data-test='no-scenarios-found-text']");

    private final SelenideElement noScenarioYetIcon = $("div[data-test='no-scenarios-icon']");
    private final SelenideElement noScenarioYetText = $("div[data-test='no-scenarios-text']");
}
