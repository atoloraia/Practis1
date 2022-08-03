package com.practis.web.selenide.page.company.user;

import static com.codeborne.selenide.CollectionCondition.anyMatch;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelModule;
import static com.practis.web.util.AwaitUtils.awaitElementVisible;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class InviteUserPage {

  private final SelenideElement inviteUsersToTheAppTitle =
      $("div[data-test='invite-users-page-title']");

  private final SelenideElement searchField = $("input[data-test='table-search-input']");
  private final SelenideElement filtersButton =
      $("button[data-test='invite-users-filters-button']");
  private final SelenideElement downloadTemplateButton =
      $("div[data-test='invite-users-download-template']");
  private final SelenideElement downloadUploadTemplateTooltip =
      $(".sc-dkQkSb.iHPkOP");
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
  private final SelenideElement practisSetColumn =
      $("th[data-test='invite-users-table-head-practis-sets']");
  private final SelenideElement labelsColumn = $("th[data-test='invite-users-table-head-labels']");

  //User Row
  private final SelenideElement inputRowElements = $("div[data-test='invite-users-new-row']");
  private final SelenideElement firstNameField =
      $("input[data-test='invite-users-new-first-name']");
  private final SelenideElement lastNameField =
      $("input[data-test='invite-users-new-last-name']");
  private final SelenideElement emailField = $("input[data-test='invite-users-new-email']");
  private final SelenideElement roleField = $("div[data-test='invite-users-new-role']");
  private final SelenideElement teamsField = $("div[data-test='invite-users-new-teams']");
  private final SelenideElement practisSetsField =
      $("div[data-test='invite-users-new-practis-sets']");

  private final SelenideElement labelsField = $("div[data-test='invite-users-new-labels']");
  private final SelenideElement addRowButton = $("button[data-test='invite-users-new-add']");
  private final SelenideElement addedRow = $("button[data-test='invite-users-new-add']");

  private final SelenideElement addUsersText = $("div[data-test='invite-users-table-prompt']");
  private final SelenideElement savedText = $("div[data-test='invite-users-saved-label']");
  private final SelenideElement saveAsDraftButton =
      $("button[data-test='invite-users-save-as-draft']");
  private final SelenideElement inviteSelectedUsersButton =
      $("button[data-test='invite-selected-users']");

  //Added Row
  private final ElementsCollection addedUserRow = $$("tr[data-test='invite-users-table-row']");
  private final ElementsCollection checkboxAddedUserRow =
      $$("input[data-test='invite-users-table-row-checkbox']");
  private final ElementsCollection checkboxWarningRow =
      $$("div[data-test='invite-users-table-row-checkbox-warning']");
  private final SelenideElement checkboxWarninText =
      $("span[data-test='invite-users-table-row-checkbox-warning-text']");
  private final ElementsCollection addedUserCell = $$("td[data-test='table-cell']");
  private final ElementsCollection deleteRowButton =
      $$("div[data-test='invite-users-table-row-delete']");
  private final SelenideElement editRowButton =
      $("div[data-test='invite-users-table-row-edit']");
  private final SelenideElement emailValidationError = $("div[data-test='invite-users-new-error']");

  //Edit User Row
  private final SelenideElement editInputRowElements =
      $("div[data-test='invite-users-table-edit-row']");
  private final SelenideElement editFirstNameField =
      $("input[data-test='invite-users-table-edit-first-name']");
  private final SelenideElement editLastNameField =
      $("input[data-test='invite-users-table-edit-last-name']");
  private final SelenideElement editEmailField =
      $("input[data-test='invite-users-table-edit-email']");
  private final SelenideElement editRoleField = $("div[data-test='invite-users-table-edit-roles']");
  private final SelenideElement editTeamsField =
      $("div[data-test='invite-users-table-edit-teams']");
  private final SelenideElement editPractisSetsField =
      $("div[data-test='invite-users-table-edit-practis-sets']");
  private final SelenideElement editLabelsField =
      $("div[data-test='invite-users-table-edit-labels']");
  private final SelenideElement applyEditChangesButton =
      $("div[data-test='invite-users-table-edit-save']");
  private final SelenideElement cancelEditChangesButton =
      $("div[data-test='invite-users-table-edit-cancel']");
  private final SelenideElement outsideTheForm =
      $(".sc-dslIDs.lfIpgc");



  /**
   * Find label checkbox.
   */
  public SelenideElement findLabelCheckbox(final String label) {
    final var labelRow = labelModule().getLabelRows().shouldHave(anyMatch("labelName",
        element -> $(element).$("input[value='" + label + "']").exists())).first();
    final var checkbox = labelRow.$("input[data-test='label-item-checkbox']");
    return awaitElementVisible(10, () -> checkbox.sibling(0));
  }

  public SelenideElement getAddedUserCell(final SelenideElement row, final int index) {
    return row.$$("td").get(index);
  }

}
