package com.practis.web.selenide.validator.company.library.scenario;

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
import static com.practis.web.selenide.configuration.PageObjectFactory.scenarioTab;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.scenarioTabService;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.codeborne.selenide.CollectionCondition;

public class ScenarioTabValidator {

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

        practisSetTab().getNoResultMatchFilterCriteriaIcon().shouldBe(visible);
        practisSetTab()
                .getNoResultMatchFilterCriteriaText()
                .shouldBe(exactText("No Results Match the Filter Criteria"));
        practisSetTab().getNoResultMatchFilterCriteriaText().shouldBe(visible);
        practisSetTab().getNoResultMatchFilterCriteriaText().shouldBe(attribute("width", "169px"));
    }

    /** Assert elements on Library - Scenario page: Empty State: No results for filter */
    public static void assertEmptyScenarioTab() {
        scenarioTab().getNoResultMatchFilterCriteriaIcon().shouldBe(visible);
        scenarioTab()
                .getNoResultMatchFilterCriteriaText()
                .shouldBe(exactText("No Results Match the Filter Criteria"));
        scenarioTab().getNoResultMatchFilterCriteriaText().shouldBe(visible);
        scenarioTab().getNoResultMatchFilterCriteriaText().shouldBe(attribute("width", "169px"));
    }

    /** Assert elements on Library - Scenario page: Empty State: No Scenario Yet */
    public static void assertNoScenarioYetTab() {
        scenarioTab().getNoScenarioYetIcon().shouldBe(visible);
        scenarioTab().getNoScenarioYetText().shouldBe(exactText("No Scenarios Yet"));
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

    /** Assert Action button on Scenario Page. */
    public static void assertDisabledAssignLabelsButton() {
        practisSetTab().getAssignLabelsBulkAction().shouldBe(visible);
        practisSetTab().getAssignLabelsBulkAction().shouldBe(enabled);
    }

    /** Assert Label counter. */
    public static void assertLabelCountOnScenarioPage(final String practisSet, final String count) {
        scenarioTabService().findScenarioLabelCounter(practisSet).shouldBe(visible);
        scenarioTabService().findScenarioLabelCounter(practisSet).shouldBe(exactText(count));
    }

    /** Assert single action for the Scenario. */
    public static void assertSingleActionScenarioNoLabels() {
        scenarioTab().getEditSingleAction().shouldBe(visible);
        scenarioTab().getEditSingleAction().shouldBe(exactText("Edit"));
        scenarioTab().getDuplicateSingleAction().shouldBe(visible);
        scenarioTab().getDuplicateSingleAction().shouldBe(exactText("Duplicate"));
        scenarioTab().getGenerateChallengeSingleAction().shouldBe(visible);
        scenarioTab().getGenerateChallengeSingleAction().shouldBe(exactText("Generate Challenge"));
        scenarioTab().getDownloadPDFSingleAction().shouldBe(visible);
        scenarioTab().getDownloadPDFSingleAction().shouldBe(exactText("Download as PDF"));
        scenarioTab().getArchiveSingleAction().shouldBe(visible);
        scenarioTab().getArchiveSingleAction().shouldBe(exactText("Archive"));
    }

    /** Assert single action for the Scenario. */
    public static void assertSingleActionScenario() {
        assertSingleActionScenarioNoLabels();
        scenarioTab().getAssignLabelsSingleAction().shouldBe(visible);
        scenarioTab().getAssignLabelsSingleAction().shouldBe(exactText("Assign Labels"));
    }

    /** Assert single action for the Archived Scenario. */
    public static void assertSingleActionArchivedScenario() {
        scenarioTab().getEditSingleAction().shouldBe(visible);
        scenarioTab().getEditSingleAction().shouldBe(exactText("Edit"));
        scenarioTab().getGenerateChallengeSingleAction().shouldBe(visible);
        scenarioTab().getGenerateChallengeSingleAction().shouldBe(exactText("Generate Challenge"));
        scenarioTab().getRestoreSingleAction().shouldBe(visible);
        scenarioTab().getRestoreSingleAction().shouldBe(exactText("Restore"));
        scenarioTab().getDeleteSingleAction().shouldBe(visible);
        scenarioTab().getDeleteSingleAction().shouldBe(exactText("Delete"));
    }

    /** Assert number of Scenario rows. */
    public static void assertScenariosRows(final Integer rows) {
        scenarioTab().getScenarioRow().shouldBe(CollectionCondition.size(rows));
    }

    /** Assert Status in the row. */
    public static void assertScenarioStatusRow(final String scenario, final String expectStatus) {
        String scenarioStatus = scenarioTabService().findStatus(scenario).getText();
        assertTrue(scenarioStatus.equals(expectStatus));
    }
}
