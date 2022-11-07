package com.practis.web.selenide.service.company.team;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static com.practis.web.util.SelenidePageUtil.openPage;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

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
   * Open Team page.
   */
  public void openTeamPage() {
    openPage(webApplicationConfig().getUrl() + "/teams");
  }

}
