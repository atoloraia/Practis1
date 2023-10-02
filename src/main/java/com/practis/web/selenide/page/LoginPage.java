package com.practis.web.selenide.page;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import com.practis.web.selenide.component.Snackbar;
import lombok.Getter;

@Getter
public class LoginPage {

    private final SelenideElement practisLogo = $("svg[data-test='practis-logo']");

    private final SelenideElement emailField = $("input[data-test='login-email']");
    private final SelenideElement platformSelector = $(".MuiFormControl-root.sc-gGCCur.bYEXBF");
    private final SelenideElement babylon = $("li[data-value='babylon']");
    private final SelenideElement passwordField = $("input[data-test='login-password']");
    private final SelenideElement showHidePasswordButton = $("div[data-test='login-password-eye']");

    private final SelenideElement forgotPasswordButton = $("a[data-test='forgot-password']");

    private final SelenideElement loginButton = $("button[data-test='login-button']");
    private final SelenideElement orLoginWithText = $("span[data-test='login-button-separator']");
    private final SelenideElement mobileNumberButton =
            $("button[data-test='mobile-number-button']");

    private final SelenideElement passwordValidationMessage =
            $("div[data-test='login-password-error']");
    private final SelenideElement emailValidationMessage = $("div[data-test='login-email-error']");
    private final Snackbar snackbar = new Snackbar();

    private final SelenideElement loginWithEmailAndPassword =
            $("button[data-test='email-and-password-button']");
}
