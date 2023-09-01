package com.practis.web.selenide.validator.company;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.companySettingsCompanyPage;

import com.practis.dto.NewUserInput;
import java.util.List;

public class CompanySettingsCompanyValidator {
    /** Assert Company elements on Company Settings page. */
    public static void assertCompanyElementsOnCompanySettingsPage() {
        companySettingsCompanyPage().getCompanySettingsTitle().shouldBe(visible);
        companySettingsCompanyPage()
                .getCompanySettingsTitle()
                .shouldBe(exactText("Company Settings • CompanyAuto"));
        // companySettingsCompanyPage().getCompanySettingsTitleCompany().shouldBe(visible);
        // companySettingsCompanyPage().getCompanySettingsTitleCompany().shouldBe(exactText("CompanyAuto"));
        companySettingsCompanyPage().getCrossButton().shouldBe(visible);
        companySettingsCompanyPage().getDetailsSection().shouldBe(visible);
        companySettingsCompanyPage().getDetailsSection().shouldBe(exactText("Details"));
        companySettingsCompanyPage().getLogoSection().shouldBe(visible);
        companySettingsCompanyPage().getLogoSection().shouldBe(exactText("Logo"));
        companySettingsCompanyPage().getLicensedSeatsSection().shouldBe(visible);
        companySettingsCompanyPage()
                .getLicensedSeatsSection()
                .shouldBe(exactText("Licensed Seats"));
        companySettingsCompanyPage().getVoiceSection().shouldBe(visible);
        companySettingsCompanyPage().getVoiceSection().shouldBe(exactText("Voice"));

        companySettingsCompanyPage().getCompanyNameField().shouldBe(visible);
        companySettingsCompanyPage().getCompanyNameField().shouldBe(exactText("Company Name"));
        companySettingsCompanyPage().getCompanyNameInput().shouldBe(visible);
        companySettingsCompanyPage().getCompanyNameInput().shouldBe(enabled);
        companySettingsCompanyPage().getCompanyNameInput().shouldBe(exactText("CompanyAuto"));
        companySettingsCompanyPage().getWorkspaceUrl().shouldBe(visible);
        companySettingsCompanyPage().getWorkspaceUrl().shouldBe(exactText("Workspace URL"));
        companySettingsCompanyPage().getWorkspaceUrlInput().shouldBe(visible);
        companySettingsCompanyPage().getWorkspaceUrlInput().shouldBe(disabled);
        companySettingsCompanyPage()
                .getWorkspaceUrlInput()
                .shouldBe(exactText("company-130.gopractis.com"));
        companySettingsCompanyPage().getAccountOwner().shouldBe(visible);
        companySettingsCompanyPage().getAccountOwner().shouldBe(exactText("Account Owner"));
        companySettingsCompanyPage().getAccountOwnerField().shouldBe(visible);
        companySettingsCompanyPage().getAccountOwnerField().shouldBe(enabled);
        companySettingsCompanyPage().getAccountOwnerField().shouldBe(exactText("No Account Owner"));

        companySettingsCompanyPage().getApplyButton().shouldBe(visible);
        companySettingsCompanyPage().getApplyButton().shouldBe(disabled);
        companySettingsCompanyPage().getApplyButton().shouldBe(attribute("color", "default"));
        companySettingsCompanyPage().getApplyButton().shouldBe(attribute("type", "submit"));
    }

    /** Assert Updated Company name. */
    public static void assertUpdatedNameCompany() {
        companySettingsCompanyPage().getCompanySettingsTitle().shouldBe(visible);
        companySettingsCompanyPage()
                .getCompanySettingsTitle()
                .shouldBe(exactText("Company Settings • CompanyAuto1"));
        companySettingsCompanyPage()
                .getCompanyNameInput()
                .shouldBe(attribute("value", "CompanyAuto1"));
    }

    /** Assert Account Owner dropdown. */
    public static void assertAccountOwnerDropdownCompany(final List<NewUserInput> user) {
        companySettingsCompanyPage().getAccountOwnerValue().get(0).shouldBe(visible);
        companySettingsCompanyPage()
                .getAccountOwnerValue()
                .get(0)
                .shouldBe(matchText(user.get(0).getFirstName()));
        companySettingsCompanyPage()
                .getAccountOwnerValue()
                .get(0)
                .shouldBe(matchText(user.get(0).getLastName()));
    }

