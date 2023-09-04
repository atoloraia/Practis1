package com.practis.web.selenide.validator.admin;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.companyConfigurationPopUp;
import static com.practis.web.selenide.configuration.PageObjectFactory.companySettingsPage;

public class CompanyConfigurationValidator {

    /** Assert data on Logo tab. */
    public static void assertLogoTabDefault() {
        companyConfigurationPopUp().getCompanyLogo().shouldBe(visible);
        companyConfigurationPopUp().getCompanyLogo().shouldBe(enabled);
        companyConfigurationPopUp().getCompanyLogoCamera().shouldBe(visible);
        companyConfigurationPopUp().getCompanyLogoCamera().shouldBe(enabled);
        companyConfigurationPopUp().getLogoDescription().shouldBe(visible);
        companyConfigurationPopUp()
                .getLogoDescription()
                .shouldBe(matchText("PNG, JPG, JPEG • Less than 2 MB"));
        companyConfigurationPopUp().getGuidelinesLink().shouldBe(enabled);
        companyConfigurationPopUp().getGuidelinesLink().shouldBe(matchText("Guidelines"));
        companyConfigurationPopUp().getNextButton().shouldBe(enabled);
    }

    /** Assert data with uploaded logo on Logo tab . */
    public static void assertLogoTabWithLogo() {
        companyConfigurationPopUp().getCompanyLogo().shouldBe(visible);
        companyConfigurationPopUp().getCompanyLogo().shouldBe(enabled);
        companyConfigurationPopUp().getDeleteCompanyLogo().shouldBe(visible);
        companyConfigurationPopUp().getCompanyLogoCamera().shouldBe(hidden);
        companyConfigurationPopUp().getLogoDescription().shouldBe(visible);
        companyConfigurationPopUp()
                .getLogoDescription()
                .shouldBe(matchText("PNG, JPG, JPEG • Less than 2 MB"));
        companyConfigurationPopUp().getGuidelinesLink().shouldBe(enabled);
        companyConfigurationPopUp().getGuidelinesLink().shouldBe(matchText("Guidelines"));
        companyConfigurationPopUp().getNextButton().shouldBe(enabled);
    }

    /** Assert data without uploaded logo on Logo tab . */
    public static void assertLogoTabWithoutLogo() {
        companyConfigurationPopUp().getCompanyLogo().shouldBe(visible);
        companyConfigurationPopUp().getCompanyLogo().shouldBe(enabled);
        companyConfigurationPopUp().getCompanyLogoCamera().shouldBe(visible);
        companyConfigurationPopUp().getCompanyLogoCamera().shouldBe(enabled);
        companyConfigurationPopUp().getLogoDescription().shouldBe(visible);
        companyConfigurationPopUp()
                .getLogoDescription()
                .shouldBe(matchText("PNG, JPG, JPEG • Less than 2 MB"));
        companyConfigurationPopUp().getGuidelinesLink().shouldBe(enabled);
        companyConfigurationPopUp().getGuidelinesLink().shouldBe(matchText("Guidelines"));
        companyConfigurationPopUp().getNextButton().shouldBe(enabled);
    }

    /** Assert data without uploaded logo on Logo tab . */
    public static void assertGuidelines() {
        $(".docs-title-input-label-inner").shouldBe(visible);
    }

