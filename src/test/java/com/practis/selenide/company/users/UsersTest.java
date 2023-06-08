package com.practis.selenide.company.users;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.validator.company.users.DraftsTabValidator.assertEmptyDraftsPage;
import static com.practis.web.selenide.validator.company.users.PendingTabValidator.assertEmptyPendingPage;
import static com.practis.web.selenide.validator.company.users.RegisteredTabValidator.assertUsersRegisteredPage;

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
    @TestRailTest(caseId = 31753)
    @DisplayName("Company: Navigation: Users: Registered tab, Pending, Drafts tabs: Check Elements")
    void checkElementsRegisteredUsers() {

        navigationCompany().getUsersNavigationItem().click();
        assertUsersRegisteredPage();

        usersPage().getPendingTab().click();
        assertEmptyPendingPage();

        usersPage().getDraftTab().click();
        assertEmptyDraftsPage();
    }
}
