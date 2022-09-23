package com.practis.web.selenide.service.company;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserRoleModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.saveAsDraftService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.unsavedProgressPopUpService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInputs;
import static com.practis.web.selenide.configuration.data.company.UploadTemplateInputData.getUploadTemplateInputs;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUserGridRowDraft;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.SECONDS;
import static java.util.stream.Collectors.toList;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.ONE_SECOND;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewUserInput;
import com.practis.utils.XmlService;
import com.practis.web.selenide.component.GridRow;
import com.practis.web.selenide.configuration.ComponentObjectFactory;
import com.practis.web.util.SelenideJsUtils;
import java.io.File;
import java.util.List;

public class InviteUserService {

  /**
   * Generate User inputs.
   */
  public List<NewUserInput> generateUserInputs(int limit) {
    final var inputs = getNewUserInputs().stream().limit(limit)
        .peek(input -> {
          input.setEmail(format(input.getEmail(), timestamp()));
          input.setFirstName(format(input.getFirstName(), timestamp()));
        })
        .collect(toList());
    return inputs;
  }


  /**
   * Generate User inputs.
   */
  public List<NewUserInput> generateUserData(int limit, List<String> usersToRemove) {
    final var inputs = userService().generateUserInputs(limit);
    inputs.forEach(input -> usersToRemove.add(input.getEmail()));
    return inputs;
  }

  /**
   * Generate User inputs.
   */
  public void uploadTemplate(File file) {
    inviteUsersPage().getUploadTemplateButton().parent().$("input").uploadFile(file);
  }

  /**
   * Generate XML file with User data.
   */
  public File getXml(final List<NewUserInput> inputs, final String role) {
    final var file = new File("test.xls");
    final var service = new XmlService(
        "/configuration/web/input/template/upload.xlsx", "List Of Users");
    inputs.forEach(input -> service.setUserRow(
        input.getFirstName(), input.getLastName(), input.getEmail(), role));
    service.write(file);
    return file;
  }

  /**
   * User Row: Fill First Name, Last Name and Email row.
   */
  public InviteUserService fillText(final NewUserInput inputData) {
    inviteUsersPage().getFirstNameField().append(inputData.getFirstName());
    inviteUsersPage().getLastNameField().append(inputData.getLastName());
    inviteUsersPage().getEmailField().append(inputData.getEmail());
    return this;
  }

  /**
   * User Row: select role.
   */
  public InviteUserService selectRole(final String role) {
    inviteUsersPage().getRoleField().click();
    switch (role) {
      case "Admin":
        inviteUserRoleModule().getAdminRoleRadioButton().click();
        break;
      case "User":
        inviteUserRoleModule().getUserRoleRadioButton().click();
        break;
      default:
        throw new RuntimeException(format("Unknown role: %s", role));
    }
    return this;
  }

  /**
   * User Row: select label.
   */
  public InviteUserService selectLabel(final String label) {
    await().pollDelay(ONE_SECOND).until(() -> true);
    inviteUsersPage().getLabelsField().click();
    await().pollDelay(ONE_SECOND).until(() -> true);
    inviteUsersPage().findLabelCheckbox(label).click();
    labelModule().getApplyButton().click();
    return this;
  }

  /**
   * User Row: select team.
   */
  public InviteUserService selectTeam(final String team) {
    inviteUsersPage().getTeamsField().click();
    await().pollDelay(ONE_SECOND).until(() -> true);
    teamService().findTeamCheckbox(team).click();
    ComponentObjectFactory.teamModule().getApplyButton().click();
    return null;
  }

  /**
   * Fill form with wrong email format.
   */
  public void wrongEmailFormatFillRow(final NewUserInput inputData) {
    inviteUsersPage().getFirstNameField().append(inputData.getFirstName());
    inviteUsersPage().getLastNameField().append(inputData.getLastName());
    inviteUsersPage().getEmailField().append("test");
    inviteUsersPage().getRoleField().click();
    inviteUserRoleModule().getUserRoleRadioButton().click();
  }

