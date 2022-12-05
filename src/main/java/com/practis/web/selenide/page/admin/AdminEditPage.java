package com.practis.web.selenide.page.admin;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class AdminEditPage {

    private final SelenideElement userSettingsTitle =
            $("div[data-test='user-settings-page-title']");
    private final SelenideElement userName = $("div[data-test='user-settings-page-subtitle']");
    private final SelenideElement backButton = $("div[data-test='back-arrow-button']");

    private final SelenideElement roleTitle = $("div[data-test='user-profile-role']");
    private final SelenideElement smallUserPic = $("div[data-test='user-profile-avatar']");
    private final SelenideElement nameInfo = $("div[data-test='user-profile-full-name']");
    private final SelenideElement emailInfo = $("div[data-test='user-profile-email']");

    private final SelenideElement editUserDetailsButton = $("a[data-test='edit-details']");

    private final SelenideElement largeUserPic = $("div[data-test='uploaded-image-section']");
    private final SelenideElement uploadPictureButton =
            $("a[data-test='user-profile-upload-text']");
    private final SelenideElement pictureText =
            $("div[data-test='user-profile-upload-description']");

    private final SelenideElement firstNameField = $("input[name='firstName']");
    private final SelenideElement lastNameField = $("input[name='lastName']");
    private final SelenideElement emailField = $("input[name='email']");
    private final SelenideElement mobileField = $("div[data-test='user-profile-phone-number']");

    private final SelenideElement deleteButton = $("button[data-test='user-profile-delete']");
    private final SelenideElement updateButton = $("button[data-test='user-profile-update']");

    private final SelenideElement changePasswordButton = $("a[data-test='change-password']");
    private final SelenideElement newPasswordField =
            $("input[data-test='change-password-new-password']");
    private final SelenideElement showNewPasswordButton =
            $("div[data-test='change-password-new-password-show']");
    private final SelenideElement confirmPasswordField =
            $("input[data-test='change-password-confirm-password']");
    private final SelenideElement showConfirmPasswordButton =
            $("div[data-test='change-password-confirm-password-show']");

    private final SelenideElement updatePasswordButton =
            $("button[data-test='change-password-update']");
}
