package com.practis.selenide.admin.navigation.manage;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationAdminSideBar;
import static com.practis.web.selenide.configuration.PageObjectFactory.manageUserSettingsPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.manageUsersService;
import static com.practis.web.selenide.validator.admin.ManageUserSettingsValidator.assertElementsOnInactiveUserSettingsPage;
import static com.practis.web.selenide.validator.admin.ManageUserSettingsValidator.assertElementsOnPendingUserSettingsPage;
import static com.practis.web.selenide.validator.admin.ManageUserSettingsValidator.assertElementsOnRegisteredUserSettingsPage;
import static com.practis.web.selenide.validator.admin.ManageUsersValidator.assertInactiveUserRow;
import static com.practis.web.selenide.validator.admin.ManageUsersValidator.assertPendingUserRow;
import static com.practis.web.selenide.validator.admin.ManageUsersValidator.assertRegisteredUserRow;

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
        assertRegisteredUserRow();

        // Click on User Row
        manageUsersService().clickOnUserRow();

        // Assert Registered User Settings page
        assertElementsOnRegisteredUserSettingsPage();
        manageUserSettingsPage().getRoleField().shouldBe(exactText("User"));
    }

    @TestRailTest(caseId = 30103)
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 4)
    @DisplayName("Manage Users: Pending Registration: Check Elements")
    void checkElementsOnPendingManageUsersPage(final List<NewUserInput> user) {

        // Search for Pending User
        manageUsersService().searchUser(user.get(0).getEmail());
        assertPendingUserRow();

        // Click on User Row
        manageUsersService().clickOnUserRow();

        // Assert Pending User Settings page
        assertElementsOnPendingUserSettingsPage();
        manageUserSettingsPage().getRoleField().shouldBe(exactText("Admin"));
    }

    @TestRailTest(caseId = 30104)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Manage Users: Deactivated: Check Elements")
    void checkElementsOnInactiveManageUsersPage(final List<NewUserInput> user) {

        // Delete User
        practisApi().deleteUser(user.get(0).getEmail());

        // Search for Inactive User
        manageUsersService().searchUser(user.get(0).getEmail());
        assertInactiveUserRow();

        // Click on User Row
        manageUsersService().clickOnUserRow();

        // Assert Inactive User Settings page
        assertElementsOnInactiveUserSettingsPage();
        manageUserSettingsPage().getRoleField().shouldBe(exactText("User"));
    }
}
