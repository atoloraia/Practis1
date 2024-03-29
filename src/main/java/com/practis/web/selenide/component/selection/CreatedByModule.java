package com.practis.web.selenide.component.selection;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class CreatedByModule {

    private final SelenideElement createdByTitle =
            $("span[data-test='creators-section-title-title']");

    private final SelenideElement searchField = $("input[data-test='creators-search']");
    private final SelenideElement searchFieldIcon = $("div[data-test='creators-search-icon']");
    private final SelenideElement cleanSearchIcon = $("div[data-test='creators-search-clear']");

    private final SelenideElement selectedText = $("span[data-test='creators-selected-text']");
    private final SelenideElement selectedAllButton = $("span[data-test='creators-select-all']");
    private final SelenideElement unSelectedAllButton =
            $("span[data-test='creators-unselect-all']");

    private final ElementsCollection userCheckbox = $$("input[data-test='creators-item-checkbox']");
    private final ElementsCollection userName = $$("div[data-test='creators-item-text']");

    private final SelenideElement noSearchResultImage = $("div[data-test='no-creators-icon']");
    private final SelenideElement noSearchResultText = $("div[data-test='no-creators-label']");
}
