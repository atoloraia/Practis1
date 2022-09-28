package com.practis.web.selenide.validator.user;

import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.downloadsFolder;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserPsModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserRoleModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.teamModule;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.userProfilePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.service.company.UserService.searchPendingUser;
import static com.practis.web.selenide.validator.company.navigation.UserValidator.assertUserGridRowPending;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertEmptyLabelModel;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertNoLabelsYet;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSelectedLabel;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertCreatedTeam;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertDisabledApplyButton;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertEmptyTeamModel;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertEmptyTeamModelAssignModel;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertSelectedTeam;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertUserData;
import static com.practis.web.util.AwaitUtils.awaitSoft;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.practis.dto.NewUserInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.rest.dto.company.RestTeamResponse;
import com.practis.web.selenide.component.GridRow;
import com.practis.web.selenide.validator.selection.LabelSelectionValidator;
import com.practis.web.util.PractisUtils;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import org.awaitility.Awaitility;
import org.openqa.selenium.Keys;

public class InviteUserValidator {

  /**
   * Assert elements on New Admin page.
   */
  public static void assertElementsOnInviteUsersPage() {
    inviteUsersPage().getInviteUsersToTheAppTitle().shouldBe(visible);
    inviteUsersPage().getInviteUsersToTheAppTitle().shouldBe(exactText("Invite Users to the App"));

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
    assertEmptyTeamModelAssignModel();
    teamModule().getCancelButton().click();

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
    inviteUsersPage().getInviteSelectedUsersButton().shouldBe(exactText("Invite Selected Users"));
  }

  /**
   * Assert Team in User row.
   */
  public static void assertOneTeamSelected(int row) {
    inviteUsersPage().getTeam().get(row).shouldBe(matchText("1 Team"));
  }

