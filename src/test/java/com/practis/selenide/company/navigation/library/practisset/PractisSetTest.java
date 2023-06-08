package com.practis.selenide.company.navigation.library.practisset;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.searchService;
import static com.practis.web.selenide.service.SearchService.searchAfter1Char;
import static com.practis.web.selenide.validator.common.SearchValidator.assertCleanSearch;
import static com.practis.web.selenide.validator.common.SearchValidator.assertSearchField;
import static com.practis.web.selenide.validator.company.library.practisset.PractisSetTabValidator.assertElementsOnLibraryPractisSetsTab;
import static com.practis.web.selenide.validator.company.library.practisset.PractisSetTabValidator.assertEmptyStateLibraryPractisSetsTab;
import static com.practis.web.selenide.validator.company.library.practisset.PractisSetTabValidator.assertNoSearchResultOnPractisSetTab;
import static com.practis.web.selenide.validator.company.library.practisset.PractisSetTabValidator.assertSearchAnyResultsOnPractisSetTab;
import static com.practis.web.selenide.validator.company.library.practisset.PractisSetTabValidator.assertSearchResultsOnPractisSetTab;

import com.practis.dto.NewPractisSetInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.PractisSetExtension;
import java.util.List;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class PractisSetTest {

    /** View Library: Practis Set tab: default view */
    @TestRailTest(caseId = 31870)
    @DisplayName("Library: Practis Sets Tab: Check Elements")
    @PractisSetExtension(count = 1)
    void checkElementsViewPractisSet() {
        // open Library: Practis Set tab
        navigationCompany().getLibraryNavigationItem().click();
        assertElementsOnLibraryPractisSetsTab();
    }

    /** View Library: Practis Set tab: Empty State */
    @TestRailTest(caseId = 31871)
    @DisplayName("Library: Practis Sets Tab: Check Elements: Empty")
    void checkElementsViewPractisSetEmptyState() {
        // open Library: Practis Set tab
        navigationCompany().getLibraryNavigationItem().click();
        assertEmptyStateLibraryPractisSetsTab();
    }

    /** View Library: Practis Set tab: Search */
    @TestRailTest(caseId = 31872)
    @DisplayName("Library: Practis Sets Tab: Search")
    @PractisSetExtension(count = 1)
    void searchOnPractisSetTab(final List<NewPractisSetInput> practisSets) {
        navigationCompany().getLibraryNavigationItem().click();

        // Assert Search Field
        assertSearchField();

        // Assert no Search results
        searchService().searchPerform("no results");
        assertNoSearchResultOnPractisSetTab();

        // Assert Search by Practis Set Name
        searchService().clearSearch();
        searchService().searchPerform(practisSets.get(0).getName());
        assertSearchResultsOnPractisSetTab(practisSets.get(0).getName());

        // Search should be performed after entering 1 character
        searchService().clearSearch();
        searchAfter1Char(practisSets.get(0).getName());
        assertSearchAnyResultsOnPractisSetTab();

        // Assert Clear Search
        assertCleanSearch();
    }
}
