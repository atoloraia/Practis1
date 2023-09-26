package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.PageObjectFactory.companySettingsCompanyPage;

import org.openqa.selenium.Keys;

public class CompanySettingsCompanyService {

    /** Change Company Name */
    public void updateCompanyName(String text) {
        companySettingsCompanyPage().getCompanyNameField().append(text);
        companySettingsCompanyPage().getApplyButton().click();
    }

    /** Change Company Name */
    public void existingCompanyName() {
        companySettingsCompanyPage().getCompanyNameField().setValue("Tulaco");
        companySettingsCompanyPage().getWorkspaceUrlInput().click();
        companySettingsCompanyPage().getApplyButton().click();
    }

    /** Revert Company Name */
    public void revertCompanyName() {
        companySettingsCompanyPage().getCompanyNameField().sendKeys(Keys.BACK_SPACE);
        companySettingsCompanyPage().getApplyButton().click();
    }

    /** Open Account Owner */
    public void openAccountOwner() {
        companySettingsCompanyPage().getSelectAccountOwnerValue().click();
    }

    /** Update Account Owner */
    public void updateAccountOwner() {
        companySettingsCompanyPage().getAccountOwnerValue().get(1).click();
        companySettingsCompanyPage().getApplyButton().click();
    }

    /** Open Licensed Seats tab */
    public void openLicensedSeatsTab() {
        companySettingsCompanyPage().getLicensedSeatsTab().click();
    }

    /** Click on Request Limit Change */
    public void clickOnRequestLimitChange() {
        companySettingsCompanyPage().getRequestLimitChangeButton().click();
    }

    /** Click on Manage Invitations */
    public void clickOnManageInvitations() {
        companySettingsCompanyPage().getManageInvitationsButton().click();
    }

    /** Click on Logo */
    public void clickOnLogo() {
        companySettingsCompanyPage().getLogoTab().click();
    }

    /** Click on Voice */
    public void clickOnVoice() {
        companySettingsCompanyPage().getVoiceTab().click();
    }

    /** Click on Voice Setting */
    public void changeVoiceSetting() {
        companySettingsCompanyPage().getValuesLine().get(0).click();
        companySettingsCompanyPage().getValuesLine().get(1).click();
    }

    /** Click on Save */
    public void clickOnSave() {
        companySettingsCompanyPage().getApplyButton().click();
    }

    /** Close the modal */
    public void closeModal() {
        companySettingsCompanyPage().getCrossButton().click();
    }

    /** Change Voice from Customer to Rep */
    public void setRepresentativeVoice() {
        companySettingsCompanyPage().getRoleField().click();
        companySettingsCompanyPage().getRoleDropdownValue().get(1).click();
    }

    /** Click on Listen button */
    public void clickOnListenButton() {
        companySettingsCompanyPage().getListenButton().click();
    }

    /** Click on Listen button */
    public void clickOnStopButton() {
        companySettingsCompanyPage().getStopButton().click();
    }

    /** Click on To Default button */
    public void clickOnDefaultButton() {
        companySettingsCompanyPage().getToDefaultClick().click();
    }

    /** Enter Sample Text */
    public void enterSampleText() {
        companySettingsCompanyPage().getSampleTextField().append("test");
    }

    /** Click on Stability Tooltip */
    public void clickOnStabilityTooltip() {
        companySettingsCompanyPage().getStabilityTooltipIcon().click();
    }

    /** Click on Clarity Tooltip */
    public void clickOnClarityTooltip() {
        companySettingsCompanyPage().getClarityTooltipIcon().click();
    }
}
