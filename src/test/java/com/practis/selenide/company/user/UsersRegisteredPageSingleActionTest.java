package com.practis.selenide.company.user;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.usersService;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertSingleActionUsersRegistered;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertSingleActionUsersRegisteredNoLabels;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertUserProfile;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewUserInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.LabelExtension;
import com.practis.support.extension.practis.RegisteredUserExtension;
import java.util.List;
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

    @TestRailTest(caseId = 23903)
    @DisplayName("Users: Registered: Single Action: No Labels + Check Elements")
    void checkElementsSingleActionUsersRegisteredNoLabels() {

        // asser single action Users - Registered - without labels
        usersService().clickSingleActionUsersRegistered();
        assertSingleActionUsersRegisteredNoLabels();
    }

    @TestRailTest(caseId = 1618)
    @LabelExtension(count = 1)
    @DisplayName("Users: Registered: Single Action: Check Elements")
    void checkElementsSingleActionUsersRegistered() {

        // asser single action Users - Registered - with labels
        Selenide.refresh();
        usersService().clickSingleActionUsersRegistered();
        assertSingleActionUsersRegistered();
    }

    @TestRailTest(caseId = 1625)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Registered: Single Action: View Profile")
    void checkElementsSingleActionRegisteredViewProfile(final List<NewUserInput> user) {

        // Click on View Profile
        usersService().clickUsersRegisteredSingleActionViewProfile(user.get(0).getEmail());

        // Assert 'User Profile' page for the Registered User
        assertUserProfile();
    }
}
