package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.PageObjectFactory.manageTeamPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.manageTeamService;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class ManageTeamService {

  /**
   * Add selected User.
   */
  public ManageTeamService addSelectedUser(final String user) {
    manageTeamService().findUserCheckbox(user).click();
    manageTeamPage().getAddSelectedUsersButton().click();
    return null;
  }

  /**
   * Find team checkbox.
   */
  public SelenideElement findUserCheckbox(final String user) {
    final var userRow = manageTeamPage().getUserRow().find(Condition.matchText(user));
    final var checkbox = userRow.$("[data-test='team-all-users-item-checkbox']").sibling(0);
    return checkbox.parent();
  }



}
