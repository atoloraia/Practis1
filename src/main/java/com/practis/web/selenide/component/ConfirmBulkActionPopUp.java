package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ConfirmBulkActionPopUp {

    public final SelenideElement confirmBulkActionTitle = $(".sc-hKAaEb.eXXCNn");
    public final SelenideElement descriptionAreYouSure = $(".sc-kffHcM.hmcoci");
    public final SelenideElement cancelButton = $(".sc-caiKgP.hztRxV.inverse");
    public final SelenideElement proceedButton = $(".sc-caiKgP.hztRxV.undefined.primary");
}
