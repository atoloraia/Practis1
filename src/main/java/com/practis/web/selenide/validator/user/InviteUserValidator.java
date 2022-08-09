package com.practis.web.selenide.validator.user;

import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserPsModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserRoleModule;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertEmptyLabelModel;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertNoLabelsYet;
import static com.practis.web.selenide.validator.user.UserTeamValidator.assertEmptyTeamModel;
import static com.practis.web.util.AwaitUtils.awaitSoft;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.practis.dto.NewUserInput;
import com.practis.web.selenide.component.GridRow;
import com.practis.web.selenide.validator.selection.LabelSelectionValidator;

public class InviteUserValidator {

  /**
   * Assert elements on New Admin page.
   */
  public static void assertElementsOnInviteUsersPage() {
    inviteUsersPage().getInviteUsersToTheAppTitle().shouldBe(visible);
    inviteUsersPage().getInviteUsersToTheAppTitle()
        .shouldBe(exactText("Invite Users to the App"));

    inviteUsersPage().getSearchField().shouldBe(visible);
    inviteUsersPage().getFiltersButton().shouldBe(visible);
    inviteUsersPage().getDownloadTemplateButton().shouldBe(visible);
    inviteUsersPage().getUploadTemplateButton().shouldBe(visible);

    //Table header
    inviteUsersPage().getFirstNameColumn().shouldBe(visible);
    inviteUsersPage().getFirstNameColumn().shouldBe(exactText("First Name"));
    inviteUsersPage().getLastNameColumn().shouldBe(visible);
    inviteUsersPage().getLastNameColumn().shouldBe(exactText("Last Name"));
    inviteUsersPage().getUserEmailColumn().shouldBe(visible);
    inviteUsersPage().getUserEmailColumn().shouldBe(exactText("User Email"));
    inviteUsersPage().getRoleColumn().shouldBe(visible);
    inviteUsersPage().getRoleColumn().shouldBe(exactText("Role"));
    inviteUsersPage().getTeamsColumn().shouldBe(visible);
    inviteUsersPage().getTeamsColumn().shouldBe(exactText("Teams"));
    inviteUsersPage().getPractisSetColumn().shouldBe(visible);
    inviteUsersPage().getPractisSetColumn().shouldBe(exactText("Practis Sets"));
    inviteUsersPage().getLabelsColumn().shouldBe(visible);
    inviteUsersPage().getLabelsColumn().shouldBe(exactText("Labels"));

    //User row
    inviteUsersPage().getFirstNameField().sibling(0).shouldBe(visible);
    inviteUsersPage().getFirstNameField().sibling(0).shouldBe(exactText("First Name*"));
    inviteUsersPage().getLastNameField().sibling(0).shouldBe(visible);
    inviteUsersPage().getLastNameField().sibling(0).shouldBe(exactText("Last Name*"));
    inviteUsersPage().getEmailField().sibling(0).shouldBe(visible);
    inviteUsersPage().getEmailField().sibling(0).shouldBe(exactText("Email*"));
    //Role modal
    inviteUsersPage().getRoleField().shouldBe(visible);
    inviteUsersPage().getRoleField().shouldBe(exactText("Role*"));
    inviteUsersPage().getRoleField().click();
    inviteUserRoleModule().getUserRoleRadioButton().shouldBe(visible);
    inviteUserRoleModule().getUserRoleRadioButton().shouldBe(exactText("User"));
    inviteUserRoleModule().getAdminRoleRadioButton().shouldBe(visible);
    inviteUserRoleModule().getAdminRoleRadioButton().shouldBe(exactText("Admin"));
    //Teams Modal
    inviteUsersPage().getTeamsField().shouldBe(visible);
    inviteUsersPage().getTeamsField().shouldBe(exactText("Teams"));
    inviteUsersPage().getTeamsField().click();
    await().pollDelay(FIVE_SECONDS).until(() -> true);
    assertEmptyTeamModel();

    //Practis Set modal
    inviteUsersPage().getPractisSetsField().shouldBe(visible);
    inviteUsersPage().getPractisSetsField().shouldBe(exactText("Practis Sets"));
    inviteUsersPage().getPractisSetsField().click();
    inviteUserPsModule().getNoPractisSetYetText().shouldBe(visible);
    inviteUserPsModule().getNoPractisSetYetText().shouldBe(exactText("No practis sets added yet"));

    //Label Modal
    inviteUsersPage().getLabelsField().shouldBe(visible);
    inviteUsersPage().getLabelsField().shouldBe(exactText("Labels"));
    inviteUsersPage().getLabelsField().click();
    assertNoLabelsYet();

    inviteUsersPage().getAddRowButton().shouldBe(visible);
    inviteUsersPage().getAddRowButton().shouldBe(disabled);
    inviteUsersPage().getAddUsersText().shouldBe(visible);
    inviteUsersPage().getAddUsersText()
        .shouldBe(exactText("Add users to the table in order to edit or invite them"));
    inviteUsersPage().getSavedText().shouldBe(visible);
    inviteUsersPage().getSavedText().shouldBe(exactText("Not Saved"));

    inviteUsersPage().getSaveAsDraftButton().shouldBe(visible);
    inviteUsersPage().getSaveAsDraftButton().shouldBe(disabled);
    inviteUsersPage().getSaveAsDraftButton().shouldBe(exactText("Save as Draft"));
    inviteUsersPage().getInviteSelectedUsersButton().shouldBe(visible);
    inviteUsersPage().getInviteSelectedUsersButton().shouldBe(disabled);
    inviteUsersPage().getInviteSelectedUsersButton()
        .shouldBe(exactText("Invite Selected Users"));
  }

