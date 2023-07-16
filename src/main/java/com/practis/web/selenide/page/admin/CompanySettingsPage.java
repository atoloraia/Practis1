package com.practis.web.selenide.page.admin;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class CompanySettingsPage {

    private final SelenideElement companySettingsTitle =
            $("div[data-test='company-settings-page-subtitle']");
    private final SelenideElement companySettingsTitleCompany = $(".sc-hAWCcR.kDoeSH");
    private final SelenideElement companyName = $("div[data-test='company-name']");
    private final SelenideElement backButton = $("div[data-test='back-arrow-button']");

    private final SelenideElement smallUserPic = $("div[data-test='company-avatar']");
    private final SelenideElement companyTitle = $("div[data-test='company-name']");
    private final ElementsCollection lastChangesText =
            $$("div[data-test='company-created-on-text']");
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
    private final SelenideElement activateButton = $("button[data-test='company-activate']");
    private final SelenideElement activationButton = $("button[data-test*='company']");
    private final SelenideElement companyStatusTitle = $("div[data-test='company-status-title']");

    private final SelenideElement actionsLogTitle = $("a[data-test='action-log-title']");
    private final ElementsCollection actionsLogs = $$("div[data-test='actions-log-item']");
    private final SelenideElement moreButton = $("div[data-test='more-button']");
    private final SelenideElement lessButton = $("div[data-test='less-button']");

    // 'User Limit' section
    private final SelenideElement userLimitButton = $("div[data-test='user-limit-button']");
    private final SelenideElement limitedUsersTitle = $("div[data-test='limit-title']");
    private final SelenideElement registeredCounter = $("span[data-test='registered-users-count']");
    private final SelenideElement pendingCounter = $("span[data-test='pending-users-count']");
    private final SelenideElement deactivatedCounter =
            $("span[data-test='deactivated-users-count']");

    private final SelenideElement limitedRadioButton =
            $("div[data-test='limited-seats-radio-view']");
    private final SelenideElement unlimitedRadioButton =
            $("div[data-test='unlimited-seats-radio-view']");
    private final SelenideElement radioButton = $("input[data-test='limited-seats-radio']");

    private final SelenideElement unlimitedUsersTitle =
            $("div[data-test='unlimited-seats-radio-label']");
    private final SelenideElement unlimitedUsersDescription =
            $("div[data-test='unlimited-seats-description']");

    private final SelenideElement limitedUsersText =
            $("div[data-test='limited-seats-radio-label']");
    private final SelenideElement limitedUsersField = $("input[data-test='limited-seats-input']");
    private final SelenideElement limitedUsersDescription =
            $("div[data-test='limited-seats-description']");
    private final SelenideElement limitedUsersError = $("div[data-test='limited-seats-warning']");

    private final SelenideElement applyButton = $("button[data-test='apply-button']");
}
