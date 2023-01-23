package com.practis.web.selenide.validator.company.library;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.libraryTabs;
import static com.practis.web.selenide.configuration.PageObjectFactory.libraryPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetTab;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertEmptyLabelModel;
import static com.practis.web.selenide.validator.selection.LibraryFilterStatusValidator.assertLibraryFilterStatusModule;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LibraryValidator {

    /** Assert elements on Library - Practis Sets page: Empty State. */
    public static void assertEmptyStateLibraryPractisSetsTab() {
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
        libraryPage().getSearchField().parent().$("label").shouldHave(cssClass("is-disabled"));
        libraryPage().getSearchFieldIcon().shouldBe(visible);
        libraryPage().getFiltersButton().shouldBe(visible);
        libraryPage().getFiltersButton().shouldBe(enabled);
        libraryPage().getFiltersCounter().shouldBe(visible);
        libraryPage().getPaginationPrevButton().shouldBe(visible);
        libraryPage().getPaginationPrevButton().shouldBe(disabled);
        libraryPage().getPaginationPrevButton().shouldBe(attribute("type", "submit"));
        libraryPage().getPaginationNextButton().shouldBe(visible);
        libraryPage().getPaginationNextButton().shouldBe(disabled);

        practisSetTab().getSelectAllCheckbox().shouldBe(visible);
        practisSetTab().getPractisSetsColumn().shouldBe(visible);
        practisSetTab().getPractisSetsColumn().shouldBe(exactText("Practis Sets"));
        practisSetTab().getPractisSetsColumn().shouldBe(attribute("width", "25"));

        practisSetTab().getPractisSetsStatusColumn().shouldBe(visible);
        practisSetTab().getPractisSetsStatusColumn().shouldBe(exactText("Status"));
        practisSetTab().getPractisSetsStatusColumn().shouldBe(attribute("width", "11"));

        practisSetTab().getContentColumn().shouldBe(visible);
        practisSetTab().getContentColumn().shouldBe(exactText("Content"));
        practisSetTab().getContentColumn().shouldBe(attribute("width", "11"));

        practisSetTab().getPractisSetsLastUpdatedColumn().shouldBe(visible);
        practisSetTab().getPractisSetsLastUpdatedColumn().shouldBe(exactText("Last Updated"));
        practisSetTab().getPractisSetsLastUpdatedColumn().shouldBe(attribute("width", "10"));

        practisSetTab().getEmptyIconPsTab().shouldBe(visible);
        practisSetTab()
                .getEmptyTextPsTab()
                .shouldBe(exactText("No Results Match the Filter Criteria"));
        practisSetTab().getEmptyTextPsTab().shouldBe(visible);
        practisSetTab().getEmptyTextPsTab().shouldBe(attribute("width", "169px"));
    }

    /** Assert elements on Library - Practis Sets tab: Default View */
    public static void assertElementsOnLibraryPractisSetsTab() {
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

        practisSetTab().getSelectAllCheckbox().shouldBe(visible);
        practisSetTab().getPractisSetsColumn().shouldBe(visible);
        practisSetTab().getPractisSetsColumn().shouldBe(exactText("Practis Sets"));
        practisSetTab().getPractisSetsColumn().shouldBe(attribute("width", "25"));

        practisSetTab().getPractisSetsStatusColumn().shouldBe(visible);
        practisSetTab().getPractisSetsStatusColumn().shouldBe(exactText("Status"));
        practisSetTab().getPractisSetsStatusColumn().shouldBe(attribute("width", "11"));

        practisSetTab().getContentColumn().shouldBe(visible);
        practisSetTab().getContentColumn().shouldBe(exactText("Content"));
        practisSetTab().getContentColumn().shouldBe(attribute("width", "11"));

        practisSetTab().getPractisSetsLastUpdatedColumn().shouldBe(visible);
        practisSetTab().getPractisSetsLastUpdatedColumn().shouldBe(exactText("Last Updated"));
        practisSetTab().getPractisSetsLastUpdatedColumn().shouldBe(attribute("width", "10"));
    }

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
