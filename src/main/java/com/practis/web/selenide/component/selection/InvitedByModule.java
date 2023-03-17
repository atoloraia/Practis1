package com.practis.web.selenide.component.selection;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class InvitedByModule {

    private final SelenideElement invitedByTitle =
            $("span[data-test='inviters-section-title-title']");

    private final SelenideElement searchField = $("input[data-test='inviters-search']");
    private final SelenideElement searchFieldIcon = $("div[data-test='inviters-search-icon']");
    private final SelenideElement cleanSearchIcon = $("div[data-test='inviters-search-clear']");

    private final SelenideElement selectedText = $("span[data-test='inviters-selected-text']");
    private final SelenideElement selectedAllButton = $("span[data-test='inviters-select-all']");
    private final SelenideElement unSelectedAllButton =
            $("span[data-test='inviters-unselect-all']");

    private final ElementsCollection userCheckbox = $$("input[data-test='inviters-item-checkbox']");
    private final ElementsCollection userName = $$("div[data-test='inviters-item-text']");

    private final SelenideElement noSearchResultImage =
            $("div[data-test='no-inviters-found-icon']");
    private final SelenideElement noSearchResultText = $("div[data-test='no-inviters-found-text']");
}
