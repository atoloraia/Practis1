package com.practis.web.selenide.component.company;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class CompanyConfigurationPopUp {

    private final SelenideElement companyConfigurationTitle =
            $("span[data-test='company-settings-modal-title']");
    private final SelenideElement companyName = $("span[data-test='company-name-modal-title']");
    private final SelenideElement closeButton = $("div[data-test='company-settings-modal-close']");

    private final SelenideElement administratorsTab = $("a[data-test='administrators-tab']");
    private final SelenideElement detailsTab = $("a[data-test='details-tab']");
    private final SelenideElement logoTab = $("a[data-test='logo-tab']");
    private final SelenideElement licensedSeatsTab = $("a[data-test='licensed-seats-tab']");

    // Administrators tab
    private final SelenideElement administratorsTitle = $("div[data-test='administrators-title']");
    private final SelenideElement administratorsDescription =
            $("div[data-test='administrators-description']");
    private final SelenideElement administratorsInviteButton =
            $("button[data-test='invite-button']");

    // Invite Administrators pop-up
    private final SelenideElement inviteAdminsModal = $("div[data-test='admin-invitation']");
    private final SelenideElement inviteAdminsTitle = $("div[data-test='admin-invitation-title']");
    private final SelenideElement inviteAdminsCloseButton =
            $("div[data-test='admin-invitation-close']");
    private final SelenideElement inviteAdminsFirstNameTitle =
            $("div[data-test='admin-first-name-title']");
    private final SelenideElement inviteAdminsEmailTitle = $("div[data-test='admin-email-title']");
    private final SelenideElement inviteAdminsLastNameTitle =
            $("div[data-test='admin-last-name-title']");
    private final SelenideElement inviteAdminsFirstNameInput =
            $("input[data-test='admin-first-name']");
    private final SelenideElement inviteAdminsLastNameInput =
            $("input[data-test='admin-last-name']");
    private final SelenideElement inviteAdminsEmailInput = $("input[data-test='admin-email']");
    private final SelenideElement inviteAdminsEmailError = $("div[data-test='admin-email-error']");

    private final SelenideElement inviteAdminsCompanyOwnerTitle =
            $("b[data-test='make-admin-owner-label']");
    private final SelenideElement inviteAdminsCompanyOwnerDescription =
            $("div[data-test='make-admin-owner-label-info']");
    private final SelenideElement inviteAdminsCompanyOwnerToggle =
            $("label[data-test='admin-owner-toggle-label']");

    private final SelenideElement inviteAdminsSendButton = $("button[data-test='send-button']");

    // Logo tab
    private final SelenideElement companyLogo = $("div[data-test='company-logo-container']");
    private final SelenideElement companyLogoImage = $("div[data-test='company-logo-image']");
    private final SelenideElement companyLogoCamera = $("div[data-test='company-logo-camera']");
    private final SelenideElement deleteCompanyLogo = $("div[data-test='company-logo-delete']");
    private final SelenideElement logoDescription = $("div[data-test='company-logo-info']");
    private final SelenideElement guidelinesLink = $("a[data-test='company-logo-guidelines']");
    private final SelenideElement nextButton = $("div[data-test='next-button']");
    private final SelenideElement uploadField = $("input[data-test='company-logo-input']");
    private final SelenideElement uploadPhotoSubmitButton =
            $("button[data-test='edit-photo-save-button']");

    // Licensed Seats tab

    private final SelenideElement unlimitedText = $("div[data-test='unlimited-seats-radio-label']");
    private final SelenideElement unlimitedRadiobutton =
            $("div[data-test='unlimited-seats-radio-view']");
    private final SelenideElement unlimitedDescription =
            $("div[data-test='unlimited-seats-description']");
    private final SelenideElement limitText = $("div[data-test='limited-seats-radio-label']");
    private final SelenideElement limitRadiobutton = $("div[data-test='limited-seats-radio-view']");
    private final SelenideElement limitField = $("input[data-test='limited-seats-input']");
    private final SelenideElement limitDescription =
            $("div[data-test='limited-seats-description']");

    private final SelenideElement applyButton = $("button[data-test='apply-button']");
}
