package com.practis.web.selenide.service.company.team;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.keepTrackPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.membersTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertLabelCountOnTeamsPage;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertTeamGridRow;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static com.practis.web.util.SelenidePageUtil.openPage;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;
import static org.awaitility.Duration.TWO_SECONDS;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.practis.dto.NewTeamInput;
import com.practis.web.selenide.component.GridRow;
import com.practis.web.util.AwaitUtils;
import com.practis.web.util.SelenideJsUtils;
import java.util.Objects;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
   * Open Team page.
   */
  public void openTeamTeamsPage(final NewTeamInput inputData) {
    var teamRow = teamsPageService().searchTeam(inputData.getName());
    teamRow.click();
    await().pollDelay(TWO_SECONDS).until(() -> true);
  }

  /**
   * Open Manage Team page.
   */
  public void openManageTeamFromTeamsPage(final String team) {
    var teamRow = teamsPageService().searchTeam(team);
    teamRow.click();
    await().pollDelay(TWO_SECONDS).until(() -> true);
    keepTrackPopUp().getGotItButton().click();
    teamPage().getMembersTab().click();
    await().pollDelay(TWO_SECONDS).until(() -> true);
    membersTab().getMembersManageTeamButton().click();
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
  public void clickSingleActionAllMembers() {
    final var teamRow = teamsPage().getTeamsAllMembersRow();
    teamRow.$("div[data-test='teams-item-menu-button']").click();
  }

  /**
   * Click 3-dot menu for the team.
   */
  public void clickSingleActionTeam(final String team) {
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

  /**
   * Click "Assign Labels' on 3-dot menu for All Members team.
   */
  public void clickAssignLabelsSingleAction() {
    teamsPage().getAssignLabelsSingleAction().click();
  }

  /**
   * Click "Duplicate' on 3-dot menu for All Members team.
   */
  public void clickDuplicateSingleAction() {
    teamsPage().getDuplicateSingleAction().click();
    await().pollDelay(FIVE_SECONDS).until(() -> true);
  }

  /**
   * Search field.
   */
  private static final SelenideElement searchFieldElement = $("input[data-test*='-search-input']");

}
