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
                .shouldBe(exactText("Company Settings"));
        companySettingsCompanyPage().getCompanySettingsTitleCompany().shouldBe(visible);
        companySettingsCompanyPage()
                .getCompanySettingsTitleCompany()
                .shouldBe(exactText("CompanyAuto"));
        companySettingsCompanyPage().getCrossButton().shouldBe(visible);
        companySettingsCompanyPage().getDetailsTab().shouldBe(visible);
        companySettingsCompanyPage().getDetailsTab().shouldBe(exactText("Details"));
        companySettingsCompanyPage().getLogoTab().shouldBe(visible);
        companySettingsCompanyPage().getLogoTab().shouldBe(exactText("Logo"));
        companySettingsCompanyPage().getLicensedSeatsTab().shouldBe(visible);
        companySettingsCompanyPage().getLicensedSeatsTab().shouldBe(exactText("Licensed Seats"));
        companySettingsCompanyPage().getVoiceTab().shouldBe(visible);
        companySettingsCompanyPage().getVoiceTab().shouldBe(exactText("Voice"));

        companySettingsCompanyPage().getCompanyName().shouldBe(visible);
        companySettingsCompanyPage().getCompanyName().shouldBe(exactText("Name"));
        companySettingsCompanyPage().getCompanyNameField().shouldBe(visible);
        companySettingsCompanyPage().getCompanyNameField().shouldBe(enabled);
        companySettingsCompanyPage()
                .getCompanyNameField()
                .shouldBe(attribute("Value", "CompanyAuto"));
        companySettingsCompanyPage().getWorkspaceUrl().shouldBe(visible);
        companySettingsCompanyPage().getWorkspaceUrl().shouldBe(exactText("Workspace URL"));
        companySettingsCompanyPage().getWorkspaceUrlInput().shouldBe(visible);
        companySettingsCompanyPage().getWorkspaceUrlInput().shouldBe(disabled);
        companySettingsCompanyPage()
                .getWorkspaceUrlInput()
                .shouldBe(attribute("value", "company-130.gopractis.com"));
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
                .shouldBe(exactText("Company Settings"));
        companySettingsCompanyPage().getCompanySettingsTitleCompany().shouldBe(matchText("1"));
    }

    /** Assert Account Owner dropdown. */
    public static void assertAccountOwnerDropdownCompany(final List<NewUserInput> user) {
        companySettingsCompanyPage().getAccountOwnerValue().get(0).shouldBe(visible);
        companySettingsCompanyPage()
                .getAccountOwnerValue()
                .get(0)
                .shouldBe(exactText("No Account Owner"));
        companySettingsCompanyPage().getAccountOwnerValue().get(1).shouldBe(visible);
        companySettingsCompanyPage()
                .getAccountOwnerValue()
                .get(1)
                .shouldBe(matchText(user.get(0).getFirstName()));
        companySettingsCompanyPage()
                .getAccountOwnerValue()
                .get(1)
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

        // Customer Section
        companySettingsCompanyPage().getCustomerTitle().shouldBe(visible);
        companySettingsCompanyPage().getCustomerTitle().shouldBe(exactText("Customer"));
        companySettingsCompanyPage().getCustomerSampleTextInput().shouldBe(visible);
        companySettingsCompanyPage().getCustomerSampleTextInput().shouldBe(enabled);
        companySettingsCompanyPage()
                .getCustomerSampleTextInput()
                .shouldBe(attribute("placeholder", "Sample text here…"));
        companySettingsCompanyPage().getCustomerTestVoice().shouldBe(visible);
        companySettingsCompanyPage().getCustomerTestVoice().shouldBe(exactText("Test Voice"));
        companySettingsCompanyPage().getCustomerTestVoice().shouldBe(attribute("disabled"));

        companySettingsCompanyPage().getValues().get(0).shouldBe(visible);
        companySettingsCompanyPage().getValues().get(0).shouldBe(exactText("0.25"));
        companySettingsCompanyPage().getValues().get(1).shouldBe(exactText("1.02"));

        companySettingsCompanyPage().getCustomerSpeedLabel().shouldBe(visible);
        companySettingsCompanyPage().getCustomerSpeedLabel().shouldBe(exactText("Speed"));
        companySettingsCompanyPage().getCustomerPitchLabel().shouldBe(visible);
        companySettingsCompanyPage().getCustomerPitchLabel().shouldBe(exactText("Pitch"));
        companySettingsCompanyPage().getCustomerGenderLabel().shouldBe(visible);
        companySettingsCompanyPage().getCustomerGenderLabel().shouldBe(exactText("Gender"));
        companySettingsCompanyPage().getCustomerGenderDropdown().shouldBe(visible);
        companySettingsCompanyPage().getCustomerGenderDropdown().shouldBe(exactText("Male"));

        companySettingsCompanyPage().getCustomerVoiceLabel().shouldBe(visible);
        companySettingsCompanyPage().getCustomerVoiceLabel().shouldBe(exactText("Voice"));
        companySettingsCompanyPage().getCustomerVoiceDropdown().shouldBe(visible);
        companySettingsCompanyPage().getCustomerVoiceDropdown().shouldBe(exactText("Wavenet D"));

        companySettingsCompanyPage().getCustomerResetButton().shouldBe(visible);
        companySettingsCompanyPage().getCustomerResetButton().shouldBe(exactText("Reset"));
        companySettingsCompanyPage().getCustomerResetButton().shouldBe(attribute("type", "submit"));
        companySettingsCompanyPage()
                .getCustomerResetButton()
                .shouldBe(attribute("color", "default"));

        companySettingsCompanyPage().getCustomerSaveButton().shouldBe(visible);
        companySettingsCompanyPage().getCustomerSaveButton().shouldBe(exactText("Save"));
        companySettingsCompanyPage().getCustomerSaveButton().shouldBe(attribute("type", "submit"));
        companySettingsCompanyPage()
                .getCustomerSaveButton()
                .shouldBe(attribute("color", "default"));

        // Representative Section
        companySettingsCompanyPage().getRepresentativeTitle().shouldBe(visible);
        companySettingsCompanyPage().getRepresentativeTitle().shouldBe(exactText("Representative"));
        companySettingsCompanyPage().getRepresentativeSampleTextInput().shouldBe(visible);
        companySettingsCompanyPage().getRepresentativeSampleTextInput().shouldBe(enabled);
        companySettingsCompanyPage()
                .getRepresentativeSampleTextInput()
                .shouldBe(attribute("placeholder", "Sample text here…"));
        companySettingsCompanyPage().getRepresentativeTestVoice().shouldBe(visible);
        companySettingsCompanyPage().getRepresentativeTestVoice().shouldBe(exactText("Test Voice"));
        companySettingsCompanyPage().getRepresentativeTestVoice().shouldBe(attribute("disabled"));

        companySettingsCompanyPage().getRepresentativeSpeedLabel().shouldBe(visible);
        companySettingsCompanyPage().getRepresentativeSpeedLabel().shouldBe(exactText("Speed"));
        companySettingsCompanyPage().getRepresentativePitchLabel().shouldBe(visible);
        companySettingsCompanyPage().getRepresentativePitchLabel().shouldBe(exactText("Pitch"));
        companySettingsCompanyPage().getRepresentativeGenderLabel().shouldBe(visible);
        companySettingsCompanyPage().getRepresentativeGenderLabel().shouldBe(exactText("Gender"));
        companySettingsCompanyPage().getRepresentativeGenderDropdown().shouldBe(visible);
        companySettingsCompanyPage()
                .getRepresentativeGenderDropdown()
                .shouldBe(exactText("Female"));

        companySettingsCompanyPage().getRepresentativeVoiceLabel().shouldBe(visible);
        companySettingsCompanyPage().getRepresentativeVoiceLabel().shouldBe(exactText("Voice"));
        companySettingsCompanyPage().getRepresentativeVoiceDropdown().shouldBe(visible);
        companySettingsCompanyPage()
                .getRepresentativeVoiceDropdown()
                .shouldBe(exactText("Wavenet C"));

        companySettingsCompanyPage().getRepresentativeResetButton().shouldBe(visible);
        companySettingsCompanyPage().getRepresentativeResetButton().shouldBe(exactText("Reset"));
        companySettingsCompanyPage()
                .getRepresentativeResetButton()
                .shouldBe(attribute("type", "submit"));
        companySettingsCompanyPage()
                .getRepresentativeResetButton()
                .shouldBe(attribute("color", "default"));

        companySettingsCompanyPage().getRepresentativeSaveButton().shouldBe(visible);
        companySettingsCompanyPage().getRepresentativeSaveButton().shouldBe(exactText("Save"));
        companySettingsCompanyPage()
                .getRepresentativeSaveButton()
                .shouldBe(attribute("type", "submit"));
        companySettingsCompanyPage()
                .getRepresentativeSaveButton()
                .shouldBe(attribute("color", "default"));
    }

    /** Assert changed Voice Settings. */
    public static void assertChangedVoiceSettings() {
        companySettingsCompanyPage().getValues().get(0).shouldBe(exactText("0.25"));
        companySettingsCompanyPage().getValues().get(1).shouldNotBe(exactText("1.02"));
    }
}
