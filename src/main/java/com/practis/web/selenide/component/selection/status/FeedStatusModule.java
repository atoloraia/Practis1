package com.practis.web.selenide.component.selection.status;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class FeedStatusModule {

    private final SelenideElement statusTitle = $("span[data-test='status-section-title']");

    private final ElementsCollection statusRows = $$("div[data-test='status-item-container']");

    private final SelenideElement archivedStatusLabel =
            $("div[data-test='archived-checkbox-label']");
    private final SelenideElement statusCheckbox = $("input[data-test='archived-checkbox']");
}