  /**
   * Assert Team in User row.
   */
  public static void assertTeamUserGridRow(int row) {
    inviteUsersPage().getTeam().get(row).shouldBe(matchText("1 Team"));
  }

  /**
   * Assert Label in User row.
   */
  public static void assertLabelUserGridRow(int row) {
    inviteUsersPage().getLabel().get(row).shouldBe(matchText("1 Label"));
  }

  /**
   * Assert all User row.
   */
  public static void assertFullUserGridRow(final NewUserInput inputData, final String role,
      final String label, final String team, int row) {
    assertRequiredUserGridRow(inputData, "User", row);
    // assertTeamUserGridRow(row);
    // assertLabelUserGridRow(row);
  }

  /**
   * Assert 'Invite User" screen after adding row.
   */
  public static void assertScreenAfterAddingRow() {
    assertEnabledSaveAsDraft();
    assertDisabledInviteButton();
    assertEmptyTopRow();
    assertNoPrompt();
  }

  /**
   * Assert 'Invite User" screen after adding row.
   */
  public static void assertUserCounter(String counter) {
    inviteUsersPage().getUserCounter().shouldBe(exactText(counter));

  }

  /**
   * Assert added User row with required input data.
   */
  public static void assertRequiredUserGridRow(final NewUserInput inputData,
      final String role, int row) {
    inviteUsersPage().getFirstName().get(row).shouldBe(matchText(inputData.getFirstName()));
    inviteUsersPage().getLastName().get(row).shouldBe(matchText(inputData.getLastName()));
    inviteUsersPage().getEmail().get(row).shouldBe(matchText(inputData.getEmail()));
    //inviteUsersPage().getRole().get(row).shouldBe(matchText(role));
  }


  /**
   * Assert added User row without First Name.
   */
  public static void asserGridRowWithoutFirstName(final NewUserInput inputData,
      final String role) {
    await().pollDelay(FIVE_SECONDS).until(() -> true);
    //TODO update
    //inviteUsersPage().getFirstName().get(0).shouldBe(empty);
    inviteUsersPage().getLastName().get(0).shouldBe(matchText(inputData.getLastName()));
    inviteUsersPage().getEmail().get(0).shouldBe(matchText(inputData.getEmail()));
    inviteUsersPage().getRole().get(0).shouldBe(matchText(role));
  }

