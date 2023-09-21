package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class CompanySettingsCompanyPage {

    private final SelenideElement detailsTab = $("a[data-test='details-tab']");
    private final SelenideElement logoTab = $("a[data-test='logo-tab']");
    private final SelenideElement licensedSeatsTab = $("a[data-test='licensed-seats-tab']");
    private final SelenideElement voiceTab = $("a[data-test='voice-tab']");

    private final SelenideElement companySettingsTitle =
            $("span[data-test='company-settings-modal-title']");
    private final SelenideElement companySettingsTitleCompany =
            $("span[data-test='company-name-modal-title']");
    private final SelenideElement crossButton = $("div[data-test='company-settings-modal-close']");

    // Company Details section
    private final SelenideElement companyName = $("div[data-test='company-name-title']");
    private final SelenideElement companyNameField =
            $("input[data-test='company-name-input-input']");
    private final SelenideElement companyNameInputError =
            $("div[data-test='company-name-input-error']");
    private final SelenideElement workspaceUrl = $("div[data-test='workspace-url-title']");
    private final SelenideElement workspaceUrlInput =
            $("input[data-test='workspace-url-input-input']");
    private final SelenideElement accountOwner = $("div[data-test='account-owner-title']");
    private final SelenideElement accountOwnerField = $("div[data-test='account-owner-dropdown']");
    private final SelenideElement accountOwnerClick = $(".sc-kexyWv.ikXNBp.company-select");
    private final SelenideElement selectAccountOwnerValue =
            $("div[data-test='account-owner-dropdown-selected-value']");

    private final ElementsCollection accountOwnerValue =
            $$("div[data-test='account-owner-dropdown-select-item']");

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
    private final SelenideElement logoDescription = $("div[data-test='company-logo-info']");
    private final SelenideElement guidelinesLink = $("a[data-test='company-logo-guidelines']");

    // Voice tab
    private final SelenideElement customerTitle = $("div[data-test='customer-settings-title']");
    private final SelenideElement customerSampleTextInput =
            $("textarea[data-test='customer-sample-text-input']");
    private final SelenideElement customerTestVoice = $("a[data-test='customer-test-voice']");
    private final SelenideElement customerSpeedLabel = $("div[data-test='customer-speed-label']");
    private final SelenideElement customerPitchLabel = $("div[data-test='customer-pitch-label']");
    private final SelenideElement customerGenderLabel = $("div[data-test='customer-gender-label']");
    private final SelenideElement customerGenderDropdown =
            $("div[data-test='customer-gender-selected-value']");
    private final SelenideElement customerVoiceLabel = $("div[data-test='customer-voice-label']");
    private final SelenideElement customerVoiceDropdown =
            $("div[data-test='customer-voice-selected-value']");
    private final SelenideElement customerResetButton =
            $("button[data-test='customer-reset-button']");
    private final SelenideElement customerSaveButton =
            $("button[data-test='customer-save-button']");

    private final SelenideElement representativeTitle =
            $("div[data-test='representative-settings-title']");
    private final SelenideElement representativeSampleTextInput =
            $("textarea[data-test='representative-sample-text-input']");
    private final SelenideElement representativeTestVoice =
            $("a[data-test='representative-test-voice']");
    private final SelenideElement representativeSpeedLabel =
            $("div[data-test='representative-speed-label']");
    private final SelenideElement representativePitchLabel =
            $("div[data-test='representative-pitch-label']");
    private final SelenideElement representativeGenderLabel =
            $("div[data-test='representative-gender-label']");
    private final SelenideElement representativeGenderDropdown =
            $("div[data-test='representative-gender-selected-value']");
    private final SelenideElement representativeVoiceLabel =
            $("div[data-test='representative-voice-label']");
    private final SelenideElement representativeVoiceDropdown =
            $("div[data-test='representative-voice-selected-value']");
    private final SelenideElement representativeResetButton =
            $("button[data-test='representative-reset-button']");
    private final SelenideElement representativeSaveButton =
            $("button[data-test='representative-save-button']");

    private final ElementsCollection values = $$(".input-range__label-container");
}
