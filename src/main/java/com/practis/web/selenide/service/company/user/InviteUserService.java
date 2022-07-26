package com.practis.web.selenide.service.company.user;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserRoleModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamService;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static java.lang.String.format;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.ONE_SECOND;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewUserInput;
import com.practis.web.selenide.component.GridRow;
import com.practis.web.selenide.configuration.ComponentObjectFactory;

public class InviteUserService {

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
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUsersPage().getLabelsField().click();
    inviteUsersPage().findLabelCheckbox(label).click();
    labelModule().getApplyButton().click();
    return this;
  }

  /**
   * User Row: select team.
   */
  public InviteUserService selectTeam(final String team) {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUsersPage().getTeamsField().click();
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
   * Select first User checkbox and click 'Invite Selected Users' button.
   */
  public void clickInviteSelectedUserButton() {
    inviteUsersPage().getCheckboxAddedUserRow().get(0).sibling(0).click();
    await().pollDelay(ONE_SECOND).until(() -> true);
    inviteUsersPage().getInviteSelectedUsersButton().click();
  }

  /**
   * Click + button.
   */
  public void addRow() {
    await().pollDelay(ONE_SECOND).until(() -> true);
    inviteUsersPage().getAddRowButton().lastChild().click();
  }

  /**
   * Delete User row.
   */
  public void deleteRow(int rowNum) {
    final var hoveredElement = inviteUsersPage().getAddedUserCell().get(9);
    Selenide.actions().moveToElement(hoveredElement).perform();
    inviteUsersPage().getDeleteRowButton().get(rowNum).click();
    System.out.println();
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
        inviteUserRoleModule().getEditRoleUserRadioButton().click();
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

}