  /**
   * Assert added User row without Last Name.
   */
  public static void asserGridRowWithoutLastName(final NewUserInput inputData,
      final String role) {

    inviteUsersPage().getFirstName().get(0).shouldBe(matchText(inputData.getFirstName()));
    inviteUsersPage().getLastName().get(0).shouldBe(empty);
    inviteUsersPage().getEmail().get(0).shouldBe(matchText(inputData.getEmail()));
    inviteUsersPage().getRole().get(0).shouldBe(matchText(role));
  }

  /**
   * Assert added User row without Email.
   */
  public static void asserGridRowWithoutEmail(final NewUserInput inputData,
      final String role) {
    inviteUsersPage().getCheckboxWarningRow().get(0).shouldBe(visible);
    inviteUsersPage().getCheckboxWarningRow().get(0).click();
    inviteUsersPage().getCheckboxWarninText().shouldBe(visible);
    inviteUsersPage().getCheckboxWarninText().shouldBe(exactText("Please edit before selecting"));

    inviteUsersPage().getFirstName().get(0).shouldBe(matchText(inputData.getFirstName()));
    inviteUsersPage().getFirstName().get(0).shouldBe(cssValue("color", "rgba(236, 81, 61, 1)"));
    inviteUsersPage().getLastName().get(0).shouldBe(matchText(inputData.getLastName()));
    inviteUsersPage().getLastName().get(0).shouldBe(cssValue("color", "rgba(236, 81, 61, 1)"));
    inviteUsersPage().getEmail().get(0).shouldNotBe(visible);
    inviteUsersPage().getRole().get(0).shouldBe(matchText(role));
  }

  /**
   * Assert added User row without Role.
   */
  public static void asserGridRowWithoutRole(final NewUserInput inputData,
      final String role) {
    inviteUsersPage().getCheckboxWarningRow().get(0).shouldBe(visible);
    inviteUsersPage().getCheckboxWarningRow().get(0).click();
    inviteUsersPage().getCheckboxWarninText().shouldBe(visible);
    inviteUsersPage().getCheckboxWarninText().shouldBe(exactText("Please edit before selecting"));

    inviteUsersPage().getFirstName().get(0).shouldBe(matchText(inputData.getFirstName()));
    inviteUsersPage().getFirstName().get(0).shouldBe(cssValue("color", "rgba(236, 81, 61, 1)"));
    inviteUsersPage().getLastName().get(0).shouldBe(matchText(inputData.getLastName()));
    inviteUsersPage().getLastName().get(0).shouldBe(cssValue("color", "rgba(236, 81, 61, 1)"));
    inviteUsersPage().getEmail().get(0).shouldBe(matchText(inputData.getEmail()));
    inviteUsersPage().getEmail().get(0).shouldBe(cssValue("color", "rgba(236, 81, 61, 1)"));
    inviteUsersPage().getRole().get(0).shouldNotBe(visible);
  }

  /**
   * Assert there is no prompt "Add users to the table in order to edit or invite them".
   */
  public static void assertNoPrompt() {
    inviteUsersPage().getAddUsersText().shouldNotBe(visible);
  }

  /**
   * Assert enabled state of "Save as Draft" button.
   */
  public static void assertEnabledSaveAsDraft() {
    inviteUsersPage().getSaveAsDraftButton().shouldBe(enabled);
  }

  /**
   * Assert disabled state of "Invite Selected Users" button.
   */
  public static void assertDisabledInviteButton() {
    inviteUsersPage().getInviteSelectedUsersButton().shouldBe(disabled);
  }

  /**
   * Assert the top empty row.
   */
  public static void assertEmptyTopRow() {
    inviteUsersPage().getFirstNameField().shouldBe(empty);
    inviteUsersPage().getLastNameField().shouldBe(empty);
    inviteUsersPage().getEmailField().shouldBe(empty);
    inviteUsersPage().getRoleField().shouldBe(exactText("Role*"));
    inviteUsersPage().getTeamsField().shouldBe(exactText("Teams"));
    inviteUsersPage().getPractisSetsField().shouldBe(exactText("Practis Sets"));
    inviteUsersPage().getLabelsField().shouldBe(exactText("Labels"));
  }

