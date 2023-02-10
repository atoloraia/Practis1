package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ConfirmBulkActionPopUp {

    public final SelenideElement confirmBulkActionTitle = $(".sc-iyuJA-D.cpdjxE");
    public final SelenideElement descriptionAreYouSure = $(".sc-lajtyh.bpZxVJ");
    public final SelenideElement cancelButton = $(".sc-caiKgP.hztRxV.inverse");
    public final SelenideElement proceedButton = $(".sc-caiKgP.hztRxV.undefined.primary");
    public final SelenideElement proceedNudgeButton = $(".sc-caiKgP.hztRxV.undefined.primary");
}
