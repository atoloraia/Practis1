package com.practis.selenide.company.user;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertElementsOnPendingFilter;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertUsersPendingPage;
import static com.practis.web.util.SelenidePageLoadAwait.awaitFullPageLoad;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.PendingUserExtension;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class UsersPendingTest {

    /** Users, Pending: Check WEB Elements. */
    @TestRailTest(caseId = 23879)
    @DisplayName("Company: Navigation: Users: Pending: Check Elements")
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    void checkElementsPendingUsers() {

        navigationCompany().getUsersNavigationItem().click();
        usersPage().getPendingTab().click();
        assertUsersPendingPage();
    }

    /** Users: Pending tab: Filters: Check Elements. */
    @TestRailTest(caseId = 23820)
    @DisplayName("Users: Pending tab: Filters: Check Elements")
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    void checkElementsPendingUsersFilers() {
        navigationCompany().getUsersNavigationItem().click();
        usersPage().getPendingTab().click();
        awaitFullPageLoad(10);
        usersPage().getFiltersButton().click();
        assertElementsOnPendingFilter();
    }
}
