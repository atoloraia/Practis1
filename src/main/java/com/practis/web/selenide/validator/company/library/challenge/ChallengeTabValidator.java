package com.practis.web.selenide.validator.company.library.challenge;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.libraryTabs;
import static com.practis.web.selenide.configuration.PageObjectFactory.challengeTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.libraryPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetTab;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.practisSetTabService;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.CollectionCondition;

public class ChallengeTabValidator {

    /** Assert elements on Library - Challenges tab: Empty State. */
    public static void assertEmptyStateLibraryChallengesTab() {
        assertElementsOnLibraryChallengeTab();
        assertEmptyChallengesTab();
    }

    /** Assert elements on Library - Practis Sets page: Empty State: No results for filter */
    public static void assertEmptyChallengesTab() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        challengeTab().getNoResultMatchFilterCriteriaIcon().shouldBe(visible);
        challengeTab()
                .getNoResultMatchFilterCriteriaText()
                .shouldBe(exactText("No Results Match the Filter Criteria"));
        challengeTab().getNoResultMatchFilterCriteriaText().shouldBe(visible);
        challengeTab().getNoResultMatchFilterCriteriaText().shouldBe(attribute("width", "169px"));
    }

    /** Assert elements on Library - Challenge tab: Default View */
    public static void assertElementsOnLibraryChallengeTab() {
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

        challengeTab().getChallengeColumn().shouldBe(visible);
        challengeTab().getChallengeColumn().shouldBe(exactText("Challenges"));
        challengeTab().getChallengeColumn().shouldBe(attribute("width", "25"));

        challengeTab().getChallengeStatusColumn().shouldBe(visible);
        challengeTab().getChallengeStatusColumn().shouldBe(exactText("Status"));

        challengeTab().getChallengeLastUpdatedColumn().shouldBe(visible);
        challengeTab().getChallengeLastUpdatedColumn().shouldBe(exactText("Last Updated"));
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

    /** Assert single action for the Assign Labels. */
    public static void assertSingleActionChallengeNoLabels() {
        challengeTab().getViewSingleAction().shouldBe(visible);
        challengeTab().getViewSingleAction().shouldBe(exactText("View"));
        challengeTab().getDuplicateSingleAction().shouldBe(visible);
        challengeTab().getDuplicateSingleAction().shouldBe(exactText("Duplicate"));
        challengeTab().getArchiveSingleAction().shouldBe(visible);
        challengeTab().getArchiveSingleAction().shouldBe(exactText("Archive"));
    }

    /** Assert single action for the Challenge. */
    public static void assertSingleActionChallenge() {
        assertSingleActionChallengeNoLabels();
        challengeTab().getAssignLabelsSingleAction().shouldBe(visible);
        challengeTab().getAssignLabelsSingleAction().shouldBe(exactText("Assign Labels"));
    }

    /** Assert single action for the Archived Practis Set. */
    public static void assertSingleActionArchivedPs() {
        practisSetTab().getEditSingleAction().shouldBe(visible);
        practisSetTab().getEditSingleAction().shouldBe(exactText("Edit"));
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
}
