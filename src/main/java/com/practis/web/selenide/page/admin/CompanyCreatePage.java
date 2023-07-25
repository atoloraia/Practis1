package com.practis.web.selenide.page.admin;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class CompanyCreatePage {

    private static final String VALIDATION_SELECTOR = ".sc-hxaKgE.jGPiQi";

    // Create Company Modal
    private final SelenideElement newCompanyTitle = $("div[data-test='new-company-page-title']");
    private final SelenideElement crossButton = $("div[data-test='new-company-page-title']");

    private final SelenideElement companyNameTitle = $("div[data-test='new-company-page-title']");
    private final SelenideElement companyNameField1 = $("div[data-test='new-company-page-title']");
    private final SelenideElement companyNameCharacterCounter =
            $("div[data-test='new-company-page-title']");
    private final SelenideElement emptyCompanyError = $("div[data-test='new-company-page-title']");
    private final SelenideElement companyAlreadyExistsError =
            $("div[data-test='new-company-page-title']");
    private final SelenideElement workspaceUrlTitle = $("div[data-test='new-company-page-title']");
    private final SelenideElement workspaceUrlField = $("div[data-test='new-company-page-title']");
    private final SelenideElement workspaceUrlCharacterCounter =
            $("div[data-test='new-company-page-title']");
    private final SelenideElement workspaceUrlExistsError =
            $("div[data-test='new-company-page-title']");
    private final SelenideElement workspaceUrlValidationText =
            $("div[data-test='new-company-page-title']");
    private final SelenideElement workspaceUrlGopractisText =
            $("div[data-test='new-company-page-title']");
    private final SelenideElement createButton = $("div[data-test='new-company-page-title']");

    // Company Created Modal
    private final SelenideElement closeButton = $("div[data-test='new-company-page-title']");
    private final SelenideElement icon = $("div[data-test='new-company-page-title']");
    private final SelenideElement companyTitle = $("div[data-test='new-company-page-title']");
    private final SelenideElement companyTitleText = $("div[data-test='new-company-page-title']");
    private final SelenideElement configureCompanyButton =
            $("div[data-test='new-company-page-title']");
    private final SelenideElement openWebPortalButton =
            $("div[data-test='new-company-page-title']");

    // Old
    private final ElementsCollection inputRow = $$("div[data-test='new-company']");
    private final SelenideElement addRowLink = $("a[data-test='new-company-item-add']");

    private final ElementsCollection companyNameField = $$("input[name*='name']");
    private final ElementsCollection companyEmailField = $$("input[name*='ownerEmail']");
    private final ElementsCollection firstNameField = $$("input[name*='ownerFirstName']");
    private final ElementsCollection lastNameField = $$("input[name*='ownerLastName']");
    private final ElementsCollection addAnotherButton = $$("a[data-test='new-company-add']");
    private final ElementsCollection deleteRowButton =
            $$("div[data-test='new-company-item-delete']");

    private final SelenideElement inviteButton = $("button[type='submit']");
}
