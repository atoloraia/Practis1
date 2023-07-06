package com.practis.web.selenide.validator.admin;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.companySettingsPage;

public class CompanySettingsValidator {

    /** Assert elements on Company Settings page. */
    public static void assertElementsOnCompanySettingsPage(String button, String text) {
        companySettingsPage().getCompanySettingsTitle().shouldBe(exactText("Company Settings"));
        companySettingsPage().getCompanyName().shouldBe(visible);

        companySettingsPage().getBackButton().shouldBe(visible);
        companySettingsPage().getBackButton().lastChild().shouldBe(attribute("width", "100%"));

        companySettingsPage().getSmallUserPic().shouldBe(visible);

        companySettingsPage().getCompanyTitle().shouldBe(visible);
        String companyName = companySettingsPage().getCompanyName().text();
        companySettingsPage().getCompanyNameField().shouldHave(attribute("value", companyName));

        // Download Report button
        companySettingsPage().getDownloadReportButton().shouldBe(visible);
        companySettingsPage().getDownloadReportButton().shouldBe(exactText("Download Report"));
        companySettingsPage().getDownloadReportButton().shouldBe(attribute("type", "submit"));
        companySettingsPage()
                .getDownloadReportButton()
                .shouldBe(attribute("title", "Download Report"));
        companySettingsPage().getDownloadReportButton().shouldBe(attribute("width", "136px"));
        companySettingsPage().getDownloadReportButton().shouldBe(attribute("color", "default"));

        // View Logs button
        companySettingsPage().getViewLogsButton().shouldBe(visible);
        companySettingsPage().getViewLogsButton().shouldBe(exactText("View Logs"));
        companySettingsPage().getViewLogsButton().shouldBe(attribute("type", "submit"));
        companySettingsPage().getViewLogsButton().shouldBe(attribute("title", "View Logs"));
        companySettingsPage().getViewLogsButton().shouldBe(attribute("width", "136px"));
        companySettingsPage().getViewLogsButton().shouldBe(attribute("color", "default"));

        // --> Company Details section
        companySettingsPage().getCompanyDetailsButton().shouldBe(visible);
        companySettingsPage().getCompanyDetailsButton().shouldBe(exactText("Company Details"));

        companySettingsPage().getLargeUserpic().shouldBe(visible);
        companySettingsPage().getLargeUserpic().shouldBe(attribute("width", "136"));
        companySettingsPage().getLargeUserpic().shouldBe(attribute("height", "136"));

        companySettingsPage().getUploadPictureButton().shouldBe(exactText("Upload a new picture"));
        companySettingsPage()
                .getPictureText()
                .shouldBe(exactText("JPG, PNG, BMP only. Less than 10 MB"));
        companySettingsPage()
                .getCompanyNameField()
                .shouldBe(attributeMatching("value", companyName));

        // Company field
        companySettingsPage().getCompanyNameField().sibling(0).shouldBe(matchText("Company Name"));
        companySettingsPage().getCompanyNameField().shouldBe(attribute("type", "text"));
        companySettingsPage().getCompanyNameField().shouldBe(attribute("maxlength", "100"));

        // Company Owner field
        companySettingsPage().getCompanyOwnerField().shouldBe(visible);
        companySettingsPage().getCompanyOwnerField().shouldBe(matchText("Company Owner"));

        // Email field
        companySettingsPage().getEmailField().sibling(0).shouldBe(matchText("Email"));
        companySettingsPage().getEmailField().shouldBe(attribute("name", "email"));
        companySettingsPage().getEmailField().shouldBe(attribute("font-family", "Manrope"));
        companySettingsPage().getEmailField().shouldBe(attribute("type", "email"));
        companySettingsPage().getEmailField().shouldBe(disabled);

        // Update button
        companySettingsPage().getUpdateButton().shouldBe(visible);
        companySettingsPage().getUpdateButton().shouldBe(exactText("Update"));
        companySettingsPage().getUpdateButton().shouldBe(attribute("type", "submit"));
        companySettingsPage().getUpdateButton().shouldBe(attribute("color", "default"));

        // --> Company Actions section
        companySettingsPage().getCompanyActionsButton().shouldBe(visible);
        companySettingsPage().getCompanyActionsButton().shouldBe(exactText("Company Actions"));
        companySettingsPage().getCompanyActionsButton().click();

        companySettingsPage().getCompanyStatusTitle().shouldBe(exactText("Company Status"));
        companySettingsPage().getActivationButton().shouldBe(visible);
        companySettingsPage().getActivationButton().shouldBe(exactText(button));
        companySettingsPage().getLastChangesText().get(0).shouldBe(visible);
        companySettingsPage().getLastChangesText().get(0).shouldBe(matchText(text));

        // --> User Limit section
        companySettingsPage().getUserLimitButton().shouldBe(visible);
        companySettingsPage().getUserLimitButton().shouldBe(exactText("User Limit"));
        companySettingsPage().getUserLimitButton().click();

        companySettingsPage().getSectionTitle().shouldBe(exactText("Unlimited Users"));
        companySettingsPage().getRegisteredCounter().shouldBe(visible);
        companySettingsPage().getRegisteredCounter().shouldBe(matchText("Registered"));
        companySettingsPage().getPendingCounter().shouldBe(visible);
        companySettingsPage().getRegisteredCounter().shouldBe(matchText("Pending Registration"));
        companySettingsPage().getDeactivatedCounter().shouldBe(visible);
        companySettingsPage().getRegisteredCounter().shouldBe(matchText("Deactivated"));

        companySettingsPage().getSelectedRadioButton().shouldBe(visible);
        companySettingsPage().getRadioButton().shouldBe(visible);

        companySettingsPage().getLimitedUsersTitle().shouldBe(visible);
        companySettingsPage().getLimitedUsersTitle().shouldBe(exactText("Unlimited Users"));
        companySettingsPage().getUnlimitedUsersDescription().shouldBe(visible);
        companySettingsPage()
                .getUnlimitedUsersDescription()
                .shouldBe(
                        exactText(
                                "The company can have as many users in different roles and"
                                    + " statuses as they wish. It can be changed at any time by a"
                                    + " Practis Admin in Company Settings."));

        companySettingsPage().getLimitedUsersTitle().shouldBe(visible);
        companySettingsPage().getLimitedUsersField().shouldBe(visible);
        companySettingsPage().getLimitedUsersField().shouldBe(empty);
        companySettingsPage().getLimitedUsersTitle().shouldBe(exactText("Limited Users"));
        companySettingsPage()
                .getLimitedUsersDescription()
                .shouldBe(
                        exactText(
                                "Company won’t be able to have more than this number of users."
                                    + " This number spreads on all statuses and roles. Customer"
                                    + " can request to change this limit. Practis Admin can edit"
                                    + " it Practis Admin in the Company Settings."));
    }

