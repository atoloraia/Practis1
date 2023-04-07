package com.practis.web.selenide.page;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class CreateAnAccountPage {

    private final SelenideElement createAnAccountImg = $("div[data-test='user-avatar-image']");
    private final SelenideElement cameraImg = $("div[data-test='user-avatar-camera']");
    private final SelenideElement createAnAccountTitle = $("div[data-test='account-form-title']");
    private final SelenideElement passwordField = $("input[data-test='password']");
    private final SelenideElement confirmPasswordField = $("input[data-test='confirm-password']");
    private final SelenideElement termsCheckboxView = $("div[data-test='terms-checkbox-view']");
    private final SelenideElement continueButton = $("button[data-test='continue-button']");
}
