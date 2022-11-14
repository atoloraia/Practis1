package com.practis.web.selenide.service.company.team;

import static com.practis.web.selenide.configuration.PageObjectFactory.manageTeamPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.assignUserModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.manageTeamService;
import static com.practis.web.util.SelenidePageLoadAwait.awaitAjaxComplete;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import java.util.List;

public class ManageTeamService {

  /**
   * Add selected User.
   */
  public ManageTeamService addSelectedUser(final String user) {
    manageTeamService().findUserCheckbox(user).click();
    awaitAjaxComplete(2);
    manageTeamPage().getAddSelectedUsersButton().click();
    await().pollDelay(TWO_SECONDS).until(() -> true);
    return null;
  }

  /**
   * Remove Team Members.
   */
  public ManageTeamService removeSelectedUser(final String user) {
    manageTeamService().findMemberCheckbox(user).click();
    manageTeamPage().getRemoveSelectedUsersButton().click();
    await().pollDelay(TWO_SECONDS).until(() -> true);
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
   * Find Team Member checkbox.
   */
  public SelenideElement findMemberCheckbox(final String user) {
    final var userRow = manageTeamPage().getTeamMemberRow().find(Condition.matchText(user));
    final var checkbox = userRow.$("[data-test='team-members-item-checkbox']").sibling(0);
    return checkbox.parent();
  }

  /**
   * Add Label to the Team.
   */
  public ManageTeamService addLabelToTeam(final List<RestCreateLabelResponse> label) {
    manageTeamPage().getAssignLabelsButton().click();
    labelModuleService().selectLabel(label.get(0).getName());
    assignUserModuleService().applyLabel();
    return null;
  }


}
