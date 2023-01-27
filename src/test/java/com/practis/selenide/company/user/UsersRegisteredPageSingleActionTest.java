package com.practis.selenide.company.user;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.usersService;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertSingleActionUsersRegistered;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertSingleActionUsersRegisteredLabels;

import com.codeborne.selenide.Selenide;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.LabelExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class UsersRegisteredPageSingleActionTest {

    @BeforeEach
    void init() {
        navigationCompany().getUsersNavigationItem().click();
    }

    @TestRailTest(caseId = 1618)
    @LabelExtension(count = 1)
    @DisplayName("Users: Registered: Single Action: Check Elements")
    void checkElementsSingleActionTeam() {

        // asser single action Users - Registered - without labels
        usersService().clickSingleActionUsersRegistered();
        assertSingleActionUsersRegistered();

        // asser single action Users - Registered - with labels
        Selenide.refresh();
        usersService().clickSingleActionUsersRegistered();
        assertSingleActionUsersRegisteredLabels();
    }
}
