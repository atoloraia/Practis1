package com.practis.selenide.admin.navigation.manage;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.confirmationAndWarningPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationAdminSideBar;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.addMobileNumberPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.addMobileService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.createAnAccountService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.loginService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.manageUserSettingsService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.manageUsersService;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static com.practis.web.selenide.configuration.model.WebCredentialsConfiguration.webCredentialsConfig;
import static com.practis.web.selenide.service.admin.ManageUserSettingsService.clickOnBackButton;
import static com.practis.web.selenide.service.admin.ManageUserSettingsService.clickOnDeleteMobileButton;
import static com.practis.web.selenide.service.admin.ManageUserSettingsService.clickOnRoleField;
import static com.practis.web.selenide.service.admin.ManageUserSettingsService.deleteMobileButton;
import static com.practis.web.selenide.service.admin.ManageUserSettingsService.selectAdminRole;
import static com.practis.web.selenide.service.admin.ManageUserSettingsService.selectUserRole;
import static com.practis.web.selenide.validator.InvalidInviteValidator.assertElementsOnThisDidNotWork;
import static com.practis.web.selenide.validator.VerifyMobileNumberValidator.assertElementsOnVerifyMobilePage;
import static com.practis.web.selenide.validator.admin.ManageUserSettingsValidator.assertElementsOnInactiveUserSettingsPage;
import static com.practis.web.selenide.validator.admin.ManageUserSettingsValidator.assertElementsOnPendingUserSettingsPage;
import static com.practis.web.selenide.validator.admin.ManageUserSettingsValidator.assertElementsOnRegisteredUserSettingsPage;
import static com.practis.web.selenide.validator.admin.ManageUserSettingsValidator.assertEmptyMobileNumberField;
import static com.practis.web.selenide.validator.admin.ManageUserSettingsValidator.assertManageUserSettingsRoleValue;
import static com.practis.web.selenide.validator.admin.ManageUserSettingsValidator.assertMobileNumberField;
import static com.practis.web.selenide.validator.admin.ManageUserSettingsValidator.assertRolesDropdown;
import static com.practis.web.selenide.validator.admin.ManageUsersValidator.assertEmptyMobileNumberRow;
import static com.practis.web.selenide.validator.admin.ManageUsersValidator.assertManageUserRow;
import static com.practis.web.selenide.validator.admin.ManageUsersValidator.assertManageUsersRoleValue;
import static com.practis.web.selenide.validator.admin.ManageUsersValidator.assertNoResultManageUsers;
import static com.practis.web.selenide.validator.popup.ConfirmAndWarningPopUpsValidator.assertConfirmationModal;
import static com.practis.web.util.SelenidePageLoadAwait.awaitAjaxComplete;
import static com.practis.web.util.SelenidePageLoadAwait.awaitFullPageLoad;
import static java.lang.String.format;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewUserInput;
import com.practis.rest.dto.admin.RestAdminResponse;
import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.AdminExtension;
import com.practis.support.extension.practis.PendingUserExtension;
import com.practis.support.extension.practis.Qualifier;
import com.practis.support.extension.practis.RegisteredUserExtension;
import com.practis.web.selenide.configuration.model.WebCredentialsConfiguration;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisAdminTestClass
@SelenideTestClass
@TestRailTestClass
class ManageUserSettingsTest {

    private final WebCredentialsConfiguration credentials = webCredentialsConfig();
    private final List<NewUserInput> usersToRemove = new ArrayList<>();
    private List<NewUserInput> inputData;

    @BeforeEach
    void beforeEach() {
        navigationAdminSideBar().getManageUsersNavigationItem().click();
    }

    @TestRailTest(caseId = 21925)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Admin: Users Settings: Registered: Check Elements")
    void checkElementsRegisteredManageUsersPage(final List<NewUserInput> user) {

        // Open Registered User Settings page
        manageUsersService().searchUser(user.get(0).getEmail());
        assertManageUserRow("Registered");

        // Click on User Row
        manageUsersService().clickOnUserRow(user.get(0).getEmail());

        // Assert Registered User Settings page
        assertElementsOnRegisteredUserSettingsPage();
        assertManageUserSettingsRoleValue("User");
    }

