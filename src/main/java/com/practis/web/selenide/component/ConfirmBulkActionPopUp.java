package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ConfirmBulkActionPopUp {

    public final SelenideElement confirmBulkActionTitle = $(".sc-iUCjfc.kPDyBr");
    public final SelenideElement descriptionAreYouSure = $(".sc-eIvUPf.jotatz");
    public final SelenideElement cancelButton = $(".sc-efQUeY.emQwdx.inverse");
    public final SelenideElement proceedButton = $(".sc-efQUeY.ghJbAQ.primary");
}
