package com.practis.selenide.company.user;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertUsersEmptyDraftsPage;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertUsersEmptyPendingPage;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertUsersRegisteredPage;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class UsersTest {

    /** Users: Check WEB Elements - Empty state. */
    @TestRailTest(caseId = 9520)
    @DisplayName("Company: Navigation: Users: Registered tab, Pending, Drafts tabs: Check Elements")
    void checkElementsRegisteredUsers() {

        navigationCompany().getUsersNavigationItem().click();
        assertUsersRegisteredPage();

        usersPage().getTabs().get(1).click();
        assertUsersEmptyPendingPage();

        usersPage().getTabs().get(2).click();
        assertUsersEmptyDraftsPage();
    }
}
