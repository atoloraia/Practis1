package com.practis.web.selenide.service;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigation;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.companyCreatePage;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static java.lang.Thread.sleep;

import com.practis.dto.NewCompanyInput;
import com.practis.web.selenide.component.GridRow;
import lombok.SneakyThrows;

public class CompanyService {

  /**
   * Fill create Company form.
   */
  public void fillCreateCompanyForm(final NewCompanyInput input, final int rowNum) {
    companyCreatePage().getCompanyNameFieldElements().get(rowNum).sendKeys(input.getName());
    companyCreatePage().getCompanyEmailFieldElements().get(rowNum).sendKeys(input.getEmail());
    companyCreatePage().getFirstNameFieldElements().get(rowNum).sendKeys(input.getFirstName());
    companyCreatePage().getLastNameFieldElements().get(rowNum).sendKeys(input.getLastName());
  }

  /**
   * Click '+ Add another' button.
   */
  public void addRow() {
    companyCreatePage().getAddRowLinkElement().click();
  }

  /**
   * Click 'Delete' button.
   */
  public void deleteRow(final int rowNum) {
    companyCreatePage().getDeleteRowButtonElements().get(rowNum).click();
  }

  /**
   * Click 'Invite' button.
   */
  @SneakyThrows
  public void clickInvite() {
    sleep(2000);
    companyCreatePage().getInviteButtonElement().click();
  }

  /**
   * Search admin on grid by email.
   */
  public GridRow searchCompany(final String name) {
    navigation().companyNavigationItem.click();
    search().search(name);
    return awaitGridRowExists(5, () -> grid().getRow(name));
  }

  /**
   * Fill Company form and click 'Invite' button.
   */
  public void createCompany(final NewCompanyInput input) {
    fillCreateCompanyForm(input, 0);
    companyCreatePage().getInviteButtonElement().click();
  }

}
