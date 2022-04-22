package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.PageObjectFactory.teamCreatePage;

import com.practis.dto.NewTeamInput;

public class TeamsService {

  public void createTeam(final NewTeamInput inputData) {
    teamCreatePage().fillForm(inputData);
  }
}
