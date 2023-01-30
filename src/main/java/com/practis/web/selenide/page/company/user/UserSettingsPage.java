package com.practis.web.selenide.page.company.user;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class UserSettingsPage {

    private final SelenideElement userSettingsHeader = $(".sc-iRFufS.lbIdOH");
    private final SelenideElement userSettingsName = $(".sc-eZhSfn.ezfhJL");
    private final SelenideElement userSettingsBackButton = $("div[data-test='back-arrow-button']");

    private final SelenideElement userPicture = $(".sc-hrJsQT.iGuvgx");
    private final SelenideElement userRole = $("div[data-test='user-profile-role']");
    private final SelenideElement userFullName = $("div[data-test='user-profile-full-name']");
    private final SelenideElement userEmail = $("div[data-test='user-profile-email']");
    private final SelenideElement viewProfileButton = $(".sc-jcFkyM.dnCPBx.undefined.primary");

    private final SelenideElement editUserDetails = $("a[data-test='edit-details']");
    private final SelenideElement changePassword = $("a[data-test='change-password']");
    private final SelenideElement uploadedImageSection =
            $("div[data-test='uploaded-image-section']");
    private final SelenideElement uploadImageText = $("a[data-test='user-profile-upload-text']");
    private final SelenideElement uploadImageDescription =
            $("div[data-test='user-profile-upload-description']");
    private final ElementsCollection userName = $$(".sc-gKckTs.jlOMHL");
    private final SelenideElement updateButton = $("button[data-test='user-profile-update']");
}
