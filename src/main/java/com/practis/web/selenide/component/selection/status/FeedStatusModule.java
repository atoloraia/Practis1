package com.practis.web.selenide.component.selection.status;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class FeedStatusModule {

    private final SelenideElement statusTitle = $(".sc-kSWJKD.cojhTd");

    private final ElementsCollection status = $$(".sc-DtlDN.jiVCQu");
    private final ElementsCollection statusCheckbox = $$(".sc-lhMhtZ.ftvXwI");
}
