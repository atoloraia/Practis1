package com.practis.selenide.admin.navigation.manage;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationAdminSideBar;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.manageUsersService;
import static com.practis.web.selenide.service.admin.ManageUsersService.clickOnClearButton;
import static com.practis.web.selenide.validator.admin.ManageUsersValidator.assertClickOnClearButton;
import static com.practis.web.selenide.validator.admin.ManageUsersValidator.assertElementsOnManageUsersPage;
import static com.practis.web.selenide.validator.admin.ManageUsersValidator.assertEmptyState;
import static com.practis.web.selenide.validator.admin.ManageUsersValidator.assertNoResultManageUsers;
import static com.practis.web.selenide.validator.admin.ManageUsersValidator.assertResultManageUsers;
import static com.practis.web.selenide.validator.admin.ManageUsersValidator.assertResultsManageUsers;
import static com.practis.web.selenide.validator.admin.ManageUsersValidator.assertRowManageUser;
import static com.practis.web.selenide.validator.admin.ManageUsersValidator.assertSearchAfter1CharManageUsers;

import com.practis.dto.NewUserInput;
import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.RegisteredUserExtension;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisAdminTestClass
@SelenideTestClass
@TestRailTestClass
class ManageUsersTest {

    @BeforeEach
    void beforeEach() {
        navigationAdminSideBar().getManageUsersNavigationItem().click();
    }

    @TestRailTest(caseId = 21923)
    @DisplayName("Manage Users: Check elements")
    void checkElementsOnManageUsersPage() {

        assertElementsOnManageUsersPage();
        assertEmptyState();
    }

    @TestRailTest(caseId = 21924)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Manage Users: Search")
    void assertSearchManageUser(final List<NewUserInput> user) {

        // Assert no search results
        manageUsersService().searchUser("no results");
        assertNoResultManageUsers();
        clickOnClearButton();
        assertClickOnClearButton();

        // Assert search from 1char
        assertSearchAfter1CharManageUsers("a");
        assertResultsManageUsers();
        clickOnClearButton();

        // Assert search by User's First name
        manageUsersService().searchUser(user.get(0).getFirstName());
        assertResultManageUsers();

        final var userGridRow = grid().getRow(user.get(0).getFirstName());
        assertRowManageUser(user.get(0), userGridRow);

        clickOnClearButton();

        // Assert search by User's Last name
        manageUsersService().searchUser(user.get(0).getLastName());
        assertResultsManageUsers();
        clickOnClearButton();

        // Assert search by User's email
        manageUsersService().searchUser(user.get(0).getEmail());
        assertResultManageUsers();
        clickOnClearButton();

        // Assert search by First Name with Upper cases
        manageUsersService().searchUserWithUpperCases(user.get(0).getFirstName());
        assertResultManageUsers();
        clickOnClearButton();

        // Assert search by Email with Upper cases
        manageUsersService().searchUserWithUpperCases(user.get(0).getEmail());
        assertResultManageUsers();
        clickOnClearButton();
    }
}
