package com.practis.web.selenide.service.company.team;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static org.awaitility.Awaitility.await;

import com.practis.web.selenide.component.GridRow;

public class TrainingTabService {

  /**
   * Search Team on grid by Team Name.
   */

  public GridRow searchTraining(final String name) {
    search().search(name);

    return awaitGridRowExists(5, () -> grid().getRow(name));
  }

}