  /**
   * Select first User checkbox and click "Assign".
   */
  public void assignFirstUser() {
    await().pollDelay(ONE_SECOND).until(() -> true);
    inviteUsersPage().getCheckboxAddedUserRow().get(0).click();
    inviteUsersPage().getAssignButton().click();
  }

  /**
   * Select first User checkbox and click 'Invite Selected Users' button.
   */
  public void inviteFirstUser() {
    inviteUsersPage().getCheckboxAddedUserRow().get(0).click();
    await().pollDelay(ONE_SECOND).until(() -> true);
    inviteUsersPage().getInviteSelectedUsersButton().click();
  }

  /**
   * Select all Users and click 'Invite Selected Users' button.
   */
  public void inviteAllUser() {
    inviteUsersPage().getSelectAllCheckbox().click();
    await().pollDelay(ONE_SECOND).until(() -> true);
    inviteUsersPage().getInviteSelectedUsersButton().click();
  }

  /**
   * Select some Users and click 'Invite Selected Users' button.
   */
  public void inviteSomeUser(int row1, int row2) {
    await().pollDelay(ONE_SECOND).until(() -> true);
    inviteUsersPage().getCheckboxAddedUserRow().get(row1).click();
    inviteUsersPage().getCheckboxAddedUserRow().get(row2).click();
    await().pollDelay(ONE_SECOND).until(() -> true);
    inviteUsersPage().getInviteSelectedUsersButton().click();
  }


  /**
   * Fill First Name, Last Name, Email, Role, Team and Role and click + button.
   */
  public void addRow(NewUserInput inputData, String role, String label, String team) {
    await().pollDelay(ONE_SECOND).until(() -> true);
    fillText(inputData);
    selectRole(role);
    selectLabel(label);
    selectTeam(team);
    inviteUsersPage().getAddRowButton().lastChild().click();
  }

  /**
   * Click + button.
   */
  public void addRow() {
    await().pollDelay(ONE_SECOND).until(() -> true);
    inviteUsersPage().getAddRowButton().lastChild().click();
  }

  /**
   * Fill First Name, Last Name, Email, Role and click + button.
   */
  public void addRow(NewUserInput inputData, String role) {
    await().pollDelay(ONE_SECOND).until(() -> true);
    fillText(inputData);
    selectRole(role);
    inviteUsersPage().getAddRowButton().lastChild().click();
  }

  /**
   * Delete User row.
   */
  public void deleteRow(int rowNum) {
    final var hoveredElement = inviteUsersPage().getAddedUserCell().get(9);
    Selenide.actions().moveToElement(hoveredElement).perform();
    inviteUsersPage().getDeleteRowButton().get(rowNum).click();
  }

  /**
   * Click Edit button.
   */
  public InviteUserService clickEdit(int rowNum) {
    final var hoveredElement = inviteUsersPage().getAddedUserCell().get(9);
    Selenide.actions().moveToElement(hoveredElement).perform();
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUsersPage().getEditRowButton().click();
    return this;
  }

  /**
   * Edit text.
   */
  public InviteUserService editText(final NewUserInput inputData) {
    inviteUsersPage().getEditFirstNameField().clear();
    inviteUsersPage().getEditFirstNameField().append(inputData.getFirstName());
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUsersPage().getEditLastNameField().clear();
    inviteUsersPage().getEditLastNameField().append(inputData.getLastName());
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUsersPage().getEditEmailField().clear();
    inviteUsersPage().getEditEmailField().append(inputData.getEmail());
    await().pollDelay(TWO_SECONDS).until(() -> true);
    return this;
  }

