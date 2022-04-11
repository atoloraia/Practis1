package com.practis.web.selenide.service;

import com.practis.dto.NewAdminInput;
import com.practis.web.selenide.component.Grid;
import com.practis.web.selenide.component.GridRow;
import com.practis.web.selenide.component.Navigation;
import com.practis.web.selenide.component.Search;
import com.practis.web.selenide.page.admin.AdminCreatePage;
import com.practis.web.selenide.page.admin.AdminEditPage;
import lombok.Getter;

public class AdminService {

  private final Navigation navigation = new Navigation();
  private final Search search = new Search();
  private final Grid grid = new Grid();

  private final AdminCreatePage createPage = new AdminCreatePage();
  @Getter
  private final AdminEditPage editPage = new AdminEditPage();

  /**
   * Search admin on grid by email.
   */
  public GridRow searchAdmin(final String email) {
    navigation.goTo("Admin");
    search.search(email);

    return grid.getRow(email);
  }

  public void createAdmin(final NewAdminInput input) {
    createAdmin(input, 0);
  }

  private void createAdmin(final NewAdminInput input, final int rowNum) {
    createPage.getEmailFieldElements().get(0).sendKeys(input.getEmail());
    createPage.getFirstNameFieldElements().get(0).sendKeys(input.getFirstName());
    createPage.getLastNameFieldElements().get(0).sendKeys(input.getLastName());
    createPage.getPasswordFieldElements().get(0).sendKeys(input.getPassword());

    createPage.getCreateButtonElement().click();
  }
}
