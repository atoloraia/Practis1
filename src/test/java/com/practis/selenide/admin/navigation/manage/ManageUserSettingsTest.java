package com.practis.selenide.admin.navigation.manage;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.confirmationAndWarningPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationAdminSideBar;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.manageUserSettingsService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.manageUsersService;
import static com.practis.web.selenide.service.admin.ManageUserSettingsService.clickOnBackButton;
import static com.practis.web.selenide.service.admin.ManageUserSettingsService.clickOnDeleteMobileButton;
import static com.practis.web.selenide.service.admin.ManageUserSettingsService.clickOnRoleField;
import static com.practis.web.selenide.service.admin.ManageUserSettingsService.selectAdminRole;
import static com.practis.web.selenide.service.admin.ManageUserSettingsService.selectUserRole;
import static com.practis.web.selenide.validator.admin.ManageUserSettingsValidator.assertElementsOnInactiveUserSettingsPage;
import static com.practis.web.selenide.validator.admin.ManageUserSettingsValidator.assertElementsOnPendingUserSettingsPage;
import static com.practis.web.selenide.validator.admin.ManageUserSettingsValidator.assertElementsOnRegisteredUserSettingsPage;
import static com.practis.web.selenide.validator.admin.ManageUserSettingsValidator.assertEmptyMobileNumberField;
import static com.practis.web.selenide.validator.admin.ManageUserSettingsValidator.assertManageUserSettingsRoleValue;
import static com.practis.web.selenide.validator.admin.ManageUserSettingsValidator.assertMobileNumberField;
import static com.practis.web.selenide.validator.admin.ManageUserSettingsValidator.assertRolesDropdown;
import static com.practis.web.selenide.validator.admin.ManageUsersValidator.assertManageUserRow;
import static com.practis.web.selenide.validator.admin.ManageUsersValidator.assertManageUsersRoleValue;
import static com.practis.web.selenide.validator.admin.ManageUsersValidator.assertNoResultManageUsers;
import static com.practis.web.selenide.validator.popup.ConfirmAndWarningPopUpsValidator.assertConfirmationModal;

import com.practis.dto.NewUserInput;
import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.PendingUserExtension;
import com.practis.support.extension.practis.RegisteredUserExtension;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisAdminTestClass
@SelenideTestClass
@TestRailTestClass
class ManageUserSettingsTest {

    @BeforeEach
    void beforeEach() {
        navigationAdminSideBar().getManageUsersNavigationItem().click();
    }

    @TestRailTest(caseId = 21925)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Manage Users: Registered: Check Elements")
    void checkElementsOnRegisteredManageUsersPage(final List<NewUserInput> user) {

        // Open Registered User Settings page
        manageUsersService().searchUser(user.get(0).getEmail());
        assertManageUserRow("Registered");
        manageUsersService().clickOnUserRow(user.get(0).getEmail());

        // Assert Registered User Settings page
        assertElementsOnRegisteredUserSettingsPage();
        assertManageUserSettingsRoleValue("User");
    }

