package com.practis.web.selenide.component.selection.status;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class CompaniesStatusModule {

    private final SelenideElement statusTitle = $("div[data-test='status-section']");

    private final ElementsCollection statusRows = $$("div[data-test='status-item-container']");

    private final SelenideElement activeStatus = $("div[data-test='active-status-checkbox-label']");
    private final SelenideElement checkedActiveStatus =
            $("input[data-test='active-status-checkbox-checked']");
    private final SelenideElement activeCheckbox =
            $("div[data-test='active-status-checkbox-view']");

    private final SelenideElement inactiveStatus =
            $("div[data-test='inactive-status-checkbox-label']");
    private final SelenideElement checkedInactiveStatus =
            $("div[data-test='inactive-status-checkbox-checked']");
    private final SelenideElement inactiveCheckbox =
            $("div[data-test='inactive-status-checkbox-view']");

    private final SelenideElement clearButton = $("button[data-test='clear-button']");
    private final SelenideElement applyButton = $("button[data-test='apply-filter-button']");
}
