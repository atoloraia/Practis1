package com.practis.selenide.admin.navigation.manage;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.confirmationAndWarningPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationAdminSideBar;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.manageUserSettingsService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.manageUsersService;
import static com.practis.web.selenide.validator.admin.ManageUserSettingsValidator.assertConfirmationModal;
import static com.practis.web.selenide.validator.admin.ManageUserSettingsValidator.assertElementsOnInactiveUserSettingsPage;
import static com.practis.web.selenide.validator.admin.ManageUserSettingsValidator.assertElementsOnPendingUserSettingsPage;
import static com.practis.web.selenide.validator.admin.ManageUserSettingsValidator.assertElementsOnRegisteredUserSettingsPage;
import static com.practis.web.selenide.validator.admin.ManageUsersValidator.assertManageUserRow;
import static com.practis.web.selenide.validator.admin.ManageUsersValidator.assertManageUserRowRole;
import static com.practis.web.selenide.validator.admin.ManageUsersValidator.assertNoResultManageUsers;

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

        // Search for Registered User
        manageUsersService().searchUser(user.get(0).getEmail());
        assertManageUserRow("Registered");

        // Click on User Row
        manageUsersService().clickOnUserRow(user.get(0).getEmail());

        // Assert Registered User Settings page
        assertElementsOnRegisteredUserSettingsPage();
        assertManageUserRowRole("User");
    }

    @TestRailTest(caseId = 30103)
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 4)
    @DisplayName("Manage Users: Pending Registration: Check Elements")
    void checkElementsOnPendingManageUsersPage(final List<NewUserInput> user) {
        // Search for Pending User
        manageUsersService().searchUser(user.get(0).getEmail());
        assertManageUserRow("Pending Registration");

        // Click on User Row
        manageUsersService().clickOnUserRow(user.get(0).getEmail());

        // Assert Pending User Settings page
        assertElementsOnPendingUserSettingsPage();
        assertManageUserRowRole("Admin");
    }

    @TestRailTest(caseId = 30104)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Manage Users: Deactivated: Check Elements")
    void checkElementsOnInactiveManageUsersPage(final List<NewUserInput> user) {

        // Delete User
        practisApi().deleteUser(user.get(0).getEmail());

        // Search for Inactive User
        manageUsersService().searchUser(user.get(0).getEmail());
        assertManageUserRow("Inactive");

        // Click on User Row
        manageUsersService().clickOnUserRow(user.get(0).getEmail());

        // Assert Inactive User Settings page
        assertElementsOnInactiveUserSettingsPage();
        assertManageUserRowRole("User");
    }

    @TestRailTest(caseId = 21927)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Manage Users: Deactivate")
    void checkDeactivateOnManageUsersPage(final List<NewUserInput> user) {

        // Search for Registered User
        manageUsersService().searchUser(user.get(0).getEmail());

        // Click on User Row
        manageUsersService().clickOnUserRow(user.get(0).getEmail());

        // Click on Deactivate
        manageUserSettingsService().clickOnDeactivateButton();

        // Assert confirmation pop-up
        assertConfirmationModal(
                "Deactivate User?",
                "Deactivating a user simply blocks their access to Practis. Their information and"
                        + " their progress will not be lost. You can re-activate them at any time.",
                "Deactivate");

        // Click on Cancel
        confirmationAndWarningPopUp().discardChanges();

        // Assert Registered User Settings page
        assertElementsOnRegisteredUserSettingsPage();

        // Click on Deactivate
        manageUserSettingsService().clickOnDeactivateButton();

        // Click on Deactivate on confirmation pop-up
        confirmationAndWarningPopUp().saveChanges();

        // Assert Snackbar
        snackbar().getMessage().shouldBe(exactText("User has been deactivated"));

        // Assert Inactive User Settings page
        assertElementsOnInactiveUserSettingsPage();
    }

    @TestRailTest(caseId = 21928)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Manage Users: Activate")
    void checkActivateOnManageUsersPage(final List<NewUserInput> user) {

        // Delete User
        practisApi().deleteUser(user.get(0).getEmail());

        // Search for Inactive User
        manageUsersService().searchUser(user.get(0).getEmail());

        // Click on User Row
        manageUsersService().clickOnUserRow(user.get(0).getEmail());

        // Click on Activate
        manageUserSettingsService().clickOnActivateButton();

        // Assert confirmation pop-up
        assertConfirmationModal(
                "Activate User?",
                "This will restore this user's access to their account so that they can continue"
                        + " using Practis as usual.",
                "Activate");

        // Click on Cancel on confirmation pop-up
        confirmationAndWarningPopUp().discardChanges();

        // Assert Registered User Settings page
        assertElementsOnInactiveUserSettingsPage();

        // Click on Activate
        manageUserSettingsService().clickOnActivateButton();

        // Click on Activate on confirmation pop-up
        confirmationAndWarningPopUp().saveChanges();

        // Assert Snackbar
        snackbar().getMessage().shouldBe(exactText("User has been activated"));

        // Assert Registered User Settings page
        assertElementsOnRegisteredUserSettingsPage();
    }

    @TestRailTest(caseId = 21929)
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Manage Users: Revoke")
    void checkRevokeOnManageUsersPage(final List<NewUserInput> user) {

        // Search for Pending User
        manageUsersService().searchUser(user.get(0).getEmail());

        // Click on User Row
        manageUsersService().clickOnUserRow(user.get(0).getEmail());

        // Click on Revoke
        manageUserSettingsService().clickOnRevokeButton();

        // Assert confirmation pop-up
        assertConfirmationModal(
                "Revoke Invitation?",
                "Are you sure you want to revoke the invitation to this user?",
                "Revoke");

        // Click on Cancel on confirmation pop-up
        confirmationAndWarningPopUp().discardChanges();

        // Assert Pending User Settings page
        assertElementsOnPendingUserSettingsPage();

        // Click on Revoke
        manageUserSettingsService().clickOnRevokeButton();

        // Click on Revoke on confirmation pop-up
        confirmationAndWarningPopUp().saveChanges();

        // Assert Snackbar
        snackbar().getMessage().shouldBe(exactText("User has been revoked"));

        // Assert no search results
        assertNoResultManageUsers();
    }
}
