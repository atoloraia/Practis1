package com.practis.web.selenide.component.selection.status;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class CompaniesStatusModule {

    // TODO update

    private final SelenideElement statusTitle = $("span[data-test*='status-section-title']");

    private final ElementsCollection statusRows = $$("div[data-test='status-item-container']");
    private final SelenideElement activeStatus = $("div[data-test='active-checkbox-label']");
    private final SelenideElement inactiveStatus = $("div[data-test='inactive-checkbox-label']");

    private final SelenideElement statusCheckbox = $("input[data-test='archived-checkbox']");

    private final SelenideElement clearButton = $("button[data-test='clear']");
    private final SelenideElement applyButton = $("button[data-test='apply']");
}