  /**
   * Get email validation message.
   */
  public static SelenideElement getEmailValidationMessage() {
    return inviteUsersPage().getEmailValidationError();
  }

  /**
   * Assert empty state.
   */
  public static void assertEmptyState() {
    inviteUsersPage().getAddUsersText().shouldBe(visible);
    inviteUsersPage().getAddUsersText()
        .shouldBe(exactText("Add users to the table in order to edit or invite them"));
  }

  /**
   * Assert grid row with input data.
   */
  public static void assertUserGridRowPending(final NewUserInput inputData,
      final GridRow gridRow) {
    gridRow.get("Users")
        .shouldBe(matchText(inputData.getFirstName() + " " + inputData.getLastName()));
    gridRow.get("Email Address").shouldBe(matchText(inputData.getEmail()));
  }

  /**
   * Assert grid row with input data.
   */
  public static void assertUserGridRowDraft(String draftName,
      final GridRow gridRow) {
    gridRow.get("Drafts").shouldBe(matchText(draftName));
    gridRow.get("Users").shouldBe(exactText("1"));
    gridRow.get("Created by").shouldBe(matchText("AutoTests User"));
  }

  /**
   * Assert No grid row with input data.
   */
  public static void assertNoSearchResultsOnDraftTab(String draftName) {
    usersPage().getNoUsersFoundText().shouldBe(visible);
    usersPage().getNoUsersFoundText().shouldBe(matchText("No Drafts Found"));
  }

  /**
   * Assert No grid row with input data.
   */
  public static void assertNoSearchResultsOnPendingTab() {
    usersPage().getNoUsersFoundText().shouldBe(visible);
    usersPage().getNoUsersFoundText().shouldBe(matchText("No Users Found"));
  }

  /**
   * Assert required fields.
   */
  public static void assertRequiredInputs(NewUserInput inputData) {
    inviteUsersPage().getFirstNameField().append(inputData.getFirstName());
    inviteUsersPage().getAddRowButton().shouldBe(disabled);
    inviteUsersPage().getLastNameField().append(inputData.getLastName());
    inviteUsersPage().getAddRowButton().shouldBe(disabled);
    inviteUsersPage().getEmailField().append(inputData.getEmail());
    inviteUsersPage().getAddRowButton().shouldBe(disabled);
    inviteUsersPage().getRoleField().click();
    inviteUserRoleModule().getUserRoleRadioButton().click();
    inviteUsersPage().getAddRowButton().shouldBe(enabled);
  }

  /**
   * Assert no teams in the Teams dropdown.
   */
  public static void assertEmptyTeamList() {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUsersPage().getTeamsField().click();
    assertEmptyTeamModel();
  }

  /**
   * Assert team in the Teams dropdown.
   */
  public static void assertAddedTeam(final String team) {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUsersPage().getTeamsField().click();
    UserTeamValidator.assertCreatedTeam(team);
    UserTeamValidator.assertDisabledApplyButton();
  }

  /**
   * Assert label in the Label dropdown.
   */
  public static void assertAddedLabel(final String label) {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUsersPage().getLabelsField().click();
    LabelSelectionValidator.assertCreatedLabel(label);
    LabelSelectionValidator.assertDisabledApplyButton();
  }

  /**
   * Assert no labels in the Label dropdown.
   */
  public static void assertEmptyLabelList() {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUsersPage().getLabelsField().click();
    assertEmptyLabelModel();
  }

  /**
   * Assert Download template button.
   */
  public static void assertDownloadButton() {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUsersPage().getDownloadTemplateButton().shouldBe(visible);
    final var hoveredElement = inviteUsersPage().getDownloadTemplateButton();
    Selenide.actions().moveToElement(hoveredElement).perform();
    inviteUsersPage().getDownloadUploadTemplateTooltip().shouldBe(exactText("Download template"));
  }

