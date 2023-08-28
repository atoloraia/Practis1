package com.practis.selenide.admin.navigation.companies;

import static com.practis.web.selenide.configuration.ServiceObjectFactory.searchService;
import static com.practis.web.selenide.service.SearchService.searchAfter1Char;
import static com.practis.web.selenide.validator.admin.CompanyAccountsValidator.assertElementsOnCompaniesPage;
import static com.practis.web.selenide.validator.admin.CompanyAccountsValidator.assertNoResultsCompanyAccounts;
import static com.practis.web.selenide.validator.admin.CompanyAccountsValidator.assertSearchAnyResultsOnCompanyAccounts;
import static com.practis.web.selenide.validator.admin.CompanyAccountsValidator.assertSearchResultsOnCompanyAccounts;
import static com.practis.web.selenide.validator.common.SearchValidator.assertCleanSearch;
import static com.practis.web.selenide.validator.common.SearchValidator.assertSearchField;

import com.practis.rest.dto.admin.RestCompanyResponse;
import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.CompanyExtension;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;

@SelenideTestClass
@TestRailTestClass
@PractisAdminTestClass
class CompanyAccountsTest {

    /** Check Web elements on Companies page. */
    @Disabled
    // @TestRailTest(caseId = 12)
    @TestRailTest(caseId = 9522)
    @DisplayName("Companies: Check Elements")
    void checkElementsOnCompanyAccountsPage() {
        assertElementsOnCompaniesPage();
    }

    /** Search on Companies page. */
    @Disabled
    // @TestRailTest(caseId = 12)
    @DisplayName("Companies: Search")
    @CompanyExtension
    void searchOnCompanyAccountsPage(List<RestCompanyResponse> companies) {
        // Assert Search Field
        assertSearchField();

        // Assert no Search results
        searchService().searchPerform("no results");
        assertNoResultsCompanyAccounts();

        // Assert Search by First Name
        searchService().clearSearch();
        searchService().searchPerform(companies.get(0).getName());
        assertSearchResultsOnCompanyAccounts(companies.get(0).getName());

        // Assert Search by Last Name
        searchService().clearSearch();
        searchService().searchPerform(companies.get(0).getName());
        assertSearchResultsOnCompanyAccounts(companies.get(0).getName());

        // Search should be performed after entering 1 character
        searchService().clearSearch();
        searchAfter1Char(companies.get(0).getName());
        assertSearchAnyResultsOnCompanyAccounts();

        // Assert Clear Search
        assertCleanSearch();
    }
}
