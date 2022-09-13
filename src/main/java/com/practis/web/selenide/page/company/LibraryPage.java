package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class LibraryPage {

  private final SelenideElement libraryTitle = $("div[data-test='library-page-subtitle']");
  private final SelenideElement practisSetsTab = $("a[data-test='library-nav-practis-sets']");
  private final SelenideElement scenariosTab = $("a[data-test='library-nav-scenarios']");
  private final SelenideElement challengesTab = $("a[data-test='library-nav-challenges']");
  private final SelenideElement timestampText = $("span[data-test='library-timestamp-label']");
  private final SelenideElement timestampRefreshButton =
      $("button[data-test='library-timestamp-refresh']");

  private final SelenideElement searchField = $("div[data-test='library-search-input']");
  private final SelenideElement searchFieldIcon = $("div[data-test='library-search-input-icon']");
  private final SelenideElement filtersButton = $("button[data-test='library-filters-button']");
  private final SelenideElement filtersCounter = $("div[data-test='library-filters-counter']");
  private final SelenideElement paginationNextButton = $("button[data-test='library-paging-next']");
  private final SelenideElement paginationPrevButton = $("button[data-test='library-paging-prev']");

  private final SelenideElement practisSetsSelectAllCheckbox =
      $("td[data-test-custom-name='library-practis-sets-master-checkbox-column']");
  private final SelenideElement selectAllCheckbox =
      $("tr[data-test='table-row']");
  private final SelenideElement practisSetsColumn =
      $("th[data-test='library-practis-sets-title-column']");
  private final SelenideElement practisSetsStatusColumn =
      $("th[data-test='library-practis-sets-status-column']");
  private final SelenideElement contentColumn =
      $("th[data-test='library-practis-sets-content-column']");
  private final SelenideElement practisSetsLastUpdatedColumn =
      $("th[data-test='library-practis-sets-date-column']");

  private final SelenideElement emptyIconPsTab =
      $("div[data-test='library-no-filtered-practis-sets-icon']");
  private final SelenideElement emptyTextPsTab =
      $("div[data-test='library-no-filtered-practis-sets-label']");
  private final SelenideElement emptyIconScenarioTab =
      $("div[data-test='library-no-filtered-scenarios-icon']");
  private final SelenideElement emptyTextScenarioTab =
      $("div[data-test='library-no-filtered-scenarios-label']");
  private final SelenideElement emptyIconChallengeTab =
      $("div[data-test='library-no-filtered-challenges-icon']");
  private final SelenideElement emptyTextChallengeTab =
      $("div[data-test='library-no-filtered-challenges-label']");

  private final SelenideElement scenariosSelectAllCheckbox =
      $("td[data-test-custom-name='library-scenarios-master-checkbox-column']");
  private final SelenideElement scenariosColumn =
      $("th[data-test='library-scenarios-title-column']");
  private final SelenideElement scenariosStatusColumn =
      $("th[data-test='library-scenarios-status-column']");
  private final SelenideElement scenariosDurationColumn =
      $("th[data-test='library-scenarios-duration-column']");
  private final SelenideElement scenariosLastUpdatedColumn =
      $("th[data-test='library-scenarios-date-column']");

  private final SelenideElement challengesSelectAllCheckbox =
      $("td[data-test-custom-name='library-challenges-master-checkbox-column']");
  private final SelenideElement challengesColumn =
      $("th[data-test='library-challenges-title-column']");
  private final SelenideElement challengesStatusColumn =
      $("th[data-test='library-challenges-status-column']");
  private final SelenideElement challengesLastUpdatedColumn =
      $("th[data-test='library-challenges-date-column']");

  private final SelenideElement filtersSelectedCounterText =
      $("span[data-test='library-filters-selected-counter']");
  private final SelenideElement filtersClearButton = $("button[data-test='library-filters-clear']");
  private final SelenideElement filtersApplyButton = $("button[data-test='library-filters-apply']");
}