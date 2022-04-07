package com.practis.web.selenide.service;

import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;

import com.practis.web.selenide.component.CompanySelector;

public class CompanySelectorService {

  private final CompanySelector companySelector = new CompanySelector();

  /**
   * Selects admin campaign if it was not selected.
   */
  public void initAdmin() {
    if (!isCompanySelected(webApplicationConfig().getAdminCompanyName())) {
      System.out.printf("1");
      //companySelector.selectCompany(properties.getAdminCompanyName());
    }
  }

  private boolean isCompanySelected(final String expected) {
    return companySelector.getCompanySelector().text().equals(expected);
  }
}
