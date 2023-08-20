package com.practis.web.selenide.validator.admin;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.companyConfigurationPopUp;

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
        companyConfigurationPopUp().getCompanyLogoCamera().shouldBe(enabled);
        companyConfigurationPopUp().getLogoDescription().shouldBe(visible);
        companyConfigurationPopUp()
                .getLogoDescription()
                .shouldBe(matchText("PNG, JPG, JPEG • Less than 2 MB"));
        companyConfigurationPopUp().getGuidelinesLink().shouldBe(enabled);
        companyConfigurationPopUp().getGuidelinesLink().shouldBe(matchText("Guidelines"));
        companyConfigurationPopUp().getNextButton().shouldBe(enabled);
    }

    /** Assert data on Administrators tab. */
    public static void assertAdministratorsTab() {
        // here Administrators tab validation
    }
}
