package com.practis.web.selenide.page.admin.navigation;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ManageUserSettingsPage {

    // Common
    private final SelenideElement userSettingsTitle =
            $("div[data-test='user-settings-page-subtitle']");
    private final SelenideElement backButton = $("div[data-test='back-arrow-button']");
    private final SelenideElement userPic = $("div[data-test='user-avatar']");
    private final SelenideElement userRole = $("div[data-test='user-role']");
    private final SelenideElement userName = $("div[data-test='user-name']");
    private final SelenideElement userEmail = $("div[data-test='user-email']");

    private final SelenideElement editUserDetails = $("div[data-test='edit-user-details']");
    private final SelenideElement firstNameField = $("input[data-test='first-name-input']");
    private final SelenideElement lastNameField = $("input[data-test='last-name-input']");
    private final SelenideElement emailField = $("input[data-test='email-input']");
    private final SelenideElement mobileNumberField = $("div[data-test='mobile-number-input']");
    private final SelenideElement pendingMobileNumberField =
            $("input[data-test='number-pending-input']");
    private final SelenideElement mobileNumberDeleteButton =
            $("div[data-test='mobile-number-input-delete']");
    private final SelenideElement roleField = $("div[data-test='role-selector-selected-value']");

    private final ElementsCollection roleValues = $$("div[data-test='role-selector-select-item']");

    // Actions
    private final SelenideElement deactivateButton = $("button[data-test='deactivate-button']");
    private final SelenideElement activateButton = $("button[data-test='activate-button']");
    private final SelenideElement revokeButton = $("button[data-test='revoke-button']");

    // User Status Label
    private final SelenideElement activeUserLabel = $("div[data-test='user-active-badge']");
    private final SelenideElement pendingUserLabel = $("div[data-test='user-pending-badge']");
    private final SelenideElement pendingUserIcon = $("div[data-test='user-avatar-pending-icon']");
    private final SelenideElement inactiveUserLabel = $("div[data-test='user-inactive-badge']");
}
