package com.practis.web.selenide.validator.admin;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.manageUserSettingsPage;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ManageUserSettingsValidator {

    /** Assert elements on Manage User Settings page - common. */
    public static void assertElementsOnManageUserSettingsPage() {
        manageUserSettingsPage().getUserSettingsTitle().shouldBe(visible);
        manageUserSettingsPage().getUserSettingsTitle().shouldBe(exactText("User Settings"));
        manageUserSettingsPage().getBackButton().shouldBe(visible);
        manageUserSettingsPage().getUserPic().shouldBe(visible);
        manageUserSettingsPage().getUserRole().shouldBe(visible);
        manageUserSettingsPage().getUserName().shouldBe(visible);
        manageUserSettingsPage().getUserEmail().shouldBe(visible);
        manageUserSettingsPage().getEditUserDetails().shouldBe(visible);
        manageUserSettingsPage().getEditUserDetails().shouldBe(exactText("Edit User Details"));
        manageUserSettingsPage().getFirstNameField().shouldBe(visible);
        manageUserSettingsPage().getFirstNameField().shouldBe(attribute("disabled"));
        manageUserSettingsPage().getFirstNameField().shouldBe(attribute("type", "text"));
        manageUserSettingsPage().getFirstNameField().shouldBe(attribute("maxlength", "100"));
        manageUserSettingsPage().getLastNameField().shouldBe(visible);
        manageUserSettingsPage().getLastNameField().shouldBe(attribute("disabled"));
        manageUserSettingsPage().getLastNameField().shouldBe(attribute("type", "text"));
        manageUserSettingsPage().getLastNameField().shouldBe(attribute("maxlength", "100"));
        manageUserSettingsPage().getEmailField().shouldBe(visible);
        manageUserSettingsPage().getEmailField().shouldBe(attribute("disabled"));
        manageUserSettingsPage().getEmailField().shouldBe(attribute("type", "text"));
        manageUserSettingsPage().getEmailField().shouldBe(attribute("maxlength", "100"));
        manageUserSettingsPage().getRoleField().shouldBe(visible);
    }

    /** Assert roles dropdown */
    public static void assertRolesDropdown() {
        manageUserSettingsPage().getRoleValues().get(0).shouldBe(visible);
        manageUserSettingsPage().getRoleValues().get(0).shouldBe(exactText("Admin"));
        manageUserSettingsPage().getRoleValues().get(1).shouldBe(visible);
        manageUserSettingsPage().getRoleValues().get(1).shouldBe(exactText("User"));
        manageUserSettingsPage().getRoleField().click();
    }

    /** Assert elements on Registered User Settings page. */
    public static void assertElementsOnRegisteredUserSettingsPage() {
        assertElementsOnManageUserSettingsPage();
        manageUserSettingsPage().getMobileNumberField().shouldBe(visible);
        manageUserSettingsPage().getMobileNumberField().shouldBe(attribute("width", "100%"));
        manageUserSettingsPage().getMobileNumberField().shouldBe(attribute("font-size", "13px"));
        manageUserSettingsPage().getMobileNumberField().shouldBe(attribute("height", "56px"));
        manageUserSettingsPage().getMobileNumberDeleteButton().shouldBe(visible);
        manageUserSettingsPage().getActiveUserLabel().shouldBe(visible);
        manageUserSettingsPage().getActiveUserLabel().shouldBe(exactText("Registered"));
        manageUserSettingsPage().getDeactivateButton().shouldBe(visible);
        manageUserSettingsPage().getDeactivateButton().shouldBe(enabled);
        manageUserSettingsPage().getDeactivateButton().shouldBe(exactText("Deactivate"));
        manageUserSettingsPage().getDeactivateButton().shouldBe(attribute("type", "submit"));
        manageUserSettingsPage().getDeactivateButton().shouldBe(attribute("color", "warning"));
        manageUserSettingsPage().getDeactivateButton().shouldBe(attribute("width", "120px"));
        manageUserSettingsPage().getRoleField().shouldBe(enabled);
    }

    /** Assert elements on Pending User Settings page. */
    public static void assertElementsOnPendingUserSettingsPage() {
        assertElementsOnManageUserSettingsPage();
        manageUserSettingsPage().getPendingMobileNumberField().shouldBe(visible);
        manageUserSettingsPage()
                .getPendingMobileNumberField()
                .shouldBe(attribute("value", "Pending Mobile Number"));
        manageUserSettingsPage().getPendingMobileNumberField().shouldBe(attribute("disabled"));
        manageUserSettingsPage().getPendingMobileNumberField().shouldBe(attribute("type", "text"));
        manageUserSettingsPage()
                .getPendingMobileNumberField()
                .shouldBe(attribute("maxlength", "100"));
        manageUserSettingsPage().getMobileNumberDeleteButton().shouldBe(hidden);
        manageUserSettingsPage().getPendingUserLabel().shouldBe(visible);
        manageUserSettingsPage().getPendingUserLabel().shouldBe(exactText("Pending Registration"));
        manageUserSettingsPage().getPendingUserIcon().shouldBe(visible);
        manageUserSettingsPage().getRevokeButton().shouldBe(visible);
        manageUserSettingsPage().getRevokeButton().shouldBe(enabled);
        manageUserSettingsPage().getRevokeButton().shouldBe(exactText("Revoke"));
        manageUserSettingsPage().getRevokeButton().shouldBe(attribute("type", "submit"));
        manageUserSettingsPage().getRevokeButton().shouldBe(attribute("color", "warning"));
        manageUserSettingsPage().getRevokeButton().shouldBe(attribute("width", "120px"));
    }

    /** Assert elements on Inactive User Settings page. */
    public static void assertElementsOnInactiveUserSettingsPage() {
        assertElementsOnManageUserSettingsPage();
        manageUserSettingsPage().getMobileNumberField().shouldBe(visible);
        manageUserSettingsPage().getMobileNumberField().shouldBe(attribute("width", "100%"));
        manageUserSettingsPage().getMobileNumberField().shouldBe(attribute("font-size", "13px"));
        manageUserSettingsPage().getMobileNumberField().shouldBe(attribute("height", "56px"));
        manageUserSettingsPage().getMobileNumberDeleteButton().shouldBe(visible);
        manageUserSettingsPage().getInactiveUserLabel().shouldBe(visible);
        manageUserSettingsPage().getInactiveUserLabel().shouldBe(exactText("Inactive"));
        manageUserSettingsPage().getActivateButton().shouldBe(visible);
        manageUserSettingsPage().getActivateButton().shouldBe(enabled);
        manageUserSettingsPage().getActivateButton().shouldBe(exactText("Activate"));
        manageUserSettingsPage().getActivateButton().shouldBe(attribute("type", "submit"));
        manageUserSettingsPage().getActivateButton().shouldBe(attribute("color", "default"));
        manageUserSettingsPage().getActivateButton().shouldBe(attribute("width", "120px"));
        manageUserSettingsPage().getRoleField().shouldBe(enabled);
    }

    public static void assertMobileNumberField() {
        manageUserSettingsPage().getMobileNumberField().shouldBe(visible);
        manageUserSettingsPage().getMobileNumberDeleteButton().shouldBe(visible);
        manageUserSettingsPage().getPendingMobileNumberField().shouldBe(hidden);
    }

    public static void assertEmptyMobileNumberField() {
        manageUserSettingsPage().getPendingMobileNumberField().shouldBe(visible);
        manageUserSettingsPage()
                .getPendingMobileNumberField()
                .shouldBe(attribute("value", "Pending Mobile Number"));
        manageUserSettingsPage().getMobileNumberDeleteButton().shouldBe(hidden);
    }

    /** Assert User Role on Manage User Setting page */
    public static void assertManageUserSettingsRoleValue(String text) {
        manageUserSettingsPage().getRoleField().shouldBe(exactText(text));
        manageUserSettingsPage().getUserRole().shouldBe(exactText(text));
    }
}