    /** Assert Account Owner Updated field. */
    public static void assertUpdatedAccountOwnerFieldCompany(final List<NewUserInput> user) {
        companySettingsCompanyPage().getAccountOwnerField().shouldBe(visible);
        companySettingsCompanyPage()
                .getAccountOwnerField()
                .shouldBe(matchText(user.get(0).getFirstName()));
        companySettingsCompanyPage()
                .getAccountOwnerField()
                .shouldBe(matchText(user.get(0).getLastName()));
    }

    /** Assert Already Existing Name */
    public static void assertNameExistsError() {
        companySettingsCompanyPage().getCompanyNameInputError().shouldBe(visible);
    }

    /** Assert Unlimited Licensed Seats */
    public static void assertLicensedSeatsCompany(String text) {
        companySettingsCompanyPage().getLicensedSeatsTitle().shouldBe(visible);
        companySettingsCompanyPage().getLicensedSeatsTitle().shouldBe(matchText(text));
        companySettingsCompanyPage().getRegisteredCount().shouldBe(visible);
        companySettingsCompanyPage().getRegisteredCount().shouldBe(matchText("Registered"));
        companySettingsCompanyPage().getPendingCount().shouldBe(visible);
        companySettingsCompanyPage().getPendingCount().shouldBe(matchText("Pending Registration"));
        companySettingsCompanyPage().getDeactivatedCount().shouldBe(visible);
        companySettingsCompanyPage().getDeactivatedCount().shouldBe(matchText("Deactivated"));
        companySettingsCompanyPage().getLimitTip().shouldBe(visible);
        companySettingsCompanyPage()
                .getLimitTip()
                .shouldBe(
                        matchText(
                                "Quick tip: You may be able to find unaccepted invitations and"
                                        + " revoke them, which will free up some seats."));

        companySettingsCompanyPage().getRequestLimitChangeButton().shouldBe(visible);
        companySettingsCompanyPage()
                .getRequestLimitChangeButton()
                .shouldBe(exactText("Request Limit Change"));
        companySettingsCompanyPage()
                .getRequestLimitChangeButton()
                .shouldBe(attribute("color", "default"));
        companySettingsCompanyPage()
                .getRequestLimitChangeButton()
                .shouldBe(attribute("type", "submit"));
        companySettingsCompanyPage().getManageInvitationsButton().shouldBe(visible);
        companySettingsCompanyPage()
                .getManageInvitationsButton()
                .shouldBe(exactText("Manage Invitations"));
        companySettingsCompanyPage()
                .getManageInvitationsButton()
                .shouldBe(attribute("color", "default"));
        companySettingsCompanyPage()
                .getManageInvitationsButton()
                .shouldBe(attribute("type", "submit"));
    }

    /** Assert Users Counter */
    public static void assertUsersCounter() {
        companySettingsCompanyPage().getRegisteredCount().shouldBe(visible);
        companySettingsCompanyPage().getRegisteredCount().shouldBe(exactText("2 Registered"));
        companySettingsCompanyPage().getPendingCount().shouldBe(visible);
        companySettingsCompanyPage()
                .getPendingCount()
                .shouldBe(exactText("3 Pending Registration"));
        companySettingsCompanyPage().getDeactivatedCount().shouldBe(visible);
        companySettingsCompanyPage().getDeactivatedCount().shouldBe(matchText("Deactivated"));
    }

    /** Assert Logo Page */
    public static void assertLogoTab() {
        companySettingsCompanyPage().getCompanyLogo().shouldBe(visible);
        companySettingsCompanyPage().getCompanyLogoCamera().shouldBe(visible);
        companySettingsCompanyPage().getLogoDescription().shouldBe(visible);
        companySettingsCompanyPage()
                .getLogoDescription()
                .shouldBe(exactText("PNG, JPG, JPEG • Less than 2 MB"));
        companySettingsCompanyPage().getGuidelinesLink().shouldBe(hidden);
    }

