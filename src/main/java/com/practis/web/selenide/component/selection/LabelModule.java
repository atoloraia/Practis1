package com.practis.web.selenide.component.selection;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class LabelModule {

    private final SelenideElement labelsTitle = $("span[data-test*='labels']");

    private final SelenideElement searchField = $("input[data-test='labels-searchbox-field']");
    private final SelenideElement searchFieldIcon =
            $("div[data-test='labels-searchbox-field-icon']");
    private final SelenideElement cleanSearchIcon =
            $("div[data-test='labels-searchbox-field-clear']");

    private final SelenideElement selectedText = $("span[data-test='labels-selected-caption']");
    private final SelenideElement selectedAllButton =
            $("span[data-test='labels-select-all-button']");
    private final SelenideElement unSelectedAllButton =
            $("span[data-test='labels-unselect-all-button']");
    private final SelenideElement noSearchResultImage =
            $("div[data-test='labels-searchbox-empty-result-icon']");
    private final SelenideElement noSearchResultText =
            $("div[data-test='labels-searchbox-empty-result-label']");

    private final ElementsCollection labelRows = $$("div[data-test='label-item-container']");
    private final ElementsCollection labelNameRows =
            $$("div[data-test='label-item-input-container']");
    private final ElementsCollection labelCheckbox = $$("input[data-test*='label-item-checkbox']");

    private final SelenideElement applyButton = $("button[data-test='apply-button']");
    private final SelenideElement cancelButton = $("button[data-test='cancel-button']");

    private final SelenideElement noLabelsYetTooltip =
            $("div[data-test='invite-users-new-labels-no-result']");
    private final SelenideElement noLabelsAddedIcon =
            $("div[data-test='labels-searchbox-no-items-icon']");
    private final SelenideElement noLabelsAddedText =
            $("div[data-test='labels-searchbox-no-items-text']");
    private final SelenideElement noLabelsSelectedText =
            $("span[data-test='labels-selected-caption']");
    private final SelenideElement checkedCheckbox =
            $("input[data-test='label-item-checkbox-checked']");

    private final SelenideElement applyButtonBulkAction = $("button[data-test='apply-button']");

    // Scenario
    private final ElementsCollection labelItemScenario =
            $$("div[data-test='scenario-labels-item']");
    private final ElementsCollection labelItemChallenge =
            $$("div[data-test='challenge-labels-item']");
    private final ElementsCollection labelItemPractisSet =
            $$("div[data-test='practis-set-labels-item']");
}
