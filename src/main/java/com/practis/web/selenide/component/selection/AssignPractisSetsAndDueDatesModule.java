package com.practis.web.selenide.component.selection;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class AssignPractisSetsAndDueDatesModule {

    private final SelenideElement moduleTitle = $(".sc-gtLQCc.kyGpbp");

    private final SelenideElement searchFiled = $("input[data-test='practisset-searchbox-field']");
    private final SelenideElement searchFiledIcon =
            $("div[data-test='practisset-searchbox-field-icon']");
    private final SelenideElement searchClearIcon =
            $("div[data-test='practisset-searchbox-field-clear']");

    private final SelenideElement selectionCounter =
            $("span[data-test='practisset-selected-caption']");
    private final SelenideElement selectAllButton = $("span[data-test='select-all-button']");
    private final SelenideElement unselectAllButton = $("span[data-test='unselect-all-button']");
    private final SelenideElement dueDatesTitle = $("span[data-test='due-dates-column-title']");

    private final ElementsCollection itemContainer =
            $$("div[data-test='practisset-item-container']");
    private final ElementsCollection itemCheckbox =
            $$("input[data-test='practisset-item-checkbox']");
    private final ElementsCollection itemCheckboxView =
            $$("div[data-test='practisset-item-checkbox-view']");
    private final ElementsCollection itemTitle = $$("div[data-test='practisset-item-title']");
    private final ElementsCollection dueDateContainer = $$("div[data-test='due-date-container']");
    private final ElementsCollection dueDateValue = $$("div[data-test='due-date-value']");
    private final ElementsCollection dueDateEditButton =
            $$("div[data-test='due-date-edit-button']");

    private final SelenideElement emptyStateIcon =
            $("div[data-test='practisset-searchbox-empty-result-icon']");
    private final SelenideElement emptyStateText =
            $("div[data-test='practisset-searchbox-empty-result-label']");

    private final SelenideElement noSearchResultIcon =
            $("div[data-test='practisset-searchbox-empty-result-icon']");
    private final SelenideElement noSearchResultText =
            $("div[data-test='practisset-searchbox-empty-result-label']");

    private final SelenideElement dividerLine = $(".sc-fNgMav.eqgnRq");

    private final SelenideElement cancelButton = $(".sc-iAKVOt.jxgHie.inverse");
    private final SelenideElement applyButton = $(".sc-jcFkyM.fypqqL.undefined.primary");
}
