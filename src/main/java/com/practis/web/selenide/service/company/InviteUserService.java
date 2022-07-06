package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersToTheAppPage;
import static com.practis.web.util.SelenideJsUtils.jsClick;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.ONE_SECOND;

import com.practis.dto.NewUserInput;
import com.practis.web.util.SelenideJsUtils;
import org.awaitility.Awaitility;
import org.awaitility.Duration;

public class InviteUserService {

  /**
   * Fill User row.
   */
  public void userRoleFillRow(final NewUserInput inputData, final String label, final String team) {
    inviteUsersToTheAppPage().getFirstNameField().append(inputData.getFirstName());
    inviteUsersToTheAppPage().getLastNameField().append(inputData.getLastName());
    inviteUsersToTheAppPage().getEmailField().append(inputData.getEmail());
    inviteUsersToTheAppPage().getRoleField().click();
    inviteUsersToTheAppPage().getRoleCheckbox().get(0).click();

    //todo ask fe team why we have to wait
    await().pollDelay(ONE_SECOND).until(() -> true);
    inviteUsersToTheAppPage().getLabelsField().click();

    addLabel(label);
    inviteUsersToTheAppPage().getTeamsField().click();
    addTeam(team);
    inviteUsersToTheAppPage().getAddRowLinkButton().click();
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
