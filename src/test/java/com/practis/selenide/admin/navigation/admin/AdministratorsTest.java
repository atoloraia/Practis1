package com.practis.selenide.admin.navigation.admin;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationAdmin;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.searchService;
import static com.practis.web.selenide.service.SearchService.searchAfter1Char;
import static com.practis.web.selenide.validator.admin.AdministratorsValidator.assertElementsOnAdministrationPage;
import static com.practis.web.selenide.validator.admin.AdministratorsValidator.assertNoSearchResultOnAdministratorsPage;
import static com.practis.web.selenide.validator.admin.AdministratorsValidator.assertSearchAnyResultsOnAdministratorsPage;
import static com.practis.web.selenide.validator.admin.AdministratorsValidator.assertSearchResultsOnAdministratorsPage;
import static com.practis.web.selenide.validator.common.SearchValidator.assertCleanSearch;
import static com.practis.web.selenide.validator.common.SearchValidator.assertSearchField;

import com.practis.rest.dto.admin.RestAdminResponse;
import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.AdminExtension;
import java.util.List;
import org.junit.jupiter.api.DisplayName;

@SelenideTestClass
@TestRailTestClass
@PractisAdminTestClass
class AdministratorsTest {

    /** Check Web elements on Administrators page. */
    @TestRailTest(caseId = 11671)
    @DisplayName("Administrators: Check Elements")
    void checkElementsOnAdministratorsPage() {
        navigationAdmin().adminNavigationItem.click();

        assertElementsOnAdministrationPage();
    }

    /** Search on Administrators page. */
    @TestRailTest(caseId = 16)
    @DisplayName("Administrators: Search")
    @AdminExtension
    void searchOnAdministratorsPage(final List<RestAdminResponse> admins) {
        navigationAdmin().adminNavigationItem.click();

        // Assert Search Field
        assertSearchField();

        // Assert no Search results
        searchService().searchPerform("no results");
        assertNoSearchResultOnAdministratorsPage();

        // Assert Search by First Name
        searchService().clearSearch();
        searchService().searchPerform(admins.get(0).getFirstName());
        assertSearchResultsOnAdministratorsPage(admins.get(0).getFirstName());

        // Assert Search by Last Name
        searchService().clearSearch();
        searchService().searchPerform(admins.get(0).getLastName());
        assertSearchResultsOnAdministratorsPage(admins.get(0).getLastName());

        // Search should be performed after entering 1 character
        searchService().clearSearch();
        searchAfter1Char(admins.get(0).getLastName());
        assertSearchAnyResultsOnAdministratorsPage();

        // Assert Clear Search
        assertCleanSearch();
    }
}
