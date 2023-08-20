package com.practis.web.selenide.page.admin.create;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class CompanyCreatePage {

    // Create Company Modal
    private final SelenideElement createCompanyModal = $("div[data-test='new-company']");
    private final SelenideElement newCompanyTitle = $("div[data-test='new-company-title']");
    private final SelenideElement crossButton = $("div[data-test='new-company-close']");

    private final SelenideElement companyNameTitle = $("div[data-test='company-name-title']");
    private final SelenideElement companyNameField1 = $("input[data-test='company-name-input']");
    private final SelenideElement companyNameCharacterCounter =
            $("div[data-test='company-name-counter']");
    private final SelenideElement emptyCompanyError =
            $("div[data-test='company-name-is-required-error']");
    private final SelenideElement companyAlreadyExistsError =
            $("div[data-test='the-company-name-is-already-in-use-error']");
    private final SelenideElement workspaceUrlTitle = $("div[data-test='workspace-url-title']");
    private final SelenideElement workspaceUrlField = $("input[data-test='workspace-url-input']");
    private final SelenideElement workspaceUrlCharacterCounter =
            $("div[data-test='workspace-url-counter']");
    private final SelenideElement workspaceUrlExistsError =
            $("div[data-test='the-workspace-url-is-already-in-use-error']");
    private final SelenideElement workspaceUrlValidationText =
            $("div[data-test='workspace-url-label']");
    private final SelenideElement workspaceUrlValidationErrorText =
            $("div[data-test='must-be-at-least-3-letters-error']");
    private final SelenideElement workspaceUrlGopractisText =
            $("div[data-test='workspace-url-suffix']");
    private final SelenideElement createButton = $("button[data-test='create-button']");

    // Company Created Modal
    private final SelenideElement companyCreatedModal = $("div[data-test='company-created']");
    private final SelenideElement closeButton = $("div[data-test='company-created-close']");
    private final SelenideElement icon = $("div[data-test='company-created-logo']");
    private final SelenideElement companyTitle = $("div[data-test='company-created-title']");
    private final SelenideElement companyTitleText = $("div[data-test='company-is-active-text']");
    private final SelenideElement configureCompanyButton =
            $("button[data-test='configure-company-button']");
    private final SelenideElement openWebPortalButton =
            $("button[data-test='open-web-portal-button']");
}
