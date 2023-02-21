package com.practis.web.selenide.validator.company.library;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.libraryTabs;
import static com.practis.web.selenide.configuration.PageObjectFactory.libraryPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.scenarioTab;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertEmptyLabelModel;
import static com.practis.web.selenide.validator.selection.LibraryFilterStatusValidator.assertLibraryFilterStatusModule;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LibraryValidator {

    /** Assert elements on Library - Scenarios page. */
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

        practisSetTab().getSelectAllCheckbox().shouldBe(visible);

        scenarioTab().getScenariosColumn().shouldBe(visible);
        scenarioTab().getScenariosColumn().shouldBe(exactText("Scenarios"));
        scenarioTab().getScenariosColumn().shouldBe(attribute("width", "28"));

        scenarioTab().getScenariosStatusColumn().shouldBe(visible);
        scenarioTab().getScenariosStatusColumn().shouldBe(exactText("Status"));
        scenarioTab().getScenariosStatusColumn().shouldBe(attribute("width", "17"));

        scenarioTab().getScenariosDurationColumn().shouldBe(visible);
        scenarioTab().getScenariosDurationColumn().shouldBe(exactText("Duration"));
        scenarioTab().getScenariosDurationColumn().shouldBe(attribute("width", "17"));

        scenarioTab().getScenariosLastUpdatedColumn().shouldBe(visible);
        scenarioTab().getScenariosLastUpdatedColumn().shouldBe(exactText("Last Updated"));
        scenarioTab().getScenariosLastUpdatedColumn().shouldBe(attribute("width", "17"));

        libraryPage().getEmptyIconScenarioTab().shouldBe(visible);
        libraryPage()
                .getEmptyTextScenarioTab()
                .shouldBe(exactText("No Results Match the Filter Criteria"));
        libraryPage().getEmptyTextScenarioTab().shouldBe(visible);
        libraryPage().getEmptyTextScenarioTab().shouldBe(attribute("width", "169px"));
    }

    /** Assert elements on Library - Challenges page. */
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

        practisSetTab().getSelectAllCheckbox().shouldBe(visible);

        libraryPage().getChallengesColumn().shouldBe(visible);
        libraryPage().getChallengesColumn().shouldBe(exactText("Challenges"));
        libraryPage().getChallengesColumn().shouldBe(attribute("width", "25"));

        libraryPage().getChallengesStatusColumn().shouldBe(visible);
        libraryPage().getChallengesStatusColumn().shouldBe(exactText("Status"));
        libraryPage().getChallengesStatusColumn().shouldBe(attribute("width", "20"));

        libraryPage().getChallengesLastUpdatedColumn().shouldBe(visible);
        libraryPage().getChallengesLastUpdatedColumn().shouldBe(exactText("Last Updated"));
        libraryPage().getChallengesLastUpdatedColumn().shouldBe(attribute("width", "20"));

        libraryPage().getEmptyIconChallengeTab().shouldBe(visible);
        libraryPage()
                .getEmptyTextChallengeTab()
                .shouldBe(exactText("No Results Match the Filter Criteria"));
        libraryPage().getEmptyTextChallengeTab().shouldBe(visible);
        libraryPage().getEmptyTextChallengeTab().shouldBe(attribute("width", "169px"));
    }

    /** Assert elements on Library - Filters modal. */
    public static void asserFiltersModal() {
        assertLibraryFilterStatusModule();
        assertEmptyLabelModel();
        libraryPage().getFiltersSelectedCounterText().shouldBe(visible);
        libraryPage().getFiltersSelectedCounterText().shouldBe(matchText("2 Selected"));
        libraryPage().getFiltersApplyButton().shouldBe(visible);
        libraryPage().getFiltersApplyButton().shouldBe(exactText("Apply Filter"));
        libraryPage().getFiltersClearButton().shouldBe(visible);
        libraryPage().getFiltersClearButton().shouldBe(exactText("Clear"));
        libraryPage().getFiltersApplyButton().click();
    }
}
