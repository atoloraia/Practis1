package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class WarningDeleteUsersPopUp {

    public final SelenideElement confirmActionTitle = $(".sc-fjILQl.cfqwTB");
    public final SelenideElement descriptionText = $(".sc-iTTZDz.jYvLEi");
    public final SelenideElement goBackButton = $(".sc-efQUeY.emQwdx.inverse");
    public final SelenideElement proceedButton = $(".sc-efQUeY.emQwdx.primary");

    // Pending Users - Revoke warning
    public final SelenideElement revokeActionTitle = $(".sc-gfHhFh.bSntph");
    public final SelenideElement descriptionRevokeText = $(".sc-iGCQqH.iGxRGi");
}
