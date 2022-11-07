package com.practis.web.selenide.service.company.team;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
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


}
