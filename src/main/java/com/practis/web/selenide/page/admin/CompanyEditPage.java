package com.practis.web.selenide.page.admin;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class CompanyEditPage {

    private final SelenideElement companySettingsTitle =
            $("div[data-test='company-settings-page-title']");
    private final SelenideElement companyName =
            $("div[data-test='company-settings-page-subtitle']");
    private final SelenideElement backButton = $("div[data-test='back-arrow-button']");

    private final SelenideElement smallUserPic = $("div[data-test='company-avatar']");
    private final SelenideElement companyTitle = $("div[data-test='company-name']");
    private final SelenideElement createdAtText = $("div[data-test='company-created-on-text']");

    private final SelenideElement downloadReportButton = $("button[data-test='download-report']");
    private final SelenideElement viewLogsButton = $("button[data-test='view-logs']");
    private final SelenideElement viewAssessmentButton =
            $("button[data-test='view-ai-assessment']");

    private final SelenideElement companyDetailsButton =
            $("a[data-test='company-navigation-section']");
    private final SelenideElement largeUserpic = $("div[data-test='uploaded-image-section']");
    private final SelenideElement uploadPictureButton = $("a[data-test='upload-new-picture-text']");
    private final SelenideElement pictureText = $("div[data-test='upload-file-format-text']");

    private final SelenideElement companyNameField = $("input[name='companyName']");
    private final SelenideElement companyOwnerField = $("div[data-test='company-owner-dropdown']");
    private final SelenideElement emailField = $("input[data-test='company-email-field']");
    private final SelenideElement deleteButton = $("button[data-test='delete-button']");
    private final SelenideElement updateButton = $("button[data-test='update-button']");
}
