package com.practis.selenide.company;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.libraryTabs;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.PageObjectFactory.libraryPage;
import static com.practis.web.selenide.validator.LibraryValidator.assertElementsOnFiltersModal;
import static com.practis.web.selenide.validator.LibraryValidator.assertElementsOnLibraryChallengesPage;
import static com.practis.web.selenide.validator.LibraryValidator.assertElementsOnLibraryPractisSetsPage;
import static com.practis.web.selenide.validator.LibraryValidator.assertElementsOnLibraryScenariosPage;
import static com.practis.web.selenide.validator.selection.StatusSelectionValidator.assertStatusModule;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class LibraryTest {

  @Test
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