    @TestRailTest(caseId = 30103)
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 4)
    @DisplayName("Admin: Users Settings: Pending Registration: Check Elements")
    void checkElementsPendingManageUsersPage(final List<NewUserInput> user) {

        // Open Pending User Settings page
        manageUsersService().searchUser(user.get(0).getEmail());
        assertManageUserRow("Pending Registration");

        // Click on User Row
        manageUsersService().clickOnUserRow(user.get(0).getEmail());

        // Assert Pending User Settings page
        assertElementsOnPendingUserSettingsPage();
        assertManageUserSettingsRoleValue("Admin");
    }

    @TestRailTest(caseId = 30104)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Admin: Users Settings: Deactivated: Check Elements")
    void checkElementsInactiveManageUsersPage(final List<NewUserInput> user) {

        // Delete User
        practisApi().deleteUser(user.get(0).getEmail());

        // Open Inactive User Settings page
        manageUsersService().searchUser(user.get(0).getEmail());
        assertManageUserRow("Inactive");

        // Click on User Row
        manageUsersService().clickOnUserRow(user.get(0).getEmail());

        // Assert Inactive User Settings page
        assertElementsOnInactiveUserSettingsPage();
        assertManageUserSettingsRoleValue("User");
    }

    @TestRailTest(caseId = 21927)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Admin: Users Settings: Deactivate")
    void deactivateOnManageUsersPage(final List<NewUserInput> user) {

        // Open Registered User Settings page
        manageUsersService().openUserRow(user.get(0).getEmail());

        // Click on Deactivate
        manageUserSettingsService().clickOnDeactivateButton();

        // Assert confirmation pop-up
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

    @TestRailTest(caseId = 21939)
    @DisplayName("Admin: Users Settings: Deactivate: Login")
    @AdminExtension
    void deactivateAndLoginManageUsersPage(final List<RestAdminResponse> admin) {

        // Open Users Settings page for Admin
        manageUsersService().openUserRow(admin.get(0).getEmail());

        // Click on Deactivate
        manageUserSettingsService().clickOnDeactivateButton();
        confirmationAndWarningPopUp().saveChanges();

        // try to log in as deactivated Admin
        loginService().logOut();
        loginService().fillFormAndLogin(admin.get(0).getEmail(), "1pass1234*");
        snackbar().getMessage().shouldBe(exactText("Invalid Email Address or Password"));
    }

    @TestRailTest(caseId = 21928)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Admin: Users Settings: Activate")
    void activateManageUsersPage(final List<NewUserInput> user) {

        // Delete User
        practisApi().deleteUser(user.get(0).getEmail());

        // Open Inactive User Settings page
        manageUsersService().openUserRow(user.get(0).getEmail());

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

    @TestRailTest(caseId = 21940)
    @DisplayName("Admin: Users Settings: Activate: Login")
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    void activateAndLoginManageUsersPage(final List<NewUserInput> pending) {
        usersToRemove.add(pending.get(0));
        // Open invitation link and create password for invited User
        createAnAccountService().createAccount("qwerty123", pending.get(0).getInvitationCode());

        // Login as Practis Admin and deactivate
        loginService().loginAsPractisAdmin(credentials);
        practisApi().deleteUser(pending.get(0).getEmail());

        // Open User and activate user
        navigationAdminSideBar().getManageUsersNavigationItem().click();
        manageUsersService().openUserRow(pending.get(0).getEmail());
        manageUserSettingsService().clickOnActivateButton();
        confirmationAndWarningPopUp().saveChanges();

        // Log out
        loginService().logOut();

        // Log in as re-activated user
        loginService().fillFormAndLogin(pending.get(0).getEmail(), "qwerty123");
        awaitAjaxComplete(20);
        addMobileNumberPage().getAddMobileTitle().should(exist);
    }

    @TestRailTest(caseId = 21929)
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Admin: Users Settings: Revoke")
    void checkRevokeOnManageUsersPage(final List<NewUserInput> user) {

        // Open Pending User Settings page
        manageUsersService().openUserRow(user.get(0).getEmail());

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

    @TestRailTest(caseId = 21941)
    @DisplayName("Admin: Users Settings: Revoke: Login revoking User's invitation")
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    void revokeAndLoginManageUsersPage(final List<NewUserInput> pending) {

        // Open Pending User Settings page
        manageUsersService().openUserRow(pending.get(0).getEmail());

        // Click on Revoke and confirm the action
        manageUserSettingsService().clickOnRevokeButton();
        confirmationAndWarningPopUp().saveChanges();
        awaitFullPageLoad(10);

        // Open invitation link
        final var url =
                format(
                        "%s/registration/?token=%s",
                        webApplicationConfig().getUrl(), pending.get(0).getInvitationCode());
        Selenide.open(url);

        // Assert 'Hmm. This didn't work' page
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        assertElementsOnThisDidNotWork();
    }

    @TestRailTest(caseId = 21930)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Admin: Users Settings: Registered: Delete Mobile Number")
    void deleteMobileOnRegisteredManageUsersPage(final List<NewUserInput> user) {

        // Open Registered User Settings page
        manageUsersService().openUserRow(user.get(0).getEmail());

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

        // Verify that Mobile Number is deleted on Manage Users list
        clickOnBackButton();
        assertEmptyMobileNumberRow();
    }

    @TestRailTest(caseId = 21938)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Admin: Users Settings: Inactive: Delete Mobile Number")
    void deleteMobileOnInactiveManageUsersPage(final List<NewUserInput> user) {

        // Delete User
        practisApi().deleteUser(user.get(0).getEmail());

        // Open Inactive User Settings page
        manageUsersService().openUserRow(user.get(0).getEmail());

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
        await().pollDelay(TWO_SECONDS).until(() -> true);

        // Click on Delete Mobile Number button and confirm the action
        clickOnDeleteMobileButton();
        confirmationAndWarningPopUp().getConfirmButton().click();

        // Assert Snackbar and empty mobile number field
        snackbar().getMessage().shouldBe(exactText("Mobile number has been deleted"));
        assertEmptyMobileNumberField();

        // Verify that Mobile Number is deleted on Manage Users list
        clickOnBackButton();
        assertEmptyMobileNumberRow();
    }

    @TestRailTest(caseId = 21931)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 4)
    @DisplayName("Admin: Users Settings: Registered: Use Deleted Mobile")
    void useDeletedMobileOnInactiveManageUsersPage(
            @Qualifier("registered") final List<NewUserInput> registered,
            @Qualifier("pending") final List<NewUserInput> pending) {

        // Deactivate the User and delete mobile number
        practisApi().deleteUser(registered.get(0).getEmail());
        manageUsersService().openUserRow(registered.get(0).getEmail());
        String mobile = registered.get(0).getPhoneNumber();
        deleteMobileButton();

        createAnAccountService().createAccount("qwerty123", pending.get(0).getInvitationCode());
        awaitFullPageLoad(10);
        addMobileService().addMobileNumberService(mobile);
        awaitFullPageLoad(10);
        assertElementsOnVerifyMobilePage(mobile);
    }

    @TestRailTest(caseId = 21934)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Admin: Users Settings: Registered: Change Role")
    void changeRoleOnRegisteredManageUsersPage(final List<NewUserInput> user) {

        // Open Registered User Settings page
        manageUsersService().openUserRow(user.get(0).getEmail());

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
    @DisplayName("Admin: Users Settings: Deactivated: Change Role")
    void changeRoleOnInactiveManageUsersPage(final List<NewUserInput> user) {

        // Open Inactive User Settings page
        manageUsersService().openUserRow(user.get(0).getEmail());

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

    @AfterEach
    void cleanup() {
        usersToRemove.forEach(user -> practisApi().deleteUser(user.getEmail()));
    }
}
