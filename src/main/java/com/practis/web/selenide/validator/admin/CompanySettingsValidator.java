package com.practis.web.selenide.validator.admin;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.companySettingsPage;

public class CompanySettingsValidator {

    /** Assert elements on Company Settings page. */
    public static void assertElementsOnCompanySettingsPage(
            String status, String button, String text) {
        companySettingsPage().getCompanySettingsTitle().shouldBe(exactText("Company Settings"));
        companySettingsPage().getCompanyName().shouldBe(visible);

        companySettingsPage().getBackButton().shouldBe(visible);
        companySettingsPage().getBackButton().lastChild().shouldBe(attribute("width", "100%"));

        companySettingsPage().getSmallUserPic().shouldBe(visible);

        companySettingsPage().getCompanyTitle().shouldBe(visible);
        String companyName = companySettingsPage().getCompanyName().text();
        companySettingsPage().getCompanyTitle().shouldBe(matchText(companyName));

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
        assertStatusChangesCompanySettings(status, button, text);
    }

    /** Assert status and action button on Company Settings page. */
    public static void assertStatusChangesCompanySettings(
            String status, String button, String text) {
        companySettingsPage().getStatusBadge().shouldBe(visible);
        companySettingsPage().getStatusBadge().shouldBe(exactText(status));
        companySettingsPage().getDeactivateButton().shouldBe(visible);
        companySettingsPage().getDeactivateButton().shouldBe(exactText(button));
        companySettingsPage().getLastChangesText().shouldBe(visible);
        companySettingsPage().getLastChangesText().shouldBe(matchText(text));
    }

    /** Assert statuses logs. */
    public static void assertStatusesLogs() {}
}
