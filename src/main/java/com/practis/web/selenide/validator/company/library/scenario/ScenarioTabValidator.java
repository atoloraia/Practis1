package com.practis.web.selenide.validator.company.library.scenario;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.libraryTabs;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.libraryPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.scenarioTab;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.scenarioTabService;
import static com.practis.web.selenide.validator.common.SearchValidator.assertNoSearchResult;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;

public class ScenarioTabValidator {

    /** Assert elements on Library - Scenario tab: Default View */
    public static void assertElementsOnLibraryScenarioTab() {
        libraryPage().getLibraryTitle().shouldBe(exactText("Library"));
        libraryTabs().getPractisSetLibraryTab().shouldBe(exactText("Practis Sets"));
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

        scenarioTab().getScenariosColumn().shouldBe(visible);
        scenarioTab().getScenariosColumn().shouldBe(exactText("Scenarios"));

        scenarioTab().getScenariosStatusColumn().shouldBe(visible);
        scenarioTab().getScenariosStatusColumn().shouldBe(exactText("Status"));

        scenarioTab().getScenariosDurationColumn().shouldBe(visible);
        scenarioTab().getScenariosDurationColumn().shouldBe(exactText("Duration"));

        scenarioTab().getScenariosLastUpdatedColumn().shouldBe(visible);
        scenarioTab().getScenariosLastUpdatedColumn().shouldBe(exactText("Last Updated"));
    }

    /** Assert elements on Library - Scenario tab: Empty State. */
    public static void assertEmptyStateLibraryScenarioTab() {
        assertElementsOnLibraryScenarioTab();
        assertEmptyScenarioTab();
    }

    /** Assert elements on Library - Scenario page: Empty State: No results for filter */
    public static void assertEmptyScenarioTab() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        scenarioTab().getNoResultMatchFilterCriteriaIcon().shouldBe(visible);
        scenarioTab()
                .getNoResultMatchFilterCriteriaText()
                .shouldBe(exactText("No Results Match the Filter Criteria"));
        scenarioTab().getNoResultMatchFilterCriteriaText().shouldBe(visible);
        scenarioTab().getNoResultMatchFilterCriteriaText().shouldBe(attribute("width", "169px"));
    }

    /** Assert Label counter. */
    public static void assertLabelCountOnScenarioPage(final String practisSet, final String count) {
        scenarioTabService().findScenarioLabelCounter(practisSet).shouldBe(visible);
        scenarioTabService().findScenarioLabelCounter(practisSet).shouldBe(exactText(count));
    }

    /** Assert single action for the Scenario. */
    public static void assertSingleActionScenarioNoLabels() {
        scenarioTab().getViewSingleAction().shouldBe(visible);
        scenarioTab().getViewSingleAction().shouldBe(exactText("View"));
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
        scenarioTab().getViewSingleAction().shouldBe(visible);
        scenarioTab().getViewSingleAction().shouldBe(exactText("View"));
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

    /** Assert no search results. */
    public static void assertNoSearchResultOnScenarioTab() {
        assertNoSearchResult();
        scenarioTab().getNoSearchResultText().shouldBe(matchText("No Scenarios Found"));
        scenarioTab().getNoSearchResultIcon().shouldBe(visible);
        scenarioTab().getScenarioRow().shouldBe(CollectionCondition.size(0));
    }

    /** Assert Search Results. */
    public static void assertSearchResultsOnScenarioTab(String input) {
        search().getSearchField().shouldBe(visible);
        scenarioTab().getScenarioRow().shouldBe(CollectionCondition.size(1));
        final var scenarioSetRow = scenarioTab().getScenarioRow().find(Condition.matchText(input));
        scenarioSetRow.shouldBe(visible);
    }

    /** Assert Search any Results. */
    public static void assertSearchAnyResultsOnScenarioTab() {
        search().getSearchFieldClearButton().shouldBe(visible);
        scenarioTab().getScenarioRow().get(0).shouldBe(visible);
    }
}
