package com.practis.web.selenide.validator;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.libraryPage;

import com.practis.web.selenide.validator.selection.LabelSelectionValidator;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LibraryValidator {

  /**
   * Assert elements on Library - Practis Sets page.
   */
  public static void assertElementsOnLibraryPractisSetsPage() {
    libraryPage().getLibraryTitle().shouldBe(exactText("Library"));
    libraryPage().getPractisSetsTab().shouldBe(exactText("Practis Sets"));
    libraryPage().getScenariosTab().shouldBe(exactText("Scenarios"));
    libraryPage().getChallengesTab().shouldBe(exactText("Challenges"));
    libraryPage().getTimestampText().shouldBe(matchText("Updated"));
    libraryPage().getTimestampRefreshButton().shouldBe(visible);

    libraryPage().getSearchField().shouldBe(visible);
    libraryPage().getSearchFieldIcon().shouldBe(visible);
    libraryPage().getFiltersButton().shouldBe(visible);
    libraryPage().getFiltersCounter().shouldBe(visible);
    libraryPage().getPaginationPrevButton().shouldBe(visible);
    libraryPage().getPaginationNextButton().shouldBe(visible);

    libraryPage().getSelectAllCheckbox().shouldBe(visible);
    libraryPage().getPractisSetsColumn().shouldBe(exactText("Practis Sets"));
    libraryPage().getPractisSetsStatusColumn().shouldBe(exactText("Status"));
    libraryPage().getContentColumn().shouldBe(exactText("Content"));
    libraryPage().getPractisSetsLastUpdatedColumn().shouldBe(exactText("Last Updated"));

    libraryPage().getEmptyStateIcon().shouldBe(visible);
    libraryPage().getEmptyStateText().shouldBe(exactText("No Results Match the Filter Criteria"));
  }


  /**
   * Assert elements on Library - Scenarios page.
   */
  public static void assertElementsOnLibraryScenariosPage() {
    libraryPage().getLibraryTitle().shouldBe(exactText("Library"));
    libraryPage().getPractisSetsTab().shouldBe(exactText("Practis Sets"));
    libraryPage().getScenariosTab().shouldBe(exactText("Scenarios"));
    libraryPage().getChallengesTab().shouldBe(exactText("Challenges"));
    libraryPage().getTimestampText().shouldBe(matchText("Updated"));
    libraryPage().getTimestampRefreshButton().shouldBe(visible);

    libraryPage().getSearchField().shouldBe(visible);
    libraryPage().getSearchFieldIcon().shouldBe(visible);
    libraryPage().getFiltersButton().shouldBe(visible);
    libraryPage().getFiltersCounter().shouldBe(visible);
    libraryPage().getPaginationPrevButton().shouldBe(visible);
    libraryPage().getPaginationNextButton().shouldBe(visible);

    libraryPage().getSelectAllCheckbox().shouldBe(visible);
    libraryPage().getScenariosColumn().shouldBe(exactText("Scenarios"));
    libraryPage().getScenariosStatusColumn().shouldBe(exactText("Status"));
    libraryPage().getScenariosDurationColumn().shouldBe(exactText("Duration"));
    libraryPage().getScenariosLastUpdatedColumn().shouldBe(exactText("Last Updated"));

    libraryPage().getEmptyStateIcon().shouldBe(visible);
    libraryPage().getEmptyStateText().shouldBe(exactText("No Results Match the Filter Criteria"));


  }

  /**
   * Assert elements on Library - Challenges page.
   */
  public static void assertElementsOnLibraryChallengesPage() {
    libraryPage().getLibraryTitle().shouldBe(exactText("Library"));
    libraryPage().getPractisSetsTab().shouldBe(exactText("Practis Sets"));
    libraryPage().getScenariosTab().shouldBe(exactText("Scenarios"));
    libraryPage().getChallengesTab().shouldBe(exactText("Challenges"));
    libraryPage().getTimestampText().shouldBe(matchText("Updated"));
    libraryPage().getTimestampRefreshButton().shouldBe(visible);

    libraryPage().getSearchField().shouldBe(visible);
    libraryPage().getSearchFieldIcon().shouldBe(visible);
    libraryPage().getFiltersButton().shouldBe(visible);
    libraryPage().getFiltersCounter().shouldBe(visible);
    libraryPage().getPaginationPrevButton().shouldBe(visible);
    libraryPage().getPaginationNextButton().shouldBe(visible);

    libraryPage().getSelectAllCheckbox().shouldBe(visible);
    libraryPage().getChallengesColumn().shouldBe(exactText("Challenges"));
    libraryPage().getChallengesStatusColumn().shouldBe(exactText("Status"));
    libraryPage().getChallengesLastUpdatedColumn().shouldBe(exactText("Last Updated"));

    libraryPage().getEmptyStateIcon().shouldBe(visible);
    libraryPage().getEmptyStateText().shouldBe(exactText("No Results Match the Filter Criteria"));


  }
}
