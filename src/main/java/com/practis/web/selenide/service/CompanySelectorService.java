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
      companySelector.selectAdmin();
    }

  }

  /**
   * Selects campaign if it was not selected.
   */
  public void initCampaign(final String name) {
    if (!isCompanySelected(name)) {
      companySelector.selectCompany(name);
    }
  }

  private boolean isCompanySelected(final String expected) {
    return companySelector.getCompanySelector().text().equals(expected);
  }
}