    /** Assert data with uploaded logo on Logo tab . */
    public static void assertLogoWithLogo() {
        companySettingsCompanyPage().getCompanyLogo().shouldBe(visible);
        companySettingsCompanyPage().getCompanyLogo().shouldBe(enabled);
        companySettingsCompanyPage().getDeleteCompanyLogo().shouldBe(visible);
        companySettingsCompanyPage().getCompanyLogoCamera().shouldBe(hidden);
        companySettingsCompanyPage().getLogoDescription().shouldBe(visible);
        companySettingsCompanyPage()
                .getLogoDescription()
                .shouldBe(matchText("PNG, JPG, JPEG • Less than 2 MB"));
        companySettingsCompanyPage().getGuidelinesLink().shouldBe(hidden);
    }

    /** Assert elements on Voice tab. */
    public static void assertVoiceTab() {
        companySettingsCompanyPage().getVoiceTitle().get(0).shouldBe(visible);
        companySettingsCompanyPage().getVoiceTitle().get(0).shouldBe(exactText("Customer"));
        companySettingsCompanyPage().getSampleTextField().get(0).shouldBe(visible);
        companySettingsCompanyPage().getSampleTextField().get(0).shouldBe(enabled);
        companySettingsCompanyPage()
                .getSampleTextField()
                .get(0)
                .shouldBe(attribute("placeholder", "Sample text here…"));
        companySettingsCompanyPage().getTestVoiceButton().get(0).shouldBe(visible);
        companySettingsCompanyPage().getTestVoiceButton().get(0).shouldBe(exactText("Test Voice"));
        // companySettingsCompanyPage().getTestVoiceButton().get(0).shouldBe(attribute("disabled"));
        companySettingsCompanyPage().getTestVoiceButton().get(1).shouldBe(visible);
        companySettingsCompanyPage().getTestVoiceButton().get(1).shouldBe(exactText("Test Voice"));
        // companySettingsCompanyPage().getTestVoiceButton().get(1).shouldBe(attribute("disabled"));

        companySettingsCompanyPage().getValues().get(0).shouldBe(visible);
        companySettingsCompanyPage().getValues().get(0).shouldBe(exactText("0.25"));
        companySettingsCompanyPage().getValues().get(1).shouldBe(exactText("1.02"));

        companySettingsCompanyPage().getCustomerSpeedText().get(0).shouldBe(visible);
        companySettingsCompanyPage().getCustomerSpeedText().get(1).shouldBe(visible);
        companySettingsCompanyPage().getCustomerSpeedText().get(2).shouldBe(visible);
        companySettingsCompanyPage().getCustomerSpeedText().get(3).shouldBe(visible);
        companySettingsCompanyPage().getCustomerGenderTitle().get(0).shouldBe(visible);
        companySettingsCompanyPage().getCustomerGenderTitle().get(1).shouldBe(visible);
        companySettingsCompanyPage().getCustomerGenderTitle().get(2).shouldBe(visible);
        companySettingsCompanyPage().getCustomerGenderTitle().get(3).shouldBe(visible);

        companySettingsCompanyPage().getResetButton().get(0).shouldBe(visible);
        companySettingsCompanyPage().getResetButton().get(0).shouldBe(exactText("Reset"));
        companySettingsCompanyPage().getResetButton().get(1).shouldBe(visible);
        companySettingsCompanyPage().getResetButton().get(1).shouldBe(exactText("Reset"));
        companySettingsCompanyPage().getSaveButton().get(0).shouldBe(visible);
        companySettingsCompanyPage().getSaveButton().get(0).shouldBe(exactText("Save"));
        companySettingsCompanyPage().getSaveButton().get(1).shouldBe(visible);
        companySettingsCompanyPage().getSaveButton().get(1).shouldBe(exactText("Save"));
    }

    /** Assert changed Voice Settings. */
    public static void assertChangedVoiceSettings() {
        companySettingsCompanyPage().getValues().get(0).shouldBe(exactText("0.25"));
        companySettingsCompanyPage().getValues().get(1).shouldNotBe(exactText("1.02"));
    }
}
