package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.CollectionCondition.anyMatch;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class InviteUsersToTheAppPage {

  private final SelenideElement inviteUsersToTheAppTitle =
      $("div[data-test='invite-users-page-title']");

  private final SelenideElement searchField = $("input[data-test='table-search-input']");
  private final SelenideElement filtersButton =
      $("button[data-test='invite-users-filters-button']");
  private final SelenideElement downloadTemplateButton =
      $("div[data-test='invite-users-download-template']");
  private final SelenideElement uploadTemplateButton =
      $("div[data-test='invite-users-upload-template']");

  //Table columns
  private final SelenideElement checkboxColumn = $("input[type='checkbox']");
  private final SelenideElement firstNameColumn =
      $("th[data-test='invite-users-table-head-first-name']");
  private final SelenideElement lastNameColumn =
      $("th[data-test='invite-users-table-head-last-name']");
  private final SelenideElement userEmailColumn =
      $("th[data-test='invite-users-table-head-email']");
  private final SelenideElement roleColumn = $("th[data-test='invite-users-table-head-role']");
  private final SelenideElement teamsColumn = $("th[data-test='invite-users-table-head-teams']");
  private final SelenideElement teamsRows = $(".sc-kTwdTh.hJtffO");
  private final SelenideElement teamsName = $(".sc-fMFieO.fJzjgs");
  private final SelenideElement applyTeamButton = $(".sc-fMFieO.fJzjgs");
  private final SelenideElement practisSetColumn =
      $("th[data-test='invite-users-table-head-practis-sets']");
  private final SelenideElement labelsColumn = $("th[data-test='invite-users-table-head-labels']");
  private final SelenideElement applyLabelButton = $(".sc-iAKVOt.dSPRvv.primary");
  private final ElementsCollection labelRows = $$(".sc-gCEojh.gQiGG");
  private final ElementsCollection labelName = $$(".sc-gKckTs.hHamtk.sc-imVSBA.cxsWUw");


  //User Row
  private final SelenideElement inputRowElements = $("div[data-test='invite-users-new-row']");
  private final SelenideElement firstNameField =
      $("input[data-test='invite-users-new-first-name']");
  private final SelenideElement lastNameField =
      $("input[data-test='invite-users-new-last-name']");
  private final SelenideElement emailField = $("input[data-test='invite-users-new-email']");
  private final SelenideElement roleField = $("div[data-test='invite-users-new-role']");
  private final SelenideElement teamsField = $("div[data-test='invite-users-new-teams']");
  private final SelenideElement applyTeam = $(".sc-iAKVOt.dSPRvv.primary");
  private final SelenideElement practisSetsField =
      $("div[data-test='invite-users-new-practis-sets']");
  private final SelenideElement labelsField = $("div[data-test='invite-users-new-labels']");
  private final SelenideElement addRowLinkButton = $("button[data-test='invite-users-new-add']");

  private final SelenideElement addUsersText = $("div[data-test='invite-users-table-prompt']");
  private final SelenideElement notSavedText = $("div[data-test='invite-users-saved-label']");
  private final SelenideElement saveAsDraftButton =
      $("button[data-test='invite-users-save-as-draft']");
  private final SelenideElement inviteSelectedUsersButton =
      $("button[data-test='invite-selected-users']");

  private final ElementsCollection roleCheckbox = $$(".sc-fNgMav.furXRe");

  /**
   * Find label checkbox.
   */
  public SelenideElement findLabelCheckbox(final String label) {
    final var labelRow = labelRows.shouldHave(anyMatch("labelName",
        element -> $(element).$("input[value='" + label + "']").exists())).first();
    return labelRow.$(".sc-gVkttX.kiRuh");
  }

  /**
   * Find team checkbox.
   */
  public SelenideElement findTeamCheckbox(final String team) {
    final var teamRow = labelRows.find(Condition.matchText(team));
    final var checkbox = teamsRows.$(".sc-gVkttX.kiRuh");
    return checkbox.parent();
  }

}
