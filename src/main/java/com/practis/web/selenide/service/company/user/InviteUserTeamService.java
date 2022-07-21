package com.practis.web.selenide.service.company.user;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserTeamModal;

public class InviteUserTeamService {

  /**
   * Search Team.
   */
  public void searchTeam(final String input) {
    inviteUserTeamModal().getSearchField().setValue(input.substring(0, input.length() - 1));
    inviteUserTeamModal().getSearchField().append(input.substring(input.length() - 1));
  }
}
