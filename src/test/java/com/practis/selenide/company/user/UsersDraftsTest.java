package com.practis.selenide.company.user;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.validator.company.navigation.UserValidator.assertUsersEmptyDraftsPage;

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
    @TestRailTest(caseId = 9520)
    @DisplayName("Check Elements: 'Users, Drafts'")
    void checkElementsDraftsUsers() {

        navigationCompany().getUsersNavigationItem().click();
        usersPage().getTabs().get(2).click();
        assertUsersEmptyDraftsPage();
    }
}
