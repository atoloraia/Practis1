package com.practis.web.selenide.validator.company;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
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
    libraryTabs().getPractisSetLibraryTab().shouldBe(attribute("aria-current", "page"));
    libraryTabs().getScenarioLibraryTab().shouldBe(exactText("Scenarios"));
    libraryTabs().getChallengesLibraryTab().shouldBe(exactText("Challenges"));

    libraryPage().getTimestampText().shouldBe(visible);
    libraryPage().getTimestampText().shouldBe(matchText("Updated"));
    libraryPage().getLibraryTitle().shouldBe(visible);
    libraryTabs().getPractisSetLibraryTab().shouldBe(visible);
    libraryTabs().getScenarioLibraryTab().shouldBe(visible);
    libraryTabs().getChallengesLibraryTab().shouldBe(visible);
    libraryPage().getTimestampRefreshButton().shouldBe(visible);

    libraryPage().getSearchField().shouldBe(visible);
    libraryPage().getSearchFieldIcon().shouldBe(visible);
    libraryPage().getFiltersButton().shouldBe(visible);
    libraryPage().getFiltersCounter().shouldBe(visible);
    libraryPage().getPaginationPrevButton().shouldBe(visible);
    libraryPage().getPaginationPrevButton().shouldBe(disabled);
    libraryPage().getPaginationPrevButton().shouldBe(attribute("type", "submit"));
    libraryPage().getPaginationNextButton().shouldBe(visible);

    libraryPage().getSelectAllCheckbox().shouldBe(visible);
    libraryPage().getPractisSetsColumn().shouldBe(visible);
    libraryPage().getPractisSetsColumn().shouldBe(exactText("Practis Sets"));
    libraryPage().getPractisSetsColumn().shouldBe(attribute("width", "25"));

    libraryPage().getPractisSetsStatusColumn().shouldBe(visible);
    libraryPage().getPractisSetsStatusColumn().shouldBe(exactText("Status"));
    libraryPage().getPractisSetsStatusColumn().shouldBe(attribute("width", "11"));

    libraryPage().getContentColumn().shouldBe(visible);
    libraryPage().getContentColumn().shouldBe(exactText("Content"));
    libraryPage().getContentColumn().shouldBe(attribute("width", "11"));

    libraryPage().getPractisSetsLastUpdatedColumn().shouldBe(visible);
    libraryPage().getPractisSetsLastUpdatedColumn().shouldBe(exactText("Last Updated"));
    libraryPage().getPractisSetsLastUpdatedColumn().shouldBe(attribute("width", "10"));

    libraryPage().getEmptyStateIcon().shouldBe(visible);
    libraryPage().getEmptyStateText().shouldBe(exactText("No Results Match the Filter Criteria"));
    libraryPage().getEmptyStateText().shouldBe(visible);
    libraryPage().getEmptyStateText().shouldBe(attribute("width", "169px"));
  }


  /**
   * Assert elements on Library - Scenarios page.
   */
  public static void assertElementsOnLibraryScenariosPage() {
    libraryPage().getLibraryTitle().shouldBe(exactText("Library"));
    libraryTabs().getPractisSetLibraryTab().shouldBe(exactText("Practis Sets"));
    libraryTabs().getScenarioLibraryTab().shouldBe(exactText("Scenarios"));
    libraryTabs().getScenarioLibraryTab().shouldBe(attribute("aria-current", "page"));
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
    libraryPage().getPaginationPrevButton().shouldBe(disabled);
    libraryPage().getPaginationPrevButton().shouldBe(attribute("type", "submit"));
    libraryPage().getPaginationNextButton().shouldBe(visible);

    libraryPage().getSelectAllCheckbox().shouldBe(visible);

    libraryPage().getScenariosColumn().shouldBe(visible);
    libraryPage().getScenariosColumn().shouldBe(exactText("Scenarios"));
    libraryPage().getScenariosColumn().shouldBe(attribute("width", "28"));

    libraryPage().getScenariosStatusColumn().shouldBe(visible);
    libraryPage().getScenariosStatusColumn().shouldBe(exactText("Status"));
    libraryPage().getScenariosStatusColumn().shouldBe(attribute("width", "17"));

    libraryPage().getScenariosDurationColumn().shouldBe(visible);
    libraryPage().getScenariosDurationColumn().shouldBe(exactText("Duration"));
    libraryPage().getScenariosDurationColumn().shouldBe(attribute("width", "17"));

    libraryPage().getScenariosLastUpdatedColumn().shouldBe(visible);
    libraryPage().getScenariosLastUpdatedColumn().shouldBe(exactText("Last Updated"));
    libraryPage().getScenariosLastUpdatedColumn().shouldBe(attribute("width", "17"));

    libraryPage().getEmptyStateIcon().shouldBe(visible);
    libraryPage().getEmptyStateText().shouldBe(exactText("No Results Match the Filter Criteria"));
    libraryPage().getEmptyStateText().shouldBe(visible);
    libraryPage().getEmptyStateText().shouldBe(attribute("width", "169px"));

  }

  /**
   * Assert elements on Library - Challenges page.
   */
  public static void assertElementsOnLibraryChallengesPage() {
    libraryPage().getLibraryTitle().shouldBe(exactText("Library"));
    libraryTabs().getPractisSetLibraryTab().shouldBe(exactText("Practis Sets"));
    libraryTabs().getScenarioLibraryTab().shouldBe(exactText("Scenarios"));
    libraryTabs().getChallengesLibraryTab().shouldBe(exactText("Challenges"));
    libraryTabs().getChallengesLibraryTab().shouldBe(attribute("aria-current", "page"));
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
    libraryPage().getPaginationPrevButton().shouldBe(disabled);
    libraryPage().getPaginationPrevButton().shouldBe(attribute("type", "submit"));
    libraryPage().getPaginationNextButton().shouldBe(visible);

    libraryPage().getSelectAllCheckbox().shouldBe(visible);

    libraryPage().getChallengesColumn().shouldBe(visible);
    libraryPage().getChallengesColumn().shouldBe(exactText("Challenges"));
    libraryPage().getChallengesColumn().shouldBe(attribute("width", "25"));

    libraryPage().getChallengesStatusColumn().shouldBe(visible);
    libraryPage().getChallengesStatusColumn().shouldBe(exactText("Status"));
    libraryPage().getChallengesStatusColumn().shouldBe(attribute("width", "20"));

    libraryPage().getChallengesLastUpdatedColumn().shouldBe(visible);
    libraryPage().getChallengesLastUpdatedColumn().shouldBe(exactText("Last Updated"));
    libraryPage().getChallengesLastUpdatedColumn().shouldBe(attribute("width", "20"));

    libraryPage().getEmptyStateIcon().shouldBe(visible);
    libraryPage().getEmptyStateText().shouldBe(exactText("No Results Match the Filter Criteria"));
    libraryPage().getEmptyStateText().shouldBe(visible);
    libraryPage().getEmptyStateText().shouldBe(attribute("width", "169px"));
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