package com.practis.web.selenide.service;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigation;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.adminCreatePage;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.ONE_SECOND;

import com.practis.dto.NewAdminInput;
import com.practis.web.selenide.component.GridRow;

public class AdminService {

  /**
   * Search admin on grid by email.
   */
  public GridRow searchAdmin(final String email) {
    navigation().goTo("Admin");
    search().search(email);

    return awaitGridRowExists(5, () -> grid().getRow(email));
  }

  /**
   * Test.
   */
  public void createAdmin(final NewAdminInput input) {
    await().pollDelay(ONE_SECOND).until(() -> true);
    adminCreatePage().fillCreateAdminForm(input, 0);
    adminCreatePage().getCreateButtonElement().click();
  }
}
