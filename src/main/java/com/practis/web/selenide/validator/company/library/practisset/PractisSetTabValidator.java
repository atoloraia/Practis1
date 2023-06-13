package com.practis.web.selenide.validator.company.library.practisset;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.libraryTabs;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.libraryPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetTab;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.practisSetTabService;
import static com.practis.web.selenide.validator.common.SearchValidator.assertNoSearchResult;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;

public class PractisSetTabValidator {

    /** Assert elements on Library - Practis Sets page: Empty State. */
    public static void assertEmptyStateLibraryPractisSetsTab() {
        assertElementsOnLibraryPractisSetsTab();
        assertEmptyPractisSetsTab();
    }

    /** Assert elements on Library - Practis Sets page: Empty State: No results for filter */
    public static void assertEmptyPractisSetsTab() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        practisSetTab().getNoResultMatchFilterCriteriaIcon().shouldBe(visible);
        practisSetTab()
                .getNoResultMatchFilterCriteriaText()
                .shouldBe(exactText("No Results Match the Filter Criteria"));
        practisSetTab().getNoResultMatchFilterCriteriaText().shouldBe(visible);
        practisSetTab().getNoResultMatchFilterCriteriaText().shouldBe(attribute("width", "169px"));
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

    /** Assert Action button on Practis Set Page. */
    public static void assertDisabledAssignLabelsButton() {
        practisSetTab().getAssignLabelsBulkAction().shouldBe(visible);
        practisSetTab().getAssignLabelsBulkAction().shouldBe(enabled);
    }

    /** Assert Label counter. */
    public static void assertLabelCountOnPsPage(final String practisSet, final String count) {
        practisSetTabService().findPsLabelCounter(practisSet).shouldBe(visible);
        practisSetTabService().findPsLabelCounter(practisSet).shouldBe(exactText(count));
    }

    /** Assert single action for the Practis Set. */
    public static void assertSingleActionPractisSetNoLabels() {
        practisSetTab().getViewSingleAction().shouldBe(visible);
        practisSetTab().getViewSingleAction().shouldBe(exactText("View"));
        practisSetTab().getAssignUsersSingleAction().shouldBe(visible);
        practisSetTab().getAssignUsersSingleAction().shouldBe(exactText("Assign Users"));
        practisSetTab().getDuplicateSingleAction().shouldBe(visible);
        practisSetTab().getDuplicateSingleAction().shouldBe(exactText("Duplicate"));
        practisSetTab().getArchiveSingleAction().shouldBe(visible);
        practisSetTab().getArchiveSingleAction().shouldBe(exactText("Archive"));
    }

    /** Assert single action for the Practis Set. */
    public static void assertSingleActionPractisSet() {
        practisSetTab().getViewSingleAction().shouldBe(visible);
        practisSetTab().getViewSingleAction().shouldBe(exactText("View"));
        practisSetTab().getAssignUsersSingleAction().shouldBe(visible);
        practisSetTab().getAssignUsersSingleAction().shouldBe(exactText("Assign Users"));
        practisSetTab().getAssignLabelsSingleAction().shouldBe(visible);
        practisSetTab().getAssignLabelsSingleAction().shouldBe(exactText("Assign Labels"));
        practisSetTab().getDuplicateSingleAction().shouldBe(visible);
        practisSetTab().getDuplicateSingleAction().shouldBe(exactText("Duplicate"));
        practisSetTab().getArchiveSingleAction().shouldBe(visible);
        practisSetTab().getArchiveSingleAction().shouldBe(exactText("Archive"));
    }

    /** Assert single action for the Archived Practis Set. */
    public static void assertSingleActionArchivedPs() {
        practisSetTab().getViewSingleAction().shouldBe(visible);
        practisSetTab().getViewSingleAction().shouldBe(exactText("View"));
        practisSetTab().getRestoreSingleAction().shouldBe(visible);
        practisSetTab().getRestoreSingleAction().shouldBe(exactText("Restore"));
        practisSetTab().getDeleteSingleAction().shouldBe(visible);
        practisSetTab().getDeleteSingleAction().shouldBe(exactText("Delete"));
    }

    /** Assert number of Practis Sets rows. */
    public static void assertPractisSetsRows(final Integer rows) {
        practisSetTab().getPractisSetRow().shouldBe(CollectionCondition.size(rows));
    }

    /** Assert Status in the row. */
    public static void assertPsStatusRow(final String team, final String status) {
        practisSetTabService().findStatus(team).shouldBe(visible);
        practisSetTabService().findStatus(team).shouldBe(exactText("Draft"));
    }

    /** Assert no search results. */
    public static void assertNoSearchResultOnPractisSetTab() {
        assertNoSearchResult();
        practisSetTab().getNoSearchResultText().shouldBe(matchText("No Practis Sets Found"));
        practisSetTab().getNoSearchResultIcon().shouldBe(visible);
        practisSetTab().getPractisSetRow().shouldBe(CollectionCondition.size(0));
    }

    /** Assert Search Results. */
    public static void assertSearchResultsOnPractisSetTab(String input) {
        search().getSearchField().shouldBe(visible);
        practisSetTab().getPractisSetRow().shouldBe(CollectionCondition.size(1));
        final var practisSetRow =
                practisSetTab().getPractisSetRow().find(Condition.matchText(input));
        practisSetRow.shouldBe(visible);
    }

    /** Assert Search any Results. */
    public static void assertSearchAnyResultsOnPractisSetTab() {
        search().getSearchFieldClearButton().shouldBe(visible);
        practisSetTab().getPractisSetRow().get(0).shouldBe(visible);
    }
}
