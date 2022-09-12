package com.practis.web.selenide.validator.company;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.feedPage;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertEmptyLabelModel;
import static com.practis.web.selenide.validator.selection.ScenarioSelectionValidator.assertEmptyScenarioModule;
import static com.practis.web.selenide.validator.selection.StatusSelectionValidator.assertFeedAccuracyStatusModule;
import static com.practis.web.selenide.validator.selection.StatusSelectionValidator.assertFeedChallengesStatusModule;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertEmptyTeamModel;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FeedValidator {

  /**
   * Assert elements on 'Feed' page: 'Accuracy Tests' tab.
   */
  public static void assertFeedAccuracyPage() {
    feedPage().getAccuracyFeedTitle().shouldBe(visible);
    feedPage().getAccuracyFeedTitle().shouldBe(exactText("Feed"));

    feedPage().getAccuracyAccuracyTestsTab().shouldBe(visible);
    feedPage().getAccuracyAccuracyTestsTab().shouldBe(exactText("Accuracy Tests"));
    feedPage().getAccuracyAccuracyTestsTab().shouldBe(attribute("aria-current", "page"));

    feedPage().getAccuracyChallengesTab().shouldBe(visible);
    feedPage().getAccuracyChallengesTab().shouldBe(exactText("Challenges"));

    feedPage().getAccuracyTimestampText().shouldBe(visible);
    feedPage().getAccuracyTimestampText().shouldBe(matchText("Updated"));
    feedPage().getAccuracyUpdateButton().shouldBe(visible);

    feedPage().getAccuracySearchField().shouldBe(visible);
    feedPage().getAccuracySearchField().shouldBe(enabled);
    feedPage().getAccuracySearchFieldIcon().shouldBe(visible);

    feedPage().getAccuracyFiltersButton().shouldBe(visible);
    feedPage().getAccuracyFiltersButton().shouldBe(enabled);

    feedPage().getAccuracyPaginationBackButton().shouldBe(visible);
    feedPage().getAccuracyPaginationBackButton().shouldBe(disabled);

    feedPage().getAccuracyPaginationNextButton().shouldBe(visible);
    feedPage().getAccuracyPaginationNextButton().shouldBe(disabled);

    feedPage().getAccuracySelectAllCheckboxButton().shouldBe(visible);

    feedPage().getAccuracyUsersColumnText().shouldBe(visible);
    feedPage().getAccuracyUsersColumnText().shouldBe(enabled);
    feedPage().getAccuracyUsersColumnText().shouldBe(exactText("Users"));
    feedPage().getAccuracyUsersColumnText().shouldBe(attribute("width", "24"));

    feedPage().getAccuracyTestColumnText().shouldBe(visible);
    feedPage().getAccuracyTestColumnText().shouldBe(enabled);
    feedPage().getAccuracyTestColumnText().shouldBe(exactText("Accuracy Test"));
    feedPage().getAccuracyTestColumnText().shouldBe(attribute("width", "14"));

    feedPage().getAccuracySubmittedColumnText().shouldBe(visible);
    feedPage().getAccuracySubmittedColumnText().shouldBe(enabled);
    feedPage().getAccuracySubmittedColumnText().shouldBe(exactText("Submitted"));
    feedPage().getAccuracySubmittedColumnText().shouldBe(attribute("width", "12"));

    feedPage().getAccuracyScenarioColumnText().shouldBe(visible);
    feedPage().getAccuracyScenarioColumnText().shouldBe(enabled);
    feedPage().getAccuracyScenarioColumnText().shouldBe(exactText("Scenario"));
    feedPage().getAccuracyScenarioColumnText().shouldBe(attribute("width", "10"));

    feedPage().getAccuracyPractisSetColumnText().shouldBe(visible);
    feedPage().getAccuracyPractisSetColumnText().shouldBe(enabled);
    feedPage().getAccuracyPractisSetColumnText().shouldBe(exactText("Practis Set"));
    feedPage().getAccuracyPractisSetColumnText().shouldBe(attribute("width", "15"));

    feedPage().getAccuracyEmptyStateIcon().shouldBe(visible);
    feedPage().getAccuracyEmptyStateText().shouldBe(visible);
    feedPage().getAccuracyEmptyStateText().shouldBe(exactText("No Accuracy Tests Yet"));

  }

  /**
   * Assert elements on 'Feed' page: 'Challenges' tab.
   */
  public static void assertFeedChallengesPage() {
    feedPage().getChallengesFeedTitle().shouldBe(visible);
    feedPage().getChallengesFeedTitle().shouldBe(exactText("Feed"));

    feedPage().getChallengesAccuracyTestsTab().shouldBe(visible);
    feedPage().getChallengesAccuracyTestsTab().shouldBe(exactText("Accuracy Tests"));

    feedPage().getChallengesChallengesTab().shouldBe(visible);
    feedPage().getChallengesChallengesTab().shouldBe(exactText("Challenges"));
    feedPage().getChallengesChallengesTab().shouldBe(attribute("aria-current", "page"));

    feedPage().getChallengesUpdateButton().shouldBe(visible);
    feedPage().getChallengesTimestampText().shouldBe(visible);
    feedPage().getChallengesTimestampText().shouldBe(matchText("Updated"));

    feedPage().getChallengesSearchField().shouldBe(visible);
    feedPage().getChallengesSearchField().shouldBe(disabled);
    feedPage().getChallengesSearchFieldIcon().shouldBe(visible);
    feedPage().getChallengesFiltersButton().shouldBe(visible);
    feedPage().getChallengesFiltersDotIcon().shouldBe(visible);

    feedPage().getChallengesPaginationBackButton().shouldBe(visible);
    feedPage().getChallengesPaginationBackButton().shouldBe(disabled);

    feedPage().getChallengesPaginationNextButton().shouldBe(visible);
    feedPage().getChallengesPaginationNextButton().shouldBe(disabled);

    feedPage().getChallengesItemsText().shouldBe(matchText("Items"));
    feedPage().getChallengesItemsText().shouldBe(visible);

    feedPage().getAccuracySelectAllCheckboxButton().shouldBe(visible);

    feedPage().getChallengesUsersColumnText().shouldBe(visible);
    feedPage().getChallengesUsersColumnText().shouldBe(enabled);
    feedPage().getChallengesUsersColumnText().shouldBe(exactText("Users"));
    feedPage().getChallengesUsersColumnText().shouldBe(attribute("width", "20"));

    feedPage().getChallengesColumnText().shouldBe(visible);
    feedPage().getChallengesColumnText().shouldBe(enabled);
    feedPage().getChallengesColumnText().shouldBe(exactText("Challenge"));
    feedPage().getChallengesColumnText().shouldBe(attribute("width", "17"));

    feedPage().getChallengesReviewStatusColumnText().shouldBe(visible);
    feedPage().getChallengesReviewStatusColumnText().shouldBe(enabled);
    feedPage().getChallengesReviewStatusColumnText().shouldBe(exactText("Review Status"));
    feedPage().getChallengesReviewStatusColumnText().shouldBe(attribute("width", "12"));

    feedPage().getChallengesScoreColumnText().shouldBe(visible);
    feedPage().getChallengesScoreColumnText().shouldBe(enabled);
    feedPage().getChallengesScoreColumnText().shouldBe(exactText("Score"));
    feedPage().getChallengesScoreColumnText().shouldBe(attribute("width", "12"));

    feedPage().getChallengesSubmittedColumnText().shouldBe(visible);
    feedPage().getChallengesSubmittedColumnText().shouldBe(enabled);
    feedPage().getChallengesSubmittedColumnText().shouldBe(exactText("Submitted"));
    feedPage().getChallengesSubmittedColumnText().shouldBe(attribute("width", "12"));

    feedPage().getChallengesPractisSetColumnText().shouldBe(visible);
    feedPage().getChallengesPractisSetColumnText().shouldBe(enabled);
    feedPage().getChallengesPractisSetColumnText().shouldBe(exactText("Practis Set"));
    feedPage().getChallengesPractisSetColumnText().shouldBe(attribute("width", "17"));

    feedPage().getChallengesEmptyStateIcon().shouldBe(visible);
    feedPage().getChallengesEmptyStateText().shouldBe(visible);
    feedPage().getChallengesEmptyStateText()
        .shouldBe(exactText("No Results Match the Filter Criteria"));

  }

  /**
   * Assert elements on 'Feed' page: 'Accuracy Test' tab: Filter.
   */
  public static void assertFeedAccuracyTestFilter() {
    assertFeedAccuracyStatusModule();
    assertEmptyScenarioModule();
    assertEmptyTeamModel();
    assertEmptyLabelModel();

    feedPage().getFiltersClearButton().shouldBe(visible);
    feedPage().getFiltersClearButton().shouldBe(attribute("disabled", ""));
    feedPage().getFiltersClearButton().shouldBe((exactText("Clear")));
    feedPage().getFiltersClearButton().shouldBe(attribute("color", "default"));
    feedPage().getFiltersClearButton().shouldBe(attribute("width", "122px"));

    feedPage().getFiltersApplyFilterButton().shouldBe(visible);
    feedPage().getFiltersApplyFilterButton().shouldBe(enabled);
    feedPage().getFiltersApplyFilterButton().shouldBe(exactText("Apply Filter"));
    feedPage().getFiltersApplyFilterButton().shouldBe(attribute("color", "default"));
    feedPage().getFiltersApplyFilterButton().shouldBe(attribute("width", "122px"));
    feedPage().getFiltersApplyFilterButton().shouldBe(attribute("type", "submit"));

    feedPage().getFiltersApplyFilterButton().click();
  }

  /**
   * Assert elements on 'Feed' page: 'Challenges' tab: Filter.
   */
  public static void assertFeedChallengeFilter() {
    assertFeedChallengesStatusModule();
    assertEmptyTeamModel();
    assertEmptyLabelModel();
  }
}
