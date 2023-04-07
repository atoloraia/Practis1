package com.practis.web.selenide.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class VerifyMobileNumberPage {

    private final SelenideElement practisLogo = $("svg[data-test='practis-logo']");

    private final SelenideElement verifyMobileNumberTitle = $(".sc-iqybmJ.fnjgmy");
    private final SelenideElement descriptionText = $(".sc-CEXub.liuVdz");
    private final SelenideElement mobileNumber = $(".sc-iwZhYo.gvRNBk");
    private final ElementsCollection mobileCell = $$("div[role='button']");
    private final SelenideElement verifyButton = $(".sc-caiKgP.dNoKWT.undefined.primary");
    private final SelenideElement didNotReceiveCodeText = $(".sc-fOYxPM.cWmzTL");
}
