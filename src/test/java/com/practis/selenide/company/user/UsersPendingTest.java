package com.practis.selenide.company.user;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.validator.company.navigation.UserValidator.assertUsersEmptyPendingPage;
import static com.practis.web.selenide.validator.company.navigation.UserValidator.assertUsersPendingPage;

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

    /** Users, Pending: Check WEB Elements - Empty state. */
    @TestRailTest(caseId = 9520)
    @DisplayName("Check Elements: 'Users, Pending - Empty state'")
    void checkElementsPendingUsersEmptyState() {

        navigationCompany().getUsersNavigationItem().click();
        usersPage().getTabs().get(1).click();
        assertUsersEmptyPendingPage();
    }

    /** Users, Pending: Check WEB Elements. */
    @TestRailTest(caseId = 9520)
    @DisplayName("Check Elements: 'Users, Pending'")
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    void checkElementsPendingUsers() {

        navigationCompany().getUsersNavigationItem().click();
        usersPage().getTabs().get(1).click();
        assertUsersPendingPage();
    }
}
