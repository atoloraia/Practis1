package com.practis.selenide.company.navigation.library.challenge;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.PageObjectFactory.libraryPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.searchService;
import static com.practis.web.selenide.service.SearchService.searchAfter1Char;
import static com.practis.web.selenide.validator.common.SearchValidator.assertCleanSearch;
import static com.practis.web.selenide.validator.common.SearchValidator.assertSearchField;
import static com.practis.web.selenide.validator.company.library.challenge.ChallengeTabValidator.assertElementsOnLibraryChallengeTab;
import static com.practis.web.selenide.validator.company.library.challenge.ChallengeTabValidator.assertEmptyStateLibraryChallengesTab;
import static com.practis.web.selenide.validator.company.library.challenge.ChallengeTabValidator.assertNoSearchResultOnChallengeTab;
import static com.practis.web.selenide.validator.company.library.challenge.ChallengeTabValidator.assertSearchAnyResultsOnChallengeTab;
import static com.practis.web.selenide.validator.company.library.challenge.ChallengeTabValidator.assertSearchResultsOnChallengeTab;

import com.practis.rest.dto.company.library.RestChallengeResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.ChallengeExtension;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class ChallengeTest {

    @BeforeEach
    void init() {
        navigationCompany().getLibraryNavigationItem().click();
        libraryPage().getChallengesTab().click();
    }

    /** View Library: Challenge tab: default view */
    @TestRailTest(caseId = 1940)
    @DisplayName("Library: Challenges Tab: Check Elements")
    @ChallengeExtension(count = 1)
    void checkElementsChallengeTab() {
        assertElementsOnLibraryChallengeTab();
    }

    /** View Library: Challenge tab: Empty State */
    @TestRailTest(caseId = 1941)
    @DisplayName("Library: Challenge Tab: Check Elements: Empty")
    void checkElementsChallengeTabEmptyState() {
        assertEmptyStateLibraryChallengesTab();
    }

    /** View Library: Challenge tab: Search */
    @TestRailTest(caseId = 31699)
    @DisplayName("Library: Challenges Tab: Search")
    @ChallengeExtension(count = 1)
    void searchOnChallengeTab(List<RestChallengeResponse> challenges) {
        // Assert Search Field
        assertSearchField();

        // Assert no Search results
        searchService().searchPerform("no results");
        assertNoSearchResultOnChallengeTab();

        // Assert Search by Challenge Name
        searchService().clearSearch();
        searchService().searchPerform(challenges.get(0).getTitle());
        assertSearchResultsOnChallengeTab(challenges.get(0).getTitle());

        // Search should be performed after entering 1 character
        searchService().clearSearch();
        searchAfter1Char(challenges.get(0).getTitle());
        assertSearchAnyResultsOnChallengeTab();

        // Assert Clear Search
        assertCleanSearch();
    }
}