  /**
   * Assert Label in User row.
   */
  public static void assertOneLabelUserGridRow(int row) {
    inviteUsersPage().getLabel().get(row).shouldBe(matchText("1 Label"));
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
  public static void assertRequiredUserGridRow(final NewUserInput inputData, final String role,
      int row) {
    inviteUsersPage().getFirstName().get(row).shouldBe(matchText(inputData.getFirstName()));
    inviteUsersPage().getLastName().get(row).shouldBe(matchText(inputData.getLastName()));
    inviteUsersPage().getEmail().get(row).shouldBe(matchText(inputData.getEmail()));
    inviteUsersPage().getRole().get(row).shouldBe(matchText(role));
  }

  /**
   * Assert added User row without First Name.
   */
  public static void assertGridRowWithoutFirstName(final NewUserInput inputData,
      final String role) {
    await().pollDelay(FIVE_SECONDS).until(() -> true);
    inviteUsersPage().getFirstName().get(0).shouldBe(hidden);
    inviteUsersPage().getLastName().get(0).shouldBe(matchText(inputData.getLastName()));
    inviteUsersPage().getEmail().get(0).shouldBe(matchText(inputData.getEmail()));
    inviteUsersPage().getRole().get(0).shouldBe(matchText(role));
  }

  /**
   * Assert added User row without Last Name.
   */
  public static void assertGridRowWithoutLastName(final NewUserInput inputData, final String role) {
    //TODO resolve issue related to checking empty Last name
    inviteUsersPage().getFirstName().get(0).shouldBe(matchText(inputData.getFirstName()));
    inviteUsersPage().getLastName().get(0).shouldBe(hidden);
    inviteUsersPage().getEmail().get(0).shouldBe(matchText(inputData.getEmail()));
    inviteUsersPage().getRole().get(0).shouldBe(matchText(role));
  }

  /**
   * Assert added User row without Email.
   */
  public static void assertGridRowWithoutEmail(final NewUserInput inputData, final String role) {
    inviteUsersPage().getCheckboxWarningRow().get(0).shouldBe(visible);
    inviteUsersPage().getCheckboxWarningRow().get(0).click();
    inviteUsersPage().getCheckboxWarningText().shouldBe(visible);
    inviteUsersPage().getCheckboxWarningText().shouldBe(exactText("Please edit before selecting"));

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
  public static void assertGridRowWithoutRole(final NewUserInput inputData, final int row) {
    inviteUsersPage().getCheckboxWarningRow().get(row).shouldBe(visible);
    inviteUsersPage().getCheckboxWarningRow().get(row).click();
    inviteUsersPage().getCheckboxWarningText().shouldBe(visible);
    inviteUsersPage().getCheckboxWarningText().shouldBe(exactText("Please edit before selecting"));

    inviteUsersPage().getFirstName().get(row).shouldBe(matchText(inputData.getFirstName()));
    inviteUsersPage().getFirstName().get(row).shouldBe(cssValue("color", "rgba(236, 81, 61, 1)"));
    inviteUsersPage().getLastName().get(row).shouldBe(matchText(inputData.getLastName()));
    inviteUsersPage().getLastName().get(row).shouldBe(cssValue("color", "rgba(236, 81, 61, 1)"));
    inviteUsersPage().getEmail().get(row).shouldBe(matchText(inputData.getEmail()));
    inviteUsersPage().getEmail().get(row).shouldBe(cssValue("color", "rgba(236, 81, 61, 1)"));
    inviteUsersPage().getRole().get(row).shouldNotBe(visible);
  }

  /**
   * Assert there is no prompt "Add users to the table in order to edit or invite them".
   */
  public static void assertNoPrompt() {
    inviteUsersPage().getAddUsersText().shouldNotBe(visible);
  }

  /**
   * Assert screen when required field is empty.
   */
  public static void assertEmptyRequiredFiled() {
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
   * Assert data on User Profile.
   */
  public static void assertPendingUser(final NewUserInput inputs, final String team,
      final String label) {
    assertUserData(inputs);
    userProfilePage().getAssignButton().click();
    assertSelectedTeam(team);
    assertSelectedLabel(label);
  }

  /**
   * Assert data on User Profile.
   */
  public static void assertPendingUser(final NewUserInput inputs, final String team) {
    assertUserData(inputs);
    userProfilePage().getAssignButton().click();
    assertSelectedTeam(team);
  }

  /**
   * Assert data on User Profile.
   */
  public static void assertPendingUser(final NewUserInput inputs) {
    assertUserData(inputs);
  }

  /**
   * Assert data on User Profile.
   */
  public static void assertInvitedUsers(final List<NewUserInput> inputs,
      final RestCreateLabelResponse label, final RestTeamResponse team) {
    IntStream.range(0, 1).forEach(idx -> {
      var userRow = searchPendingUser(inputs.get(idx));
      assertUserGridRowPending(inputs.get(idx), userRow);
      //view User Profile
      userRow.click();
      assertPendingUser(inputs.get(idx), team.getName(), label.getName());

      PractisUtils.clickOutOfTheForm();
      userService().openPendingUsersList();
    });
  }

  /**
   * Assert data on User Profile.
   */
  public static void assertInvitedUsers(final List<NewUserInput> inputs) {
    IntStream.range(0, 1).forEach(idx -> {
      var userRow = searchPendingUser(inputs.get(idx));
      assertUserGridRowPending(inputs.get(idx), userRow);
      //view User Profile
      userRow.click();
      assertPendingUser(inputs.get(idx));

      PractisUtils.clickOutOfTheForm();
      userService().openPendingUsersList();
    });
  }

  /**
   * Assert data on User Profile.
   */
  public static void assertInvitedUser(final NewUserInput input,
      final RestCreateLabelResponse label, final RestTeamResponse team) {
    var userRow = searchPendingUser(input);
    assertUserGridRowPending(input, userRow);
    //view User Profile
    userRow.click();
    assertPendingUser(input, team.getName(), label.getName());

    PractisUtils.clickOutOfTheForm();
    userService().openPendingUsersList();
  }

  /**
   * Assert data on User Profile.
   */
  public static void assertInvitedUser(final NewUserInput input, final RestTeamResponse team) {
    var userRow = searchPendingUser(input);
    assertUserGridRowPending(input, userRow);
    //view User Profile
    userRow.click();
    assertPendingUser(input, team.getName());

    PractisUtils.clickOutOfTheForm();
    userService().openPendingUsersList();
  }

  /**
   * Assert data on User Profile.
   */
  public static void assertInvitedUser(final NewUserInput input) {
    var userRow = searchPendingUser(input);
    assertUserGridRowPending(input, userRow);
    //view User Profile
    userRow.click();
    assertPendingUser(input);
    PractisUtils.clickOutOfTheForm();
    userService().openPendingUsersList();
  }

  /**
   * Assert data on User Profile.
   */
  public static void assertNotInvitedUser(final NewUserInput input) {
    userService().searchUser(input.getEmail());
    assertNoSearchResultsOnPendingTab();
  }


  /**
   * Assert User: search, assert data on Draft list.
   */
  public static void asserDraftUser(String draftName, NewUserInput inputData, String role,
      int row) {
    final var userGridRow = userService().searchUser(draftName);
    assertUserGridRowDraft(draftName, userGridRow);
    userGridRow.click();
    assertRequiredUserGridRow(inputData, role, row);
  }

  /**
   * Assert grid row with input data.
   */
  public static void assertUserGridRowDraft(String draftName, final GridRow gridRow) {
    gridRow.get("Drafts").shouldBe(matchText(draftName));
    gridRow.get("Users").shouldBe(exactText("1"));
    gridRow.get("Created by").shouldBe(matchText("AutomationTest User"));
  }

  /**
   * Assert grid row with existing input data.
   */
  public static void assertExistingGridRowDraft(GridRow gridRow) {
    gridRow.get("Drafts").shouldBe(matchText("existingName"));
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
    usersPage().getNoUsersFoundIcon().shouldBe(visible);
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
    assertEmptyTeamModelAssignModel();
  }

  /**
   * Assert team in the Teams dropdown.
   */
  public static void assertAddedTeam(final String team) {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUsersPage().getTeamsField().click();
    assertCreatedTeam(team);
    assertDisabledApplyButton();
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
    awaitSoft(5, () -> new File(downloadsFolder).exists());
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
    inviteUsersPage().getCheckboxWarningText().shouldBe(visible);
    inviteUsersPage().getCheckboxWarningText().shouldBe(exactText("Please edit before selecting"));
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
    //inviteUsersPage().getCheckboxAddedUserRow().get(0).shouldBe(checked);
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

  /**
   * Assert problematic User row.
   */
  public static void asserProblemGridRow(int row, String message) {
    inviteUsersPage().getCheckboxWarningRow().get(0).shouldBe(visible);
    inviteUsersPage().getCheckboxWarningRow().get(0).click();
    inviteUsersPage().getCheckboxWarningText().shouldBe(exactText(message));

    inviteUsersPage().getFirstName().get(row).shouldBe(visible);
    inviteUsersPage().getFirstName().get(row).shouldBe(cssValue("font-size", "13px"));
    inviteUsersPage().getFirstName().get(row).shouldBe(cssValue("width", "auto"));
    inviteUsersPage().getFirstName().get(row).shouldBe(cssValue("color", "rgba(236, 81, 61, 1)"));
    inviteUsersPage().getLastName().get(row).shouldBe(visible);
    inviteUsersPage().getLastName().get(row).shouldBe(cssValue("font-size", "13px"));
    inviteUsersPage().getLastName().get(row).shouldBe(cssValue("width", "auto"));
    inviteUsersPage().getLastName().get(row).shouldBe(cssValue("color", "rgba(236, 81, 61, 1)"));
    inviteUsersPage().getEmail().get(row).shouldBe(visible);
    inviteUsersPage().getRole().get(row).shouldBe(visible);
  }

  /**
   * Assert problematic User row.
   */
  public static void asserNormalGridRow(int row) {
    inviteUsersPage().getFirstName().get(row).shouldBe(visible);
    inviteUsersPage().getFirstName().get(row).shouldBe(cssValue("font-size", "13px"));
    inviteUsersPage().getFirstName().get(row).shouldBe(cssValue("color", "rgba(33, 33, 33, 1)"));
    inviteUsersPage().getLastName().get(row).shouldBe(visible);
    inviteUsersPage().getLastName().get(row).shouldBe(cssValue("color", "rgba(33, 33, 33, 1)"));
    inviteUsersPage().getEmail().get(row).shouldNotBe(visible);
    inviteUsersPage().getRole().get(row).shouldBe(visible);

  }

  /**
   * Get warning checkbox.
   */
  public static SelenideElement getWarningCheckbox() {
    return inviteUsersPage().getCheckboxWarningRow().get(0);
  }

  /**
   * Assert edit User - remove email.
   */
  public static void asserEditGridRowRemoveEmailCancel() {
    userService().clickEdit(0);
    inviteUsersPage().getEditEmailField().sendKeys(Keys.COMMAND + "a");
    Awaitility.await().pollDelay(1, TimeUnit.SECONDS).until(() -> true);
    inviteUsersPage().getEditEmailField().sendKeys(Keys.DELETE);
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUsersPage().getEmptyEmailError().shouldBe(visible);
    userService().cancelEditChanges(0);
  }

  /**
   * Assert selection panel.
   */
  public static void asserSelectionPanel() {
    inviteUsersPage().getAssignButton().shouldBe(visible);
    inviteUsersPage().getAssignButton().shouldBe(exactText("Assign..."));
    inviteUsersPage().getAssignButton().shouldBe(cssValue("height", "40px"));
    inviteUsersPage().getAssignButton().shouldBe(cssValue("width", "152px"));
    inviteUsersPage().getAssignButton().shouldBe(cssValue("border-radius", "4px"));
    inviteUsersPage().getAssignButton().shouldBe(cssValue("cursor", "pointer"));
    inviteUsersPage().getAssignButton().shouldBe(cssValue("color", "rgba(109, 127, 140, 1)"));

    inviteUsersPage().getDeleteUsersButton().shouldBe(visible);
    inviteUsersPage().getDeleteUsersButton().shouldBe(cssValue("height", "40px"));
    inviteUsersPage().getDeleteUsersButton().shouldBe(cssValue("width", "40px"));
    inviteUsersPage().getDeleteUsersButton().shouldBe(cssValue("align-items", "center"));
    inviteUsersPage().getDeleteUsersButton().shouldBe(cssValue("align-items", "center"));
    inviteUsersPage().getDeleteUsersButton().shouldBe(cssValue("border-radius", "4px"));
    inviteUsersPage().getDeleteUsersButton().shouldBe(cssValue("cursor", "pointer"));
    inviteUsersPage().getDeleteUsersButton().shouldBe(cssValue("margin-left", "8px"));

    inviteUsersPage().getSelectedItemCounterText().shouldBe(visible);
    inviteUsersPage().getSelectedText().shouldBe(matchText(" selected."));
    inviteUsersPage().getSelectedItemCounterText().shouldBe(cssValue("font-weight", "800"));
    inviteUsersPage().getSelectedItemCounterText().shouldBe(cssValue("box-sizing", "border-box"));
    inviteUsersPage().getSelectedItemCounterText().shouldBe(cssValue("font-size", "13px"));
    inviteUsersPage().getSelectedItemCounterText().shouldBe(cssValue("line-height", "19px"));
    inviteUsersPage().getSelectedItemCounterText().shouldBe(cssValue("color", "rgba(0, 0, 0, 1)"));

    inviteUsersPage().getClearSelectionButton().shouldBe(visible);
    inviteUsersPage().getClearSelectionButton().shouldBe(exactText("Clear Selection"));
    inviteUsersPage().getClearSelectionButton()
        .shouldBe(cssValue("color", "rgba(74, 169, 226, 1)"));
    inviteUsersPage().getClearSelectionButton()
        .shouldBe(cssValue("border-left", "1px solid rgb(202, 207, 207)"));
    inviteUsersPage().getClearSelectionButton().shouldBe(cssValue("padding", "1px 0px 0px 8px"));
    inviteUsersPage().getClearSelectionButton().shouldBe(cssValue("padding", "1px 0px 0px 8px"));
    inviteUsersPage().getClearSelectionButton().shouldBe(cssValue("cursor", "pointer"));
    inviteUsersPage().getClearSelectionButton().shouldBe(cssValue("margin-left", "8px"));
    inviteUsersPage().getClearSelectionButton()
        .shouldBe(cssValue("color", "rgba(74, 169, 226, 1)"));
    inviteUsersPage().getClearSelectionButton().shouldBe(cssValue("line-height", "18px"));
    inviteUsersPage().getClearSelectionButton().shouldBe(cssValue("font-size", "13px"));
  }

  /**
   * Assert selection panel - User Already Exists.
   */
  public static void asserSelectionPanelExistingUser() {
    inviteUsersPage().getAssignButton().shouldBe(visible);
    inviteUsersPage().getAssignButton().shouldBe(exactText("Assign..."));
    inviteUsersPage().getAssignButton().shouldBe(cssValue("height", "40px"));
    inviteUsersPage().getAssignButton().shouldBe(cssValue("width", "152px"));
    inviteUsersPage().getAssignButton().shouldBe(cssValue("border-radius", "4px"));
    inviteUsersPage().getAssignButton().shouldBe(cssValue("cursor", "pointer"));
    inviteUsersPage().getAssignButton().shouldBe(cssValue("color", "rgba(109, 127, 140, 1)"));

    inviteUsersPage().getDeleteUsersButton().shouldBe(visible);
    inviteUsersPage().getDeleteUsersButton().shouldBe(cssValue("height", "40px"));
    inviteUsersPage().getDeleteUsersButton().shouldBe(cssValue("width", "40px"));
    inviteUsersPage().getDeleteUsersButton().shouldBe(cssValue("align-items", "center"));
    inviteUsersPage().getDeleteUsersButton().shouldBe(cssValue("align-items", "center"));
    inviteUsersPage().getDeleteUsersButton().shouldBe(cssValue("border-radius", "4px"));
    inviteUsersPage().getDeleteUsersButton().shouldBe(cssValue("cursor", "pointer"));
    inviteUsersPage().getDeleteUsersButton().shouldBe(cssValue("margin-left", "8px"));

    inviteUsersPage().getDeleteExistingUsersButton().shouldBe(visible);
    inviteUsersPage().getDeleteExistingUsersButton().shouldBe(cssValue("height", "40px"));
    inviteUsersPage().getDeleteExistingUsersButton().shouldBe(cssValue("width", "40px"));
    inviteUsersPage().getDeleteExistingUsersButton().shouldBe(cssValue("align-items", "center"));
    inviteUsersPage().getDeleteExistingUsersButton().shouldBe(cssValue("align-items", "center"));
    inviteUsersPage().getDeleteExistingUsersButton().shouldBe(cssValue("border-radius", "4px"));
    inviteUsersPage().getDeleteExistingUsersButton().shouldBe(cssValue("cursor", "pointer"));
    inviteUsersPage().getDeleteExistingUsersButton().shouldBe(cssValue("margin-left", "8px"));

    inviteUsersPage().getSelectedItemCounterText().shouldBe(visible);
    inviteUsersPage().getSelectedText().shouldBe(matchText(" selected."));
    inviteUsersPage().getSelectedItemCounterText().shouldBe(cssValue("font-weight", "800"));
    inviteUsersPage().getSelectedItemCounterText().shouldBe(cssValue("box-sizing", "border-box"));
    inviteUsersPage().getSelectedItemCounterText().shouldBe(cssValue("font-size", "13px"));
    inviteUsersPage().getSelectedItemCounterText().shouldBe(cssValue("line-height", "19px"));
    inviteUsersPage().getSelectedItemCounterText().shouldBe(cssValue("color", "rgba(0, 0, 0, 1)"));

    inviteUsersPage().getClearSelectionButton().shouldBe(visible);
    inviteUsersPage().getClearSelectionButton().shouldBe(exactText("Clear Selection"));
    inviteUsersPage().getClearSelectionButton()
        .shouldBe(cssValue("color", "rgba(74, 169, 226, 1)"));
    inviteUsersPage().getClearSelectionButton()
        .shouldBe(cssValue("border-left", "1px solid rgb(202, 207, 207)"));
    inviteUsersPage().getClearSelectionButton().shouldBe(cssValue("padding", "1px 0px 0px 8px"));
    inviteUsersPage().getClearSelectionButton().shouldBe(cssValue("padding", "1px 0px 0px 8px"));
    inviteUsersPage().getClearSelectionButton().shouldBe(cssValue("cursor", "pointer"));
    inviteUsersPage().getClearSelectionButton().shouldBe(cssValue("margin-left", "8px"));
    inviteUsersPage().getClearSelectionButton()
        .shouldBe(cssValue("color", "rgba(74, 169, 226, 1)"));
    inviteUsersPage().getClearSelectionButton().shouldBe(cssValue("line-height", "18px"));
    inviteUsersPage().getClearSelectionButton().shouldBe(cssValue("font-size", "13px"));
  }


}
