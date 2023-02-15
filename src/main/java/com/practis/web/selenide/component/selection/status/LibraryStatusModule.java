package com.practis.web.selenide.component.selection.status;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class LibraryStatusModule {

    private final SelenideElement statusTitle = $("span[data-test='library-filters-status-title']");
    private final ElementsCollection statusRow = $$(".sc-eGPWxh.cdGPvu");

    private final SelenideElement activeStatusCheckbox =
            $("input[data-test*='library-active-checkbox']");
    private final SelenideElement activeStatusLabel =
            $("div[data-test='library-active-checkbox-label']");

    private final SelenideElement draftStatusCheckbox =
            $("input[data-test*='library-draft-checkbox']");
    private final SelenideElement draftStatusCheckboxView =
            $("div[data-test*='library-draft-checkbox-view']");
    private final SelenideElement draftStatusLabel =
            $("div[data-test='library-draft-checkbox-label']");

    private final SelenideElement archivedStatusCheckbox =
            $("input[data-test='library-archived-checkbox']");
    private final SelenideElement archivedStatusCheckboxView =
            $("div[data-test='library-archived-checkbox-view']");
    private final SelenideElement archivedStatusLabel =
            $("div[data-test='library-archived-checkbox-label']");

    // private final ElementsCollection status = $$(".sc-iukwUI.djFtvI");
}
