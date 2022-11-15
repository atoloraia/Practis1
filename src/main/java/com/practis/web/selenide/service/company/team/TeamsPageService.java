package com.practis.web.selenide.service.company.team;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertLabelCountOnTeamsPage;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertTeamGridRow;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static com.practis.web.util.SelenidePageUtil.openPage;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.practis.dto.NewTeamInput;
import com.practis.web.selenide.component.GridRow;

public class TeamsPageService {

  /**
   * Search Team on grid by Team Name.
   */
  public GridRow searchTeam(final String name) {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    search().search(name);
    return awaitGridRowExists(5, () -> grid().getRow(name));
  }

  /**
   * Open Teams page.
   */
  public void openTeamsPage() {
    openPage(webApplicationConfig().getUrl() + "/teams");
  }

  /**
   * Search Team on grid by Team Name.
   */
  public void assertDataOnTeamsPage(final NewTeamInput inputData, String members, String ps,
      String leader, String label) {
    teamsPageService().openTeamsPage();
    var teamRow = teamsPageService().searchTeam(inputData.getName());
    assertTeamGridRow(inputData, members, ps, leader);
    assertLabelCountOnTeamsPage(inputData, label);
    teamRow.click();
    await().pollDelay(TWO_SECONDS).until(() -> true);
  }

  /**
   * Find team labels.
   */
  public SelenideElement findTeamLabelCounter(final String team) {
    final var teamRow = teamsPage().getTeamRow().find(Condition.matchText(team));
    return teamRow.$("[data-test='teams-item-labels']");
  }

  /**
   * Find Members counter.
   */
  public SelenideElement findMembersCounter(final String team) {
    final var teamRow = teamsPage().getTeamRow().find(Condition.matchText(team));
    return teamRow.$("[data-test='teams-item-members']");
  }

  /**
   * Click 3-dot menu for All Members team.
   */
  public void singleActionAllMembers() {
    final var teamRow = teamsPage().getTeamsAllMembersRow();
    teamRow.$("div[data-test='teams-item-menu-button']").click();
  }

  /**
   * Click 3-dot menu for the team.
   */
  public void singleActionTeam(final String team) {
    final var teamRow = teamsPage().getTeamRow().find(Condition.matchText(team));
    teamRow.$("div[data-test='teams-item-menu-button']").click();
  }

  /**
   * Click "View Team' on 3-dot menu for All Members team.
   */
  public void clickViewTeamSingleAction() {
    teamsPage().getViewTeamSingleAction().click();
  }

  /**
   * Click "View Team' on 3-dot menu for All Members team.
   */
  public void clickManageTeamSingleAction() {
    teamsPage().getManageTeamSingleAction().click();
  }
}
