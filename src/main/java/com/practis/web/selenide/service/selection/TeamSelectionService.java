package com.practis.web.selenide.service.selection;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.teamModule;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class TeamSelectionService {

  /**
   * Find team checkbox.
   */
  public SelenideElement findTeamCheckbox(final String team) {
    final var teamRow = teamModule().getTeamRows().find(Condition.matchText(team));
    final var checkbox = teamRow.$("[data-test='team-item-checkbox']").sibling(0);
    return checkbox.parent();
  }

  /**
   * Find selected team checkbox.
   */
  public SelenideElement findSelectedTeamCheckbox(final String team) {
    final var teamRow = teamModule().getTeamRows().find(Condition.matchText(team));
    return teamRow.$("[data-test='team-item-checkbox']");
  }

  /**
   * Search Team.
   */
  public void searchTeam(final String input) {
    teamModule().getSearchField().setValue(input.substring(0, input.length() - 1));
    teamModule().getSearchField().append(input.substring(input.length() - 1));
  }

  /**
   * Select All Team.
   */
  public void selectAllTeam() {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    teamModule().getSelectedAllButton().click();
  }

  /**
   * Unselect All Team.
   */
  public void unSelectAllTeam() {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    teamModule().getUnSelectedAllButton().click();
  }
}
