package com.practis.web.selenide.page.admin;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class AdminCreatePage {

    private static final String VALIDATION_SELECTOR = ".sc-bTfYlY.agMza";

    private final SelenideElement newPractisAdminModal = $("div[data-test='admin-invitation']");
    private final SelenideElement newPractisAdminTitle =
            $("div[data-test='admin-invitation-title']");

    private final SelenideElement closeButton = $("div[data-test='admin-invitation-close']");
    private final SelenideElement emailFieldTitle = $("div[data-test='email']");
    private final SelenideElement emailInput = $("input[data-test='email-url-input']");
    private final SelenideElement emailSuffix = $("div[data-test='email-url-suffix']");
    private final SelenideElement explanationText = $("div[data-test='email-url-label']");
    private final SelenideElement createButton = $("button[data-test='create-button']");
    private final SelenideElement userExistsError =
            $("div[data-test='user-already-exists-in-our-system-error']");

    // old
    private final ElementsCollection inputRow = $$("div[data-test='new-admin']");
    private final SelenideElement addRowLink = $("a[data-test='new-admin-item-add']");
    private final ElementsCollection deleteRowButton = $$("div[data-test='new-admin-item-delete']");

    final ElementsCollection emailFieldElements = $$("input[name*='email']");
    private final ElementsCollection firstNameField = $$("input[name*='firstName']");
    private final ElementsCollection lastNameField = $$("input[name*='lastName']");
    private final ElementsCollection passwordField = $$("input[name*='password']");
    private final ElementsCollection passwordValidationError =
            $$("div[data-test*='new-admin-item-password-error']");

    private final ElementsCollection showPassword =
            $$("div[data-test='new-admin-item-password-show']");
    private final ElementsCollection hidePassword =
            $$("div[data-test='new-admin-item-password-hide']");
}
