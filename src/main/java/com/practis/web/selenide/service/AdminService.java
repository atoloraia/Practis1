package com.practis.web.selenide.service;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigation;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.adminCreatePage;

import com.practis.dto.NewAdminInput;
import com.practis.web.selenide.component.GridRow;

public class AdminService {

  /**
   * Search admin on grid by email.
   */
  public GridRow searchAdmin(final String email) {
    navigation().goTo("Admin");
    search().search(email);

    return grid().getRow(email);
  }

  public void createAdmin(final NewAdminInput input) {
    adminCreatePage().fillCreateAdminForm(input, 0);
    adminCreatePage().getCreateButtonElement().click();
  }
}
