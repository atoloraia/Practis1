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
import static com.practis.web.selenide.configuration.ServiceObjectFactory.challengeTabService;
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
    public static void assertLabelCountOnChallengePage(final String challenge, final String count) {
        challengeTabService().findChallengeLabelCounter(challenge).shouldBe(visible);
        challengeTabService().findChallengeLabelCounter(challenge).shouldBe(exactText(count));
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

    /** Assert single action for the Archived Challenge. */
    public static void assertSingleActionArchivedChallenge() {
        challengeTab().getViewSingleAction().shouldBe(visible);
        challengeTab().getViewSingleAction().shouldBe(exactText("View"));
        challengeTab().getRestoreSingleAction().shouldBe(visible);
        challengeTab().getRestoreSingleAction().shouldBe(exactText("Restore"));
        challengeTab().getDeleteSingleAction().shouldBe(visible);
        challengeTab().getDeleteSingleAction().shouldBe(exactText("Delete"));
    }

    /** Assert number of Challenge rows. */
    public static void assertChallengesRows(final Integer rows) {
        challengeTab().getChallengeRow().shouldBe(CollectionCondition.size(rows));
        challengeTab().getChallengeRow().shouldBe(CollectionCondition.size(rows));
    }

    /** Assert Status in the row. */
    public static void assertChallengeStatusRow(final String challenge, final String status) {
        challengeTabService().findStatus(challenge).shouldBe(visible);
        challengeTabService().findStatus(challenge).shouldBe(exactText(status));
    }
}
