package com.practis.web.selenide.service.company.team;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.teamModule;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertLabelCountOnTeamsPage;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertTeamGridRow;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static com.practis.web.util.SelenidePageUtil.openPage;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.CollectionCondition;
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
  /**
   * Search field.
   */
  private static final SelenideElement searchFieldElement = $("input[data-test*='-search-input']");

  /**
   * Assert Search should be performed after entering 1 characters.
   */
  public static void assertTeamsSearchAfter1CharTeamsPage(final String searchString) {
    final var input = searchString.charAt(searchString.length() - 1);
    //teamsPage().getTeamSearchField().setValue(String.valueOf(input));
    teamsPage().getTeamSearchField().append(String.valueOf(input));
    teamsPage().getTeamSearchFieldCrossButton().shouldBe(Condition.visible);
    teamsPage().getTeamRow().get(0).shouldBe(visible);
    teamsPage().getTeamSearchFieldCrossButton().click();
  }

  /**
   * Assert no search results.
   */
  public static void assertNoTeamSearchResultTeamsPage() {
    await().pollDelay(FIVE_SECONDS).until(() -> true);
    teamsPage().getNoTeamsFoundIcon().shouldBe(visible);
    teamsPage().getNoTeamsFoundText().shouldBe(visible);
    teamsPage().getNoTeamsFoundText().shouldBe(exactText("No Teams Found"));
    teamsPage().getTeamsItemsCounter().shouldBe(visible);
    teamsPage().getTeamsItemsCounter().shouldBe(exactText("0 Items"));
    teamsPage().getTeamFilterButton().shouldBe(visible);
    teamsPage().getTeamFilterButton().shouldBe(disabled);
    teamsPage().getTeamsColumn().shouldBe(visible);
    teamsPage().getTeamMembersColumn().shouldBe(visible);
    teamsPage().getTeamPractisSetsColumn().shouldBe(visible);
    teamsPage().getTeamTeamLeadersColumn().shouldBe(visible);
    teamsPage().getTeamRow().shouldBe(CollectionCondition.size(0));
    teamsPage().getTeamSearchFieldCrossButton().click();
  }

  /**
   * Assert clean search on Teams page.
   */

  public static void assertCleanSearchTeamPage(int teamRows, final String input) {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    teamsPage().getTeamSearchFieldCrossButton().shouldNotBe(visible);
    teamsPage().getTeamRow().shouldHave(CollectionCondition.size(teamRows));
    searchFieldElement.setValue(input.substring(0, input.length() - 1));
    searchFieldElement.append(input.substring(input.length() - 1));
    //teamsPage().getTeamSearchField().setValue(("check clean icon"));
    teamsPage().getTeamSearchFieldIcon().shouldBe(visible);
    teamsPage().getTeamRow().shouldHave(CollectionCondition.size(0));
    teamsPage().getTeamSearchFieldCrossButton().click();
    teamsPage().getTeamSearchFieldCrossButton().shouldNotBe(visible);
    teamsPage().getTeamRow().shouldHave(CollectionCondition.size(teamRows));
  }

}