    /** Assert data on Licensed Seats tab. */
    public static void assertLicensedSeatsTabDefault() {
        companyConfigurationPopUp().getUnlimitedText().shouldBe(visible);
        companyConfigurationPopUp()
                .getUnlimitedText()
                .shouldBe(matchText("Unlimited licensed seats"));
        companySettingsPage().getRegisteredCounter().shouldBe(visible);
        companySettingsPage().getRegisteredCounter().shouldBe(matchText("0 Registered"));
        companySettingsPage().getPendingCounter().shouldBe(visible);
        companySettingsPage().getPendingCounter().shouldBe(matchText("0 Pending Registration"));
        companySettingsPage().getDeactivatedCounter().shouldBe(visible);
        companySettingsPage().getDeactivatedCounter().shouldBe(matchText("0 Deactivated"));
        companyConfigurationPopUp().getUnlimitedRadiobutton().shouldBe(visible);
        companyConfigurationPopUp().getUnlimitedRadiobutton().shouldBe(enabled);
        companyConfigurationPopUp().getUnlimitedDescription().shouldBe(visible);
        companyConfigurationPopUp()
                .getUnlimitedDescription()
                .shouldBe(
                        matchText(
                                "Company can have as many users in different roles and statuses as"
                                        + " they wish."));

        companyConfigurationPopUp().getLimitText().shouldBe(visible);
        companyConfigurationPopUp().getLimitText().shouldBe(matchText("Limit licensed seats to"));
        companyConfigurationPopUp().getLimitRadiobutton().shouldBe(visible);
        companyConfigurationPopUp().getLimitRadiobutton().shouldBe(enabled);
        companyConfigurationPopUp().getLimitDescription().shouldBe(visible);
        companySettingsPage().getLimitedUsersField().shouldBe(empty);
        companySettingsPage().getLimitedUsersField().shouldBe(disabled);
        companyConfigurationPopUp()
                .getLimitDescription()
                .shouldBe(
                        matchText(
                                "Company won't be able to have more than this number of seats."
                                        + " Deactivated user seats still count towards the licensed"
                                        + " seat count."));
    }

    /** Assert data on Logo tab. */
    public static void assertEditLogoTabDefault() {
        companyConfigurationPopUp().getCompanyLogo().shouldBe(visible);
        companyConfigurationPopUp().getCompanyLogo().shouldBe(enabled);
        companyConfigurationPopUp().getCompanyLogoCamera().shouldBe(visible);
        companyConfigurationPopUp().getCompanyLogoCamera().shouldBe(enabled);
        companyConfigurationPopUp().getLogoDescription().shouldBe(visible);
        companyConfigurationPopUp()
                .getLogoDescription()
                .shouldBe(matchText("PNG, JPG, JPEG • Less than 2 MB"));
        companyConfigurationPopUp().getGuidelinesLink().shouldBe(enabled);
        companyConfigurationPopUp().getGuidelinesLink().shouldBe(matchText("Guidelines"));
        companyConfigurationPopUp().getNextButton().shouldBe(hidden);
    }

    /** Assert data without uploaded logo on Logo tab . */
    public static void assertEditLogoTabWithoutLogo() {
        companyConfigurationPopUp().getCompanyLogo().shouldBe(visible);
        companyConfigurationPopUp().getCompanyLogo().shouldBe(enabled);
        companyConfigurationPopUp().getCompanyLogoCamera().shouldBe(visible);
        companyConfigurationPopUp().getCompanyLogoCamera().shouldBe(enabled);
        companyConfigurationPopUp().getLogoDescription().shouldBe(visible);
        companyConfigurationPopUp()
                .getLogoDescription()
                .shouldBe(matchText("PNG, JPG, JPEG • Less than 2 MB"));
        companyConfigurationPopUp().getGuidelinesLink().shouldBe(enabled);
        companyConfigurationPopUp().getGuidelinesLink().shouldBe(matchText("Guidelines"));
        companyConfigurationPopUp().getNextButton().shouldBe(hidden);
    }

    /** Assert data with uploaded logo on Logo tab . */
    public static void assertEditLogoTabWithLogo() {
        companyConfigurationPopUp().getCompanyLogo().shouldBe(visible);
        companyConfigurationPopUp().getCompanyLogo().shouldBe(enabled);
        companyConfigurationPopUp().getDeleteCompanyLogo().shouldBe(visible);
        companyConfigurationPopUp().getCompanyLogoCamera().shouldBe(hidden);
        companyConfigurationPopUp().getLogoDescription().shouldBe(visible);
        companyConfigurationPopUp()
                .getLogoDescription()
                .shouldBe(matchText("PNG, JPG, JPEG • Less than 2 MB"));
        companyConfigurationPopUp().getGuidelinesLink().shouldBe(enabled);
        companyConfigurationPopUp().getGuidelinesLink().shouldBe(matchText("Guidelines"));
        companyConfigurationPopUp().getNextButton().shouldBe(hidden);
    }
}
