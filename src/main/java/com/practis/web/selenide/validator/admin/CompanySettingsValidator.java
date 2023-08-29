package com.practis.web.selenide.validator.admin;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.companySettingsPage;

import com.practis.dto.NewUserInput;
import java.util.List;

public class CompanySettingsValidator {

    /** Assert elements on Company Settings page. */
    public static void assertElementsOnCompanySettingsPage(String button, String text) {
        companySettingsPage().getCompanySettingsTitle().shouldBe(exactText("Company Settings"));
        companySettingsPage().getCompanyName().shouldBe(visible);

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
        companySettingsPage().getUserLimitButton().shouldBe(exactText("Licensed Seats"));
        companySettingsPage().getUserLimitButton().click();

        companySettingsPage()
                .getLimitedUsersTitle()
                .shouldBe(exactText("Unlimited licensed seats"));
        companySettingsPage().getRegisteredCounter().shouldBe(visible);
        companySettingsPage().getRegisteredCounter().shouldBe(matchText("0 Registered"));
        companySettingsPage().getPendingCounter().shouldBe(visible);
        companySettingsPage().getPendingCounter().shouldBe(matchText("0 Pending Registration"));
        companySettingsPage().getDeactivatedCounter().shouldBe(visible);
        companySettingsPage().getDeactivatedCounter().shouldBe(matchText("0 Deactivated"));

        companySettingsPage().getUnlimitedRadioButton().shouldBe(visible);
        companySettingsPage().getLimitedRadioButton().shouldBe(visible);

        companySettingsPage().getLimitedUsersTitle().shouldBe(visible);
        companySettingsPage()
                .getLimitedUsersTitle()
                .shouldBe(exactText("Unlimited licensed seats"));
        companySettingsPage().getUnlimitedUsersDescription().shouldBe(visible);
        companySettingsPage()
                .getUnlimitedUsersDescription()
                .shouldBe(
                        exactText(
                                "Company can have as many users in different roles and statuses as"
                                        + " they wish."));

        companySettingsPage().getLimitedUsersText().shouldBe(visible);
        companySettingsPage().getLimitedUsersText().shouldBe(exactText("Limit licensed seats to"));
        companySettingsPage().getLimitedUsersField().shouldBe(empty);
        companySettingsPage().getLimitedUsersField().shouldBe(disabled);
        companySettingsPage()
                .getLimitedUsersDescription()
                .shouldBe(
                        exactText(
                                "Company won't be able to have more than this number of seats."
                                        + " Deactivated user seats still count towards the licensed"
                                        + " seat count."));
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

    /** Assert Limited Users Elements. */
    public static void assertLimitedUsersElement() {
        // --> User Limit section
        companySettingsPage().getUserLimitButton().shouldBe(visible);
        companySettingsPage().getUserLimitButton().shouldBe(exactText("Licensed Seats"));
        companySettingsPage().getUserLimitButton().click();

        companySettingsPage()
                .getLimitedUsersTitle()
                .shouldBe(exactText("0 of 5 licensed seats have been used"));
        companySettingsPage().getRegisteredCounter().shouldBe(visible);
        companySettingsPage().getRegisteredCounter().shouldBe(matchText("0 Registered"));
        companySettingsPage().getPendingCounter().shouldBe(visible);
        companySettingsPage().getPendingCounter().shouldBe(exactText("0 Pending Registration"));
        companySettingsPage().getDeactivatedCounter().shouldBe(visible);
        companySettingsPage().getDeactivatedCounter().shouldBe(matchText("0 Deactivated"));

        companySettingsPage().getLimitedRadioButton().shouldBe(visible);
        companySettingsPage().getUnlimitedRadioButton().shouldBe(visible);

        companySettingsPage().getUnlimitedUsersTitle().shouldBe(visible);
        companySettingsPage()
                .getUnlimitedUsersTitle()
                .shouldBe(exactText("Unlimited Licensed Seats"));
        companySettingsPage().getUnlimitedUsersDescription().shouldBe(visible);
        companySettingsPage()
                .getUnlimitedUsersDescription()
                .shouldBe(
                        exactText(
                                "Company can have as many users in different roles and statuses as"
                                        + " they wish."));

        companySettingsPage().getLimitedUsersTitle().shouldBe(visible);
        companySettingsPage().getLimitedUsersField().shouldBe(visible);
        companySettingsPage().getLimitedUsersField().shouldNotBe(empty);
        companySettingsPage().getLimitedUsersField().shouldNotBe(disabled);
        companySettingsPage().getLimitedUsersText().shouldBe(exactText("Limit licensed seats to"));
        companySettingsPage()
                .getLimitedUsersDescription()
                .shouldBe(
                        exactText(
                                "Company won't be able to have more than this number of seats."
                                        + " Deactivated user seats still count towards the licensed"
                                        + " seat count."));
        companySettingsPage().getLimitedUsersError().shouldBe(hidden);
    }

    /** Assert Users Counter on Users Limit tab. */
    public static void assertUsersCounterUsersLimit() {
        companySettingsPage().getRegisteredCounter().shouldBe(visible);
        companySettingsPage().getRegisteredCounter().shouldBe(matchText("Registered"));
        companySettingsPage().getPendingCounter().shouldBe(visible);
        companySettingsPage().getPendingCounter().shouldBe(matchText("Pending Registration"));
        companySettingsPage().getDeactivatedCounter().shouldBe(visible);
        companySettingsPage().getDeactivatedCounter().shouldBe(matchText("Deactivated"));
    }

    /** Assert Limited Users error. */
    public static void assertLimitedUsersErrorText() {
        companySettingsPage().getLimitedUsersError().shouldBe(visible);
        companySettingsPage()
                .getLimitedUsersError()
                .shouldBe(
                        exactText(
                                "Important: You've set a limit that's lower than the existing"
                                    + " number of licensed user seats in this account. Once you"
                                    + " apply this limit, existing users in this account will be"
                                    + " able to continue using the product, but the account won't"
                                    + " be able to invite new users until a Practis Admin deletes"
                                    + " users below this new limit. Seats can also be freed up by"
                                    + " revoking pending/unaccepted invitations and assigning them"
                                    + " to other users."));
    }

    /** Assert Company elements on Company Settings page. */
    public static void assertNewCompanyElementsOnCompanySettingsPage() {
        companySettingsPage().getCompanySettingsTitle().shouldBe(visible);
        companySettingsPage()
                .getCompanySettingsTitle()
                .shouldBe(exactText("Company Settings • CompanyAuto"));
        // companySettingsPage().getCompanySettingsTitleCompany().shouldBe(visible);
        // companySettingsPage().getCompanySettingsTitleCompany().shouldBe(exactText("CompanyAuto"));
        companySettingsPage().getCrossButton().shouldBe(visible);
        companySettingsPage().getDetailsSection().shouldBe(visible);
        companySettingsPage().getDetailsSection().shouldBe(exactText("Details"));
        companySettingsPage().getLogoSection().shouldBe(visible);
        companySettingsPage().getLogoSection().shouldBe(exactText("Logo"));
        companySettingsPage().getLicensedSeatsSection().shouldBe(visible);
        companySettingsPage().getLicensedSeatsSection().shouldBe(exactText("Licensed Seats"));
        companySettingsPage().getVoiceSection().shouldBe(visible);
        companySettingsPage().getVoiceSection().shouldBe(exactText("Voice"));

        companySettingsPage().getCompanyNameField().shouldBe(visible);
        companySettingsPage().getCompanyNameField().shouldBe(exactText("Company Name"));
        companySettingsPage().getCompanyNameInput().shouldBe(visible);
        companySettingsPage().getCompanyNameInput().shouldBe(enabled);
        companySettingsPage().getCompanyNameInput().shouldBe(exactText("CompanyAuto"));
        companySettingsPage().getWorkspaceUrl().shouldBe(visible);
        companySettingsPage().getWorkspaceUrl().shouldBe(exactText("Workspace URL"));
        companySettingsPage().getWorkspaceUrlInput().shouldBe(visible);
        companySettingsPage().getWorkspaceUrlInput().shouldBe(disabled);
        companySettingsPage()
                .getWorkspaceUrlInput()
                .shouldBe(exactText("company-130.gopractis.com"));
        companySettingsPage().getAccountOwner().shouldBe(visible);
        companySettingsPage().getAccountOwner().shouldBe(exactText("Account Owner"));
        companySettingsPage().getAccountOwnerField().shouldBe(visible);
        companySettingsPage().getAccountOwnerField().shouldBe(enabled);
        companySettingsPage().getAccountOwnerField().shouldBe(exactText("No Account Owner"));

        companySettingsPage().getApplyButton().shouldBe(visible);
        companySettingsPage().getApplyButton().shouldBe(disabled);
        companySettingsPage().getApplyButton().shouldBe(attribute("color", "default"));
        companySettingsPage().getApplyButton().shouldBe(attribute("type", "submit"));
    }

    /** Assert Updated Company name. */
    public static void assertUpdatedName() {
        companySettingsPage().getCompanySettingsTitle().shouldBe(visible);
        companySettingsPage()
                .getCompanySettingsTitle()
                .shouldBe(exactText("Company Settings • CompanyAuto1"));
        companySettingsPage().getCompanyNameInput().shouldBe(attribute("value", "CompanyAuto1"));
    }

    /** Assert Account Owner dropdown. */
    public static void assertAccountOwnerDropdown(final List<NewUserInput> user) {
        companySettingsPage().getAccountOwnerValue().get(0).shouldBe(visible);
        companySettingsPage()
                .getAccountOwnerValue()
                .get(0)
                .shouldBe(matchText(user.get(0).getFirstName()));
        companySettingsPage()
                .getAccountOwnerValue()
                .get(0)
                .shouldBe(matchText(user.get(0).getLastName()));
    }

    /** Assert Account Owner Updated field. */
    public static void assertUpdatedAccountOwnerField(final List<NewUserInput> user) {
        companySettingsPage().getAccountOwnerField().shouldBe(visible);
        companySettingsPage()
                .getAccountOwnerField()
                .shouldBe(matchText(user.get(0).getFirstName()));
        companySettingsPage().getAccountOwnerField().shouldBe(matchText(user.get(0).getLastName()));
    }
}
