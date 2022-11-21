package com.practis.web.selenide.page.company.team;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class TeamsPage {
  private final SelenideElement teamsTitle = $("div[data-test='teams-page-subtitle']");

  //Teams Page
  private final SelenideElement teamsTimestamp = $("span[data-test='teams-timestamp-label']");
  private final SelenideElement teamsTimestampRefresh =
      $("button[data-test='teams-timestamp-refresh']");
  private final SelenideElement teamSearchField = $("input[data-test='teams-search-input']");
  private final SelenideElement teamSearchFieldIcon = $("div[data-test='teams-search-input-icon']");
  private final SelenideElement teamSearchFieldCrossButton =
      $("div[data-test='teams-search-input-clear']");
  private final SelenideElement noTeamsFoundIcon = $("div[data-test='no-found-teams-icon']");
  private final SelenideElement noTeamsFoundText = $("div[data-test='no-found-teams-label']");
  private final SelenideElement teamFilterButton = $("button[data-test='teams-filters-button']");
  private final SelenideElement teamsItemsCounter = $("div[data-test='teams-paging-counter']");
  private final SelenideElement teamsPrevButton = $("button[data-test='teams-paging-prev']");
  private final SelenideElement teamsNextButton = $("button[data-test='teams-paging-next']");
  private final SelenideElement selectAllCheckbox =
      $("input[data-test='teams-master-checkbox-input']");

  private final ElementsCollection teamRow = $$("tr[data-test='teams-item']");
  private final SelenideElement teamsColumn = $("th[data-test='teams-name-column']");
  private final SelenideElement teamMembersColumn = $("th[data-test='teams-members-column']");
  private final SelenideElement teamPractisSetsColumn =
      $("th[data-test='teams-practis-sets-column']");
  private final SelenideElement teamTeamLeadersColumn =
      $("th[data-test='teams-team-leaders-column']");
  private final SelenideElement teamsColumnItem = $("div[data-test='teams-item-name']");
  private final SelenideElement teamMembersColumnItem = $("div[data-test='teams-item-members']");
  private final SelenideElement teamPractisSetsColumnItem =
      $("div[data-test='teams-item-practis-sets']");
  private final SelenideElement teamTeamLeadersColumnItem =
      $("div[data-test='teams-item-team-leaders']");
  private final SelenideElement teamsAllMembersRow = $("tr[data-test='all-members-item']");
  private final SelenideElement teamsAllMembersStar =
      $("div[data-test='all-members-item-checkbox-star']");
  private final ElementsCollection teamMembersCount = $$(".sc-PDIlc.fMkdaU");

  //3-dot menu
  private final SelenideElement singleActionOnTeams =
      $("div[data-test='library-practis-sets-item-menu-button']");
  private final SelenideElement viewTeamSingleAction = $("div[data-test='view-team-action']");
  private final SelenideElement manageTeamSingleAction = $("div[data-test='manage-team-action']");
  private final SelenideElement assignLabelsSingleAction =
      $("div[data-test='assign-labels-team-action']");
  private final SelenideElement duplicateSingleAction = $("div[data-test='duplicate-team-action']");
  private final SelenideElement deleteSingleAction = $("div[data-test='delete-team-action']");

  //Selection modal - Action
  private final SelenideElement actionButton = $(".sc-ctrbHu.hrYdRd");
  private final SelenideElement deleteTeamsActionButton =
      $(".sc-eIMkXl.hCHkQc");
  private final SelenideElement selectedItemCounterText =
      $("span[data-test='table-selected-counter']");
  private final SelenideElement selectAllButton = $("button[data-test='table-select-all']");
  private final SelenideElement clearSelectionButton =
      $("button[data-test='table-clear-selection']");


}
