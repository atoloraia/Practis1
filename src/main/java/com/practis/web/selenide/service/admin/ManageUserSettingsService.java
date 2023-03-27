package com.practis.web.selenide.service.admin;

import static com.practis.web.selenide.configuration.PageObjectFactory.manageUserSettingsPage;

public class ManageUserSettingsService {

    /** Click on Deactivate button. */
    public static void clickOnDeactivateButton() {
        manageUserSettingsPage().getDeactivateButton().click();
    }

    /** Click on Activate button. */
    public static void clickOnActivateButton() {
        manageUserSettingsPage().getActivateButton().click();
    }

    /** Click on Revoke button. */
    public static void clickOnRevokeButton() {
        manageUserSettingsPage().getRevokeButton().click();
    }
}
