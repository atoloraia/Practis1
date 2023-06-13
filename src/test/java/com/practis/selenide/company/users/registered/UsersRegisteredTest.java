package com.practis.selenide.company.users.registered;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.registeredUsersService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.searchService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.service.SearchService.searchAfter1Char;
import static com.practis.web.selenide.validator.common.SearchValidator.assertCleanSearch;
import static com.practis.web.selenide.validator.common.SearchValidator.assertSearchField;
import static com.practis.web.selenide.validator.company.users.RegisteredTabValidator.assertNoSearchResultOnRegisteredUserTab;
import static com.practis.web.selenide.validator.company.users.RegisteredTabValidator.assertRegisteredFiltersEmptyState;
import static com.practis.web.selenide.validator.company.users.RegisteredTabValidator.assertSearchAnyResultsOnRegisteredUserTab;
import static com.practis.web.selenide.validator.company.users.RegisteredTabValidator.assertSearchResultsOnRegisteredUserTab;
import static com.practis.web.selenide.validator.user.UserSettingsValidator.assertUserSettingsPage;
import static com.practis.web.util.SelenidePageLoadAwait.awaitFullPageLoad;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.practis.dto.NewUserInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.RegisteredUserExtension;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class UsersRegisteredTest {

    @BeforeEach
    void init() {
        navigationCompany().getUsersNavigationItem().click();
        awaitFullPageLoad(10);
    }

    /** Users: Registered tab: Filters: Check Elements */
    @TestRailTest(caseId = 31973)
    @DisplayName("Users: Registered tab: Filters: Check Elements")
    void checkElementsRegisteredUsersFilters() {
        usersPage().getFiltersButton().click();
        assertRegisteredFiltersEmptyState();
    }

    /** Users: Registered tab: Search */
    @DisplayName("Users: Registered tab: Search")
    @TestRailTest(caseId = 31974)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    void searchOnRegisteredTab(final List<NewUserInput> user) {
        // Assert Search Field
        assertSearchField();

        // Assert no Search results
        searchService().searchPerform("no results");
        assertNoSearchResultOnRegisteredUserTab();

        // Assert Search by First Name
        searchService().clearSearch();
        searchService().searchPerform(user.get(0).getFirstName());
        assertSearchResultsOnRegisteredUserTab(user.get(0).getFirstName());

        // Assert Search by Last Name
        searchService().clearSearch();
        searchService().searchPerform(user.get(0).getLastName());
        assertSearchResultsOnRegisteredUserTab(user.get(0).getLastName());

        // Search should be performed after entering 1 character
        searchService().clearSearch();
        searchAfter1Char(user.get(0).getLastName());
        assertSearchAnyResultsOnRegisteredUserTab();

        // Assert Clear Search
        assertCleanSearch();
    }

    @TestRailTest(caseId = 32080)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Registered Tab: Edit User")
    void editRegisteredUser(final List<NewUserInput> user) {

        // Click on User Settings
        userService().searchUser(user.get(0).getFirstName());
        await().pollDelay(TWO_SECONDS).until(() -> true);
        registeredUsersService().clickSingleActionUserSettings();

        // Edit User
        registeredUsersService().editRegisteredUser();

        // Verify changes have been applied
        navigationCompany().getUsersNavigationItem().click();
        userService().searchUser(user.get(0).getFirstName());
        await().pollDelay(TWO_SECONDS).until(() -> true);
        registeredUsersService().clickSingleActionUserSettings();
        assertUserSettingsPage("User");
    }
}
