package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class TeamCreatePage {

  private final SelenideElement createNewTeamoTitle = $("div[data-test='team-page-title']");
  private final SelenideElement notCreatedYet = $("div[data-test='team-timestamp']");

  private final SelenideElement titleField = $(
      "input[data-test='team-name']");
  private final SelenideElement assignLabelsButton = $("button[data-test='team-assign-labels']");

  private final SelenideElement allUserTitle = $("div[data-test='team-all-users-title']");
  private final SelenideElement allUsersUpdatedLabel =
      $("span[data-test='team-all-users-timestamp-label']");
  private final SelenideElement allUsersUpdatedButton =
      $("button[data-test='team-all-users-timestamp-refresh']");
  private final ElementsCollection searchField =
      $$("input[data-test='table-search-input']");
  private final SelenideElement allUsersFilter =
      $("button[data-test='team-all-users-filters-button']");
  private final ElementsCollection userRows = $$("div[data-test='all-users-item']");
  private final ElementsCollection usersLabelsRows = $$("div[data-test='table-labels']");
  private final ElementsCollection usersCheckboxRow = $$("input[data-test='checkbox']");

  private final SelenideElement teamMemberTitle = $("div[data-test='team-members-title']");
  private final SelenideElement noTeamLeaderTitle =
      $("div[data-test='team-members-leaders-counter']");
  private final SelenideElement teamMembersUpdatedLabel =
      $("span[data-test='team-members-timestamp-label']");
  private final SelenideElement teamMembersUpdatedButton =
      $("button[data-test='team-members-timestamp-refresh']");
  private final SelenideElement teamMembersFilter =
      $("button[data-test='team-members-filters-button']");
  private final SelenideElement noTeamMembersIcon =
      $("div[data-test='team-members-no-entries-icon']");
  private final SelenideElement addMemberLabel =
      $("div[data-test='team-members-no-entries-label']");

  private final ElementsCollection tableRows = $$("tr[data-test='table-row']");

  private final SelenideElement addSelectedUsersButton =
      $("div[data-test='all-users-add-selected']");
  private final SelenideElement removeSelectedUsersButton =
      $("div[data-test='team-members-remove-selected']");

  private final SelenideElement createButton = $("button[title='Create']");
  private final SelenideElement cancelButton = $("button[title='Cancel']");

}
