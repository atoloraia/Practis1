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
        companySettingsCompanyPage().getVoiceTab().shouldBe(exactText("AI Voice"));

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
        companySettingsCompanyPage().getRoleFieldTitle().shouldBe(visible);
        companySettingsCompanyPage().getRoleFieldTitle().shouldBe(exactText("Role"));
        companySettingsCompanyPage().getRoleField().shouldBe(visible);
        companySettingsCompanyPage().getRoleField().shouldBe(exactText("Customer"));

        companySettingsCompanyPage().getVoiceTitle().shouldBe(visible);
        companySettingsCompanyPage().getVoiceTitle().shouldBe(matchText("Customer"));
        companySettingsCompanyPage().getVoiceTitle().shouldBe(matchText("Voice"));
        companySettingsCompanyPage().getVoiceField().shouldBe(visible);
        companySettingsCompanyPage().getVoiceField().shouldBe(exactText("Bella"));

        companySettingsCompanyPage().getAdvancedVoiceSettings().shouldBe(visible);
        companySettingsCompanyPage()
                .getAdvancedVoiceSettings()
                .shouldBe(exactText("ADVANCED VOICE SETTINGS"));
        companySettingsCompanyPage().getToDefaultButton().shouldBe(visible);
        companySettingsCompanyPage().getToDefaultButton().shouldBe(exactText("To Default"));

        companySettingsCompanyPage().getValuesLine().get(0).shouldBe(visible);
        companySettingsCompanyPage().getValuesLine().get(1).shouldBe(visible);
        companySettingsCompanyPage().getStabilityTitle().shouldBe(visible);
        companySettingsCompanyPage().getStabilityTitle().shouldBe(exactText("Stability"));
        companySettingsCompanyPage().getStabilityTooltipIcon().shouldBe(visible);
        companySettingsCompanyPage().getMinValue().get(0).shouldBe(visible);
        companySettingsCompanyPage().getMinValue().get(0).shouldBe(exactText("More variable"));
        companySettingsCompanyPage().getMaxValue().get(0).shouldBe(visible);
        companySettingsCompanyPage().getMaxValue().get(0).shouldBe(exactText("More stable"));

        companySettingsCompanyPage().getClarityTitle().shouldBe(visible);
        companySettingsCompanyPage()
                .getClarityTitle()
                .shouldBe(exactText("Clarity + Similarity Enhancement"));
        companySettingsCompanyPage().getClarityTooltipIcon().shouldBe(visible);
        companySettingsCompanyPage().getMinValue().get(1).shouldBe(visible);
        companySettingsCompanyPage().getMinValue().get(1).shouldBe(exactText("Low"));
        companySettingsCompanyPage().getMaxValue().get(1).shouldBe(visible);
        companySettingsCompanyPage().getMaxValue().get(1).shouldBe(exactText("High"));

        companySettingsCompanyPage().getSampleTextTitle().shouldBe(visible);
        companySettingsCompanyPage().getSampleTextTitle().shouldBe(exactText("Sample Text"));
        companySettingsCompanyPage().getSampleTextField().shouldBe(visible);
        companySettingsCompanyPage()
                .getSampleTextField()
                .shouldBe(
                        exactText(
                                "Hello can you help me with something? I bought these wireless"
                                        + " headphones online last week. Sound is horrible. Total"
                                        + " disappointment. Just want someone to set me up with a"
                                        + " quality pair of headphones."));
        companySettingsCompanyPage().getListenButton().shouldBe(visible);
        companySettingsCompanyPage().getListenButton().shouldBe(enabled);
        companySettingsCompanyPage().getListenButton().shouldBe(exactText("Listen"));
        companySettingsCompanyPage().getCharacterCounter().shouldBe(visible);
        companySettingsCompanyPage().getCharacterCounter().shouldBe(matchText("197"));
        companySettingsCompanyPage().getCharacterCounter().shouldBe(matchText("250"));

        companySettingsCompanyPage().getApplyButton().shouldBe(visible);
        companySettingsCompanyPage().getApplyButton().shouldBe(disabled);
        companySettingsCompanyPage().getApplyButton().shouldBe(exactText("Apply"));
        companySettingsCompanyPage().getApplyButton().shouldBe(attribute("type", "submit"));
        companySettingsCompanyPage().getApplyButton().shouldBe(attribute("color", "default"));
    }

    /** Assert elements on Voice tab. */
    public static void assertNewRepVoiceTab() {

        // Customer Section
        companySettingsCompanyPage().getRoleFieldTitle().shouldBe(visible);
        companySettingsCompanyPage().getRoleFieldTitle().shouldBe(exactText("Role"));
        companySettingsCompanyPage().getRoleField().shouldBe(visible);
        companySettingsCompanyPage().getRoleField().shouldBe(exactText("Representative"));

        companySettingsCompanyPage().getVoiceTitle().shouldBe(visible);
        companySettingsCompanyPage().getVoiceTitle().shouldBe(matchText("Representative"));
        companySettingsCompanyPage().getVoiceTitle().shouldBe(matchText("Voice"));
        companySettingsCompanyPage().getVoiceField().shouldBe(visible);
        companySettingsCompanyPage().getVoiceField().shouldBe(exactText("Bella"));

        companySettingsCompanyPage().getAdvancedVoiceSettings().shouldBe(visible);
        companySettingsCompanyPage()
                .getAdvancedVoiceSettings()
                .shouldBe(exactText("ADVANCED VOICE SETTINGS"));
        companySettingsCompanyPage().getToDefaultButton().shouldBe(visible);
        companySettingsCompanyPage().getToDefaultButton().shouldBe(exactText("To Default"));

        companySettingsCompanyPage().getValuesLine().get(0).shouldBe(visible);
        companySettingsCompanyPage().getValuesLine().get(1).shouldBe(visible);
        companySettingsCompanyPage().getStabilityTitle().shouldBe(visible);
        companySettingsCompanyPage().getStabilityTitle().shouldBe(exactText("Stability"));
        companySettingsCompanyPage().getStabilityTooltipIcon().shouldBe(visible);
        companySettingsCompanyPage().getMinValue().get(0).shouldBe(visible);
        companySettingsCompanyPage().getMinValue().get(0).shouldBe(exactText("More variable"));
        companySettingsCompanyPage().getMaxValue().get(0).shouldBe(visible);
        companySettingsCompanyPage().getMaxValue().get(0).shouldBe(exactText("More stable"));

        companySettingsCompanyPage().getClarityTitle().shouldBe(visible);
        companySettingsCompanyPage()
                .getClarityTitle()
                .shouldBe(exactText("Clarity + Similarity Enhancement"));
        companySettingsCompanyPage().getMinValue().get(1).shouldBe(visible);
        companySettingsCompanyPage().getMinValue().get(1).shouldBe(exactText("Low"));
        companySettingsCompanyPage().getMaxValue().get(1).shouldBe(visible);
        companySettingsCompanyPage().getMaxValue().get(1).shouldBe(exactText("High"));

        companySettingsCompanyPage().getSampleTextTitle().shouldBe(visible);
        companySettingsCompanyPage().getSampleTextTitle().shouldBe(exactText("Sample Text"));
        companySettingsCompanyPage().getSampleTextField().shouldBe(visible);
        companySettingsCompanyPage()
                .getSampleTextField()
                .shouldBe(
                        exactText(
                                "Sorry to hear that. The headphone market is so saturated these"
                                    + " days. It can be really difficult to make an informed"
                                    + " decision. Especially when buying online. I can help. These"
                                    + " are the new headphones by InTunes. Everybody loves"
                                    + " these!"));
        companySettingsCompanyPage().getListenButton().shouldBe(visible);
        companySettingsCompanyPage().getListenButton().shouldBe(exactText("Listen"));
        companySettingsCompanyPage().getCharacterCounter().shouldBe(visible);
        companySettingsCompanyPage().getCharacterCounter().shouldBe(matchText("232"));
        companySettingsCompanyPage().getCharacterCounter().shouldBe(matchText("250"));
    }

    /** Assert Stability Tooltip. */
    public static void assertStabilityTooltip() {
        companySettingsCompanyPage().getStabilityTooltipText().shouldBe(visible);
        companySettingsCompanyPage().getStabilityTooltipText().shouldBe(matchText("More variable"));
        companySettingsCompanyPage()
                .getStabilityTooltipText()
                .shouldBe(
                        matchText(
                                "Increasing variability can make speech more expressive with"
                                    + " output varying between re-generations. It can also lead to"
                                    + " instabilities."));
        companySettingsCompanyPage().getStabilityTooltipText().shouldBe(matchText("More stable"));
        companySettingsCompanyPage()
                .getStabilityTooltipText()
                .shouldBe(
                        matchText(
                                "Increasing stability will make the voice more consistent between"
                                    + " re-generations, but it can also make it sounds a bit"
                                    + " monotone. On longer text fragments we recommend lowering"
                                    + " this value."));
    }

    /** Assert Tooltips. */
    public static void assertClarityTooltip() {
        companySettingsCompanyPage().getClarityTooltipText().shouldBe(visible);
        companySettingsCompanyPage().getClarityTooltipText().shouldBe(matchText("Low"));
        companySettingsCompanyPage()
                .getClarityTooltipText()
                .shouldBe(
                        matchText(
                                "Low values are recommended if background artifacts are present in"
                                        + " generated speech."));
        companySettingsCompanyPage().getClarityTooltipText().shouldBe(matchText("High"));
        companySettingsCompanyPage()
                .getClarityTooltipText()
                .shouldBe(
                        matchText(
                                "It boosts overall voice clarity and target speaker similarity."
                                        + " Very high values can cause artifacts, so adjusting this"
                                        + " setting to find the optimal value is encouraged."));
    }

    /** Assert Listening Mode. */
    public static void assertListeningMode() {
        companySettingsCompanyPage().getSampleTextField().shouldBe(disabled);
        companySettingsCompanyPage().getListenButton().shouldBe(hidden);
        companySettingsCompanyPage().getStopButton().shouldBe(visible);
        companySettingsCompanyPage().getStopButton().shouldBe(exactText("Stop"));
    }

    /** Assert Generating mode */
    public static void assertGeneratingMode() {
        companySettingsCompanyPage().getSampleTextField().shouldBe(disabled);
        companySettingsCompanyPage().getStopButton().shouldBe(hidden);
        companySettingsCompanyPage().getListenButton().shouldBe(hidden);
        companySettingsCompanyPage().getGeneratingButton().shouldBe(visible);
        companySettingsCompanyPage().getGeneratingButton().shouldBe(exactText("Generating..."));
    }

    /** Assert entered Sample text. */
    public static void assertChangedSampleText() {
        companySettingsCompanyPage()
                .getSampleTextField()
                .shouldBe(
                        exactText(
                                "Hello can you help me with something? I bought these wireless"
                                        + " headphones online last week. Sound is horrible. Total"
                                        + " disappointment. Just want someone to set me up with a"
                                        + " quality pair of headphones.test"));
    }
}
