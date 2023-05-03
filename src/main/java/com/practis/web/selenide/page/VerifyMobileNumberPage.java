package com.practis.web.selenide.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class VerifyMobileNumberPage {

    private final SelenideElement practisLogo = $("svg[data-test='practis-logo']");

    private final SelenideElement verifyMobileNumberTitle =
            $("span[data-test='verify-mobile-number']");
    private final SelenideElement descriptionText = $("div[data-test='instructions-text']");
    private final SelenideElement mobileNumber = $("div[data-test='mobile-number']");
    private final ElementsCollection mobileCell = $$("div[role='button']");
    private final SelenideElement verifyButton = $("button[data-test='verify-button']");
    private final SelenideElement didNotReceiveCodeText =
            $("span[data-test='didnt-receive-code-text']");
}
