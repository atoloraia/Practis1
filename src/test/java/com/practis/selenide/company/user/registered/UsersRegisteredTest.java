package com.practis.selenide.company.user.registered;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertRegisteredFiltersEmptyState;
import static com.practis.web.util.SelenidePageLoadAwait.awaitFullPageLoad;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class UsersRegisteredTest {

    /** Users: Registered tab: Filters: Check Elements */
    @TestRailTest(caseId = 23822)
    @DisplayName("Users: Registered tab: Filters: Check Elements")
    void checkElementsRegisteredUsersFilters() {

        navigationCompany().getUsersNavigationItem().click();
        awaitFullPageLoad(10);
        usersPage().getFiltersButton().click();
        assertRegisteredFiltersEmptyState();
    }
}
