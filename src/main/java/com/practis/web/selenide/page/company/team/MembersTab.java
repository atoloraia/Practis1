package com.practis.web.selenide.page.company.team;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class MembersTab {

  //Members columns
  private final SelenideElement membersTeamsTitle = $(".sc-ihIMkv.iWOdaK");
  private final SelenideElement membersTeamsSubTitle = $(".sc-jefHGm.bjntlk");
  private final SelenideElement membersManageTeamButton = $(".sc-kHTCUc.fibyXN");
  private final SelenideElement membersManageTeamIcon = $(".sc-egtvG.hzvEcd");
  private final SelenideElement membersFiltersIcon = $(".sc-gsNzhy.jUaWYV");
  private final SelenideElement membersSelectAllCheckbox = $(".sc-gVkttX.iUPQZY");
  private final SelenideElement membersTeamMembersColumn = $("th[data-test='team-members-column']");
  private final SelenideElement membersOverdueColumn = $("th[data-test='overdue-column']");
  private final SelenideElement membersPractisSetStatusColumns =
      $("th[data-test='practisset-status-column']");
  private final SelenideElement membersNotStartedColumn = $("th[data-test='not-started-column']");
  private final SelenideElement membersInProgressColumn = $("th[data-test='in-progress-column']");
  private final SelenideElement membersCompletedColumn = $("th[data-test='completed-column']");
  private final SelenideElement membersAccuracyColumn = $("th[data-test='accuracy-column']");
  private final SelenideElement membersTrainingTimeColumn =
      $("th[data-test='training-time-column']");
  private final SelenideElement membersLastTrainingColumn =
      $("th[data-test='last-training-column']");
  //Member row values
  private final ElementsCollection memberRow = $$("tr[data-test='table-row']");
  private final ElementsCollection memberLabelIcon = $$("div[data-test='table-labels']");
  //Members 3-dot menu
  private final SelenideElement membersThreeDotMenu = $("ul[role='menu']");
  private final SelenideElement membersViewProfileOption =
      $("div[data-test='view-profile-action-item']");
  private final SelenideElement membersAssignPractisSetOption =
      $("div[data-test='assign-practisset-action-item']");
  private final SelenideElement membersNudgeUserOption =
      $("div[data-test='nudge-user-action-item']");
  private final SelenideElement membersExportReportOption =
      $("div[data-test='export-report-action-item']");
  private final SelenideElement itemsCounterText =
      $("div[data-test='table-paging-counter']");

  //Members empty values
  private final SelenideElement noMembersIcon = $("div[data-test='no-members-yet-icon']");
  private final SelenideElement noMembersText = $("div[data-test='no-members-yet-label']");
  private final SelenideElement membersNoSearchResultsIcon = $("div[data-test='members-icon']");
  private final SelenideElement membersNoSearchResultsText = $("div[data-test='members-label']");
  private final SelenideElement membersNoFilterResultsIcon =
      $("div[data-test='no-filtering-criteria-icon']");
  private final SelenideElement membersNoFilterResultsText =
      $("div[data-test='no-filtering-criteria-label']");

  //Search field
  private final SelenideElement membersSearchField = $("input[data-test='table-search-input']");
  private final SelenideElement membersSearchFieldIcon =
      $("div[data-test='table-search-input-icon']");
  private final SelenideElement membersSearchFieldCrossButton =
      $("div[data-test='table-search-input-clear']");

  //filters modal - buttons
  private final SelenideElement applyFiltersButton = $("button[data-test='apply-filters-button']");
  private final SelenideElement clearFiltersButton = $("button[data-test='clear-filters-button']");
  private final SelenideElement selectionCounter = $("span[data-test='total-filters-counter']");
  private final SelenideElement itemsCounter = $("div[data-test='table-paging-counter']");

}


