package com.practis.web.selenide.page.company.team;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ManageTeamPage {

  private final SelenideElement createNewTeamTitle = $("div[data-test='team-page-title']");

  private final SelenideElement lastUpdatedTimestamp = $("div[data-test='team-timestamp']");
  private final SelenideElement savingChangesText = $("div[data-test='team-saving-changes-text']");
  private final SelenideElement savingChangesIcon = $("div[data-test='team-saving-changes-icon']");
  private final SelenideElement savedChangesText = $("div[data-test='team-changes-saved-text']");
  private final SelenideElement savedChangesIcon = $("div[data-test='team-changes-saved-icon']");
  private final SelenideElement closeButton = $("button[data-test='team-cancel']");

  private final SelenideElement titleField = $("input[data-test='team-name-input']");
  private final SelenideElement titleSaveButton = $("div[data-test='team-name-save']");
  private final SelenideElement titleCancelButton = $("div[data-test='team-name-cancel']");

  private final SelenideElement assignLabelsButton = $("button[data-test='team-assign-labels']");
  private final SelenideElement pendingIcon = $(".sc-edESPO.heEMwv");
  private final SelenideElement pendingToolTip = $(".sc-dkQkSb.iHPkOP");

  //All Users section
  private final SelenideElement allUserTitle = $("div[data-test='team-all-users-title']");
  private final SelenideElement allUsersUpdatedLabel =
      $("span[data-test='team-all-users-timestamp-label']");
  private final SelenideElement allUsersUpdatedButton =
      $("button[data-test='team-all-users-timestamp-refresh']");
  private final ElementsCollection searchField =
      $$("input[data-test='table-search-input']");
  private final ElementsCollection searchFieldIcon =
      $$("input[data-test='table-search-input-icon']");
  private final SelenideElement allUsersFilter =
      $("button[data-test='team-all-users-filters-button']");
  private final SelenideElement userCounter =
      $("p[data-test='team-all-users-counter']");

  //Columns of "All Users" table
  private final SelenideElement selectAllCheckboxUsersTable =
      $("th[data-test='team-all-users-column-checkbox']");
  private final SelenideElement usersColumnUsersTable =
      $("th[data-test='team-all-users-column-users']");
  private final SelenideElement lastTrainingColumnUsersTable =
      $("th[data-test='team-all-users-column-last-training']");

  private final ElementsCollection userRow =
      $$("div[data-test='team-all-users-item']");
  private final ElementsCollection labelsUserRow =
      $$("div[data-test='team-all-users-item-labels']");
  private final ElementsCollection checkboxUserRow =
      $$("input[data-test='team-all-users-item-checkbox']");
  private final ElementsCollection avatarUserRow =
      $$("div[data-test='team-all-users-item-avatar']");
  private final ElementsCollection nameUserRow = $$("div[data-test='team-all-users-item-name']");
  private final ElementsCollection lastTrainingUserRow =
      $$("div[data-test='team-all-users-item-last-training']");

  //Team Members section
  private final SelenideElement teamMemberTitle = $("div[data-test='team-members-title']");
  private final SelenideElement noTeamLeaderTitle =
      $("div[data-test='team-members-leaders-counter']");
  private final SelenideElement teamMembersUpdatedLabel =
      $("span[data-test='team-members-timestamp-label']");
  private final SelenideElement teamMembersUpdatedButton =
      $("button[data-test='team-members-timestamp-refresh']");
  private final SelenideElement teamMembersFilter =
      $("button[data-test='team-members-filters-button']");
  private final SelenideElement teamLeaderCounter =
      $("p[data-test='team-members-counter']");
  private final SelenideElement noTeamMembersIcon =
      $("div[data-test='team-members-no-entries-icon']");
  private final SelenideElement addMemberLabel =
      $("div[data-test='team-members-no-entries-label']");

  private final ElementsCollection noAllMembersIcon = $$(".sc-gpZsfs.HJlJi");
  private final ElementsCollection addAllMembersLabel = $$(".sc-lliPGf.feEuiR");

  //Columns of "Team Members" table
  private final ElementsCollection teamMemberRow = $$("div[data-test='team-members-item']");
  private final SelenideElement selectAllCheckboxMembersTable =
      $("th[data-test='team-members-column-checkbox']");
  private final SelenideElement userColumnMembersTable =
      $("th[data-test='team-members-column-users']");
  private final SelenideElement teamLeaderColumnMembersTable =
      $("th[data-test='team-members-column-team-leader']");

  private final SelenideElement addSelectedUsersButton =
      $("div[data-test='all-users-add-selected']");
  private final SelenideElement removeSelectedUsersButton =
      $("div[data-test='team-members-remove-selected']");
  private final SelenideElement allMembersText = $(".sc-jTjQFw.nDijv");
  private final SelenideElement teamLeadersCount = $(".sc-gAqPTu.fFlcmW");
  private final ElementsCollection manageTeamFilter = $$(".sc-fwqDLH.eNjjIo");
  private final SelenideElement manageTeamItemsCounter = $("p[data-test='table-selected-item']");
  private final ElementsCollection tableColumns = $$(".sc-iZnKzf.eApllq");
  private final ElementsCollection labelsTag = $$(".sc-cLqpJY.kCytdu");
  private final ElementsCollection toggleButton = $$(".sc-hvOLwE.cvmWxm");
  private final ElementsCollection userPic = $$(".sc-jivCuG.gylJwJ.sc-dghjZA.hJSiAU");
  private final ElementsCollection tableRow = $$("tr[data-test='table-row']");
  private final SelenideElement teamLeaderToggle =
      $("input[data-test='team-members-item-team-lead']");
  private final ElementsCollection allUsersSingleCheckbox =
      $$("div[data-test='team-all-users-item-checkbox-view']");

  private final SelenideElement changesSavedText = $("div[data-test='team-changes-saved-text']");
  private final SelenideElement changesSavedIcon = $("div[data-test='team-changes-saved-icon']");

  //All Members
  private final SelenideElement userColumnAllMembers = $(".sc-eaUozk.iBUBG");
  private final SelenideElement teamLeaderColumnAllMembers = $(".sc-eaUozk.jQtYXm");


}
