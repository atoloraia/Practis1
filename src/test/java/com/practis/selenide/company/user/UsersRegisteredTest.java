package com.practis.selenide.company.user;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.validator.company.navigation.UserValidator.assertUsersRegisteredPage;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class UsersRegisteredTest {

    /** Users, Registered: Check WEB Elements. */
    @TestRailTest(caseId = 9520)
    @DisplayName("Check Elements: 'Users, Registered'")
    void checkElementsRegisteredUsers() {

        navigationCompany().getUsersNavigationItem().click();
        assertUsersRegisteredPage();
    }
}
