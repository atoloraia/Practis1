package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersToTheAppPage;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.ONE_SECOND;
import static org.awaitility.Duration.TWO_SECONDS;

import com.practis.dto.NewUserInput;
import org.awaitility.Awaitility;
import org.awaitility.Duration;

public class InviteUserService {

  /**
   * User Role: Fill User row.
   */
  public void userRoleFillRow(final NewUserInput inputData, final String label, final String team) {
    inviteUsersToTheAppPage().getFirstNameField().append(inputData.getFirstName());
    inviteUsersToTheAppPage().getLastNameField().append(inputData.getLastName());
    inviteUsersToTheAppPage().getEmailField().append(inputData.getEmail());
    inviteUsersToTheAppPage().getRoleField().click();
    inviteUsersToTheAppPage().getRoleCheckbox().get(0).click();

    //todo ask fe team why we have to wait
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUsersToTheAppPage().getLabelsField().click();
    //todo ask fe team why we have to wait
    await().pollDelay(TWO_SECONDS).until(() -> true);
    addLabel(label);
    inviteUsersToTheAppPage().getTeamsField().click();
    //todo ask fe team why we have to wait
    await().pollDelay(TWO_SECONDS).until(() -> true);
    addTeam(team);
  }

  /**
   * User Role: Fill Admin row.
   */
  public void adminRoleFillRow(final NewUserInput inputData,
      final String label, final String team) {
    inviteUsersToTheAppPage().getFirstNameField().append(inputData.getFirstName());
    inviteUsersToTheAppPage().getLastNameField().append(inputData.getLastName());
    inviteUsersToTheAppPage().getEmailField().append(inputData.getEmail());
    inviteUsersToTheAppPage().getRoleField().click();
    inviteUsersToTheAppPage().getRoleCheckbox().get(1).click();

    //todo ask fe team why we have to wait
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUsersToTheAppPage().getLabelsField().click();
    //todo ask fe team why we have to wait
    await().pollDelay(TWO_SECONDS).until(() -> true);
    addLabel(label);
    inviteUsersToTheAppPage().getTeamsField().click();
    //todo ask fe team why we have to wait
    await().pollDelay(TWO_SECONDS).until(() -> true);
    addTeam(team);
  }

  public void clickInviteSelectedUserButton() {
    inviteUsersToTheAppPage().getCheckboxAddedUserRow().get(0).sibling(0).click();
    inviteUsersToTheAppPage().getInviteSelectedUsersButton().click();
  }

  public void addRow() {
    await().pollDelay(ONE_SECOND).until(() -> true);
    inviteUsersToTheAppPage().getAddRowButton().lastChild().click();
  }

  public void addLabel(final String label) {
    inviteUsersToTheAppPage().findLabelCheckbox(label).click();
    inviteUsersToTheAppPage().getApplyLabelButton().click();
  }

  public void addTeam(final String label) {
    inviteUsersToTheAppPage().findTeamCheckbox(label).click();
    inviteUsersToTheAppPage().getApplyTeam().click();
  }

}
