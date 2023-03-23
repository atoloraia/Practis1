package com.practis.web.selenide.service.admin;

import static com.practis.web.selenide.configuration.PageObjectFactory.companySettingsPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.activateCompanyPopUpService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.deactivateCompanyPopUpService;

public class CompanySettingsService {

    /** Open "Activate Company" pop-up. */
    public void openActivateCompanyPopUp() {
        companySettingsPage().getActivateButton().click();
    }

    /** Open "Deactivate Company" pop-up. */
    public void openDeactivateCompanyPopUp() {
        companySettingsPage().getDeactivateButton().click();
    }

    /** Activate Company. */
    public void activateCompany(String company) {
        companySettingsPage().getDangerZoneButton().click();
        companySettingsPage().getActivateButton().click();
        activateCompanyPopUpService().activateCompany(company);
    }

    /** Deactivate Company. */
    public void deactivateCompany(String company) {
        companySettingsPage().getDangerZoneButton().click();
        companySettingsPage().getDeactivateButton().click();
        deactivateCompanyPopUpService().deactivateCompany(company);
    }

    /** Activate Company. */
    public void openActivateCompanyPopUp(String companyName) {
        companySettingsPage().getActivateButton().click();
    }
}
