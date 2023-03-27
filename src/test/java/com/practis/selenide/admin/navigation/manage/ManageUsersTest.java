package com.practis.selenide.admin.navigation.manage;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationAdminSideBar;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.manageUsersService;
import static com.practis.web.selenide.validator.admin.ManageUsersValidator.assertClickOnClearButton;
import static com.practis.web.selenide.validator.admin.ManageUsersValidator.assertElementsOnManageUsersPage;
import static com.practis.web.selenide.validator.admin.ManageUsersValidator.assertElementsOnNoSearchResultManageUsersPage;
import static com.practis.web.selenide.validator.admin.ManageUsersValidator.assertEmptyState;
import static com.practis.web.selenide.validator.admin.ManageUsersValidator.assertRowManageUser;
import static com.practis.web.selenide.validator.admin.ManageUsersValidator.assertSearchAfter1CharUsers;
import static com.practis.web.selenide.validator.admin.ManageUsersValidator.assertSearchResultsOnManageUsersPage;
import static com.practis.web.selenide.validator.admin.ManageUsersValidator.assertSeveralSearchResultsOnManageUsersPage;

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
        assertElementsOnNoSearchResultManageUsersPage();
        assertClickOnClearButton();

        // Assert search from 1char
        assertSearchAfter1CharUsers("a");
        assertSeveralSearchResultsOnManageUsersPage();
        assertClickOnClearButton();

        // Assert search by User's First name
        manageUsersService().searchUser(user.get(0).getFirstName());
        assertSearchResultsOnManageUsersPage();

        // marked
        final var userGridRow = grid().getRow(user.get(0).getFirstName());
        assertRowManageUser(user.get(0), userGridRow);

        // assertUserRowSearchByFirstName(user.get(0).getFirstName());
        assertClickOnClearButton();

        // Assert search by User's Last name
        manageUsersService().searchUser(user.get(0).getLastName());
        assertSeveralSearchResultsOnManageUsersPage();
        assertClickOnClearButton();

        // Assert search by User's email
        manageUsersService().searchUser(user.get(0).getEmail());
        assertSearchResultsOnManageUsersPage();
        // assertUserRowSearchByEmail(user.get(0).getEmail());
        assertClickOnClearButton();

        // Assert search by First Name with Upper cases
        manageUsersService().searchUserWithUpperCases(user.get(0).getFirstName());
        assertSearchResultsOnManageUsersPage();
        // assertUserRowSearchByFirstName(user.get(0).getLastName());
        assertClickOnClearButton();

        // Assert search by Email with Upper cases
        manageUsersService().searchUserWithUpperCases(user.get(0).getEmail());
        assertSearchResultsOnManageUsersPage();
        // assertUserRowSearchByEmail(user.get(0).getEmail());
        assertClickOnClearButton();
    }
}
