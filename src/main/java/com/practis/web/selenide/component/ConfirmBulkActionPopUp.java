package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ConfirmBulkActionPopUp {

    public final SelenideElement confirmBulkActionTitle = $(".sc-dDllZQ.bWOfqM");
    public final SelenideElement descriptionAreYouSure = $(".sc-nBTfK.WZhqy");
    public final SelenideElement cancelButton = $(".sc-iAKVOt.ioplhQ.inverse");
    public final SelenideElement proceedButton = $(".sc-iAKVOt.ioplhQ.primary");
}