    @TestRailTest(caseId = 30103)
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 4)
    @DisplayName("Manage Users: Pending Registration: Check Elements")
    void checkElementsOnPendingManageUsersPage(final List<NewUserInput> user) {

        // Open Pending User Settings page
        manageUsersService().searchUser(user.get(0).getEmail());
        assertManageUserRow("Pending Registration");
        manageUsersService().clickOnUserRow(user.get(0).getEmail());

        // Assert Pending User Settings page
        assertElementsOnPendingUserSettingsPage();
        assertManageUserSettingsRoleValue("Admin");
    }

    @TestRailTest(caseId = 30104)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Manage Users: Deactivated: Check Elements")
    void checkElementsOnInactiveManageUsersPage(final List<NewUserInput> user) {

        // Delete User
        practisApi().deleteUser(user.get(0).getEmail());

        // Open Inactive User Settings page
        manageUsersService().searchUser(user.get(0).getEmail());
        assertManageUserRow("Inactive");
        manageUsersService().clickOnUserRow(user.get(0).getEmail());

        // Assert Inactive User Settings page
        assertElementsOnInactiveUserSettingsPage();
        assertManageUserSettingsRoleValue("User");
    }

    @TestRailTest(caseId = 21927)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Manage Users: Deactivate")
    void checkDeactivateOnManageUsersPage(final List<NewUserInput> user) {

        // Open Registered User Settings page
        manageUsersService().searchUser(user.get(0).getEmail());
        manageUsersService().clickOnUserRow(user.get(0).getEmail());

        // Click on Deactivate
        manageUserSettingsService().clickOnDeactivateButton();

        // Assert confirmation pop-up and close it without changes
        assertConfirmationModal(
                "Deactivate User?",
                "Deactivating a user simply blocks their access to Practis. Their information and"
                        + " their progress will not be lost. You can re-activate them at any time.",
                "Deactivate",
                "Cancel");
        confirmationAndWarningPopUp().discardChanges();
        assertElementsOnRegisteredUserSettingsPage();

        // Click on Deactivate and confirm the action
        manageUserSettingsService().clickOnDeactivateButton();
        confirmationAndWarningPopUp().saveChanges();

        // Assert Snackbar and updated user status on User Settings and Manage Users pages
        snackbar().getMessage().shouldBe(exactText("User has been deactivated"));
        assertElementsOnInactiveUserSettingsPage();
        clickOnBackButton();
        assertManageUserRow("Inactive");
    }

    @TestRailTest(caseId = 21928)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Manage Users: Activate")
    void checkActivateOnManageUsersPage(final List<NewUserInput> user) {

        // Delete User
        practisApi().deleteUser(user.get(0).getEmail());

        // Open Inactive User Settings page
        manageUsersService().searchUser(user.get(0).getEmail());
        manageUsersService().clickOnUserRow(user.get(0).getEmail());

        // Click on Activate
        manageUserSettingsService().clickOnActivateButton();

        // Assert confirmation pop-up and close it without changes
        assertConfirmationModal(
                "Activate User?",
                "This will restore this user's access to their account so that they can continue"
                        + " using Practis as usual.",
                "Activate",
                "Cancel");
        confirmationAndWarningPopUp().discardChanges();
        assertElementsOnInactiveUserSettingsPage();

        // Click on Activate and confirm the action
        manageUserSettingsService().clickOnActivateButton();
        confirmationAndWarningPopUp().saveChanges();

        // Assert Snackbar and updated user status on User Settings and Manage Users pages
        snackbar().getMessage().shouldBe(exactText("User has been activated"));
        assertElementsOnRegisteredUserSettingsPage();
        clickOnBackButton();
        assertManageUserRow("Registered");
    }

    @TestRailTest(caseId = 21929)
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Manage Users: Revoke")
    void checkRevokeOnManageUsersPage(final List<NewUserInput> user) {

        // Open Pending User Settings page
        manageUsersService().searchUser(user.get(0).getEmail());
        manageUsersService().clickOnUserRow(user.get(0).getEmail());

        // Click on Revoke
        manageUserSettingsService().clickOnRevokeButton();

        // Assert confirmation pop-up and close it without changes
        assertConfirmationModal(
                "Revoke Invitation?",
                "Are you sure you want to revoke the invitation to this user?",
                "Revoke",
                "Cancel");
        confirmationAndWarningPopUp().discardChanges();
        assertElementsOnPendingUserSettingsPage();

        // Click on Revoke and confirm the action
        manageUserSettingsService().clickOnRevokeButton();
        confirmationAndWarningPopUp().saveChanges();

        // Assert Snackbar and no search results
        snackbar().getMessage().shouldBe(exactText("User has been revoked"));
        assertNoResultManageUsers();
    }

    @TestRailTest(caseId = 21930)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Manage Users: Registered: Delete Mobile Number")
    void deleteMobileOnRegisteredManageUsersPage(final List<NewUserInput> user) {

        // Open Registered User Settings page
        manageUsersService().searchUser(user.get(0).getEmail());
        manageUsersService().clickOnUserRow(user.get(0).getEmail());

        // Click on Delete Mobile Number button
        clickOnDeleteMobileButton();

        // Assert confirmation pop-up and close it without changes
        assertConfirmationModal(
                "Delete mobile number?",
                "This action will force this user to enter a new mobile number the next time they"
                        + " log into the app.",
                "Delete",
                "Cancel");
        confirmationAndWarningPopUp().discardChanges();
        assertMobileNumberField();

        // Click on Delete Mobile Number button and confirm the action
        clickOnDeleteMobileButton();
        confirmationAndWarningPopUp().saveChanges();

        // Assert Snackbar and empty mobile number field
        snackbar().getMessage().shouldBe(exactText("Mobile number has been deleted"));
        assertEmptyMobileNumberField();
    }

    @TestRailTest(caseId = 21938)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Manage Users: Inactive: Delete Mobile Number")
    void deleteMobileOnInactiveManageUsersPage(final List<NewUserInput> user) {

        // Delete User
        practisApi().deleteUser(user.get(0).getEmail());

        // Open Inactive User Settings page
        manageUsersService().searchUser(user.get(0).getEmail());
        manageUsersService().clickOnUserRow(user.get(0).getEmail());

        // Click on Delete Mobile Number button
        clickOnDeleteMobileButton();

        // Assert confirmation pop-up and close it without changes
        assertConfirmationModal(
                "Delete mobile number?",
                "This action will force this user to enter a new mobile number the next time they"
                        + " log into the app.",
                "Delete",
                "Cancel");
        confirmationAndWarningPopUp().discardChanges();
        assertMobileNumberField();

        // Click on Delete Mobile Number button and confirm the action
        clickOnDeleteMobileButton();
        confirmationAndWarningPopUp().saveChanges();

        // Assert Snackbar and empty mobile number field
        snackbar().getMessage().shouldBe(exactText("Mobile number has been deleted"));
        assertEmptyMobileNumberField();
    }

    @TestRailTest(caseId = 21934)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Manage Users: Registered: Change Role")
    void changeRoleOnRegisteredManageUsersPage(final List<NewUserInput> user) {

        // Open Registered User Settings page
        manageUsersService().searchUser(user.get(0).getEmail());
        manageUsersService().clickOnUserRow(user.get(0).getEmail());

        // Click on Role field and assert dropdown
        clickOnRoleField();
        assertRolesDropdown();

        // Select Admin role
        selectAdminRole();

        // Assert confirmation pop-up and close it without changes
        assertConfirmationModal(
                "Change user role?",
                "This will impact this user’s permissions immediately. They will not lose their"
                        + " Team Leader rights, assignments, or progress.",
                "Change",
                "Cancel");
        confirmationAndWarningPopUp().discardChanges();
        assertManageUserSettingsRoleValue("User");

        // Select Admin role and confirm changes
        selectAdminRole();
        confirmationAndWarningPopUp().saveChanges();

        // Assert Snackbar and changed role
        snackbar().getMessage().shouldBe(exactText("User role has been changed"));
        assertManageUserSettingsRoleValue("Admin");

        // Verify changed role on Manage Users list
        clickOnBackButton();
        assertManageUsersRoleValue("Admin");
    }

    @TestRailTest(caseId = 21937)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 4)
    @DisplayName("Manage Users: Deactivated: Change Role")
    void changeRoleOnInactiveManageUsersPage(final List<NewUserInput> user) {

        // Open Inactive User Settings page
        manageUsersService().searchUser(user.get(0).getEmail());
        manageUsersService().clickOnUserRow(user.get(0).getEmail());

        // Click on Role field and assert dropdown
        clickOnRoleField();
        assertRolesDropdown();

        // Select User role
        selectUserRole();

        // Assert confirmation pop-up and close it without changes
        assertConfirmationModal(
                "Change user role?",
                "This will impact this user’s permissions immediately. They will not lose their"
                        + " Team Leader rights, assignments, or progress.",
                "Change",
                "Cancel");
        confirmationAndWarningPopUp().discardChanges();
        assertManageUserSettingsRoleValue("Admin");

        // Select User role and confirm changes
        selectUserRole();
        confirmationAndWarningPopUp().saveChanges();

        // Assert Snackbar and changed role
        snackbar().getMessage().shouldBe(exactText("User role has been changed"));
        assertManageUserSettingsRoleValue("User");

        // Verify changed role on Manage Users list
        clickOnBackButton();
        assertManageUsersRoleValue("User");
    }
}
