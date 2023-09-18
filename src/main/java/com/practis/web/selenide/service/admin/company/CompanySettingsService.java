package com.practis.web.selenide.service.admin.company;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.companyConfigurationPopUp;
import static com.practis.web.selenide.configuration.PageObjectFactory.companySettingsPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.activateCompanyPopUpService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.deactivateCompanyPopUpService;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;

public class CompanySettingsService {

    /** Open "Activate Company" pop-up. */
    public void openActivateCompanyPopUp() {
        companySettingsPage().getActionsSection().click();
        companySettingsPage().getActivateButton().click();
    }

    /** Open "Deactivate Company" pop-up. */
    public void openDeactivateCompanyPopUp() {
        companySettingsPage().getActionsSection().click();
        ;
        companySettingsPage().getDeactivateButton().click();
    }

    /** Deactivate Company. */
    public void deactivateCompany(String company) {
        companySettingsPage().getActionsSection().click();
        companySettingsPage().getDeactivateButton().click();
        deactivateCompanyPopUpService().deactivateCompany(company);
        await().pollDelay(FIVE_SECONDS).until(() -> true);
    }

    /** Activate Company. */
    public void activateCompany(String company) {
        companySettingsPage().getActionsSection().click();
        companySettingsPage().getActivateButton().click();
        activateCompanyPopUpService().activateCompany(company);
        await().pollDelay(FIVE_SECONDS).until(() -> true);
    }

    /** Change User Limit to Limited. */
    public void changeUserLimitLimited() {
        companySettingsPage().getLimitedRadioButton().click();
    }

    /** Add number to limit field. */
    public void fillLimitNumber(String text) {
        companySettingsPage().getLimitedUsersField().append(text);
    }

    /** Save Limit. */
    public void clickOnApplyButton() {
        companySettingsPage().getApplyButton().click();
    }

    /** Open Actions Tab. */
    public void clickOnActions() {
        companySettingsPage().getActionsSection().click();
    }

    /** Open Actions Tab. */
    public void clickOnAuditLog() {
        companySettingsPage().getAuditLogSection().click();
    }

    /** Change User Limit to Limited. */
    public void updateUserLimitLimited(String text) {
        companyConfigurationPopUp().getLicensedSeatsTab().click();
        companySettingsPage().getLimitedRadioButton().click();
        companySettingsPage().getLimitedUsersField().append(text);
        companySettingsPage().getApplyButton().click();
    }

    /** Change User Limit to unlimited. */
    public void updateUserLimitUnlimited() {
        companyConfigurationPopUp().getLicensedSeatsTab().click();
        companySettingsPage().getUnlimitedRadioButton().click();
        companySettingsPage().getApplyButton().click();
    }
}