  /**
   * Assert Upload template button.
   */
  public static void assertUploadButton() {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUsersPage().getUploadTemplateButton().shouldBe(visible);
    final var hoveredElement = inviteUsersPage().getUploadTemplateButton();
    Selenide.actions().moveToElement(hoveredElement).perform();
    inviteUsersPage().getDownloadUploadTemplateTooltip().shouldBe(exactText("Upload template"));
  }

  /**
   * Assert template has been downloaded.
   */
  public static void assertDownloadedFile(final String filename) {
    awaitSoft(5, () -> webdriver().driver().browserDownloadsFolder().file(filename).exists());
  }

  /**
   * Assert screen after draft saving.
   */
  public static void assertScreenAfterSaving() {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUsersPage().getInviteUsersToTheAppTitle().shouldBe(visible);
    inviteUsersPage().getAddedUserRow().get(0).shouldBe(visible);
    inviteUsersPage().getSaveAsDraftButton().shouldNotBe(visible);
    inviteUsersPage().getInviteSelectedUsersButton().shouldBe(visible);
    inviteUsersPage().getInviteSelectedUsersButton().shouldBe(disabled);
    inviteUsersPage().getSavedText().shouldBe(visible);
    inviteUsersPage().getSavedText().shouldBe(matchText("Saved"));
  }

  /**
   * Assert screen after draft saving.
   */
  public static void assertScreenAfterSavingWithIssues() {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUsersPage().getCheckboxWarningRow().get(0).shouldBe(visible);
    inviteUsersPage().getCheckboxWarningRow().get(0).click();
    inviteUsersPage().getCheckboxWarninText().shouldBe(visible);
    inviteUsersPage().getCheckboxWarninText().shouldBe(exactText("Please edit before selecting"));
  }

  /**
   * Assert screen after draft saving.
   */
  public static void assertScreenOneFromManyInvitation() {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUsersPage().getInviteUsersToTheAppTitle().shouldBe(visible);
    inviteUsersPage().getAddedUserRow().get(0).shouldBe(visible);
    inviteUsersPage().getSaveAsDraftButton().shouldBe(visible);
    inviteUsersPage().getSaveAsDraftButton().shouldBe(enabled);
    inviteUsersPage().getInviteSelectedUsersButton().shouldBe(visible);
    inviteUsersPage().getInviteSelectedUsersButton().shouldBe(disabled);
    inviteUsersPage().getSavedText().shouldBe(visible);
    inviteUsersPage().getSavedText().shouldBe(matchText("Saved"));
  }

  /**
   * Assert screen after draft canceling.
   */
  public static void assertInviteScreenCancelDraft() {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUsersPage().getInviteUsersToTheAppTitle().shouldBe(visible);
    inviteUsersPage().getAddedUserRow().get(0).shouldBe(visible);
    inviteUsersPage().getCheckboxAddedUserRow().get(0).shouldBe(checked);
    inviteUsersPage().getSaveAsDraftButton().shouldBe(visible);
    inviteUsersPage().getInviteSelectedUsersButton().shouldBe(visible);
    inviteUsersPage().getInviteSelectedUsersButton().shouldBe(enabled);
    inviteUsersPage().getSavedText().shouldBe(visible);
    inviteUsersPage().getSavedText().shouldBe(matchText("Not Saved"));
  }

  /**
   * Assert screen after draft canceling.
   */
  public static void assertUploadedData() {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUsersPage().getInviteUsersToTheAppTitle().shouldBe(visible);
    inviteUsersPage().getAddedUserRow().get(0).shouldBe(visible);
    inviteUsersPage().getCheckboxAddedUserRow().get(0).shouldBe(checked);
    inviteUsersPage().getSaveAsDraftButton().shouldBe(visible);
    inviteUsersPage().getInviteSelectedUsersButton().shouldBe(visible);
    inviteUsersPage().getInviteSelectedUsersButton().shouldBe(enabled);
    inviteUsersPage().getSavedText().shouldBe(visible);
    inviteUsersPage().getSavedText().shouldBe(matchText("Not Saved"));
  }

}
