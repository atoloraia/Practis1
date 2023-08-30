package com.practis.web.selenide.validator.company;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
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
}
