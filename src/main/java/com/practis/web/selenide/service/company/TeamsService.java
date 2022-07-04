package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.PageObjectFactory.teamCreatePage;

import com.practis.dto.NewTeamInput;

public class TeamsService {
  /**
   * Fill Create New Team form.
   */
  public void createTeam(final NewTeamInput inputData) {
    teamCreatePage().getTitleField().append(inputData.getName());

    teamCreatePage().getCheckboxUserRow().get(0).parent().click();
    teamCreatePage().getAddSelectedUsersButton().click();
    teamCreatePage().getCreateButton().click();
  }
}
