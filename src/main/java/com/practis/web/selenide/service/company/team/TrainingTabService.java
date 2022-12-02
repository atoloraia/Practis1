package com.practis.web.selenide.service.company.team;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.keepTrackPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.membersTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.trainingTab;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.practis.web.selenide.component.GridRow;

public class TrainingTabService {

  /**
   * Search Team on grid by Team Name.
   */

  public GridRow searchTraining(final String name) {
    search().search(name);
    return awaitGridRowExists(5, () -> grid().getRow(name));
  }

  /**
   * Open Team: Training Tab: find by name.
   */
  public void openTeamTrainingTab(String teamName) {
    teamsPageService().searchTeam(teamName);
    teamsPage().getTeamRow().get(0).click();
    await().pollDelay(TWO_SECONDS).until(() -> true);
    keepTrackPopUp().getGotItButton().click();
  }

  /**
   * Click 3-dot menu for the Practis Set.
   */
  public void clickTrainingTabSingleAction() {
    trainingTab().getTrainingThreeDotMenu().click();
  }

  /**
   * Click "View Progress' on 3-dot menu for the Practis set.
   */
  public void clickViewProgressSingleAction() {
    trainingTab().getTrainingViewProgressOption().click();
  }

  /**
   * Click "Assign Users' on 3-dot menu for the Practis set.
   */
  public void clickAssignUsersSingleAction() {
    trainingTab().getTrainingAssignUsersOption().click();
  }

}
