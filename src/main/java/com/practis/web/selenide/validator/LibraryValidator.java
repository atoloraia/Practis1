package com.practis.web.selenide.validator;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.libraryTabs;
import static com.practis.web.selenide.configuration.PageObjectFactory.libraryPage;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertEmptyLabelModel;
import static com.practis.web.selenide.validator.selection.StatusSelectionValidator.assertStatusModule;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LibraryValidator {

  /**
   * Assert elements on Library - Practis Sets page.
   */
  public static void assertElementsOnLibraryPractisSetsPage() {
    libraryPage().getLibraryTitle().shouldBe(exactText("Library"));
    libraryTabs().getPractisSetLibraryTab().shouldBe(exactText("Practis Sets"));
    libraryTabs().getScenarioLibraryTab().shouldBe(exactText("Scenarios"));
    libraryTabs().getChallengesLibraryTab().shouldBe(exactText("Challenges"));
    libraryPage().getTimestampText().shouldBe(matchText("Updated"));
    libraryPage().getLibraryTitle().shouldBe(visible);
    libraryTabs().getPractisSetLibraryTab().shouldBe(visible);
    libraryTabs().getScenarioLibraryTab().shouldBe(visible);
    libraryTabs().getChallengesLibraryTab().shouldBe(visible);
    libraryPage().getTimestampText().shouldBe(visible);
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
    libraryPage().getPractisSetsColumn().shouldBe(visible);
    libraryPage().getPractisSetsStatusColumn().shouldBe(visible);
    libraryPage().getContentColumn().shouldBe(visible);
    libraryPage().getPractisSetsLastUpdatedColumn().shouldBe(visible);

    libraryPage().getEmptyStateIcon().shouldBe(visible);
    libraryPage().getEmptyStateText().shouldBe(exactText("No Results Match the Filter Criteria"));
    libraryPage().getEmptyStateText().shouldBe(visible);
  }


  /**
   * Assert elements on Library - Scenarios page.
   */
  public static void assertElementsOnLibraryScenariosPage() {
    libraryPage().getLibraryTitle().shouldBe(exactText("Library"));
    libraryTabs().getPractisSetLibraryTab().shouldBe(exactText("Practis Sets"));
    libraryTabs().getScenarioLibraryTab().shouldBe(exactText("Scenarios"));
    libraryTabs().getChallengesLibraryTab().shouldBe(exactText("Challenges"));
    libraryPage().getTimestampText().shouldBe(matchText("Updated"));
    libraryPage().getLibraryTitle().shouldBe(visible);
    libraryTabs().getPractisSetLibraryTab().shouldBe(visible);
    libraryTabs().getScenarioLibraryTab().shouldBe(visible);
    libraryTabs().getChallengesLibraryTab().shouldBe(visible);
    libraryPage().getTimestampText().shouldBe(visible);
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
    libraryPage().getScenariosColumn().shouldBe(visible);
    libraryPage().getScenariosStatusColumn().shouldBe(visible);
    libraryPage().getScenariosDurationColumn().shouldBe(visible);
    libraryPage().getScenariosLastUpdatedColumn().shouldBe(visible);

    libraryPage().getEmptyStateIcon().shouldBe(visible);
    libraryPage().getEmptyStateText().shouldBe(exactText("No Results Match the Filter Criteria"));
    libraryPage().getEmptyStateText().shouldBe(visible);


  }

  /**
   * Assert elements on Library - Challenges page.
   */
  public static void assertElementsOnLibraryChallengesPage() {
    libraryPage().getLibraryTitle().shouldBe(exactText("Library"));
    libraryTabs().getPractisSetLibraryTab().shouldBe(exactText("Practis Sets"));
    libraryTabs().getScenarioLibraryTab().shouldBe(exactText("Scenarios"));
    libraryTabs().getChallengesLibraryTab().shouldBe(exactText("Challenges"));
    libraryPage().getTimestampText().shouldBe(matchText("Updated"));
    libraryPage().getLibraryTitle().shouldBe(visible);
    libraryTabs().getPractisSetLibraryTab().shouldBe(visible);
    libraryTabs().getScenarioLibraryTab().shouldBe(visible);
    libraryTabs().getChallengesLibraryTab().shouldBe(visible);
    libraryPage().getTimestampText().shouldBe(visible);
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
    libraryPage().getChallengesColumn().shouldBe(visible);
    libraryPage().getChallengesStatusColumn().shouldBe(visible);
    libraryPage().getChallengesLastUpdatedColumn().shouldBe(visible);

    libraryPage().getEmptyStateIcon().shouldBe(visible);
    libraryPage().getEmptyStateText().shouldBe(exactText("No Results Match the Filter Criteria"));
    libraryPage().getEmptyStateText().shouldBe(visible);
  }

  /**
   * Assert elements on Library - Filters modal.
   */

  public static void assertElementsOnFiltersModal() {
    libraryPage().getFiltersButton().click();
    assertStatusModule();
    assertEmptyLabelModel();
    libraryPage().getFiltersApplyButton().click();
  }


}
