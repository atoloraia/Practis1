package com.practis.web.selenide.page.admin;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class CompanySettingsPage {

    private final SelenideElement companySettingsTitle =
            $("div[data-test='company-settings-page-title']");
    private final SelenideElement companyName =
            $("div[data-test='company-settings-page-subtitle']");
    private final SelenideElement backButton = $("div[data-test='back-arrow-button']");

    private final SelenideElement smallUserPic = $("div[data-test='company-avatar']");
    private final SelenideElement companyTitle = $("div[data-test='company-name']");
    private final SelenideElement lastChangesText = $("div[data-test='company-created-on-text']");
    private final SelenideElement statusBadge = $("div[data-test*='badge']");

    private final SelenideElement downloadReportButton = $("button[data-test='download-report']");
    private final SelenideElement viewLogsButton = $("button[data-test='view-logs']");

    // 'Company Details' section
    private final SelenideElement companyDetailsButton =
            $("a[data-test='company-navigation-section']");
    private final SelenideElement largeUserpic = $("div[data-test='uploaded-image-section']");
    private final SelenideElement uploadPictureButton = $("a[data-test='upload-new-picture-text']");
    private final SelenideElement pictureText = $("div[data-test='upload-file-format-text']");

    private final SelenideElement companyNameField = $("input[data-test='company-name-field']");
    private final SelenideElement companyOwnerField = $("div[data-test='company-owner-dropdown']");
    private final SelenideElement emailField = $("input[data-test='company-email-field']");
    private final SelenideElement updateButton = $("button[data-test='update-button']");

    // 'Company Actions' section
    private final SelenideElement companyActionsButton =
            $("a[data-test='company-actions-section']");
    private final SelenideElement deactivateButton = $("button[data-test='company-deactivate']");
    private final SelenideElement companyStatusTitle = $("div[data-test='company-status-title']");

    private final SelenideElement activateButton = $("a[data-test='activate']");
    private final SelenideElement actionsLogTitle = $("a[data-test='action-log-title']");
    private final SelenideElement activatedByText = $("a[data-test='activated-by-text']");
    private final SelenideElement deactivatedByText = $("a[data-test='deactivated-by-text']");
    private final SelenideElement createdByText = $("a[data-test='created-by-text']");
}
