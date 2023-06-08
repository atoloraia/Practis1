package com.practis.selenide.company.navigation.library.scenario;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.PageObjectFactory.libraryPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.searchService;
import static com.practis.web.selenide.service.SearchService.searchAfter1Char;
import static com.practis.web.selenide.validator.common.SearchValidator.assertCleanSearch;
import static com.practis.web.selenide.validator.common.SearchValidator.assertSearchField;
import static com.practis.web.selenide.validator.company.library.scenario.ScenarioTabValidator.assertElementsOnLibraryScenarioTab;
import static com.practis.web.selenide.validator.company.library.scenario.ScenarioTabValidator.assertEmptyStateLibraryScenarioTab;
import static com.practis.web.selenide.validator.company.library.scenario.ScenarioTabValidator.assertNoSearchResultOnScenarioTab;
import static com.practis.web.selenide.validator.company.library.scenario.ScenarioTabValidator.assertSearchAnyResultsOnScenarioTab;
import static com.practis.web.selenide.validator.company.library.scenario.ScenarioTabValidator.assertSearchResultsOnScenarioTab;

import com.practis.rest.dto.company.library.RestScenarioResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.ScenarioExtension;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class ScenarioTest {

    @BeforeEach
    void init() {
        navigationCompany().getLibraryNavigationItem().click();
        libraryPage().getScenariosTab().click();
    }

    /** View Library: Scenario tab: default view */
    @TestRailTest(caseId = 31846)
    @DisplayName("Library: Scenario Tab: Check Elements")
    @ScenarioExtension(count = 1)
    void checkElementsScenarioTab() {
        assertElementsOnLibraryScenarioTab();
    }

    /** View Library: Scenario tab: Empty State */
    @TestRailTest(caseId = 31847)
    @DisplayName("Library: Scenario Tab: Check Elements: Empty")
    void checkElementsChallengeTabEmptyState() {
        assertEmptyStateLibraryScenarioTab();
    }

    /** View Library: Scenario tab: Search */
    @TestRailTest(caseId = 31848)
    @DisplayName("Library: Scenario Tab: Search")
    @ScenarioExtension(count = 1)
    void searchOnChallengeTab(List<RestScenarioResponse> scenario) {
        // Assert Search Field
        assertSearchField();

        // Assert no Search results
        searchService().searchPerform("no results");
        assertNoSearchResultOnScenarioTab();

        // Assert Search by Practis Set Name
        searchService().clearSearch();
        searchService().searchPerform(scenario.get(0).getTitle());
        assertSearchResultsOnScenarioTab(scenario.get(0).getTitle());

        // Search should be performed after entering 1 character
        searchService().clearSearch();
        searchAfter1Char(scenario.get(0).getTitle());
        assertSearchAnyResultsOnScenarioTab();

        // Assert Clear Search
        assertCleanSearch();
    }
}
