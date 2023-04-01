package com.practis.web.selenide.page;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class CreateAnAccountPage {

    private final SelenideElement createAnAccountImg = $(".sc-brSwnh.lhZTyF']");
    private final SelenideElement createAnAccountTitle = $(".sc-fvxABq.ghCwME");
    private final SelenideElement passwordField = $("input[name='password']");
    private final SelenideElement confirmPasswordField = $("input[name='confirmPassword']");
    private final SelenideElement termsCheckboxView = $("div[data-test='terms-checkbox-view']");
    private final SelenideElement continueButton = $(".sc-efQUeY.fIevMu.primary");
}
