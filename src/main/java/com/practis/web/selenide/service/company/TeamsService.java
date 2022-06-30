package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.PageObjectFactory.teamCreatePage;

import com.practis.dto.NewTeamInput;

public class TeamsService {
  /**
   * Fill Create New Team form.
   */
  public void createTeam(final NewTeamInput inputData) {
    teamCreatePage().getTitleField().append(inputData.getName());

    teamCreatePage().getUserRows().get(0).$("input[type=checkbox]").parent().click();
    teamCreatePage().getAddSelectedUsersButton().click();
    teamCreatePage().getCreateButton().click();
  }
}
