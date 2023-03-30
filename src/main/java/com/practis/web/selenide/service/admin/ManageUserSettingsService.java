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

    /** Click on Delete Mobile Number button. */
    public static void clickOnDeleteMobileButton() {
        manageUserSettingsPage().getMobileNumberDeleteButton().click();
    }

    /** Click on Role Field. */
    public static void clickOnRoleField() {
        manageUserSettingsPage().getRoleField().click();
    }

    /** Select User role */
    public static void selectUserRole() {
        manageUserSettingsPage().getRoleField().click();
        manageUserSettingsPage().getRoleValues().get(1).click();
    }

    /** Select Admin role */
    public static void selectAdminRole() {
        manageUserSettingsPage().getRoleField().click();
        manageUserSettingsPage().getRoleValues().get(0).click();
    }

    /** Select Admin role */
    public static void clickOnBackButton() {
        manageUserSettingsPage().getBackButton().click();
    }
}