    /** Assert status and action button on Company Settings page. */
    public static void assertStatusChangesCompanySettings(String status, String button) {
        companySettingsPage().getStatusBadge().shouldBe(visible);
        companySettingsPage().getStatusBadge().shouldBe(exactText(status));
        companySettingsPage().getActivationButton().shouldBe(visible);
        companySettingsPage().getActivationButton().shouldBe(exactText(button));
    }

    /** Assert Deactivated Logs. */
    public static void assertDeactivatedLogs(String text) {
        companySettingsPage().getActionsLogs().get(1).shouldBe(visible);
        companySettingsPage().getActionsLogs().get(1).shouldBe(matchText(text));
    }

    /** Assert Activated Logs. */
    public static void assertActivatedLogs(String text) {
        companySettingsPage().getActionsLogs().get(0).shouldBe(visible);
        companySettingsPage().getActionsLogs().get(0).shouldBe(matchText(text));
    }

    /** Assert statuses logs. */
    public static void assertStatusesLogs() {}

    /** Assert Limited Users Elements. */
    public static void assertLimitedUsersElement() {
        // --> User Limit section
        companySettingsPage().getUserLimitButton().shouldBe(visible);
        companySettingsPage().getUserLimitButton().shouldBe(exactText("User Limit"));
        companySettingsPage().getUserLimitButton().click();

        companySettingsPage().getSectionTitle().shouldBe(exactText("Limited Users"));
        companySettingsPage().getRegisteredCounter().shouldBe(visible);
        companySettingsPage().getRegisteredCounter().shouldBe(matchText("Registered"));
        companySettingsPage().getPendingCounter().shouldBe(visible);
        companySettingsPage().getRegisteredCounter().shouldBe(matchText("Pending Registration"));
        companySettingsPage().getDeactivatedCounter().shouldBe(visible);
        companySettingsPage().getRegisteredCounter().shouldBe(matchText("Deactivated"));

        companySettingsPage().getSelectedRadioButton().shouldBe(visible);
        companySettingsPage().getRadioButton().shouldBe(visible);

        companySettingsPage().getLimitedUsersTitle().shouldBe(visible);
        companySettingsPage().getLimitedUsersTitle().shouldBe(exactText("Unlimited Users"));
        companySettingsPage().getUnlimitedUsersDescription().shouldBe(visible);
        companySettingsPage()
                .getUnlimitedUsersDescription()
                .shouldBe(
                        exactText(
                                "The company can have as many users in different roles and"
                                    + " statuses as they wish. It can be changed at any time by a"
                                    + " Practis Admin in Company Settings."));

        companySettingsPage().getLimitedUsersTitle().shouldBe(visible);
        companySettingsPage().getLimitedUsersField().shouldBe(visible);
        companySettingsPage().getLimitedUsersField().shouldNotBe(empty);
        companySettingsPage().getLimitedUsersTitle().shouldBe(exactText("Limited Users"));
        companySettingsPage()
                .getLimitedUsersDescription()
                .shouldBe(
                        exactText(
                                "Company won’t be able to have more than this number of users."
                                    + " This number spreads on all statuses and roles. Customer"
                                    + " can request to change this limit. Practis Admin can edit"
                                    + " it Practis Admin in the Company Settings."));
    }

    /** Assert Users Counter on Users Limit tab. */
    public static void assertUsersCounterUsersLimit() {
        companySettingsPage().getRegisteredCounter().shouldBe(visible);
        companySettingsPage().getRegisteredCounter().shouldBe(exactText("5 Registered"));
        companySettingsPage().getPendingCounter().shouldBe(visible);
        companySettingsPage().getPendingCounter().shouldBe(exactText("3 Pending Registration"));
        companySettingsPage().getDeactivatedCounter().shouldBe(visible);
        companySettingsPage().getDeactivatedCounter().shouldBe(matchText("Deactivated"));
    }
}
