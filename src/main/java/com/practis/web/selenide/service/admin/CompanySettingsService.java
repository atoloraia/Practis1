package com.practis.web.selenide.service.admin;

import static com.practis.web.selenide.configuration.PageObjectFactory.companySettingsPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.activateCompanyPopUpService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.deactivateCompanyPopUpService;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;

public class CompanySettingsService {

    /** Open "Activate Company" pop-up. */
    public void openActivateCompanyPopUp() {
        companySettingsPage().getCompanyActionsButton().click();
        companySettingsPage().getActivateButton().click();
    }

    /** Open "Deactivate Company" pop-up. */
    public void openDeactivateCompanyPopUp() {
        companySettingsPage().getCompanyActionsButton().click();
        companySettingsPage().getDeactivateButton().click();
    }

    /** Deactivate Company. */
    public void deactivateCompany(String company) {
        companySettingsPage().getCompanyActionsButton().click();
        companySettingsPage().getDeactivateButton().click();
        deactivateCompanyPopUpService().deactivateCompany(company);
        await().pollDelay(FIVE_SECONDS).until(() -> true);
    }

    /** Activate Company. */
    public void activateCompany(String company) {
        companySettingsPage().getCompanyActionsButton().click();
        companySettingsPage().getActivateButton().click();
        activateCompanyPopUpService().activateCompany(company);
        await().pollDelay(FIVE_SECONDS).until(() -> true);
    }
}
