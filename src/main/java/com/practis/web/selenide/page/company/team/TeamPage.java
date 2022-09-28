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

  private final SelenideElement practisSetCounterIcon =
      $("div[data-test='practisset-counter-container']");
  private final SelenideElement practisSetCounter = $("div[data-test='practisset-counter-title']");
  private final SelenideElement usersCounterIcon = $("div[data-test='users-counter-container']");
  private final SelenideElement usersCounterCounter = $("div[data-test='users-counter-title']");
  private final SelenideElement teamLeaderCounterIcon =
      $("div[data-test='team-leaders-counter-container']");
  private final SelenideElement teamLeaderCounter =
      $("div[data-test='team-leaders-counter-title']");

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

  //Training Tab
  private final SelenideElement trainingTab = $("a[data-test='team-training']");
  //Training columns
  private final SelenideElement trainingTabSelectAllCheckboxTrainingTab = $(".sc-hJhJlY.fsBihe");
  private final SelenideElement trainingPractisSetColumn = $("th[data-test='practis-sets-column']");
  private final SelenideElement trainingOverdueColumn = $("th[data-test='overdue-column']");
  private final SelenideElement trainingTeamMemberStatusColumns =
      $("th[data-test='team-member-status-column']");
  private final SelenideElement trainingNotStartedColumn = $("th[data-test='not-started-column']");
  private final SelenideElement trainingInProgressColumn = $("th[data-test='in-progress-column']");
  private final SelenideElement trainingCompletedColumn = $("th[data-test='completed-column']");
  private final SelenideElement trainingLastTrainingColumn =
      $("th[data-test='last-training-column']");
  //Training row values
  private final ElementsCollection teamRow = $$("tr[data-test='table-row']");
  private final ElementsCollection trainingLabelIcon = $$("div[data-test='table-labels']");
  //Training 3-dot menu
  private final SelenideElement trainingThreeDotMenu = $("ul[role='menu']");
  private final SelenideElement trainingViewProgressOption =
      $("div[data-test='view-progress-action-item']");
  private final SelenideElement trainingAssignUserOption =
      $("div[data-test='assign-users-action-item']");
  private final SelenideElement trainingExportActionOption =
      $("div[data-test='export-report-action-item']");
  private final SelenideElement trainingEditPractisSetOption =
      $("div[data-test='edit-practisset-action-item']");
  //Training empty values
  private final SelenideElement noTrainingIcon = $("div[data-test='no-trainings-yet-icon']");
  private final SelenideElement noTrainingText = $("div[data-test='no-trainings-yet-label']");
  private final SelenideElement trainingNoSearchResultsIcon = $("div[data-test='trainings-icon']");
  private final SelenideElement trainingNoSearchResultsText = $("div[data-test='trainings-label']");
  private final SelenideElement trainingNoFilterResultsIcon =
      $("div[data-test='no-filtering-criteria-icon']");
  private final SelenideElement trainingNoFilterResultsText =
      $("div[data-test='no-filtering-criteria-label']");

  //Members Tab
  private final SelenideElement membersTab = $("a[data-test='team-members']");
  //Members columns
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
  //Members empty values
  private final SelenideElement noMembersIcon = $("div[data-test='no-trainings-yet-icon']");
  private final SelenideElement noMembersText = $("div[data-test='no-trainings-yet-label']");
  private final SelenideElement membersNoSearchResultsIcon = $("div[data-test='members-icon']");
  private final SelenideElement membersNoSearchResultsText = $("div[data-test='members-label']");
  private final SelenideElement membersNoFilterResultsIcon =
      $("div[data-test='no-filtering-criteria-icon']");
  private final SelenideElement membersNoFilterResultsText =
      $("div[data-test='no-filtering-criteria-label']");


}
