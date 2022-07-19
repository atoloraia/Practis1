package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserLabelModel;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserRoleModel;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserTeamModal;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;
import static org.awaitility.Duration.ONE_SECOND;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewUserInput;
import com.practis.web.selenide.component.GridRow;
import org.awaitility.Duration;

public class InviteUserService {

  /**
   * User Role: Fill User row.
   */
  public void userRoleFillRow(final NewUserInput inputData, final String label, final String team) {
    inviteUsersPage().getFirstNameField().append(inputData.getFirstName());
    inviteUsersPage().getLastNameField().append(inputData.getLastName());
    inviteUsersPage().getEmailField().append(inputData.getEmail());
    inviteUsersPage().getRoleField().click();
    inviteUserRoleModel().getUserRoleRadioButton().click();

    //todo ask fe team why we have to wait
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUsersPage().getLabelsField().click();
    //todo ask fe team why we have to wait
    await().pollDelay(TWO_SECONDS).until(() -> true);
    addLabel(label);
    inviteUsersPage().getTeamsField().click();
    //todo ask fe team why we have to wait
    await().pollDelay(TWO_SECONDS).until(() -> true);
    addTeam(team);
  }

  /**
   * User Role: Fill Admin row.
   */
  public void adminRoleFillRow(final NewUserInput inputData, final String label,
      final String team) {
    inviteUsersPage().getFirstNameField().append(inputData.getFirstName());
    inviteUsersPage().getLastNameField().append(inputData.getLastName());
    inviteUsersPage().getEmailField().append(inputData.getEmail());
    inviteUsersPage().getRoleField().click();
    inviteUserRoleModel().getAdminRoleRadioButton().click();

    //todo ask fe team why we have to wait
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUsersPage().getLabelsField().click();
    //todo ask fe team why we have to wait
    await().pollDelay(FIVE_SECONDS).until(() -> true);
    addLabel(label);
    inviteUsersPage().getTeamsField().click();
    //todo ask fe team why we have to wait
    await().pollDelay(TWO_SECONDS).until(() -> true);
    addTeam(team);
  }

  /**
   * Fill form with wrong email format.
   */
  public void wrongEmailFormatFillRow(final NewUserInput inputData) {
    inviteUsersPage().getFirstNameField().append(inputData.getFirstName());
    inviteUsersPage().getLastNameField().append(inputData.getLastName());
    inviteUsersPage().getEmailField().append("test");
    inviteUsersPage().getRoleField().click();
    inviteUserRoleModel().getUserRoleRadioButton().click();
  }

  /**
   * Select first User checkbox and click 'Invite Selected Users' button.
   */
  public void clickInviteSelectedUserButton() {
    inviteUsersPage().getCheckboxAddedUserRow().get(0).sibling(0).click();
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
   * Edit User row.
   */
  public void editRow(
      int rowNum, final NewUserInput inputData, final String label, final String team) {
    final var hoveredElement = inviteUsersPage().getAddedUserCell().get(9);
    Selenide.actions().moveToElement(hoveredElement).perform();
    inviteUsersPage().getEditRowButton().get(rowNum).click();
    inviteUsersPage().getEditRoleField().click();
    inviteUserRoleModel().getEditRoleUserRadioButton().click();
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUsersPage().getEditFirstNameField().clear();
    inviteUsersPage().getEditFirstNameField().append(inputData.getFirstName());
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUsersPage().getEditLastNameField().clear();
    inviteUsersPage().getEditLastNameField().append(inputData.getLastName());
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUsersPage().getEditEmailField().clear();
    inviteUsersPage().getEditEmailField().append(inputData.getEmail());
    await().pollDelay(TWO_SECONDS).until(() -> true);
  }


  /**
   * Apply Edit changes.
   */
  public void applyEditChanges(int rowNum) {
    inviteUsersPage().getApplyEditChangesButton().click();
  }

  /**
   * Cancel Edit changes.
   */
  public void cancelEditChanges(int rowNum) {
    inviteUsersPage().getCancelEditChangesButton().click();
  }


  /**
   * Select Label in label dropdown.
   */
  public void addLabel(final String label) {
    inviteUsersPage().findLabelCheckbox(label).click();
    inviteUserLabelModel().getApplyButton().click();
  }

  /**
   * Select Team in Team dropdown.
   */
  public void addTeam(final String label) {
    inviteUsersPage().findTeamCheckbox(label).click();
    inviteUserTeamModal().getApplyButton().click();
  }

  /**
   * Search User on grid by User Name.
   */
  public GridRow searchUser(final String name) {
    search().search(name);
    return awaitGridRowExists(5, () -> grid().getRow(name));
  }

}
