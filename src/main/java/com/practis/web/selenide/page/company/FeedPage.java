package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class FeedPage {

  private final SelenideElement accuracyFeedTitle = $("div[data-test='accuracy-page-subtitle']");
  private final SelenideElement accuracyAccuracyTestsTab =
      $("a[data-test='accuracy-nav-accuracy-tests']");
  private final SelenideElement accuracyChallengesTab = $("a[data-test='accuracy-nav-challenges']");
  private final SelenideElement accuracyTimestampText =
      $("span[data-test='accuracy-timestamp-label']");
  private final SelenideElement accuracyUpdateButton =
      $("button[data-test='accuracy-timestamp-refresh']");

  private final SelenideElement accuracySearchField = $("div[data-test='accuracy-search-input']");
  private final SelenideElement accuracySearchFieldIcon =
      $("div[data-test='accuracy-search-input-icon']");
  private final SelenideElement accuracyFiltersButton =
      $("button[data-test='accuracy-filters-button']");
  private final SelenideElement accuracyPaginationBackButton =
      $("button[data-test='accuracy-paging-prev']");
  private final SelenideElement accuracyPaginationNextButton =
      $("button[data-test='accuracy-paging-next']");

  private final SelenideElement accuracySelectAllCheckboxButton =
      $("td[data-test='table-cell']");
  private final SelenideElement accuracyUsersColumnText =
      $("th[data-test='accuracy-users-column']");
  private final SelenideElement accuracyTestColumnText = $("th[data-test='accuracy-test-column']");
  private final SelenideElement accuracySubmittedColumnText =
      $("th[data-test='accuracy-submitted-column']");
  private final SelenideElement accuracyScenarioColumnText =
      $("th[data-test='accuracy-scenario-column']");
  private final SelenideElement accuracyPractisSetColumnText =
      $("th[data-test='accuracy-practis-set-column']");

  private final SelenideElement accuracyEmptyStateIcon =
      $("div[data-test='accuracy-no-entries-icon']");
  private final SelenideElement accuracyEmptyStateText =
      $("div[data-test='accuracy-no-entries-label']");


  private final SelenideElement challengesFeedTitle =
      $("div[data-test='challenges-page-subtitle']");
  private final SelenideElement challengesAccuracyTestsTab =
      $("a[data-test='challenges-nav-accuracy-tests']");
  private final SelenideElement challengesChallengesTab =
      $("a[data-test='challenges-nav-challenges']");
  private final SelenideElement challengesTimestampText =
      $("span[data-test='challenges-timestamp-label']");
  private final SelenideElement challengesUpdateButton =
      $("button[data-test='challenges-timestamp-refresh']");

  private final SelenideElement challengesSearchField =
      $("input[data-test='challenges-search-input']");
  private final SelenideElement challengesSearchFieldIcon =
      $("div[data-test='challenges-search-input-icon']");
  private final SelenideElement challengesFiltersButton =
      $("button[data-test='challenges-filters-button']");
  private final SelenideElement challengesFiltersDotIcon =
      $("div[data-test='challenges-filters-counter']");
  private final SelenideElement challengesItemsText =
      $("div[data-test='challenges-paging-counter']");
  private final SelenideElement challengesPaginationBackButton =
      $("button[data-test='challenges-paging-prev']");
  private final SelenideElement challengesPaginationNextButton =
      $("button[data-test='challenges-paging-next']");

  private final SelenideElement challengesUsersColumnText =
      $("th[data-test='challenges-users-column']");
  private final SelenideElement challengesColumnText = $("th[data-test='challenge-column']");
  private final SelenideElement challengesReviewStatusColumnText =
      $("th[data-test='challenges-review-status-column']");
  private final SelenideElement challengesScoreColumnText =
      $("th[data-test='challenges-score-column']");
  private final SelenideElement challengesSubmittedColumnText =
      $("th[data-test='challenges-submitted-column']");
  private final SelenideElement challengesPractisSetColumnText =
      $("th[data-test='challenges-practis-set-column']");

  private final SelenideElement challengesEmptyStateIcon =
      $("div[data-test='challenges-no-filter-results-icon']");
  private final SelenideElement challengesEmptyStateText =
      $("div[data-test='challenges-no-filter-results-label']");

  private final SelenideElement filtersSelectionText = $(".sc-jekYeE.foYoXN");
  private final SelenideElement challengeFiltersSelectionText = $(".sc-dwyCAC.kmTRun");
  private final SelenideElement filtersClearButton = $(".sc-jcFkyM.emOTnY.inverse");
  private final SelenideElement filtersApplyFilterButton = $(".sc-iAKVOt.bwFkCh.primary");

}
