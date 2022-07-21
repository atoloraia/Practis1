package com.practis.web.selenide.service.company.user;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserTeamModal;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

public class InviteUserTeamService {

  /**
   * Search Team.
   */
  public void searchTeam(final String input) {
    inviteUserTeamModal().getSearchField().setValue(input.substring(0, input.length() - 1));
    inviteUserTeamModal().getSearchField().append(input.substring(input.length() - 1));
  }

  /**
   * Select All Team.
   */
  public void selectAllTeam() {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUserTeamModal().getSelectedAllButton().click();
  }

  /**
   * Unselect All Team.
   */
  public void unSelectAllTeam() {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUserTeamModal().getUnSelectedAllButton().click();
  }
}
