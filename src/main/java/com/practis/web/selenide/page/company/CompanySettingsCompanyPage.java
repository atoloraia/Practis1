package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class CompanySettingsCompanyPage {

    private final SelenideElement companySettingsTitle = $(".sc-kwDLmJ.friYUN");
    // private final SelenideElement companySettingsTitleCompany = $(".sc-fulxZH.cghdln");
    private final SelenideElement crossButton = $(".sc-bzvcsD.jgiOns");
    private final ElementsCollection sections = $$(".sc-IUXKK.fyDYoq");
    private final SelenideElement detailsSection = $(".sc-bzvcsD.jgiOns");
    private final SelenideElement logoSection = $(".sc-bzvcsD.jgiOns");
    private final SelenideElement licensedSeatsSection = $(".sc-bzvcsD.jgiOns");
    private final SelenideElement voiceSection = $(".sc-bzvcsD.jgiOns");

    // Company Details section
    private final SelenideElement companyName = $("div[data-test='company-name-title']");
    private final SelenideElement companyNameInput = $("input[data-test='company-name-input']");
    private final SelenideElement companyNameField = $("input[data-test='company-name-field']");
    private final SelenideElement companyNameInputError =
            $("div[data-test='company-name-input-error']");
    private final SelenideElement workspaceUrl = $("div[data-test='workspace-url-title']");
    private final SelenideElement workspaceUrlInput = $("input[data-test='workspace-url-input']");
    private final SelenideElement accountOwner = $("div[data-test='account-owner-title']");
    private final SelenideElement accountOwnerField = $("div[data-test='account-owner-dropdown']");
    private final SelenideElement accountOwnerClick = $(".sc-kexyWv.ikXNBp.company-select");
    private final ElementsCollection accountOwnerValue = $$(".sc-fZzaJJ.eFCJXk");

    // Licensed Seats section
    private final SelenideElement licensedSeatsTitle = $("span[data-test='user-stats-text']");
    private final SelenideElement registeredCount = $("span[data-test='registered-users-count']");
    private final SelenideElement pendingCount = $("span[data-test='pending-users-count']");
    private final SelenideElement deactivatedCount = $("span[data-test='deactivated-users-count']");
    private final SelenideElement limitTip = $("div[data-test='limit-tip']");
    private final SelenideElement requestLimitChangeButton =
            $("button[data-test='request-limit-change-button']");
    private final SelenideElement manageInvitationsButton =
            $("button[data-test='manage-invitations-button']");

    private final SelenideElement applyButton = $("button[data-test='apply-button']");

    // Logo tab
    private final SelenideElement companyLogo = $("div[data-test='company-logo-container']");
    private final SelenideElement companyLogoCamera = $("div[data-test='company-logo-camera']");
    private final SelenideElement deleteCompanyLogo = $("div[data-test='company-logo-delete']");
    private final SelenideElement logoDescription = $(".sc-cSpvHg.etoKkn");
    private final SelenideElement guidelinesLink = $(".sc-kTwdTh.hJtffO");
    private final SelenideElement uploadField = $("input[data-test='company-logo-input']");
    private final SelenideElement uploadPhotoSubmitButton = $(".sc-efQUeY.bBiKXK.primary");

    // Voice tab
    private final ElementsCollection voiceTitle = $$(".sc-jLuWEH.WDEOL");
    private final ElementsCollection sampleTextField = $$(".sc-cQYgEB.jNLVkP");
    private final ElementsCollection testVoiceButton = $$(".sc-ddnlPB.fRDIem");
    private final ElementsCollection customerSpeedText = $$(".sc-fZDjfC.cXXQOB");
    // private final SelenideElement customerPitchText = $(".sc-fZDjfC.cXXQOB");
    private final ElementsCollection customerGenderTitle = $$(".sc-fZDjfC.cXXQOB");
    // private final ElementsCollection customerVoiceTitle = $$(".sc-fZDjfC.cXXQOB");
    private final ElementsCollection values = $$(".input-range__label-container");
    private final ElementsCollection resetButton = $$(".sc-caiKgP.jrzCxx.inverse");
    private final ElementsCollection saveButton = $$(".sc-caiKgP.jrzCxx.undefined.primary");
}
