package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.PageObjectFactory.companySettingsCompanyPage;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import org.openqa.selenium.Keys;

public class CompanySettingsCompanyService {

    /** Change Company Name */
    public void updateCompanyName(String text) {
        companySettingsCompanyPage().getCompanyNameInput().append(text);
        companySettingsCompanyPage().getApplyButton().click();
    }

    /** Change Company Name */
    public void existingCompanyName() {
        companySettingsCompanyPage().getCompanyNameInput().setValue("Tulaco");
        companySettingsCompanyPage().getWorkspaceUrlInput().click();
        companySettingsCompanyPage().getApplyButton().click();
    }

    /** Revert Company Name */
    public void revertCompanyName() {
        companySettingsCompanyPage().getCompanyNameInput().append(String.valueOf(Keys.BACK_SPACE));
        companySettingsCompanyPage().getApplyButton().click();
        await().pollDelay(TWO_SECONDS).until(() -> true);
    }

    /** Open Account Owner */
    public void openAccountOwner() {
        companySettingsCompanyPage().getAccountOwnerClick().click();
    }

    /** Update Account Owner */
    public void updateAccountOwner() {
        companySettingsCompanyPage().getAccountOwnerField().click();
        companySettingsCompanyPage().getAccountOwnerValue().get(0).click();
        companySettingsCompanyPage().getApplyButton().click();
    }

    /** Open Licensed Seats tab */
    public void openLicensedSeatsTab() {
        companySettingsCompanyPage().getSections().get(2).click();
    }

    /** Click on Request Limit Change */
    public void clickOnRequestLimitChange() {
        companySettingsCompanyPage().getRequestLimitChangeButton().click();
    }

    /** Click on Manage Invitations */
    public void clickOnManageInvitations() {
        companySettingsCompanyPage().getManageInvitationsButton().click();
    }
}
