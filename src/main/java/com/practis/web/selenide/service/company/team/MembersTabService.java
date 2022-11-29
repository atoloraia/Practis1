package com.practis.web.selenide.service.company.team;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.keepTrackPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.membersTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.practis.web.selenide.component.GridRow;

public class MembersTabService {

  /**
   * Search Team on grid by Team Name.
   */
  public static GridRow searchMember(final String name) {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    search().search(name);
    return awaitGridRowExists(5, () -> grid().getRow(name));
  }

  /**
   * Open Team: Members Tab: find by name.
   */
  public void openTeamMembersTab(String teamName) {
    teamsPageService().searchTeam(teamName);
    teamsPage().getTeamRow().get(0).click();
    await().pollDelay(TWO_SECONDS).until(() -> true);
    keepTrackPopUp().getGotItButton().click();
    teamPage().getMembersTab().click();
  }

  /**
   * Click 3-dot menu for the team.
   */
  public void clickMembersTabSingleAction() {
    membersTab().getMembersThreeDotMenu().click();
  }

  /**
   * Click "View Profile' on 3-dot menu for the team.
   */
  public void clickViewProfileSingleAction() {
    membersTab().getMembersViewProfileOption().click();
  }

  /**
   * Click "Nudge User' on 3-dot menu for the team.
   */
  public void clickNudgeUserSingleAction() {
    membersTab().getMembersNudgeUserOption().click();
  }


}
