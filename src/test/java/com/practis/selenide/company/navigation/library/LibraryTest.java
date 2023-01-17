package com.practis.selenide.company.navigation.library;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.libraryTabs;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.PageObjectFactory.libraryPage;
import static com.practis.web.selenide.validator.company.LibraryValidator.asserFiltersModal;
import static com.practis.web.selenide.validator.company.LibraryValidator.assertElementsOnLibraryChallengesPage;
import static com.practis.web.selenide.validator.company.LibraryValidator.assertElementsOnLibraryScenariosPage;
import static com.practis.web.selenide.validator.company.LibraryValidator.assertEmptyStateLibraryPractisSetsTab;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class LibraryTest {

    @TestRailTest(caseId = 9328)
    @DisplayName("Company: Navigation: Library: Check Elements: Empty State")
    void assertElementsOnLibraryScreen() {
        // Open 'Library' page
        navigationCompany().getLibraryNavigationItem().click();
        assertEmptyStateLibraryPractisSetsTab();

        // Open filter and check elements
        libraryPage().getFiltersButton().click();
        asserFiltersModal();

        libraryTabs().getScenarioLibraryTab().click();
        assertElementsOnLibraryScenariosPage();
        libraryPage().getFiltersButton().click();
        asserFiltersModal();

        libraryTabs().getChallengesLibraryTab().click();
        assertElementsOnLibraryChallengesPage();
        libraryPage().getFiltersButton().click();
        asserFiltersModal();
    }
}