  /**
   * Edit role.
   */
  public InviteUserService editRole(final String role) {
    inviteUsersPage().getEditRoleField().click();
    switch (role) {
      case "Admin":
        inviteUserRoleModule().getAdminRoleRadioButton().click();
        break;
      case "User":
        inviteUserRoleModule().getUserRoleRadioButton().click();
        break;
      default:
        throw new RuntimeException(format("Unknown role: %s", role));
    }
    return this;
  }

  /**
   * Apply Edit changes.
   */
  public InviteUserService applyEditChanges(int rowNum) {
    inviteUsersPage().getApplyEditChangesButton().click();
    return this;
  }

  /**
   * Cancel Edit changes.
   */
  public InviteUserService cancelEditChanges(int rowNum) {
    inviteUsersPage().getCancelEditChangesButton().click();
    return this;
  }

  /**
   * Search User on grid by User Name.
   */
  public GridRow searchUser(final String name) {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    search().search(name);
    return awaitGridRowExists(5, () -> grid().getRow(name));
  }

  /**
   * Click Save As Draft button.
   */
  public void clickSaveAsDraftButton() {
    inviteUsersPage().getCheckboxAddedUserRow().get(0).click();
    await().pollDelay(ONE_SECOND).until(() -> true);
    inviteUsersPage().getSaveAsDraftButton().click();

  }

  /**
   * Save as draft.
   */
  public void saveAsDraft(String draftName) {
    inviteUsersPage().getCheckboxAddedUserRow().get(0).click();
    await().pollDelay(ONE_SECOND).until(() -> true);
    inviteUsersPage().getSaveAsDraftButton().click();
    saveAsDraftService().saveAsDraft(draftName);
  }

  /**
   * Cancel 'Save as draft' action.
   */
  public void cancelSaveAsDraft() {
    inviteUsersPage().getCheckboxAddedUserRow().get(0).click();
    await().pollDelay(ONE_SECOND).until(() -> true);
    inviteUsersPage().getSaveAsDraftButton().click();
    saveAsDraftService().clickCancel();
  }


  /**
   * Open 'Pending' list without saving.
   */
  public void openPendingUsersListWithoutSaving() {
    SelenideJsUtils.jsClick(inviteUsersPage().getOutsideTheForm());
    unsavedProgressPopUpService().clickExitWithoutSavingButton();
    navigationCompanies().getUsersNavigationItem().click();
    await().pollDelay(TWO_SECONDS).until(() -> true);
    usersPage().getTabs().get(1).click();
    await().pollDelay(TWO_SECONDS).until(() -> true);
  }

  /**
   * Open 'Pending' list without saving.
   */
  public void openPendingUsersList() {
    navigationCompanies().getUsersNavigationItem().click();
    await().pollDelay(TWO_SECONDS).until(() -> true);
    usersPage().getTabs().get(1).click();
    await().pollDelay(TWO_SECONDS).until(() -> true);
  }

  /**
   * Open 'Draft' list.
   */
  public void openDraftUsersList() {
    await().pollDelay(1, SECONDS).until(() -> true);
    navigationCompanies().getUsersNavigationItem().click();
    await().pollDelay(1, SECONDS).until(() -> true);
    usersPage().getTabs().get(2).click();
  }

  /**
   * Open draft from list.
   */
  public void openDraftFromList(String draftName) {
    final var userGridRow = userService().searchUser(draftName);
    assertUserGridRowDraft(draftName, userGridRow);
    userGridRow.click();
  }

  /**
   * Exit the page without saving.
   */
  public void exitWithoutSaving() {
    SelenideJsUtils.jsClick(inviteUsersPage().getOutsideTheForm());
    unsavedProgressPopUpService().clickExitWithoutSavingButton();
  }

  /**
   * Generate template.
   */
  public File generateTemplate(String firstName, String lastName, String email, String role) {
    final var file = new File("test.xls");
    new XmlService("/configuration/web/input/template/upload.xlsx", "List Of Users")
        .set("First Name", firstName)
        .set("Last Name", lastName)
        .set("Email", email)
        .set("Role", role)
        .write(file);
    return file;
  }

}
