package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ConfirmBulkActionPopUp {

    public final SelenideElement confirmBulkActionTitle = $(".sc-gouzmr.hmbdjH");
    public final SelenideElement descriptionAreYouSure = $(".sc-iyuJA-D.cpdjxE");
    public final SelenideElement cancelButton = $(".sc-iAKVOt.ioplhQ.inverse");
    public final SelenideElement proceedButton = $(".sc-iAKVOt.ioplhQ.primary");
}
