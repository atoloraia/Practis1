package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.PageObjectFactory.companySettingsPage;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import org.openqa.selenium.Keys;

public class CompanySettingsAdminService {

    /** Change Company Name */
    public void updateCompanyName(String text) {
        companySettingsPage().getCompanyNameInput().append(text);
        companySettingsPage().getApplyButton().click();
    }

    /** Revert Company Name */
    public void revertCompanyName() {
        companySettingsPage().getCompanyNameInput().append(String.valueOf(Keys.BACK_SPACE));
        companySettingsPage().getApplyButton().click();
        await().pollDelay(TWO_SECONDS).until(() -> true);
    }

    /** Open Account Owner */
    public void openAccountOwner() {
        companySettingsPage().getAccountOwnerClick().click();
    }

    /** Update Account Owner */
    public void updateAccountOwner() {
        companySettingsPage().getAccountOwnerField().click();
        companySettingsPage().getAccountOwnerValue().get(0).click();
        companySettingsPage().getApplyButton().click();
    }
}
