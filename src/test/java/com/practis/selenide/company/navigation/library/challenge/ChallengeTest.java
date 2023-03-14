package com.practis.selenide.company.navigation.library.challenge;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.PageObjectFactory.libraryPage;
import static com.practis.web.selenide.validator.company.library.challenge.ChallengeTabValidator.assertElementsOnLibraryChallengeTab;
import static com.practis.web.selenide.validator.company.library.challenge.ChallengeTabValidator.assertEmptyStateLibraryChallengesTab;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.ChallengeExtension;
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
}
