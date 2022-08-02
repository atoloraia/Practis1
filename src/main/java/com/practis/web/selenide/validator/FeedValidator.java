package com.practis.web.selenide.validator;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.feedPage;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertEmptyLabelModel;
import static com.practis.web.selenide.validator.selection.ScenarioSelectionValidator.assertScenarioModule;
import static com.practis.web.selenide.validator.selection.StatusSelectionValidator.assertChallengesAccuracyStatusModule;
import static com.practis.web.selenide.validator.selection.StatusSelectionValidator.assertFeedAccuracyStatusModule;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertTeamModule;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FeedValidator {


  /**
   * Assert elements on Feed Page.
   */
  public static void assertElementsOnFeedPage() {
    feedPage().getAccuracyFeedTitle().shouldBe(exactText("Feed"));
    feedPage().getAccuracyAccuracyTestsTab().shouldBe(exactText("Accuracy Tests"));
    feedPage().getAccuracyChallengesTab().shouldBe(exactText("Challenges"));
    feedPage().getAccuracyTimestampText().shouldBe(matchText("Updated"));
    feedPage().getAccuracyFeedTitle().shouldBe(visible);
    feedPage().getAccuracyAccuracyTestsTab().shouldBe(visible);
    feedPage().getAccuracyChallengesTab().shouldBe(visible);
    feedPage().getAccuracyTimestampText().shouldBe(visible);
    feedPage().getAccuracyUpdateButton().shouldBe(visible);

    feedPage().getAccuracySearchField().shouldBe(visible);
    feedPage().getAccuracySearchFieldIcon().shouldBe(visible);
    feedPage().getAccuracyFiltersButton().shouldBe(visible);
    feedPage().getAccuracyPaginationBackButton().shouldBe(visible);
    feedPage().getAccuracyPaginationNextButton().shouldBe(visible);

    feedPage().getAccuracySelectAllCheckboxButton().shouldBe(visible);
    feedPage().getAccuracyUsersColumnText().shouldBe(exactText("Users"));
    feedPage().getAccuracyTestColumnText().shouldBe(exactText("Accuracy Test"));
    feedPage().getAccuracySubmittedColumnText().shouldBe(exactText("Submitted"));
    feedPage().getAccuracyScenarioColumnText().shouldBe(exactText("Scenario"));
    feedPage().getAccuracyPractisSetColumnText().shouldBe(exactText("Practis Set"));
    feedPage().getAccuracyUsersColumnText().shouldBe(visible);
    feedPage().getAccuracyTestColumnText().shouldBe(visible);
    feedPage().getAccuracySubmittedColumnText().shouldBe(visible);
    feedPage().getAccuracyScenarioColumnText().shouldBe(visible);
    feedPage().getAccuracyPractisSetColumnText().shouldBe(visible);

    feedPage().getAccuracyEmptyStateIcon().shouldBe(visible);
    feedPage().getAccuracyEmptyStateText().shouldBe(visible);
    feedPage().getAccuracyEmptyStateText().shouldBe(exactText("No Accuracy Tests Yet"));

  }

  /**
   * Assert elements on Challenges page.
   */
  public static void assertElementsOnFeedChallengesPage() {
    feedPage().getChallengesFeedTitle().shouldBe(exactText("Feed"));
    feedPage().getChallengesAccuracyTestsTab().shouldBe(exactText("Accuracy Tests"));
    feedPage().getChallengesChallengesTab().shouldBe(exactText("Challenges"));
    feedPage().getChallengesTimestampText().shouldBe(matchText("Updated"));
    feedPage().getChallengesFeedTitle().shouldBe(visible);
    feedPage().getChallengesAccuracyTestsTab().shouldBe(visible);
    feedPage().getChallengesChallengesTab().shouldBe(visible);
    feedPage().getChallengesTimestampText().shouldBe(visible);
    feedPage().getChallengesUpdateButton().shouldBe(visible);

    feedPage().getChallengesSearchField().shouldBe(visible);
    feedPage().getChallengesSearchFieldIcon().shouldBe(visible);
    feedPage().getChallengesFiltersButton().shouldBe(visible);
    feedPage().getChallengesFiltersDotIcon().shouldBe(visible);
    feedPage().getChallengesPaginationBackButton().shouldBe(visible);
    feedPage().getChallengesPaginationNextButton().shouldBe(visible);
    feedPage().getChallengesItemsText().shouldBe(matchText("Items"));
    feedPage().getChallengesItemsText().shouldBe(visible);

    feedPage().getAccuracySelectAllCheckboxButton().shouldBe(visible);
    feedPage().getChallengesUsersColumnText().shouldBe(exactText("Users"));
    feedPage().getChallengesColumnText().shouldBe(exactText("Challenge"));
    feedPage().getChallengesReviewStatusColumnText().shouldBe(exactText("Review Status"));
    feedPage().getChallengesScoreColumnText().shouldBe(exactText("Score"));
    feedPage().getChallengesSubmittedColumnText().shouldBe(exactText("Submitted"));
    feedPage().getChallengesPractisSetColumnText().shouldBe(exactText("Practis Set"));
    feedPage().getChallengesUsersColumnText().shouldBe(visible);
    feedPage().getChallengesColumnText().shouldBe(visible);
    feedPage().getChallengesReviewStatusColumnText().shouldBe(visible);
    feedPage().getChallengesScoreColumnText().shouldBe(visible);
    feedPage().getChallengesSubmittedColumnText().shouldBe(visible);
    feedPage().getChallengesPractisSetColumnText().shouldBe(visible);

    feedPage().getChallengesEmptyStateIcon().shouldBe(visible);
    feedPage().getChallengesEmptyStateText().shouldBe(visible);
    feedPage().getChallengesEmptyStateText()
        .shouldBe(exactText("No Results Match the Filter Criteria"));

  }

  /**
   * Assert elements on Feed - Filters modal.
   */
  public static void assertElementsOnFeedFiltersPage() {
    feedPage().getAccuracyFiltersButton().click();
    assertFeedAccuracyStatusModule();
    assertScenarioModule();
    assertTeamModule();
    assertEmptyLabelModel();
    feedPage().getFiltersApplyFilterButton().click();
  }

  /**
   * Assert elements on Challenges - Filters modal.
   */
  public static void assertElementsOnChallengesFiltersPage() {
    feedPage().getChallengesFiltersButton().click();
    assertChallengesAccuracyStatusModule();
    assertTeamModule();
    assertEmptyLabelModel();
    feedPage().getFiltersApplyFilterButton().click();
  }
}
