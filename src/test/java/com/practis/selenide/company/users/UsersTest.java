package com.practis.selenide.company.users;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertLimitInfoOnUserPage;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertNoLimitInfoOnUserPage;
import static com.practis.web.selenide.validator.company.users.DraftsTabValidator.assertEmptyDraftsPage;
import static com.practis.web.selenide.validator.company.users.PendingTabValidator.assertEmptyPendingPage;
import static com.practis.web.selenide.validator.company.users.RegisteredTabValidator.assertUsersRegisteredPage;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.CompanyUserLimitExtension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class UsersTest {

    /** Users: Check WEB Elements - Empty state. */
    @TestRailTest(caseId = 31753)
    @DisplayName("Company: Navigation: Users: Registered tab, Pending, Drafts tabs: Check Elements")
    void checkElementsUsersPage() {

        navigationCompany().getUsersNavigationItem().click();
        assertUsersRegisteredPage();
        assertNoLimitInfoOnUserPage();

        usersPage().getPendingTab().click();
        assertEmptyPendingPage();
        assertNoLimitInfoOnUserPage();

        usersPage().getDraftTab().click();
        assertEmptyDraftsPage();
        assertNoLimitInfoOnUserPage();
    }

    /** Company: Navigation: Users: Registered tab, Pending, Drafts tabs: Check User Limit info. */
    @Disabled
    @TestRailTest(caseId = 32191)
    @DisplayName(
            "Company: Navigation: Users: Registered tab, Pending, Drafts tabs: Check User Limit"
                    + " info")
    @CompanyUserLimitExtension(2)
    void checkLimitsOnUsersPage() {
        navigationCompany().getUsersNavigationItem().click();
        assertUsersRegisteredPage();
        assertLimitInfoOnUserPage();

        usersPage().getPendingTab().click();
        assertEmptyPendingPage();
        assertLimitInfoOnUserPage();

        usersPage().getDraftTab().click();
        assertEmptyDraftsPage();
        assertLimitInfoOnUserPage();
    }
}
