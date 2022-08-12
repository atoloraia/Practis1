package com.practis.selenide.company.navigation.library;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.libraryTabs;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.validator.company.LibraryValidator.assertElementsOnFiltersModal;
import static com.practis.web.selenide.validator.company.LibraryValidator.assertElementsOnLibraryChallengesPage;
import static com.practis.web.selenide.validator.company.LibraryValidator.assertElementsOnLibraryPractisSetsPage;
import static com.practis.web.selenide.validator.company.LibraryValidator.assertElementsOnLibraryScenariosPage;

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
  @DisplayName("Check WEB Elements 'Library' page")
  void assertElementsOnLibraryScreen() {
    navigationCompanies().getLibraryNavigationItem().click();
    assertElementsOnLibraryPractisSetsPage();
    assertElementsOnFiltersModal();

    libraryTabs().getScenarioLibraryTab().click();
    assertElementsOnLibraryScenariosPage();
    assertElementsOnFiltersModal();

    libraryTabs().getChallengesLibraryTab().click();
    assertElementsOnLibraryChallengesPage();
    assertElementsOnFiltersModal();

  }

}
