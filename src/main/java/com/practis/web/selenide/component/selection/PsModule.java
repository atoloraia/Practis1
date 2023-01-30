package com.practis.web.selenide.component.selection;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class PsModule {

    private final SelenideElement practisSetTitle =
            $("span[data-test='practis-sets-section-title']");

    private final SelenideElement searchField = $("input[data-test='practisset-searchbox-field']");
    private final SelenideElement searchFieldIcon =
            $("div[data-test='practisset-searchbox-field-icon']");
    private final SelenideElement cleanSearchIcon =
            $("div[data-test='practisset-searchbox-field-clear']");
    private final SelenideElement selectedText = $("span[data-test='practisset-selected-caption']");
    private final SelenideElement dueDatesColumnTitle =
            $("span[data-test='due-dates-column-title']");

    private final SelenideElement selectedAllButton =
            $("span[data-test='practisset-select-all-button']");
    private final SelenideElement unSelectedAllButton =
            $("span[data-test='practisset-unselect-all-button']");
    private final ElementsCollection practisSetRows =
            $$("div[data-test='practisset-item-container']");
    private final ElementsCollection practisSetCheckbox =
            $$("input[data-test*='practisset-item-checkbox']");
    private final ElementsCollection practisSetName = $$("div[data-test='practisset-item-title']");
    private final ElementsCollection dueDateValue = $$("div[data-test='due-date-value']");
    private final ElementsCollection editDueDateButton =
            $$("div[data-test='due-date-edit-button']");
    private final SelenideElement applyButton = $("button[data-test='apply-button']");
    private final SelenideElement cancelButton = $("button[data-test='cancel-button']");
    private final SelenideElement noPractisSetYetTooltip =
            $("div[data-test='invite-users-new-practis-sets-no-result']");

    private final SelenideElement noSearchResultImage =
            $("div[data-test='practisset-searchbox-empty-result-icon']");
    private final SelenideElement noPractisSetYetText =
            $("div[data-test='practisset-searchbox-empty-result-label']");
}
