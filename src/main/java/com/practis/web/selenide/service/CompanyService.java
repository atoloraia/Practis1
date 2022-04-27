package com.practis.web.selenide.service;

import static com.practis.utils.AwaitUtils.awaitGridRowExists;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigation;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.companyCreatePage;

import com.practis.dto.NewCompanyInput;
import com.practis.web.selenide.component.GridRow;

public class CompanyService {

  /**
   * Search admin on grid by email.
   */
  public GridRow searchCompany(final String name) {
    navigation().goTo("Companies");
    search().search(name);

    return awaitGridRowExists(5, () -> grid().getRow(name));
  }

  public void createCompany(final NewCompanyInput input) {
    companyCreatePage().fillCreateCompanyForm(input, 0);
    companyCreatePage().getInviteButtonElement().click();
  }

}
