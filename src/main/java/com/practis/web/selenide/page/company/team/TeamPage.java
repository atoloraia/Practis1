package com.practis.web.selenide.page.company.team;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class TeamPage {

  private final SelenideElement teamTitle = $("div[data-test='team-page-title']");
  private final SelenideElement teamNameTitle = $("div[data-test='team-page-subtitle']");
  private final SelenideElement backButton = $("div[data-test='back-arrow-button']");

  private final SelenideElement updatedTimestampText = $("span[data-test='table-timestamp-label']");
  private final SelenideElement updateButton = $("button[data-test='table-timestamp-refresh']");

  private final SelenideElement searchField = $("div[data-test='table-search-input']");
  private final SelenideElement searchFieldIcon =
      $("div[data-test='table-search-input-icon']");
  private final SelenideElement cleanSearchIcon = $("div[data-test='table-search-input-clear']");
  private final SelenideElement filtersButton =
      $("button[data-test='team-filters-button']");

  private final SelenideElement paginationCounterText = $("div[data-test='table-paging-counter']");
  private final SelenideElement paginationBackButton = $("button[data-test='table-paging-prev']");
  private final SelenideElement paginationNextButton = $("button[data-test='table-paging-next']");
  private final SelenideElement sortingArrow = $("button[data-test='table-column-sorting']");
  private final ElementsCollection teamRowTitle = $$("div[data-test='teams-item-name']");

  private final SelenideElement practisSetCounterIcon =
      $("div[data-test='practisset-counter-container']");
  private final SelenideElement practisSetCounter = $("div[data-test='practisset-counter-title']");
  private final SelenideElement usersCounterIcon = $("div[data-test='users-counter-container']");
  private final SelenideElement usersCounter = $("div[data-test='users-counter-title']");
  private final SelenideElement teamLeaderCounterIcon =
      $("div[data-test='team-leaders-counter-container']");
  private final SelenideElement teamLeaderCounter =
      $("div[data-test='team-leaders-counter-title']");

  //Members Tab
  private final SelenideElement membersTab = $("a[data-test='team-members']");
  //Training Tab
  private final SelenideElement trainingTab = $("a[data-test='team-training']");

}
