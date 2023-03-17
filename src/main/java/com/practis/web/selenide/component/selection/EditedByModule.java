package com.practis.web.selenide.component.selection;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class EditedByModule {

    private final SelenideElement editedByTitle =
            $("span[data-test='editors-section-title-title']");

    private final SelenideElement searchField = $("input[data-test='editors-search']");
    private final SelenideElement searchFieldIcon = $("div[data-test='editors-search-icon']");
    private final SelenideElement cleanSearchIcon = $("div[data-test='table-search-input-clear']");

    private final SelenideElement selectedText = $("span[data-test='editors-selected-text']");
    private final SelenideElement selectedAllButton = $("span[data-test='editors-select-all']");
    private final SelenideElement unSelectedAllButton = $("span[data-test='editors-unselect-all']");

    private final ElementsCollection userCheckbox = $$("input[data-test='editors-item-checkbox']");
    private final ElementsCollection userName = $$("div[data-test='editors-item-text']");

    private final SelenideElement noSearchResultImage = $("div[data-test='no-editors-found-icon']");
    private final SelenideElement noSearchResultText = $("div[data-test='no-editors-found-label']");

    private final SelenideElement noUsersYetImage = $("div[data-test='no-editors-icon']");
    private final SelenideElement noUsersYetText = $("div[data-test='no-editors-label']");
}
