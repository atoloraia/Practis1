package com.practis.web.selenide.validator;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.feedPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetEditPage;

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

    feedPage().getAccuracyEmptyStateIcon().shouldBe(visible);
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
    feedPage().getChallengesUpdateButton().shouldBe(visible);

    feedPage().getChallengesSearchField().shouldBe(visible);
    feedPage().getChallengesSearchFieldIcon().shouldBe(visible);
    feedPage().getChallengesFiltersButton().shouldBe(visible);
    feedPage().getChallengesFiltersDotIcon().shouldBe(visible);
    feedPage().getChallengesPaginationBackButton().shouldBe(visible);
    feedPage().getChallengesPaginationNextButton().shouldBe(visible);
    feedPage().getChallengesItemsText().shouldBe(matchText("Items"));

    feedPage().getAccuracySelectAllCheckboxButton().shouldBe(visible);
    feedPage().getChallengesUsersColumnText().shouldBe(exactText("Users"));
    feedPage().getChallengesColumnText().shouldBe(exactText("Challenge"));
    feedPage().getChallengesReviewStatusColumnText().shouldBe(exactText("Review Status"));
    feedPage().getChallengesScoreColumnText().shouldBe(exactText("Score"));
    feedPage().getChallengesSubmittedColumnText().shouldBe(exactText("Submitted"));
    feedPage().getChallengesPractisSetColumnText().shouldBe(exactText("Practis Set"));

    feedPage().getChallengesEmptyStateIcon().shouldBe(visible);
    feedPage().getChallengesEmptyStateText()
        .shouldBe(exactText("No Results Match the Filter Criteria"));

  }
}
