package com.practis.selenide.company.user;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertUsersDraftsPage;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class UsersDraftsTest {

    /** Users, Drafts: Check WEB Elements. */
    @TestRailTest(caseId = 23880)
    @DisplayName("Company: Navigation: Users: Drafts: Check Elements")
    void checkElementsDraftsUsers() {

        navigationCompany().getUsersNavigationItem().click();
        usersPage().getTabs().get(2).click();
        assertUsersDraftsPage();
    }
}
