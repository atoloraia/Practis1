package com.practis.selenide.company.users.pending;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.searchService;
import static com.practis.web.selenide.service.SearchService.searchAfter1Char;
import static com.practis.web.selenide.validator.common.SearchValidator.assertCleanSearch;
import static com.practis.web.selenide.validator.common.SearchValidator.assertSearchField;
import static com.practis.web.selenide.validator.company.users.PendingTabValidator.assertElementsOnPendingFilter;
import static com.practis.web.selenide.validator.company.users.PendingTabValidator.assertNoSearchResultOnPendingUserTab;
import static com.practis.web.selenide.validator.company.users.PendingTabValidator.assertPendingPage;
import static com.practis.web.selenide.validator.company.users.PendingTabValidator.assertSearchResultsOnPendingUserTab;
import static com.practis.web.util.SelenidePageLoadAwait.awaitFullPageLoad;

import com.practis.dto.NewUserInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.PendingUserExtension;
import java.util.List;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class UsersPendingTest {

    /** Users, Pending: Check WEB Elements. */
    @TestRailTest(caseId = 31992)
    @DisplayName("Company: Navigation: Users: Pending: Check Elements")
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    void checkElementsPendingUsers() {

        navigationCompany().getUsersNavigationItem().click();
        usersPage().getPendingTab().click();
        assertPendingPage();
    }

    /** Users: Pending tab: Filters: Check Elements. */
    @TestRailTest(caseId = 31991)
    @DisplayName("Users: Pending tab: Filters: Check Elements")
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    void checkElementsPendingUsersFilers() {
        navigationCompany().getUsersNavigationItem().click();
        usersPage().getPendingTab().click();
        awaitFullPageLoad(10);
        usersPage().getFiltersButton().click();
        assertElementsOnPendingFilter();
    }

    /** Users: Draft tab: Search */
    @DisplayName("Users: Pending tab: Search")
    @TestRailTest(caseId = 31993)
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    void searchOnPendingTab(final List<NewUserInput> user) {
        navigationCompany().getUsersNavigationItem().click();
        usersPage().getPendingTab().click();

        // Assert Search Field
        assertSearchField();

        // Assert no Search results
        searchService().searchPerform("no results");
        assertNoSearchResultOnPendingUserTab();

        // Assert Search by First Name
        searchService().clearSearch();
        searchService().searchPerform(user.get(0).getFirstName());
        assertSearchResultsOnPendingUserTab(user.get(0).getFirstName());

        // Search should be performed after entering 1 character
        searchService().clearSearch();
        searchAfter1Char(user.get(0).getFirstName());
        assertSearchResultsOnPendingUserTab(user.get(0).getFirstName());

        // Assert Clear Search
        assertCleanSearch();
    }
}
