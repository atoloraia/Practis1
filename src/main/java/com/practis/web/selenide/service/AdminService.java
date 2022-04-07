package com.practis.web.selenide.service;

import com.practis.dto.NewAdminInput;
import com.practis.web.selenide.page.admin.AdminCreatePage;

public class AdminService {

  private final AdminCreatePage createPage = new AdminCreatePage();

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
