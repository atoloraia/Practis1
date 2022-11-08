package com.practis.web.selenide.service.company.team;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.manageTeamPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.manageTeamService;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.practis.web.selenide.component.GridRow;

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
   * Find user checkbox.
   */
  public SelenideElement findUserCheckbox(final String user) {
    final var userRow = manageTeamPage().getUserRow().find(Condition.matchText(user));
    final var checkbox = userRow.$("[data-test='team-all-users-item-checkbox']").sibling(0);
    return checkbox.parent();
  }

  /**
   * Search Member on 'Team Members' section.
   */
  public GridRow searchMember(final String user) {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    search().search(user);
    return awaitGridRowExists(5, () -> grid().getRow(user));
  }





}
