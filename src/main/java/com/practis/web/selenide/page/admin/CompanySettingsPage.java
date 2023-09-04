package com.practis.web.selenide.page.admin;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class CompanySettingsPage {

    private final SelenideElement companySettingsTitle = $(".sc-jIujIb.iKMyKR");
    private final SelenideElement companySettingsTitleCompany = $(".sc-kERfdS.idPyUj");
    private final SelenideElement companyActiveBadge = $("div[data-test='company-active-badge']");
    private final SelenideElement companyInactiveBadge =
            $("div[data-test='company-inactive-badge']");
    private final SelenideElement crossButton = $(".sc-dDgBMj.jRPqsM");
    private final ElementsCollection sections = $$(".sc-jTOrvi.eHfXqa");
    private final SelenideElement detailsSection = $(".sc-bzvcsD.jgiOns");
    private final SelenideElement logoSection = $(".sc-bzvcsD.jgiOns");
    private final SelenideElement licensedSeatsSection = $(".sc-bzvcsD.jgiOns");
    private final SelenideElement voiceSection = $(".sc-bzvcsD.jgiOns");

    // Company Details section
    private final SelenideElement companyName = $("div[data-test='company-name-title']");
    private final SelenideElement companyNameInput = $("input[data-test='company-name-input']");
    private final SelenideElement companyNameField =
            $("input[data-test='company-name-input-input']");
    private final SelenideElement companyNameInputError =
            $("div[data-test='company-name-input-error']");
    private final SelenideElement workspaceUrl = $("div[data-test='workspace-url-title']");
    private final SelenideElement workspaceUrlInput = $("input[data-test='workspace-url-input']");
    private final SelenideElement workspaceUrlField =
            $("input[data-test='workspace-url-input-input']");
    private final SelenideElement accountOwner = $("div[data-test='account-owner-title']");
    private final SelenideElement accountOwnerField = $("div[data-test='account-owner-dropdown']");
    private final SelenideElement accountOwnerClick = $(".sc-ekMSpL.ipclTR.company-select-input");
    private final ElementsCollection accountOwnerValue = $$(".sc-ezDxia.eRomjo");
    private final SelenideElement selectedAccountOwnerValue = $(".sc-ezDxia.wGQHC.selected-item");

    // Licensed Seats section
    private final SelenideElement limitUsersTitle = $("span[data-test='user-stats-text']");
    private final SelenideElement setLimitCount = $("span[data-test='user-stats-limit']");
    private final SelenideElement totalSeatsTakenCount = $("span[data-test='user-stats-total']");

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
    private final SelenideElement backButton = $("div[data-test='back-arrow-button']");

    // Old
    private final ElementsCollection lastChangesText =
            $$("div[data-test='company-created-on-text']");
    private final SelenideElement statusBadge = $("div[data-test*='badge']");


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
}
